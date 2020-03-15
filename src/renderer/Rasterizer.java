package renderer;

import model.Vertex;
import rasterOperation.VisibilityBuffer;
import transforms.Col;

public class Rasterizer {
    private final VisibilityBuffer vb;
    private boolean wired = false;
    public Rasterizer(VisibilityBuffer vb){
        this.vb = vb;
    }

    public void toggleWired(){
        wired = !wired;
    }

    public void rasterizeTriangle(Vertex a, Vertex b, Vertex c, Col color){
        // sort Ay < By < Cy
        if(a.getPosition().getY() > b.getPosition().getY()){
            Vertex tmp = a;
            a = b;
            b = tmp;
        }
        if(b.getPosition().getY() > c.getPosition().getY()){
            Vertex tmp = b;
            b = c;
            c = tmp;
        }
        if(a.getPosition().getY() > b.getPosition().getY()){
            Vertex tmp = a;
            a = b;
            b = tmp;
        }

        a = a.dehomog();
        b = b.dehomog();
        c = c.dehomog();

        int width = vb.getWidth();
        int height = vb.getHeight();

        double xA = (a.getPosition().getX()+1) * (width/2.0)-1;
        double yA = (a.getPosition().getY()+1) * (height/2.0)-1;
        double xB = (b.getPosition().getX()+1) * (width/2.0)-1;
        double yB = (b.getPosition().getY()+1) * (height/2.0)-1;
        double xC = (c.getPosition().getX()+1) * (width/2.0)-1;
        double yC = (c.getPosition().getY()+1) * (height/2.0)-1;

        if (wired){
            vb.drawLine(xA, yA, xB, yB, new Col(255,255,255));
            vb.drawLine(xB, yB, xC, yC, new Col(255,255,255));
            vb.drawLine(xC, yC, xA, yA, new Col(255,255,255));
            return;
        }

        for (int y = (int)yA; y < yB; y++){
            double t = Math.max(Math.min((y-yA)/(yC - yA),1),0);
            double t2 = Math.max(Math.min((y-yA)/(yB - yA),1),0);
            double x1 =xA*(1-t) + t*xC;
            double x2 = xA*(1-t2) + t2*xB;
            Vertex ab = a.mul(1-t).add(c.mul(t));
            Vertex ac = a.mul(1-t2).add(b.mul(t2));
            if(x1 > x2){
                double tmp = x1;
                x1 = x2;
                x2 = tmp;
                Vertex tmp2 = ab;
                ab = ac;
                ac = tmp2;
            }
            for (int x = (int) x1; x < x2; x++){
                double s = Math.max(Math.min((x-x1)/(x2-x1),1),0);
                Vertex abc = ab.mul(1-s).add(ac.mul(s));
                double z = abc.getPosition().getZ();
                vb.drawPixelZ( x, y, z, color);
            }
        }

        for (int y = (int)yB; y < yC; y++){
            double t = Math.max(Math.min((y-yC)/(yA - yC),1),0);
            double t2 = Math.max(Math.min((y-yC)/(yB - yC),1),0);
            double x1 =xC*(1-t) + t*xA;
            double x2 = xC*(1-t2) + t2*xB;
            Vertex cb = c.mul(1-t).add(a.mul(t));
            Vertex ca = c.mul(1-t2).add(b.mul(t2));
            if(x1 > x2){
                double tmp = x1;
                x1 = x2;
                x2 = tmp;
                Vertex tmp2 = cb;
                cb = ca;
                ca = tmp2;
            }
            for (int x = (int) x1; x < x2; x++){
                double s = Math.max(Math.min((x-x1)/(x2-x1),1),0);
                Vertex abc = cb.mul(1-s).add(ca.mul(s));
                double z = abc.getPosition().getZ();
                vb.drawPixelZ( x, y, z, color);
            }
        }
    }

    public void rasterizeLine(Vertex a, Vertex b){
        a = a.dehomog();
        b = b.dehomog();

        int width = vb.getWidth();
        int height = vb.getHeight();

        double xA = (a.getPosition().getX()+1) * (width/2.0)-1;
        double yA = (a.getPosition().getY()+1) * (height/2.0)-1;
        double xB = (b.getPosition().getX()+1) * (width/2.0)-1;
        double yB = (b.getPosition().getY()+1) * (height/2.0)-1;
        vb.drawLine(xA,yA,xB,yB, a.getColor());
    }
}
