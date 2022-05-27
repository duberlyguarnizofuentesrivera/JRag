package rag;

import components.Figure;
import components.Relation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class DrawingPanel extends JPanel {
    final Set<Figure> figures;


    public DrawingPanel(Set<Figure> figures) {
        this.figures = figures;
        MouseAdapter adapter = new MouseAdapter() {
            Figure draggedFigure;
            Point lastMousePosition;
            int diffX=0;
            int diffY=0;

            @Override
            public void mousePressed(MouseEvent e) {
                for (Figure figure : figures) {
                    if (figure.getPosition().x <= e.getX() && figure.getPosition().x + figure.getWidth() >= e.getX() &&
                            figure.getPosition().y <= e.getY() && figure.getPosition().y + figure.getHeight() >= e.getY()) {
                        lastMousePosition = e.getPoint();
                        diffX = figure.getPosition().x - e.getX();
                        diffY = figure.getPosition().y - e.getY();
                        draggedFigure = figure;
                        //System.out.printf("Pressed on %s\n", draggedFigure.getName());
                        repaint();
                    }
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                draggedFigure = null;
                lastMousePosition = null;
            }

            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (draggedFigure != null) {
                    draggedFigure.setPosition(new Point(e.getX() + diffX, e.getY() + diffY));
                    lastMousePosition = e.getPoint();
                    repaint();
                    //System.out.println("dragged " + draggedFigure.getName());
                } else {
                    // System.out.println("dragged nothing");
                }
            }
        };
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        //first paint the processes and resources
        for (Figure figure : figures) {
            if (!(figure instanceof Relation)) {
                figure.drawFigure(g2);
            }
        }
        //then paint the relations (lines and arrows)
        for (Figure figure : figures) {
            if (figure instanceof Relation) {
                figure.drawFigure(g2);
            }
        }

    }
}
