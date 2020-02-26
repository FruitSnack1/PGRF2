package model;

import transform.Vec3D;

import java.util.ArrayList;

public class Solid {
    protected ArrayList<Vertex> vertexBuffer = new ArrayList<>();
    protected ArrayList<Integer> indexBuffer = new ArrayList<>();
    protected ArrayList<Part> partBuffer = new ArrayList<>();

    public ArrayList<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }

    public void setVertexBuffer(ArrayList<Vertex> vertexBuffer) {
        this.vertexBuffer = vertexBuffer;
    }

    public ArrayList<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public void setIndexBuffer(ArrayList<Integer> indexBuffer) {
        this.indexBuffer = indexBuffer;
    }

    public ArrayList<Part> getPartBuffer() {
        return partBuffer;
    }

    public void setPartBuffer(ArrayList<Part> partBuffer) {
        this.partBuffer = partBuffer;
    }
}
