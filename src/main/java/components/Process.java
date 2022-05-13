package components;

import properties.PropertiesLoader;

import java.awt.*;

public class Process extends Figure {


    private int DIAMENTER = 0;
    public Process(int id, String name, Point position) {
        super(id, name,position);
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

        g2.setColor(Color.BLUE);
        g2.fillOval(position.x, position.y, DIAMENTER, DIAMENTER);
        g2.setColor(this.getForegroundColor());
        g2.drawString(name, position.x + DIAMENTER / 2 - stringWidth / 2, position.y + DIAMENTER / 2 + stringHeight / 2);
        }

    @Override
    public void loadConfig() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        this.setBackgroundColor(propertiesLoader.getDefaultColor());
        this.setForegroundColor(propertiesLoader.getPrimaryColor());
        this.DIAMENTER = propertiesLoader.getProcessDiamenter();
    }

}
