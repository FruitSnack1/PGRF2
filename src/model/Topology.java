package model;

import java.util.ArrayList;
import java.util.List;

public class Topology {
    protected ArrayList<Integer> indexBuffer = new ArrayList<>();
    protected ArrayList<Part> partBuffer = new ArrayList<>();

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
