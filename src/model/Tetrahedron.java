package model;

public class Tetrahedron extends Solid {

    public Tetrahedron(){
        getVertexBuffer().add(new Vertex());
        getVertexBuffer().add(new Vertex());
        getVertexBuffer().add(new Vertex());
        getVertexBuffer().add(new Vertex());

        getPartBuffer().add(new Part(Topology.TRIANGLES, 4,0));

        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        getIndexBuffer().add(2);

        getIndexBuffer().add(1);
        getIndexBuffer().add(2);
        getIndexBuffer().add(3);

        getIndexBuffer().add(2);
        getIndexBuffer().add(3);
        getIndexBuffer().add(0);

        getIndexBuffer().add(0);
        getIndexBuffer().add(1);
        getIndexBuffer().add(3);
    }
}
