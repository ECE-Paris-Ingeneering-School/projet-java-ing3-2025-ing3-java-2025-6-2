package Dao;

import Model.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO
{

    private DaoFactory daoFactory;

    /// constructeur dépendant de la classe DaoFactory
    public ArticleDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public Article getArticle(int id)
    {
        Article article = null;
        try
        {
            Connection connexion = daoFactory.getConnection();
            /// On récupère les informations de l'article à partir de son id
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "SELECT *" +
                            "FROM article WHERE id_article = '"+id+"'"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== Articles dans la base de données ===");
            while (resultSet.next()) {
                int id_article = resultSet.getInt("id_article");
                String nom = resultSet.getString("nom");
                String marque = resultSet.getString("marque");
                String categorie = resultSet.getString("categorie");
                String description = resultSet.getString("description");
                float prix = resultSet.getFloat("prix");
                int stock = resultSet.getInt("stock");
                int seuil_remise = resultSet.getInt("seuil_remise");

                System.out.println("Article trouvé - Nom: '" + nom + "', Catégorie: '" + categorie + "'");

                /// Par défaut, on considère que l'article est disponible
                boolean disponibilite = true;

                article = new Article(id_article, stock, seuil_remise, nom, marque, categorie, description, prix, disponibilite);
                return article;
            }
            System.out.println("=== Fin de la liste des articles ===\n");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des articles");
        }
        return article;
    }

    @Override
    public void ajouterArticle(Article article)
    {
        try
        {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page d'inscription
            int id_article = article.getId();
            String nom = article.getNom();
            String description = article.getDescription();
            float prix = article.getPrixUnitaire();
            int stock = article.getStock();
            int seuil_remise = article.getSeuil_remise();
            String categroie = article.getCategorie();
            String marque = article.getMarque();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement(
                "INSERT INTO article(id_article, nom, description, prix, stock, seuil_remise, categorie, marque) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, id_article);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, description);
            preparedStatement.setDouble(4, prix);
            preparedStatement.setInt(5, stock);
            preparedStatement.setInt(6, seuil_remise);
            preparedStatement.setString(7, categroie);
            preparedStatement.setString(8, marque);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout de l'article impossible");
        }
    }

    @Override
    public void modifierArticle(Article article)
    {
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page d'inscription
            int id_article = article.getId();
            String nom = article.getNom();
            String description = article.getDescription();
            float prix = article.getPrixUnitaire();
            int stock = article.getStock();
            int seuil_remise = article.getSeuil_remise();
            String categroie = article.getCategorie();
            String marque = article.getMarque();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE article " +
                            "SET nom = '"+nom+"', description = '"+description+"', prix = '"+prix+"', stock = '"+stock+"', seuil_remise = '"+seuil_remise+"', categorie = '"+categroie+"', marque = '"+marque+"' " +
                            " WHERE id_article = '"+id_article+"'"
            );
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Modification de l'article impossible");
        }
    }

    @Override
    public List<Article> listerArticles()
    {
        List<Article> articles = new ArrayList<>();
        try
        {
            Connection connexion = daoFactory.getConnection();
            /// On récupère les informations de chaque article
            PreparedStatement preparedStatement = connexion.prepareStatement(
                "SELECT id_article, nom, marque, categorie, description, prix, stock, seuil_remise " +
                "FROM article"
            );
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== Articles dans la base de données ===");
            while (resultSet.next()) {
                int id = resultSet.getInt("id_article");
                String nom = resultSet.getString("nom");
                String marque = resultSet.getString("marque");
                String categorie = resultSet.getString("categorie");
                String description = resultSet.getString("description");
                float prix = resultSet.getFloat("prix");
                int stock = resultSet.getInt("stock");
                int seuil_remise = resultSet.getInt("seuil_remise");

                System.out.println("Article trouvé - Nom: '" + nom + "', Catégorie: '" + categorie + "'");

                /// Par défaut, on considère que l'article est disponible
                boolean disponibilite = true;

                Article article = new Article(id, stock, seuil_remise, nom, marque, categorie, description, prix, disponibilite);
                articles.add(article);
            }
            System.out.println("=== Fin de la liste des articles ===\n");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des articles");
        }
        return articles;
    }

    @Override
    public List<Article> getNouveauxProduits(int limit)
    {
        List<Article> articles = new ArrayList<>();
        try
        {
            Connection connexion = daoFactory.getConnection();
            /// On récupère les informations d'un article, regroupé par ordre croissant des id
            PreparedStatement preparedStatement = connexion.prepareStatement(
                "SELECT id_article, nom, marque, categorie, description, prix, stock, seuil_remise " +
                "FROM article ORDER BY id_article DESC LIMIT ?"
            );
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_article");
                String nom = resultSet.getString("nom");
                String marque = resultSet.getString("marque");
                String categorie = resultSet.getString("categorie");
                String description = resultSet.getString("description");
                float prix = resultSet.getFloat("prix");
                int stock = resultSet.getInt("stock");
                int seuil_remise = resultSet.getInt("seuil_remise");

                boolean disponibilite = true;
                Article article = new Article(id, stock, seuil_remise, nom, marque, categorie, description, prix, disponibilite);
                articles.add(article);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des nouveaux articles");
        }
        return articles;
    }

    @Override
    public List<Article> getArticlesEnPromotion()
    {
        List<Article> articles = new ArrayList<>();
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connexion = daoFactory.getConnection();
            /// On récupère les informations de chaque article si le seuil de remise est positif
            preparedStatement = connexion.prepareStatement(
                "SELECT id_article, nom, marque, categorie, description, prix, stock, seuil_remise " +
                "FROM article WHERE seuil_remise > 0"
            );
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_article");
                String nom = resultSet.getString("nom");
                String marque = resultSet.getString("marque");
                String categorie = resultSet.getString("categorie");
                String description = resultSet.getString("description");
                float prix = resultSet.getFloat("prix");
                int stock = resultSet.getInt("stock");
                int seuil_remise = resultSet.getInt("seuil_remise");
                
                Article article = new Article(id, stock, seuil_remise, nom, marque, categorie, description, prix, true);
                articles.add(article);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return articles;
    }

    public int compteArticle() {
        int compte = 0;
        String query = "SELECT COUNT(*) AS comptArt FROM article WHERE 1;";
        System.out.println("TEST1");
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                compte = resultSet.getInt("comptArt");
            }
            resultSet.close();
            preparedStatement.close();
            connexion.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur lors du comptage des articles.");
        }
        finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connexion != null) connexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return compte;
    }
    public int getPrixMinMax(String operation) {
        int price = 0;
        String query = "SELECT " + operation + "(prix) AS comptArt FROM article;";

        try
        {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                price = resultSet.getInt("comptArt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération du prix " + operation);
        }
        return price;
    }

    public int Articlemax() {
        return getPrixMinMax("MAX");
    }

    public int Articlemin() {
        return getPrixMinMax("MIN");
    }
}
