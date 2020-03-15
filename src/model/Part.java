package model;

public class Part {
    TopologyType type;
    int index;
    int count;

    public Part(TopologyType type, int index, int count){
        this.type = type;
        this.index = index;
        this.count = count;
    }

    public int getIndex() {
        return index;
    }

    public TopologyType getType() {
        return type;
    }

    public int getCount() {
        return count;
    }
}
