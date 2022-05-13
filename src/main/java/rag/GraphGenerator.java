package rag;

import components.Figure;
import components.Process;
import components.Relation;
import components.Resource;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class GraphGenerator {
    static Set<Figure> figures = new HashSet<>();


    public static void main(String[] args) {

        Process process1 = new Process(1, "P1", new Point(100, 200));
        Process process2 = new Process(2, "P2", new Point(200, 200));
        Process process3 = new Process(3, "P3", new Point(300, 300));
        Process process4 = new Process(4, "P4", new Point(400, 400));
        Resource resource1 = new Resource(1, "R1", new Point(500, 500), 2);
        Resource resource2 = new Resource(2, "R2", new Point(500, 200), 3);
        Resource resource3 = new Resource(3, "R3", new Point(300, 450), 1);
        Relation relation1 = new Relation(resource1, process1);
        Relation relation2 = new Relation(process2, resource3);
        Relation relation3 = new Relation(process4, resource1);
        figures.add(process1);
        figures.add(process2);
        figures.add(process3);
        figures.add(process4);
        figures.add(resource1);
        figures.add(resource2);
        figures.add(resource3);
        figures.add(relation1);
        figures.add(relation2);
        figures.add(relation3);


        JFrame frame = new JFrame("Resource Allocation Graph");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawingPanel panel = new DrawingPanel(figures);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private void saveImage(JPanel panel) {
        BufferedImage imagebuf = null;
        try {
            imagebuf = new Robot().createScreenCapture(panel.bounds());
        } catch (AWTException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Graphics2D graphics2D = imagebuf.createGraphics();
        panel.paint(graphics2D);
        try {
            ImageIO.write(imagebuf, "jpeg", new File("save1.jpeg"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error");
        }
    }

}

