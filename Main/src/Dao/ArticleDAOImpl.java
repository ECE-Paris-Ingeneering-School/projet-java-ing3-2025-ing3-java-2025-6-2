package Dao;

import Model.Article;
import Model.Utilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleDAOImpl implements ArticleDAO
{

    private DaoFactory daoFactory;

    // constructeur dépendant de la classe DaoFactory


    public ArticleDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public Article getArticle(Article article)
    {
        return article;
    }

    public void ajouterArticle(Article article)
    {
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page d'inscription
            int id_article = article.getId();
            String nom = article.getNom();
            String description = article.getDescription();
            double prix = article.getPrixUnitaire();
            int stock = article.getStock();
            int seuil_remise = article.getSeuil_remise();
            String categroie = article.getCategorie();
            String marque = article.getMarque();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO article(id_article, nom, description, prix, stock, seuil_remise, categorie, marque) VALUES ('"+id_article+"', '"+nom+"', '"+description+"', '"+prix+"', '"+stock+"', '"+seuil_remise+"', '"+categroie+"', '"+marque+"')");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout du client impossible");
        }
    }

    public void modifierArticle(Article article)
    {

    }
}
