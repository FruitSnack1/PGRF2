package rasterOperation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Optional;

public class ImageBuffer implements Raster<Color> {
    private BufferedImage buffer;

    @Override
    public int getWidth() {
        return buffer.getWidth();
    }

    @Override
    public int getHeight() {
        return buffer.getHeight();
    }

    @Override
    public void clear() {

    }

    @Override
    public void setElement(int x, int y, Color color) {
        if(inBounds(x,y)){
            buffer.setRGB(x, y, color.getRGB());
        }
    }

    @Override
    public Optional<Color> getElement(int x, int y) {
        if(inBounds(x,y)){
            return Optional.of(new Color(buffer.getRGB(x,y)));
        }
        return Optional.empty();
    }

    public ImageBuffer(BufferedImage buffer){
        this.buffer = buffer;
    }
}