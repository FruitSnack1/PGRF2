package rasterOperation;

import java.util.Optional;

public class DepthBuffer implements GridBuffer<Double> {

    private double[][] array;
    private final int width, height;

    public DepthBuffer(int width, int height){
        this.width = width;
        this.height = height;
        array = new double[width][height];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setElement(int x, int y, Double value) {
        if(!inBounds(x,y))
            return;
        array[x][y] = value;
    }

    @Override
    public Optional<Double> getElement(int x, int y) {
        if(!inBounds(x,y))
            return Optional.empty();
        return Optional.of(array[x][y]);
    }
}
