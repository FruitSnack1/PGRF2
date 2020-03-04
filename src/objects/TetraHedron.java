package objects;

import model.Part;
import model.Solid;
import model.TopologyType;
import model.Vertex;
import transforms.Col;
import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;

public class TetraHedron extends Solid {

    public TetraHedron() {

        List<Vertex> geolist = new ArrayList<>();

        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 0, 1), new Col(255, 255, 255), 1));
        getGeometry().getVertecies().add(new Vertex(new Point3D(1, 0, 0), new Col(255, 0, 255), 1));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 1, 0), new Col(255, 255, 0), 1));
        getGeometry().getVertecies().add(new Vertex(new Point3D(0, 0, 0), new Col(0, 0, 255), 1));

        int[] indexes = {0, 1, 2, 0, 2, 3, 1, 2, 3, 0, 3, 1};
        for (int i : indexes) {
            getTopology().getIndexBuffer().add(i);
        }

        getTopology().getPartBuffer().add(new Part(TopologyType.TRIANGLE, 0, 4));
    }
}
