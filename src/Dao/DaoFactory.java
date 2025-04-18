package Dao;

// import des packages
import java.sql.*;
import java.util.ArrayList;

/**
 * La DAO Factory (DaoFactory.java) permet d'initialiser le DAO en chargeant notamment les drivers nécessaires
 * (ici un driver JDBC MySQL) et se connecte à la base de données. La Factory peut fournir plusieurs DAO (ici,
 * il n'y en a qu'un seul, UtilisateurDao, qui correspond à une table de la base).
 */
public class DaoFactory {
    private static DaoFactory instance;
    private String url = "jdbc:mysql://localhost:3306/ecommerce_db";
    private String username = "root";
    private String password = "";

    private DaoFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DaoFactory getInstance(String database, String username, String password) {
        if (instance == null) {
            instance = new DaoFactory();
            instance.url = "jdbc:mysql://localhost:3306/" + database;
            instance.username = username;
            instance.password = password;
        }
        return instance;
    }

    /**
     * Méthode qui retourne le driver de base de données approprié
     * @return : le driver approprié
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        // Retourner la connection du driver de la base de données
        return DriverManager.getConnection(url, username, password);
    }

    /**
     *     Fermer la connexion à la base de données
     */
    public void disconnect() {
        Connection connexion = null;

        try {
            // création d'un ordre SQL (statement)
            connexion = this.getConnection();
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur de déconnexion à la base de données");
        }
    }
}