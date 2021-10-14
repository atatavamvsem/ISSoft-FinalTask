package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class ResourceProperties {
    private static FileInputStream fileConfigInputStream;
    private static Properties DATA_PROPERTIES;

    static {
        try {
            fileConfigInputStream = new FileInputStream("src/test/resources/testData.properties");
            DATA_PROPERTIES = new Properties();
            DATA_PROPERTIES.load(fileConfigInputStream);
        } catch (IOException e) {
            throw new UnsupportedOperationException(e);
        } finally {
            if (Objects.nonNull(fileConfigInputStream))
                try {
                    fileConfigInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getDataProperty(String key) {
        return DATA_PROPERTIES.getProperty(key);
    }
}
