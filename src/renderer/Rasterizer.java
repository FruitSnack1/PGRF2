package renderer;

import model.Vertex;
import rasterOperation.VisibilityBuffer;
import transforms.Col;
import transforms.Point3D;

import java.awt.*;

public class Rasterizer {
    private final VisibilityBuffer vb;

    public Rasterizer(VisibilityBuffer vb){
        this.vb = vb;
    }

    public void rasterizeTriangle(Vertex a, Vertex b, Vertex c){

        a = a.dehomog();
        b = b.dehomog();
        c = c.dehomog();

        int width = vb.getWidth();
        int height = vb.getHeight();

        double xA = (a.getPosition().getX()+1) * (width/2.0-1);
        double yA = (a.getPosition().getY()+1) * (height/2.0-1);
        double xB = (b.getPosition().getX()+1) * (width/2.0-1);
        double yB = (b.getPosition().getY()+1) * (height/2.0-1);
        double xC = (c.getPosition().getX()+1) * (width/2.0-1);
        double yC = (c.getPosition().getY()+1) * (height/2.0-1);

        //yA < yB < yC

        for (int y = (int)yA-1; y < yB; y++){

            double t = (y-yA)/(yB - yA);
            double x1 =xA*(1-t) + t*xB;
            Vertex ab = a.mul(1-t).add(b.mul(t));

            double t2 = (y-yA)/(yC - yA);
            double x2 = xA*(1-t2) + t2*xC;
            Vertex ac = a.mul(1-t2).add(c.mul(t2));

            for (int x = (int) x1; x < x2; x++){
                double s = (x-x1)/(x2-x1);
                Vertex abc = ab.mul(1-s).add(ac.mul(s));

                double z = abc.getPosition().getZ();

                //Mapovani textury
                Col color = abc.getColor();
                vb.drawPixelZ( x, y, 10, color);
            }
        }

        //TO DO yB - yC


    }


}
