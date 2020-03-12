// #   navrhněte reprezentaci scény pomocí vhodných datových struktur a navržených objektových tříd
// #  implementujte uložení těles pomocí vertex a index buferu, předpokládejte tělesa složená z hran i ploch (trojúhelníků)
// #  implementujte modelovací transformaci jednotlivých těles ve scéně, řízenou klávesnicí či programově jako animaci (pohyb, otáčení atd.)
// #  implementujte pohledovou transformaci řízenou pohybem pozorovatele pomocí klávesnice (WSAD) a rozhlížení pomocí myši (kamera)
//   implementujte transformaci zobrazovacího objemu tj. projekci prostorové scény, možnost přepínání perspektivní a pravoúhlé projekce
//   implementujte ořezání scény zobrazovacím objemem
// #  implementujte rasterizaci a algoritmus viditelnosti Z-buffer
// #  umožněte přepínání zobrazení drátového modelu a vyplněných ploch
// #   znázorněte soustavu souřadnic scény zobrazením barevně odlišených souřadnicových os
//   obarvěte jednotlivé plochy těles různou barvou
// #  namapovaní textury na povrch těles či jejich osvětlení bude oceněno bonusem
// #   vymodelujte alespoň dvě jednoduchá tělesa a alespoň jedno z těles bude definováno bikubickou plochou
// #  natočte scénu a umístěte tělesa tak, aby byla zřejmá správná funkce algoritmu viditelnosti, vhodné je například protnutí dvou těles

import model.Solid;
import model.Vertex;
import objects.Axis;
import objects.Cube;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
    private Solid trinagle = new Triangle();
    private Solid axis = new Axis();
    private Solid cube = new Cube();
    private ArrayList<Solid> solids = new ArrayList<>();

    private int mouseX = 0, mouseY= 0;
    private final double mouseSensitivity = 0.02;
    private Camera cam = new Camera().withPosition(new Vec3D(0,0,0));


    public App(int width, int height){
        this.width = width;
        this.height = height;
    }

    public void initGUI(){
        cam.withFirstPerson(false);
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

        tetrahedron.setTransform(new Mat4Scale(.5));
        axis.setTransform(new Mat4Scale(.5));
        cube.setTransform(new Mat4Scale(.2).mul(new Mat4Transl(-.1,-.1,-.1)));
        solids.add(tetrahedron);
//        solids.add(trinagle);
        solids.add(axis);
        solids.add(cube);

        panel.repaint();
        initListeners();
        draw();
    }

    public void initListeners(){
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(e.getX() > mouseX){
                    cam = cam.addAzimuth(mouseSensitivity);
                }
                if(e.getX() < mouseX){
                    cam = cam.addAzimuth(-mouseSensitivity);
                }
                if(e.getY() < mouseY){
                    cam = cam.addZenith(mouseSensitivity);
                }
                if(e.getY() > mouseY){
                    cam = cam.addZenith(-mouseSensitivity);
                }
                mouseY = e.getY();
                mouseX = e.getX();
                draw();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_A)
                    cam = cam.left(.1);
                if(e.getKeyCode() == KeyEvent.VK_D)
                    cam = cam.right(.1);
                if(e.getKeyCode() == KeyEvent.VK_W)
                    cam = cam.down(.1);
                if(e.getKeyCode() == KeyEvent.VK_S)
                    cam = cam.up(.1);
                if(e.getKeyCode() == KeyEvent.VK_R)
                    resetPosition();
                if(e.getKeyCode() == KeyEvent.VK_R)
                    cam.addZenith(0.01);
                if(e.getKeyCode() == KeyEvent.VK_B)
                    rasterizer.toggleWired();
                draw();
            }
        });
    }

    public void draw(){
        clear();
        vb.clearZ();
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

    public void resetPosition(){
        cam = new Camera().withPosition(new Vec3D(0,0,0));;
    }

    public void present(Graphics graphics) {
        graphics.drawImage(img, 0, 0, null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App(800,800).initGUI());
    }
}