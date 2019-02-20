package services;

import org.h2.tools.RunScript;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


        } catch (IOException io) {

        }

    }


    public static void firstLoad() {
        try (Connection connection = Configuration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) from ADMIN;");
        ) {
            preparedStatement.executeQuery();

        } catch (Exception e) {
            try {
                RunScript.execute(Configuration.getConnection(), new FileReader("sql/Create_Tables.sql"));
                RunScript.execute(Configuration.getConnection(), new FileReader("sql/Insert_Question.sql"));
            } catch (Exception ex) {
                System.err.println("could not create tables and sample questions");
                ex.printStackTrace();
            }
        }
    }

    public static void dropLoad() {
        try (Connection connection = Configuration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) from ADMIN;");
        ) {
            preparedStatement.executeQuery();

        } catch (Exception e) {
            try {
                RunScript.execute(Configuration.getConnection(), new FileReader("sql/Drop_Tables.sql"));

            } catch (Exception ex) {
                System.err.println("could not create tables and sample questions");
                ex.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws SQLException {

        Configuration conf = Configuration.getInstance();
        String dburl = conf.getPropertyValue("db.url");
        String user = conf.getPropertyValue("db.username");
        String password = conf.getPropertyValue("db.password");
        Connection connection = DriverManager.getConnection(dburl, user, password);
        return connection;

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
