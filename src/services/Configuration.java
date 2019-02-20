package services;

import org.h2.tools.RunScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    public Properties properties;

    private static Configuration configuration;   // this object is a singleton

    private Configuration() {
        File file = new File("app.properties");  // getting the properties file
        try {
            properties = new Properties();  // instantiation of a new properties set
            properties.load(new FileInputStream(file));  // loading all the properties of the file
            // create a map<key, value> of all properties
        } catch (IOException e) {

//            try{
//                RunScript.execute(QuestionDAO.getConnection(), new FileReader("SQL_Queries.txt"));
//            } catch (IOException e1) {
//
//            }
        }
    }

    public static Configuration getInstance() {
        if (configuration == null) { // if the config is not initialized
            configuration = new Configuration();  // we initialize it
        }
        return configuration;  // otherwise we return the already existing one
    }

    public String getPropertyValue(String key) { // get the value of a given property
        return properties.getProperty(key);
    }
}