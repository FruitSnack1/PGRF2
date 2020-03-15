package model;

public interface Vectorable<E> {
    Vertex mul(Double b);
    Vertex add(Vertex b);
}
