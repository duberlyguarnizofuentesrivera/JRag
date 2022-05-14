package components;

import properties.PropertiesLoader;

import java.awt.*;

public class Relation extends Figure {


    private final Figure from;
    private final Figure to;
    private static int idCounter = 0;
    private int ARROW_SIZE = 0;

    public Relation(Figure from, Figure to) {
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
        double angle = getAngle();
        int x1;
        int y1;
        int x2;
        int y2;
        // relation lines are drawn from one figure to another
        // for this, I divide a figure in 6 parts, according to
        // the angle that the figures make with the horizontal axis

        if (angle <= 30) {
            //right
            x1 = from.getPosition().x + from.getWidth();
            y1 = from.getPosition().y + from.getHeight() / 2;
            x2 = to.getPosition().x;
            y2 = to.getPosition().y + to.getHeight() / 2;

        } else {
            if (angle <= 60) {
                //top right
                if (from instanceof Process) {
                    x1 = (int) (from.getPosition().x + 1.7071 * from.getWidth() / 2);
                    y1 = (int) (from.getPosition().y +0.2928*from.getHeight()/2);
                } else {
                    x1 = from.getPosition().x + from.getWidth();
                    y1 = from.getPosition().y;
                }
                x2 = to.getPosition().x;
                y2 = to.getPosition().y + to.getHeight();
            } else {

                if (angle <= 120) {
                    //top
                    x1 = from.getPosition().x + from.getWidth() / 2;
                    y1 = from.getPosition().y;
                    x2 = to.getPosition().x + to.getWidth() / 2;
                    y2 = to.getPosition().y + to.getHeight();

                } else {
                    if (angle <= 150) {
                        if (from instanceof Process) {
                            x1 = (int) (from.getPosition().x + 0.2928 * from.getWidth() / 2);
                            y1 = (int) (from.getPosition().y + 0.2928 * from.getHeight() / 2);
                        } else {
                            x1 = from.getPosition().x;
                            y1 = from.getPosition().y;

                        }
                        //top left
                        x2 = to.getPosition().x + to.getWidth();
                        y2 = to.getPosition().y + to.getHeight();

                    } else {
                        if (angle <= 210) {
                            //left
                            x1 = from.getPosition().x;
                            y1 = from.getPosition().y + from.getHeight();
                            x2 = to.getPosition().x + to.getWidth();
                            y2 = to.getPosition().y + to.getHeight();


                        } else {
                            if (angle <= 270) {
                                //bottom left
                                if (from instanceof Process) {
                                    x1 = (int) (from.getPosition().x + 0.2928 * from.getWidth() / 2);
                                    y1 = (int) (from.getPosition().y + 1.7071 * from.getHeight() / 2);
                                } else {
                                    x1 = from.getPosition().x;
                                    y1 = from.getPosition().y + from.getHeight();
                                }
                                x2 = to.getPosition().x + to.getWidth();
                                y2 = to.getPosition().y;

                            } else {
                                if (angle <= 300) {
                                    //bottom
                                    x1 = from.getPosition().x + from.getWidth() / 2;
                                    y1 = from.getPosition().y + from.getHeight();
                                    x2 = to.getPosition().x + to.getWidth() / 2;
                                    y2 = to.getPosition().y;


                                } else {
                                    if (angle <= 330) {
                                        //bottom right
                                        if (from instanceof Process) {
                                            x1 = (int) (from.getPosition().x + 1.7071 * from.getWidth() / 2);
                                            y1 = (int) (from.getPosition().y + 1.7071 * from.getHeight() / 2);
                                        } else {
                                            x1 = from.getPosition().x + from.getWidth();
                                            y1 = from.getPosition().y + from.getHeight();
                                        }

                                        x2 = to.getPosition().x;
                                        y2 = to.getPosition().y;
                                    } else {
                                        //right
                                        x1 = from.getPosition().x + from.getWidth();
                                        y1 = from.getPosition().y + from.getHeight() / 2;
                                        x2 = to.getPosition().x;
                                        y2 = to.getPosition().y + to.getHeight() / 2;

                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
        if(from instanceof Process){
            g2d.setColor(this.getForegroundColor());
        }else{
            g2d.setColor(this.getBackgroundColor());
        }
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(x1, y1, x2, y2);
        //System.out.println("x1: " + x1 + " y1: " + y1 + " x2: " + x2 + " y2: " + y2 + " from " + from.getName() + " to " + to.getName());
        Polygon arrowPoint = createArrow(angle, x2, y2);
        g2d.setStroke(new BasicStroke(1));
        g2d.fillPolygon(arrowPoint);

    }


    @Override
    public void loadConfig() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        this.setBackgroundColor(propertiesLoader.getRelationPrimaryColor());
        this.setForegroundColor(propertiesLoader.getRelationSecondaryColor());
        this.ARROW_SIZE = propertiesLoader.getArrowSize();
    }

    /**
     * Returns the angle between the two points, in degrees.
     */
    private double getAngle() {
        int x1 = from.getPosition().x + from.getWidth() / 2;
        int y1 = from.getPosition().y + from.getHeight() / 2;
        int x2 = to.getPosition().x + to.getWidth() / 2;
        int y2 = to.getPosition().y + to.getHeight() / 2;
        double angle = (int) -Math.toDegrees(Math.atan2(y2 - y1, x2 - x1));
        if (angle < 0) {
            angle = 360 + angle;
        }
        System.out.println(angle + " " + from.name + "->" + to.name);
        return angle;
    }

    private Polygon createArrow(double angle, int x, int y) {
        double radians = Math.PI * 2 - Math.toRadians(angle);
        Polygon arrowPoint = new Polygon();

        Point b = new Point(x - ARROW_SIZE, y - ARROW_SIZE);
        Point c = new Point(x - ARROW_SIZE, y + ARROW_SIZE);
        //rotate around X = x-ARROW_SIZE/2, Y = y
        int new_b_x = (int) (x + (b.x - x) * Math.cos(radians) - (b.y - y) * Math.sin(radians));
        int new_b_y = (int) (y + (b.x - x) * Math.sin(radians) + (b.y - y) * Math.cos(radians));
        int new_c_x = (int) (x + (c.x - x) * Math.cos(radians) - (c.y - y) * Math.sin(radians));
        int new_c_y = (int) (y + (c.x - x) * Math.sin(radians) + (c.y - y) * Math.cos(radians));
        arrowPoint.addPoint(x, y);
        //System.out.println("arrowPoint: " + x + ", " + y);
        arrowPoint.addPoint(new_b_x, new_b_y);
        arrowPoint.addPoint(new_c_x, new_c_y);
        return arrowPoint;
    }
}
