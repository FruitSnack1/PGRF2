package objects;

import model.Solid;
import model.Vertex;
import transforms.Col;
import transforms.Point2D;
import transforms.Point3D;

public class Triangle extends Solid {
    public Triangle(){
        getGeometry().getVertecies().add(new Vertex(new Point3D(1,.5,0), new Col(1d,1d,1d),1,new Point2D(1,1)));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,1,0), new Col(.5,.5,.5),1,new Point2D(1,1)));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,0,0), new Col(.2,.2,.2),1,new Point2D(1,1)));

        getTopology().getIndexBuffer().add(0);
        getTopology().getIndexBuffer().add(1);
        getTopology().getIndexBuffer().add(1);
        getTopology().getIndexBuffer().add(2);
        getTopology().getIndexBuffer().add(2);
        getTopology().getIndexBuffer().add(0);
    }
}
