package components;

public interface Figure {
    int xPos = 0;
    int yPos = 0;
    int id = 0;
    String name = null;

    void drawFigure();

    void setName(String name);

    void setId(int id);

    void setXPos(int xPos);

    void setYPos(int yPos);

    default int getId() {
        return id;
    }

    default String getName() {
        return name;
    }

    default int getXPos() {
        return xPos;
    }

    default int getYPos() {
        return yPos;
    }
}
