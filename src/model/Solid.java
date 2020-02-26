package model;

import transforms.Mat4;
import transforms.Mat4Identity;

public class Solid {
    protected Geometry geo;
    protected Mat4 transform = new Mat4Identity();
    protected Topology topology;

    public Topology getTopology() {
        return topology;
    }
    public Geometry getGeometry() {
        return geo;
    }
    public Mat4 getTransform() {
        return transform;
    }

    public void setTransform(Mat4 transform) {
        this.transform = transform;
    }
}
