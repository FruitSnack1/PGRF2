package model;

import java.util.ArrayList;

public class Topology {
    protected ArrayList<Integer> indexBuffer = new ArrayList<>();
    protected ArrayList<Part> partBuffer = new ArrayList<>();

    public ArrayList<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public ArrayList<Part> getPartBuffer() {
        return partBuffer;
    }

}
