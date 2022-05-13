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

    public static void main(String[] args) {
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
        return Color.decode(prop.getProperty("color.default", "#ffffff"));
    }

    public Color getPrimaryColor() {
        return Color.decode(prop.getProperty("color.primary", "#2a536e"));
    }

    public Color getSecondaryColor() {
        return Color.decode(prop.getProperty("color.secondary", "#7e3560"));
    }

    public Color getBackgroundColor() {
        return Color.decode(prop.getProperty("color.background", "#f1f1e6"));
    }

    public int getArrowSize() {
        return Integer.parseInt(prop.getProperty("figure.arrow-size", "5"));
    }

    public int getResourceWidth() {
        return Integer.parseInt(prop.getProperty("figure.resource-width", "50"));
    }

    public double getResourceAspectRatio() {
        return Double.parseDouble(prop.getProperty("figure.resource-aspect-ratio", "0.75"));
    }

    public int getProcessDiamenter() {
        return Integer.parseInt(prop.getProperty("figure.process-diameter", "45"));
    }
}
