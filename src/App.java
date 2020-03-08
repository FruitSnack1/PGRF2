import model.Solid;
import model.Vertex;
import objects.Triangle;
import rasterOperation.ImageBuffer;
import rasterOperation.VisibilityBuffer;
import renderer.Rasterizer;
import renderer.Renderer;
import renderer.Shader;
import transforms.Col;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class App extends JFrame {
    private JPanel panel;
    private VisibilityBuffer vb;
    private BufferedImage img;
    private int width, height;
    private Rasterizer rasterizer;
    private Renderer renderer;

    public App(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void initGUI(){
        setTitle("PGRF2");
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        img = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        vb = new VisibilityBuffer(new ImageBuffer(img));
        renderer = new Renderer();
        rasterizer = new Rasterizer(vb, new Shader() {
            @Override
            public Col getColor(Vertex vertex) {
                return vertex.getColor();
            }
        });

        panel = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                present(g);
            }
        };

        panel.setPreferredSize(new Dimension(width, height));
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        Solid triangle = new Triangle();
        Vertex a = triangle.getGeometry().getVertecies().get(0);
        Vertex b = triangle.getGeometry().getVertecies().get(1);
        Vertex c = triangle.getGeometry().getVertecies().get(2);


        rasterizer.rasterizeTriangle(a,b,c);

        vb.drawPixel(399,399,new Col(1,0,0));
        vb.drawPixel(799,399,new Col(0,1,0));
        vb.drawPixel(399,799,new Col(0,0,1));

        panel.repaint();
    }

    public void present(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App(800,800).initGUI());
    }
}