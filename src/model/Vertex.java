package model;

import transforms.Col;
import transforms.Point3D;
import transforms.Vec3D;

public class Vertex implements Vectorable<Vertex> {
    private Point3D position;
    private Col color;
    private Vec3D normal;
    private double one;

    public Vertex(Point3D p, Col c, double o) {
        this.color = c;
        this.position = p;
        this.normal = computeNormal(p);
        this.one = o;
    }

    public Vertex(Point3D p, Col c, Vec3D n, double o) {
        this.color = c;
        this.position = p;
        this.normal = n;
        this.one = o;
    }

//    public Vertex mul( Vertex b) {
//        // CROSS PRODUCT
//
//        double cx = this.getPosition().getY() * b.getPosition().getZ() - this.getPosition().getZ() * b.getPosition().getY();
//        double cy = this.getPosition().getZ() * b.getPosition().getX() - this.getPosition().getX() * b.getPosition().getZ();
//        double cz = this.getPosition().getX() * b.getPosition().getY() - this.getPosition().getY() * b.getPosition().getX();
//
//        Col c = this.getColor().mul(b.getColor());
//        Vec3D n = this.getNormal().mul(b.getNormal());
//        double o = this.getOne()+ b.getOne();
//
//        return new Vertex(new Point3D(cx, cy, cz), c, n, o);
//    }

    @Override
    public Vertex mul(Double b) {

        Point3D p = this.getPosition().mul(b);
        Col c = this.getColor().mul(b);
        Vec3D n = this.getNormal().mul(b);
        double o = this.getOne() * b;

        return new Vertex(p, c, n, o);
    }

    @Override
    public Vertex add(Vertex b) {

        Point3D p = this.getPosition().add(b.getPosition());
        Vec3D n = this.getNormal().add(b.getNormal());
        Col c = this.getColor().add(b.getColor()).mul(0.5);
        double o = this.getOne() + b.getOne();

        return new Vertex(p, c, n, o);
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
