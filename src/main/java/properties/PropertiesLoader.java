package properties;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class PropertiesLoader {
    static private Properties prop = new Properties();
    public static void main(String[] args) {
        try {
            String fileName = "rag.config";
            ClassLoader classLoader = PropertiesLoader.class.getClassLoader();

            // check file exists
            URL res = Objects.requireNonNull(classLoader.getResource(fileName),
                    "Can't find configuration file app.config");

            InputStream is = new FileInputStream(res.getFile());

            prop.load(is);

            // get the value for app.name key
            System.out.println(prop.getProperty("app.name"));
            // get the value for app.version key
            System.out.println(prop.getProperty("app.version"));

            // get the value for app.vendor key and if the
            // key is not available return Kode Java as
            // the default value
            System.out.println(prop.getProperty("app.vendor","Kode Java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Color getDefaultColor(){
        Color color = Color.decode(prop.getProperty("color.default", "#ffffff"));
        return color;
    }
    public Color getPrimaryColor(){
        Color color = Color.decode(prop.getProperty("color.primary", "#2a536e"));
        return color;
    }
    public Color getSecondaryColor(){
        Color color = Color.decode(prop.getProperty("color.secondary", "#7e3560"));
        return color;
    }
    public Color getBackgroundColor(){
        Color color = Color.decode(prop.getProperty("color.background", "#f1f1e6"));
        return color;
    }
}
