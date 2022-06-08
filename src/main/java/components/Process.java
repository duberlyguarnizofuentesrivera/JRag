package components;

import properties.PropertiesLoader;

import java.awt.*;

public class Process extends Figure {


    private int DIAMETER = 0;

    public Process(int id, String name, Point position) {
        super(id, name, position);
        loadConfig();
        setHeight(DIAMETER);
        setWidth(DIAMETER);
    }

    @Override
    public void drawFigure(Graphics2D g2) {

        FontMetrics fontMetrics = g2.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(name);
        int stringHeight = fontMetrics.getAscent();

        g2.setColor(this.getBackgroundColor());
        g2.fillOval(position.x, position.y, DIAMETER, DIAMETER);
        g2.setColor(this.getForegroundColor());
        g2.drawString(name, position.x + DIAMETER / 2 - stringWidth / 2, position.y + DIAMETER / 2 + stringHeight / 2);

    }

    @Override
    public void loadConfig() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        this.setBackgroundColor(propertiesLoader.getProcessBackgroundColor());
        this.setForegroundColor(propertiesLoader.getProcessForegroundColor());
        DIAMETER = propertiesLoader.getProcessDiameter();
    }

}
