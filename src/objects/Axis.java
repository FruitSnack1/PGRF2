package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;
import transforms.Point2D;
import transforms.Point3D;

public class Axis extends Solid {
    public Axis(){
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,0,0), new Col(0,0,0),1,new Point2D(1,1)));
        getGeometry().getVertecies().add(new Vertex(new Point3D(1,0,0), new Col(1d,0,0),1,new Point2D(1,1)));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,1,0), new Col(0,1d,0),1,new Point2D(1,1)));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0,0,1), new Col(0,0,1d),1,new Point2D(1,1)));

        int[] indexes = {1,0,2,0,3,0};
        for(int i: indexes){
            getTopology().getIndexBuffer().add(i);
        }

        getTopology().getPartBuffer().add(new Part(TopologyType.LINES, 0, 3));
    }
}
