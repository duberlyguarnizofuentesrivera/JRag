package components;

import properties.PropertiesLoader;

import java.awt.*;

public class Relation extends Figure {


    private Figure from;
    private Figure to;
    private static int idCounter = 0;
    private int ARROW_SIZE = 0;

    public Relation(Process from, Resource to) {
        super(idCounter, from.getName() + "->" + to.getName(),
                from.getPosition());
        this.from = from;
        this.to = to;
        idCounter++;
        loadConfig();
    }

    public Relation(Resource from, Process to) {
        super(idCounter, from.getName() + "->" + to.getName(),
                from.getPosition());
        this.from = from;
        this.to = to;
        idCounter++;
        loadConfig();
    }

    @Override
    public void drawFigure(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setColor(this.getBackgroundColor());
        int x1 = from.getPosition().x;
        int y1 = from.getPosition().y;
        int x2 = to.getPosition().x;
        int y2 = to.getPosition().y;
        g2d.drawLine(x1, y1, x2, y2);
        Polygon arrowPoint = new Polygon();
        arrowPoint.addPoint(x2 - ARROW_SIZE, y2 - ARROW_SIZE);
        arrowPoint.addPoint(x2, y2);
        arrowPoint.addPoint(x2 - ARROW_SIZE, y2 + ARROW_SIZE);
        g2d.fillPolygon(arrowPoint);
    }


    @Override
    public void loadConfig() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        this.setBackgroundColor(propertiesLoader.getSecondaryColor());
        this.setForegroundColor(propertiesLoader.getDefaultColor());
        this.ARROW_SIZE = propertiesLoader.getArrowSize();
    }
}
