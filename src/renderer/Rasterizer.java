package renderer;

import model.Vertex;
import rasterOperation.VisibilityBuffer;
import transforms.Col;
import transforms.Point3D;

import java.awt.*;

public class Rasterizer {
    private final VisibilityBuffer vb;
    private Shader sh;
    public Rasterizer(VisibilityBuffer vb, Shader sh){
        this.vb = vb;
        this.sh =sh;
    }

    public void rasterizeTriangle(Vertex a, Vertex b, Vertex c){
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


//        if(a.getPosition().getX() < b.getPosition().getX()){
//            Vertex tmp = a;
//            a = b;
//            b = tmp;
//        }
//        if(b.getPosition().getX() < c.getPosition().getX()){
//            Vertex tmp = b;
//            b = c;
//            c = tmp;
//        }
//        if(a.getPosition().getX() < b.getPosition().getX()){
//            Vertex tmp = a;
//            a = b;
//            b = tmp;
//        }

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

//        System.out.println(xA);
//        System.out.println(yA);
//        System.out.println(xB);
//        System.out.println(yB);
//        System.out.println(xC);
//        System.out.println(yC);
//        System.out.println("----------------------");

        //yA < yB < yC

        if(xB > xA){
            for (int y = (int)yA; y < yB; y++){


//            System.out.println("Y : "+y);
                double t = (y-yA)/(yC - yA);
                double x1 =xA*(1-t) + t*xC;
//            System.out.println("x1 : "+x1);
                Vertex ab = a.mul(1-t).add(c.mul(t));
//            System.out.println("t : "+t);
                double t2 = (y-yA)/(yB - yA);
                double x2 = xA*(1-t2) + t2*xB;
//            System.out.println("x2 : "+x2);
                Vertex ac = a.mul(1-t2).add(b.mul(t2));
//            System.out.println("range : "+(x2-x1));

                for (int x = (int) x1; x < x2; x++){
                    double s = (x-x1)/(x2-x1);
                    Vertex abc = ab.mul(1-s).add(ac.mul(s));

                    double z = abc.getPosition().getZ();

                    //Mapovani textury
//                Col color = abc.getColor();
//                System.out.println(x);
//                System.out.println(y);
                    vb.drawPixelZ( x, y, z, sh.getColor(abc));
                }
            }

            for (int y = (int)yB; y < yC; y++){
                double t = (y-yC)/(yA - yC);
                double x1 =xC*(1-t) + t*xA;
                Vertex cb = c.mul(1-t).add(a.mul(t));
//            System.out.println("t : "+t);
                double t2 = (y-yC)/(yB - yC);
                double x2 = xC*(1-t2) + t2*xB;
                Vertex ca = c.mul(1-t2).add(b.mul(t2));

                for (int x = (int) x1; x < x2; x++){

                    double s = (x-x1)/(x2-x1);
                    Vertex abc = cb.mul(1-s).add(ca.mul(s));

                    double z = abc.getPosition().getZ();

                    //Mapovani textury
//                Col color = abc.getColor();
//                System.out.println(x);
//                System.out.println(y);
                    vb.drawPixelZ( x, y, z, sh.getColor(abc));
                }
            }
        }else{
            for (int y = (int)yA; y < yB; y++){


//            System.out.println("Y : "+y);
                double t = (y-yA)/(yB - yA);
                double x1 =xA*(1-t) + t*xB;
//            System.out.println("x1 : "+x1);
                Vertex ab = a.mul(1-t).add(b.mul(t));
//            System.out.println("t : "+t);
                double t2 = (y-yA)/(yC - yA);
                double x2 = xA*(1-t2) + t2*xC;
//            System.out.println("x2 : "+x2);
                Vertex ac = a.mul(1-t2).add(c.mul(t2));
//            System.out.println("range : "+(x2-x1));

                for (int x = (int) x1; x < x2; x++){
                    double s = (x-x1)/(x2-x1);
                    Vertex abc = ab.mul(1-s).add(ac.mul(s));

                    double z = abc.getPosition().getZ();

                    //Mapovani textury
//                Col color = abc.getColor();
//                System.out.println(x);
//                System.out.println(y);
                    vb.drawPixelZ( x, y, z, sh.getColor(abc));
                }
            }

            for (int y = (int)yB; y < yC; y++){
                double t = (y-yC)/(yB - yC);
                double x1 =xC*(1-t) + t*xB;
                Vertex cb = c.mul(1-t).add(b.mul(t));
//            System.out.println("t : "+t);
                double t2 = (y-yC)/(yA - yC);
                double x2 = xC*(1-t2) + t2*xA;
                Vertex ca = c.mul(1-t2).add(a.mul(t2));

                for (int x = (int) x1; x < x2; x++){

                    double s = (x-x1)/(x2-x1);
                    Vertex abc = cb.mul(1-s).add(ca.mul(s));

                    double z = abc.getPosition().getZ();

                    //Mapovani textury
//                Col color = abc.getColor();
//                System.out.println(x);
//                System.out.println(y);
                    vb.drawPixelZ( x, y, z, sh.getColor(abc));
                }
            }
        }


        //TO DO yB - yC


    }


}
