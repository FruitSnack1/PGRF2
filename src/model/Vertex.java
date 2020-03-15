package model;

import transforms.Col;
import transforms.Point2D;
import transforms.Point3D;
import transforms.Vec3D;

public class Vertex implements Vectorable<Vertex> {
    private Point3D position;
    private Col color;
    private Vec3D normal;
    private double one;
    private Point2D texture;

    public Vertex(Point3D p, Col c, double o, Point2D texture) {
        this.color = c;
        this.position = p;
        this.normal = computeNormal(p);
        this.one = o;
        this.texture = texture;
    }

    public Vertex(Point3D p, Col c, Vec3D n, double o, Point2D texture) {
        this.color = c;
        this.position = p;
        this.normal = n;
        this.one = o;
        this.texture = texture;
    }

    public Point2D getTexture() {
        return texture;
    }

    @Override
    public Vertex mul(Double b) {

        Point3D p = this.getPosition().mul(b);
        Col c = this.getColor().mul(b);
        Vec3D n = this.getNormal().mul(b);
        double o = this.getOne() * b;
        Point2D t = this.getTexture().mul(b);
        return new Vertex(p, c, n, o, t);
    }

    @Override
    public Vertex add(Vertex b) {
        Point3D p = this.getPosition().add(b.getPosition());
        Vec3D n = this.getNormal().add(b.getNormal());
        Col c = this.getColor().add(b.getColor());
        double o = this.getOne() + b.getOne();
        Point2D t = this.getTexture().add(new Point2D(b.getPosition().getX(), b.getPosition().getY()));
        return new Vertex(p, c, n, o, t);
    }


    public Point3D getPosition() {
        return position;
    }

    public Col getColor() {
        return color;
    }

    public Vec3D getNormal() {
        return normal;
    }

    public Double getOne() {
        return one;
    }

    private Vec3D computeNormal(Point3D p) {
        return new Vec3D(p.getY(), -p.getX(), p.getZ());
    }

    @Override
    public String toString() {
        return "Vertex{"+
                "position " + position +
                ", color " + color +
                "}";
    }

    public Vertex dehomog(){
        return mul(1/position.getW());
    }
}
