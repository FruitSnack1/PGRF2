package model;

import java.util.List;

public class Topology {
    protected List<Integer> indexBuffer;
    protected List<Part> partBuffer;

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public void setIndexBuffer(List<Integer> indexBuffer) {
        this.indexBuffer = indexBuffer;
    }

    public List<Part> getPartBuffer() {
        return partBuffer;
    }


    public void setPartBuffer(List<Part> partBuffer) {
        this.partBuffer = partBuffer;
    }
}
