package components;

import java.awt.*;

public class Resource extends Figure {
    public static int RADIUS = 50;
    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    private int numberOfInstances;

    private final Relation relation = new Relation();

    public Resource(int id, String name, int xPos, int yPos, int numberOfInstances) {
        super(id, name, xPos, yPos);
        this.numberOfInstances = numberOfInstances;
    }

    public boolean addProcess(Process process) {
        if (this.numberOfInstances > this.relation.getProcesses().size()) {
            return this.relation.getProcesses().add(process);
        }
        return false;
    }

    @Override
    public void drawFigure(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(xPos, yPos, RADIUS, RADIUS);
        g.setColor(Color.BLACK);
        g.drawString(name, xPos+RADIUS/2, yPos+RADIUS/2);
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
