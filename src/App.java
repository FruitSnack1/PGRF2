import model.Solid;
import model.Vertex;
import objects.TetraHedron;
import objects.Triangle;
import rasterOperation.ImageBuffer;
import rasterOperation.VisibilityBuffer;
import renderer.Rasterizer;
import renderer.Renderer;
import renderer.Shader;
import transforms.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class App extends JFrame {
    private JPanel panel;
    private VisibilityBuffer vb;
    private BufferedImage img;
    private int width, height;
    private Rasterizer rasterizer;
    private Renderer renderer;
    private Solid tetrahedron = new TetraHedron();
    private ArrayList<Solid> solids = new ArrayList<>();

    private Camera cam = new Camera().withPosition(new Vec3D(0,0,0));

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
        rasterizer = new Rasterizer(vb, new Shader() {
            @Override
            public Col getColor(Vertex vertex) {
                return vertex.getColor();
            }
        });
        renderer = new Renderer(rasterizer);


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

        solids.add(tetrahedron);
        Solid triangle = new Triangle();
        renderer.draw(tetrahedron);
//        renderer.draw(triangle);
//        Vertex a = triangle.getGeometry().getVertecies().get(0);
//        Vertex b = triangle.getGeometry().getVertecies().get(1);
//        Vertex c = triangle.getGeometry().getVertecies().get(2);
//
//
//        rasterizer.rasterizeTriangle(a,b,c);

        panel.repaint();
        initListeners();
    }

    public void initListeners(){
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_A){
                    tetrahedron.setTransform(tetrahedron.getTransform().mul(new Mat4RotX(.0001)));
                    clear();
                    renderer.draw(tetrahedron);
                    vb.clearZ();
                    panel.repaint();
                }
                if(e.getKeyCode() == KeyEvent.VK_D){
                    tetrahedron.setTransform(tetrahedron.getTransform().mul(new Mat4RotY(.0001)));
                    clear();
                    renderer.draw(tetrahedron);
                    vb.clearZ();
                    panel.repaint();
                }

            }
        });
    }

    public void draw(){
        clear();
        renderer.setView(cam.getViewMatrix());
        for(Solid solid: solids){
            renderer.draw(solid);
        }
        panel.repaint();
    }
    public void clear(){
        Graphics gr = img.getGraphics();
        gr.setColor(Color.BLACK);
        gr.fillRect(0, 0, img.getWidth(), img.getHeight());
    }

    public void present(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App(800,800).initGUI());
    }
}