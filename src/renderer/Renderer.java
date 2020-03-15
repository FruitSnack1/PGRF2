package renderer;

import model.Part;
import model.Solid;
import model.Vertex;
import model.TopologyType;
import transforms.Col;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Mat4PerspRH;

import java.util.ArrayList;

public class Renderer {

    private Rasterizer raster;
    private Mat4 view;
    private Mat4 projection;

    public Rasterizer getRaster() {
        return raster;
    }

    public void setRaster(Rasterizer raster) {
        this.raster = raster;
    }

    public Mat4 getView() {
        return view;
    }

    public void setView(Mat4 view) {
        this.view = view;
    }

    public Mat4 getProjection() {
        return projection;
    }

    public void setProjection(Mat4 projection) {
        this.projection = projection;
    }


    public Renderer(Rasterizer raster) {
        this.raster = raster;
        this.view = new Mat4Identity();
//        this.projection = new Mat4PerspRH(1,1,1,1);
        this.projection = new Mat4Identity();
    }

    public void draw(Solid s) {
        ArrayList<Vertex> vertexes = new ArrayList<>();
        Mat4 transform = s.getTransform().mul(view).mul(projection);
        for (Vertex vertex: s.getGeometry().getVertecies()){
            vertexes.add(new Vertex(vertex.getPosition().mul(transform), vertex.getColor(), vertex.getOne(),vertex.getTexture()));
//            v.setPosition(v.getPosition().mul(s.getTransform().mul(view).mul(projection)));
        }

        for (Part p : s.getTopology().getPartBuffer()) {
            if (p.getType() == TopologyType.TRIANGLE) {
                for (int i = 0; i < p.getCount(); i++) {
                    int i1 = s.getTopology().getIndexBuffer().get(i * 3 + p.getIndex());
                    int i2 = s.getTopology().getIndexBuffer().get(i * 3 + p.getIndex() + 1);
                    int i3 = s.getTopology().getIndexBuffer().get(i * 3 + p.getIndex() + 2);
                    drawTriangle(vertexes.get(i1),
                            vertexes.get(i2),
                            vertexes.get(i3), vertexes.get(i1).getColor());
                }
            }
            if (p.getType() == TopologyType.LINES) {
                for (int i = 0; i < p.getCount(); i++) {
                    int i1 = s.getTopology().getIndexBuffer().get(i * 2 + p.getIndex());
                    int i2 = s.getTopology().getIndexBuffer().get(i * 2 + p.getIndex() + 1);
                    drawLine(vertexes.get(i1), vertexes.get(i2));
                }
            }
        }


    }

    private void drawTriangle(Vertex a, Vertex b, Vertex c, Col color) {
        raster.rasterizeTriangle(a,b,c, color);
        //TODO RYCHLE OREZANI
        //SORT
//        if (a.getPosition().getZ() < b.getPosition().getZ()) {
//            Vertex buffer = a;
//            a = b;
//            b = buffer;
//        }
//        if (b.getPosition().getZ() < c.getPosition().getZ()) {
//            Vertex buffer = b;
//            b = c;
//            c = buffer;
//        }
//        if (c.getPosition().getZ() < a.getPosition().getZ()) {
//            Vertex buffer = c;
//            c = a;
//            a = buffer;
//        }
//
//        if(a.getPosition().getZ() <= 0){
//            return;
//        }
//
//        if(a.getPosition().getZ() > 0 && b.getPosition().getZ() <= 0){
//            // TODO je videt vrchol A + 2 nove vrcholy b1 a c1
//            return;
//        }
//
//        if(a.getPosition().getZ() > 0 && b.getPosition().getZ() > 0 && c.getPosition().getZ() <= 0){
//            // TODO je videt vrchol A a B + novy vrchol c1 a d1 (tedy 2 trojuhelniky)
//            return;
//        }
//
//        if(a.getPosition().getZ() > 0 && b.getPosition().getZ() > 0 && c.getPosition().getZ() > 0){
//            // TODO je videt vrchol A a B C
//            return;
//        }


    }

    private void drawLine(Vertex a, Vertex b){
        raster.rasterizeLine(a, b);
    }
}
