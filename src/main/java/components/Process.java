package components;

import java.awt.*;

public class Process extends Figure {
    public static int RECT_HEIGHT = 50;

    final private Relation relation = new Relation();

    public Process(int id, String name, int xPos, int yPos) {
        super(id, name, xPos, yPos);

    }

    @Override
    public void drawFigure(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(xPos, yPos, RECT_HEIGHT * 3 / 2, RECT_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString(name, xPos + RECT_HEIGHT * 3 / 4, yPos + RECT_HEIGHT / 2);
        for (Resource resource : relation.getResources()) {
            g.drawLine(xPos + RECT_HEIGHT * 3 / 2, yPos + RECT_HEIGHT / 2, resource.getXPos() + Resource.RADIUS/2, resource.getYPos() + Resource.RADIUS/2);
            System.out.println(resource.getName());
        }
    }

    public boolean addResource(Resource resource) {
        return this.relation.getResources().add(resource);
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void setId(int id) {

    }

    @Override
    public void setXPos(int xPos) {


    }

    @Override
    public void setYPos(int yPos) {

    }
}
