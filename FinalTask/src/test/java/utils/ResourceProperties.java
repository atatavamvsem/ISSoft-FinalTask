package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ResourceProperties {
    private static FileInputStream fileConfigInputStream;
    private static FileInputStream fileTestDataInputStream;
    private static Properties CONF_PROPERTIES;
    private static Properties DATA_PROPERTIES;

    static {
        try {
            fileConfigInputStream = new FileInputStream("src/test/resources/credentials.properties");
            fileTestDataInputStream = new FileInputStream("src/test/resources/testData.properties");
            CONF_PROPERTIES = new Properties();
            DATA_PROPERTIES = new Properties();
            CONF_PROPERTIES.load(fileConfigInputStream);
            DATA_PROPERTIES.load(fileTestDataInputStream);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            if (Objects.nonNull(fileConfigInputStream) && Objects.nonNull(fileTestDataInputStream))
                try {
                    fileConfigInputStream.close();
                    fileTestDataInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getCredProperty(String key) {
        return CONF_PROPERTIES.getProperty(key);
    }

    public static String getDataProperty(String key) {
        return DATA_PROPERTIES.getProperty(key);
    }
}
