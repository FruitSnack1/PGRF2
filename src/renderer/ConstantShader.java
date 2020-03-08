package renderer;

import model.Vertex;
import transforms.Col;

public class ConstantShader implements Shader {
    private Col color;

    public ConstantShader(Col color){
        this.color = color;
    }

    @Override
    public Col getColor(Vertex vertex) {
        return color;
    }
}
