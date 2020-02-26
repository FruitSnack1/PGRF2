package renderer;

import model.Part;
import model.Solid;
import model.Vertex;
import model.TopologyType;

public class Renderer {
    public void draw(Solid s) {
        for (Part p : s.getTopology().getPartBuffer()) {
            if (p.getType() == TopologyType.TRIANGLE) {
                for (int i = 0; i < p.getCount(); i++) {
                    int i1 = s.getTopology().getIndexBuffer().get(i * 3 + p.getIndex());
                    int i2 = s.getTopology().getIndexBuffer().get(i * 3 + p.getIndex() + 1);
                    int i3 = s.getTopology().getIndexBuffer().get(i * 3 + p.getIndex() + 2);

                    drawTriangle(s.getGeometry().getVertecies().get(i1),
                            s.getGeometry().getVertecies().get(i2),
                            s.getGeometry().getVertecies().get(i3));
                }
            }
        }


    }

    private void drawTriangle(Vertex a, Vertex b, Vertex c) {
        //TODO RYCHLE OREZANI
        //SORT
        if (a.getPosition().getZ() < b.getPosition().getZ()) {
            Vertex buffer = a;
            a = b;
            b = buffer;
        }
        if (b.getPosition().getZ() < c.getPosition().getZ()) {
            Vertex buffer = b;
            b = c;
            c = buffer;
        }
        if (c.getPosition().getZ() < a.getPosition().getZ()) {
            Vertex buffer = c;
            c = a;
            a = buffer;
        }

        if(a.getPosition().getZ() <= 0){
            return;
        }

        if(a.getPosition().getZ() > 0 && b.getPosition().getZ() <= 0){
            // TODO je videt vrchol A + 2 nove vrcholy b1 a c1
            return;
        }

        if(a.getPosition().getZ() > 0 && b.getPosition().getZ() > 0 && c.getPosition().getZ() <= 0){
            // TODO je videt vrchol A a B + novy vrchol c1 a d1 (tedy 2 trojuhelniky)
            return;
        }

        if(a.getPosition().getZ() > 0 && b.getPosition().getZ() > 0 && c.getPosition().getZ() > 0){
            // TODO je videt vrchol A a B C
            return;
        }


    }
}
