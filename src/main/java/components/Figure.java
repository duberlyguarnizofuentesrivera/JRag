package components;

import java.awt.*;

public abstract class Figure {

    int id;
    String name;
    Point position;
    Color backgroundColor;
    Color foregroundColor;
    Color borderColor;

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Figure(int id, String name, Point position) {
        this.id = id;
        this.name = name;
        this.position = position;
        loadConfig();
    }

    abstract public void drawFigure(Graphics g);
    abstract public void loadConfig();


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

}
