package components;

import properties.PropertiesLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Resource extends Figure {
    private double ASPECT_RATIO = 0;
    private int WIDTH = 0;

    private int numberOfInstances;
    List<Instance> instances = new ArrayList<>();


    public Resource(int id, String name, Point position, int numberOfInstances) {
        super(id, name, position);
        this.numberOfInstances = numberOfInstances;
    }


    @Override
    public void drawFigure(Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(name);
        int stringHeight = fontMetrics.getAscent();
        g.setColor(this.getBackgroundColor());
        g.fillRect(position.x, position.y, WIDTH, (int) (WIDTH * ASPECT_RATIO));
        g.setColor(this.getForegroundColor());
        g.drawString(name, position.x + WIDTH / 2 - stringWidth / 2, position.y + WIDTH / 2 + stringHeight / 2);
        for (Instance instance : instances) {
            instance.drawFigure(g);
        }
    }


    @Override
    public void loadConfig() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        this.setBackgroundColor(propertiesLoader.getPrimaryColor());
        this.setForegroundColor(propertiesLoader.getDefaultColor());
        this.WIDTH = propertiesLoader.getResourceWidth();
        this.ASPECT_RATIO = propertiesLoader.getResourceAspectRatio();
    }


    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    class Instance extends Figure {
        private int id;
        private Point position;
        private static int count = 0;

        public Instance(int id) {
            super(id, "Instance " + count, Resource.this.position);
            this.id = id;

            count++;
        }

        @Override
        public void drawFigure(Graphics g) {
            g.setColor(this.getForegroundColor());

            g.drawString("*", position.x + count * 3, position.y);
        }

        @Override
        public void loadConfig() {
            PropertiesLoader propertiesLoader = new PropertiesLoader();
            this.setForegroundColor(propertiesLoader.getDefaultColor());
        }

    }
}
