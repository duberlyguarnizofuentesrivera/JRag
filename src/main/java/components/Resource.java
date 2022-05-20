package components;

import properties.PropertiesLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Resource extends Figure {
    private double ASPECT_RATIO = 0;
    private int WIDTH = 0;

    private int numberOfInstances;
    private final List<Instance> instances = new ArrayList<>();


    public Resource(int id, String name, Point position, int numberOfInstances) {
        super(id, name, position);
        this.numberOfInstances = numberOfInstances;
        loadConfig();
        setWidth(WIDTH);
        setHeight((int) (WIDTH * ASPECT_RATIO));
        for (int i = 0; i < numberOfInstances; i++) {
            instances.add(new Instance(i));
        }
    }

    public List<Instance> getInstances() {
        return instances;
    }

    @Override
    public void drawFigure(Graphics2D g2) {
        FontMetrics fontMetrics = g2.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(name);
        int stringHeight = fontMetrics.getAscent();
        g2.setColor(this.getBackgroundColor());
        g2.fillRect(position.x, position.y, WIDTH, (int) (WIDTH * ASPECT_RATIO));
        g2.setColor(this.getForegroundColor());
        g2.drawString(name, position.x + WIDTH / 2 - stringWidth / 2, position.y + WIDTH / 2 + stringHeight / 2);
        //System.out.println("Drawing resource " + name + " at " + position.x + " " + position.y);
        for (Instance instance : instances) {
            instance.drawFigure(g2);
        }
    }


    @Override
    public void loadConfig() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        this.setBackgroundColor(propertiesLoader.getResourceBackgroundColor());
        this.setForegroundColor(propertiesLoader.getResourceForegroundColor());
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

        private static int INSTANCE_DIAMETER = 0;
        Point instancePosition;

        public Instance(int id) {
            super(id, "Instance " + id + " of " + Resource.this.getName(), Resource.this.position);
            loadConfig();
            int widthOfColumns = Resource.this.WIDTH / Resource.this.getNumberOfInstances();
            int x = Resource.this.position.x + id * widthOfColumns + (widthOfColumns - INSTANCE_DIAMETER) / 2;
            int y = (int) (Resource.this.position.y + Resource.this.WIDTH * Resource.this.ASPECT_RATIO / 3);
            instancePosition = new Point(x, y);
            this.position = instancePosition;
            this.setHeight(INSTANCE_DIAMETER);
            this.setWidth(INSTANCE_DIAMETER);
        }

        @Override
        public void drawFigure(Graphics2D g2) {
            g2.setColor(this.getForegroundColor());
            g2.fillOval(instancePosition.x, instancePosition.y, INSTANCE_DIAMETER, INSTANCE_DIAMETER);
            //System.out.println("Instance coordinates: " + Resource.this.getName() + " -> id=" + id + " " + instancePosition.x + " " + instancePosition.y);

        }

        @Override
        public void loadConfig() {
            PropertiesLoader propertiesLoader = new PropertiesLoader();
            this.setForegroundColor(propertiesLoader.getDefaultColor());
            INSTANCE_DIAMETER = propertiesLoader.getInstanceDiameter();
        }

    }
}
