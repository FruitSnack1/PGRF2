package rasterOperation;


import transforms.Col;
import transforms.Mat4;

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

    public void clearZ(){
        depth.clear();
    }

    public void drawPixelZ(double x, double y, double z, Col col){

        depth.getElement((int)x,(int)y).ifPresent(bufferZ ->{

            if(z > bufferZ)
                return;

            depth.setElement((int)x, (int)y, z);
            img.setElement((int)x, (int)y, new Color(col.getRGB()));
        });
    }

    public void drawLine(double x1, double y1,double x2,double y2, Col color){
        Graphics gr = img.getBuffer().getGraphics();
        gr.setColor(new Color(color.getRGB()));
        gr.drawLine((int)x1,(int)y1,(int)x2,(int)y2);
    }

}
