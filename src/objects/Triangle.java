package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;
import transforms.Point2D;
import transforms.Point3D;

public class Triangle extends Solid {
    public Triangle(){
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,.5,1), new Col(183,85,230),1,new Point2D(1,1)));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,1,0), new Col(.5,.5,.5),1,new Point2D(1,1)));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,0,0), new Col(.2,.2,.2),1,new Point2D(1,1)));

        int[] indexes = {0,1,2};
        for(int i: indexes){
            getTopology().getIndexBuffer().add(i);
        }

        getTopology().getPartBuffer().add(new Part(TopologyType.TRIANGLE, 0, 1));
    }
}
