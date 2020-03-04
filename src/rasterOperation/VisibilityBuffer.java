package rasterOperation;


import transforms.Col;

import java.awt.*;

public class VisibilityBuffer {
    private ImageBuffer img;
    private DepthBuffer depth;

    private int height;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private int width;

    public VisibilityBuffer(ImageBuffer img){
        this.img = img;
    }


    public void drawPixel(int x, int y, Col col){

    }

    public void drawPixelZ(int x, int y, int z, Col col){

    }
}
