package components;

import java.awt.*;

public abstract class Figure {
    int xPos;
    int yPos;
    int id;
    String name;

    public Figure(int id, String name, int xPos, int yPos) {
        this.id = id;
        this.name = name;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    abstract public void drawFigure(Graphics g);


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
