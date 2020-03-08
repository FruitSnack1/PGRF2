package renderer;

import model.Vertex;
import transforms.Col;

public class TextureShader implements Shader {
    @Override
    public Col getColor(Vertex vertex) {
        if((int)vertex.getTexture().getY()*10%2==0)
            return new Col(1d,1d,0);
        return new Col(0,0,1d);
    }
}
