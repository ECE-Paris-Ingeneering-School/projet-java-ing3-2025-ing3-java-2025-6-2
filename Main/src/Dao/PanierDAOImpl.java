package Dao;

import Model.Article;
import Model.Client;
import Model.Panier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class PanierDAOImpl implements PanierDAO
{
    private DaoFactory daoFactory;

    public PanierDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public void nouveauPanier(Client client)
    {
        try
        {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_panier = new Random().nextInt();
            int id_client = client.getIdentifiant();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO panier(id_panier, id_client) VALUES ('"+id_panier+"','"+id_client+"')");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout d'une nouvelle commande impossible");
        }
    }

    @Override
    public void ajouterAuPanier(Panier panier, Article article)
    {
        try
        {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_ligne_panier = new Random().nextInt();
            int id_panier = panier.getId();
            int id_article = article.getId();
            int quantite = panier.getArticles().size();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO lignepanier(id_ligne_panier, id_panier, id_article, quantite) VALUES ('"+id_ligne_panier+"','"+id_panier+"', '"+id_article+"', '"+quantite+"')");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout de l'article impossible");
        }
    }

    @Override
    public void supprimerDuPanier(Article article)
    {
        try
        {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_article = article.getId();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM lignepanier WHERE id_article = '"+id_article+"'");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout de l'article impossible");
        }
    }

    @Override
    public void supprimerPanier(Client client)
    {
        try
        {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_client = client.getIdentifiant();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM panier WHERE id_client = '"+id_client+"'");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout d'une nouvelle commande impossible");
        }
    }
}
