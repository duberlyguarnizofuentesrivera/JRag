package properties;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class PropertiesLoader {
    static private final Properties prop = new Properties();

    public PropertiesLoader() {
        try {
            String fileName = "rag.config";
            ClassLoader classLoader = PropertiesLoader.class.getClassLoader();

            // check file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                    "Can't find configuration file app.config");

            InputStream is = new FileInputStream(res.getFile());

            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Color getDefaultColor() {
        return Color.decode(prop.getProperty("color.default", "#FEFAE0"));
    }

    public Color getProcessBackgroundColor() {
        return Color.decode(prop.getProperty("color.process-background", "#CCD5AE"));
    }

    public Color getProcessForegroundColor() {
        return Color.decode(prop.getProperty("color.process-foreground", "#264653"));
    }

    public Color getBackgroundColor() {
        return Color.decode(prop.getProperty("color.background", "#FFFFFF"));
    }

    public Color getRelationPrimaryColor() {
        return Color.decode(prop.getProperty("color.relation-primary", "#E76F51"));
    }

    public Color getRelationSecondaryColor() {
        return Color.decode(prop.getProperty("color.relation-secondary", "#2A9D8F"));
    }
    public Color getResourceBackgroundColor() {
        return Color.decode(prop.getProperty("color.resource-background", "#D4A373"));
    }
    public Color getResourceForegroundColor() {
        return Color.decode(prop.getProperty("color.resource-foreground", "#FEFAE0"));
    }
    public int getArrowSize() {
        return Integer.parseInt(prop.getProperty("figure.arrow-size", "7"));
    }

    public int getResourceWidth() {
        return Integer.parseInt(prop.getProperty("figure.resource-width", "50"));
    }

    public double getResourceAspectRatio() {
        return Double.parseDouble(prop.getProperty("figure.resource-aspect-ratio", "0.75"));
    }

    public int getProcessDiameter() {
        return Integer.parseInt(prop.getProperty("figure.process-diameter", "45"));
    }


    public int getInstanceDiameter() {
        return Integer.parseInt(prop.getProperty("figure.instance-diameter", "6"));
    }
}
