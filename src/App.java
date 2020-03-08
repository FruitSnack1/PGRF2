import model.Vertex;
import renderer.Rasterizer;
import transforms.Col;

import java.util.function.Function;

public class App{
    public App(){
        Function<Vertex, Col> sh = new Function<Vertex, Col>() {
            @Override
            public Col apply(Vertex vertex) {
                return vertex.getColor();
            }
        };

    }
}