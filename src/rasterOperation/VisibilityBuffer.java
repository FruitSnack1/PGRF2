package rasterOperation;


import transforms.Col;

import java.awt.*;

public class VisibilityBuffer {
    private ImageBuffer img;
    private DepthBuffer depth;

    public VisibilityBuffer(ImageBuffer img){
        this.img = img;
        this.depth = new DepthBuffer(getWidth(), getHeight());
    }

    public ImageBuffer getImg() {
        return img;
    }

    public int getHeight() {
        return img.getHeight();
    }

    public int getWidth() {
        return img.getWidth();
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
