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

    public Renderer(Rasterizer raster) {
        this.raster = raster;
        this.view = new Mat4Identity();
        this.projection = new Mat4Identity();
    }

    public void setView(Mat4 view) {
        this.view = view;
    }

    public void draw(Solid s) {
        ArrayList<Vertex> vertexes = new ArrayList<>();
        Mat4 transform = s.getTransform().mul(view).mul(projection);

        for (Vertex vertex: s.getGeometry().getVertecies()){
            vertexes.add(new Vertex(vertex.getPosition().mul(transform), vertex.getColor(), vertex.getOne(),vertex.getTexture()));
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
    }

    private void drawLine(Vertex a, Vertex b){
        raster.rasterizeLine(a, b);
    }
}
