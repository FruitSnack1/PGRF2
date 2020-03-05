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
        img.setElement(x,y,Color.YELLOW);
    }

    public void drawPixelZ(double x, double y, double z, Col col){
        depth.getElement((int)x,(int)y).ifPresent(bufferZ ->{
            if(z > bufferZ)
                return;
            depth.setElement((int)x, (int)y, z);
            img.setElement((int)x, (int)y, new Color(col.getRGB()));
        });

    }
}
