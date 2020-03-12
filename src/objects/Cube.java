package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;
import transforms.Point2D;
import transforms.Point3D;

public class Cube extends Solid {
    public Cube() {

        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 0, 0), new Col(255, 0, 0),0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 1, 0), new Col(0, 255, 0), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(1, 0, 0), new Col(0, 0, 255), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(1, 1, 0), new Col(255, 255, 0), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 0, 1), new Col(0, 255, 255), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 1, 1), new Col(255, 0, 255), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(1, 0, 1), new Col(255, 255, 255), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(1, 1, 1), new Col(255, 0, 255), 0, new Point2D()));

        int[] indexes = {0, 1, 2, 1,2,3,2,3,6,3,6,7,1,3,5,3,5,7,0,2,4,2,4,6,4,6,7,4,5,7,0,1,4,1,4,5};
        for (int i : indexes) {
            getTopology().getIndexBuffer().add(i);
        }

        getTopology().getPartBuffer().add(new Part(TopologyType.TRIANGLE, 0, 12));
    }
}
