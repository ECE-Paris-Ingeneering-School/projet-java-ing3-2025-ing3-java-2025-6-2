package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static DatabaseConfig instance;
    private final Properties properties;

    private DatabaseConfig() {
        properties = new Properties();
        loadConfig();
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    private void loadConfig() {
        try (FileInputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            // Si le fichier n'existe pas, utiliser les valeurs par défaut
            setDefaultProperties();
        }
    }

    private void setDefaultProperties() {
        properties.setProperty("db.url", "jdbc:mysql://localhost:3306/ecommerce_db");
        properties.setProperty("db.user", "root");
        properties.setProperty("db.password", "");
        properties.setProperty("db.poolSize", "10");
    }

    public String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public String getDbUser() {
        return properties.getProperty("db.user");
    }

    public String getDbPassword() {
        return properties.getProperty("db.password");
    }

    public int getDbPoolSize() {
        return Integer.parseInt(properties.getProperty("db.poolSize", "10"));
    }
} 