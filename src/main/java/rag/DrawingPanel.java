package rag;

import components.Figure;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class DrawingPanel extends JPanel {
    Set<Figure> figures;

    public DrawingPanel(Set<Figure> figures) {
        this.figures = figures;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figure figure : figures) {
            figure.drawFigure(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
