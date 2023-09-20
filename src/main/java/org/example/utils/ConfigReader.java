package org.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties initializeProperties() throws IOException {
        Properties prop = new Properties();
       // File proFile = new File("src/main/java/org/example/resources/GlobalData.properties");
        try
        {
            FileInputStream fis = new FileInputStream("src/main/java/org/example/resources/GlobalData.properties");
            prop.load(fis);
        }catch(Throwable e){
            e.printStackTrace();
        }
        return prop;

    }
}
