package rag;

import components.Figure;
import components.Relation;

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
        //first paint the processes and resources
        for (Figure figure : figures) {
            if (!(figure instanceof Relation)) {
                figure.drawFigure(g);
            }
        }
        //then paint the relations (lines and arrows)
        for (Figure figure : figures) {
            if (figure instanceof Relation) {
                figure.drawFigure(g);
            }
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
