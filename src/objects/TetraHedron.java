package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.*;

import java.util.ArrayList;
import java.util.List;

public class TetraHedron extends Solid {

    public TetraHedron() {

        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 0, 1), new Col(255, 0, 0),0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(1, 0, 0), new Col(0, 255, 0), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 1, 0), new Col(0, 0, 255), 0, new Point2D()));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 0, 0), new Col(255, 0, 255), 0, new Point2D()));

        int[] indexes = {0, 1, 2, 0, 2, 3, 1, 2, 3, 0, 3, 1};
        for (int i : indexes) {
            getTopology().getIndexBuffer().add(i);
        }

        getTopology().getPartBuffer().add(new Part(TopologyType.TRIANGLE, 0, 4));
    }
}
