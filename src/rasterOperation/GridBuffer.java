package rasterOperation;

import java.util.Optional;

public interface GridBuffer<V> {
    int getWidth();
    int getHeight();
    void setElement(int x, int y, V value);
    Optional<V> getElement(int x, int y);

    default boolean inBounds(int x, int y){
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }
}
