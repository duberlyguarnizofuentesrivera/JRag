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
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);

        FontMetrics fontMetrics = g2.getFontMetrics();
        int stringWidth = fontMetrics.stringWidth(name);
        int stringHeight = fontMetrics.getAscent();

        g2.setColor(Color.WHITE);
        g2.fillRect(xPos, yPos, RECT_HEIGHT * 3 / 2, RECT_HEIGHT);
        g2.setColor(Color.BLACK);
        g2.drawString(name, xPos + (RECT_HEIGHT * 3 / 4) - stringWidth / 2, yPos + (RECT_HEIGHT / 2) + stringHeight / 2);
        for (Resource resource : relation.getResources()) {
            Polygon arrowPoint = new Polygon();
            double angle = -Math.atan2((resource.getYPos() + Resource.DIAMETER / 2) - (yPos + RECT_HEIGHT / 2), (resource.getXPos() + Resource.DIAMETER / 2) - (xPos + RECT_HEIGHT * 3 / 4));
            int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
            if (angle > 0) {
                if (angle < Math.PI / 4) {
                    // right
                    x1 = this.xPos + RECT_HEIGHT * 3 / 2;
                    x2 = resource.getXPos();
                    y1 = this.yPos + RECT_HEIGHT / 2;
                    y2 = resource.getYPos() + Resource.DIAMETER / 2;
                    g2.drawLine(x1, y1, x2 - 5, y2);
                    arrowPoint.addPoint(x2 - 5, y2 - 5);
                    arrowPoint.addPoint(x2, y2);
                    arrowPoint.addPoint(x2 - 5, y2 + 5);
                    g2.fillPolygon(arrowPoint);
                } else {
                    if (angle < 3 * Math.PI / 4) {
                        //top
                        x1 = this.xPos + RECT_HEIGHT * 3 / 4;
                        x2 = resource.getXPos() + Resource.DIAMETER / 2;
                        y1 = this.yPos;
                        y2 = resource.getYPos() + Resource.DIAMETER;
                        g2.drawLine(x1, y1, x2, y2 + 5);
                        arrowPoint.addPoint(x2 - 5, y2 + 5);
                        arrowPoint.addPoint(x2, y2);
                        arrowPoint.addPoint(x2 + 5, y2 + 5);
                        g2.fillPolygon(arrowPoint);
                    } else {
                        //left
                        x1 = this.xPos;
                        x2 = resource.getXPos() + Resource.DIAMETER;
                        y1 = this.yPos + RECT_HEIGHT / 2;
                        y2 = resource.getYPos() + Resource.DIAMETER / 2;
                        g2.drawLine(x1, y1, x2 + 5, y2);
                        arrowPoint.addPoint(x2 + 5, y2 + 5);
                        arrowPoint.addPoint(x2, y2);
                        arrowPoint.addPoint(x2 + 5, y2 - 5);
                        g2.fillPolygon(arrowPoint);

                    }
                }
            } else {
                if (angle > -Math.PI / 4) {
                    // right
                    x1 = this.xPos + RECT_HEIGHT * 3 / 2;
                    x2 = resource.getXPos();
                    y1 = this.yPos + RECT_HEIGHT / 2;
                    y2 = resource.getYPos() + Resource.DIAMETER / 2;
                    g2.drawLine(x1, y1, x2 - 5, y2);
                    arrowPoint.addPoint(x2 - 5, y2 - 5);
                    arrowPoint.addPoint(x2, y2);
                    arrowPoint.addPoint(x2 - 5, y2 + 5);
                    g2.fillPolygon(arrowPoint);
                } else {
                    if (angle > -3 * Math.PI / 4) {
                        //bottom
                        x1 = this.xPos + RECT_HEIGHT * 3 / 4;
                        x2 = resource.getXPos() + Resource.DIAMETER / 2;
                        y1 = this.yPos + RECT_HEIGHT;
                        y2 = resource.getYPos();
                        g2.drawLine(x1, y1, x2, y2 - 5);
                        arrowPoint.addPoint(x2 - 5, y2 - 5);
                        arrowPoint.addPoint(x2, y2);
                        arrowPoint.addPoint(x2 + 5, y2 - 5);
                        g2.fillPolygon(arrowPoint);
                    } else {
                        //left
                        x1 = this.xPos;
                        x2 = resource.getXPos() + Resource.DIAMETER;
                        y1 = this.yPos + RECT_HEIGHT / 2;
                        y2 = resource.getYPos() + Resource.DIAMETER / 2;
                        g2.drawLine(x1, y1, x2 + 5, y2);
                        arrowPoint.addPoint(x2 + 5, y2 + 5);
                        arrowPoint.addPoint(x2, y2);
                        arrowPoint.addPoint(x2 + 5, y2 - 5);
                        g2.fillPolygon(arrowPoint);
                    }
                }

            }
            System.out.println(this.getName() + " " + resource.getName() + ", angulo: " + Math.toDegrees(angle) + " ");


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
