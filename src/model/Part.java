package model;



public class Part {
    private final Topology type;
    private final int count;
    private final int startIndex;

    public Topology getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public Part(Topology type, int count, int startIndex) {
        this.type = type;
        this.count = count;
        this.startIndex = startIndex;
    }

}
