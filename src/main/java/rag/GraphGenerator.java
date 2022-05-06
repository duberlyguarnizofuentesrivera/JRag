package rag;

import components.Figure;
import components.Process;
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
        Process process1 = new Process(1, "P1", 30, 50);
        Resource resource1 = new Resource(1, "R1", 150, 50, 2);
        Process process2 = new Process(2, "P2", 30, 150);
        Resource resource2 = new Resource(2, "R2", 200, 250, 3);
        Resource resource3 = new Resource(3, "R3", 220, 75, 1);
        process1.addResource(resource1);
        process1.addResource(resource3);
        process2.addResource(resource2);
        process2.addResource(resource3);
        figures.add(process1);
        figures.add(resource1);
        figures.add(process2);
        figures.add(resource2);
        figures.add(resource3);
        JFrame frame = new JFrame("Resource Allocation Graph");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawingPanel panel = new DrawingPanel(figures);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
    private void saveImage(JPanel panel){
        BufferedImage imagebuf=null;
        try {
            imagebuf = new Robot().createScreenCapture(panel.bounds());
        } catch (AWTException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        Graphics2D graphics2D = imagebuf.createGraphics();
        panel.paint(graphics2D);
        try {
            ImageIO.write(imagebuf,"jpeg", new File("save1.jpeg"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("error");
        }
    }

}

