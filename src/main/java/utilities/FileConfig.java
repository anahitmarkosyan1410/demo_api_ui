package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileConfig {
    private static Properties properties = new Properties();
    private static final Logger logger = LogManager.getLogger(FileConfig.class);

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/config.properties");
            properties.load(file);
        }catch (IOException e){
            logger.error("Failed to load config.properties file");
        }
    }

    public static String getProperty(String key){
        String value = properties.getProperty(key);
        if (value == null){
            logger.error(key + " value is not found in the file");
        }
        return value;
    }
}
