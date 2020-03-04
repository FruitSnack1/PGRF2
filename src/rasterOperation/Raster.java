package rasterOperation;

import java.util.Optional;

public interface Raster<E> {
    int getWidth();
    int getHeight();
    void clear();

    void setElement(int x, int y, E e);
    Optional<E> getElement(int x, int y);

    default boolean inBounds(int x, int y){
        return(x >= 0 && x < getWidth() && y >=0 && y < getHeight());
    }
}
