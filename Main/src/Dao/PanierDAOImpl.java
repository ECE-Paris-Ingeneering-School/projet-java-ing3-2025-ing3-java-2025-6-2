package Dao;

import Model.Article;
import Model.Client;
import Model.Panier;
import Model.Utilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PanierDAOImpl implements PanierDAO
{
    private DaoFactory daoFactory;

    public PanierDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public Panier getPanier(Client client)
    {
        Panier panier = null;
        try
        {
            Connection connexion = daoFactory.getConnection();
            /// Récupération de l'id du panier en fonction du client
            PreparedStatement preparedStatementPanier = connexion.prepareStatement(
                    "SELECT id_panier " +
                            "FROM panier WHERE id_client = '" + client.getIdentifiant() + "'"
            );
            ResultSet resultSetPanier = preparedStatementPanier.executeQuery();
            if(resultSetPanier.next())
            {
                int id_panier = resultSetPanier.getInt("id_panier");
                panier = new Panier(id_panier, client);
                return panier;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Récupération du panier impossible");
        }
        return panier;
    }

    @Override
    public void nouveauPanier(Client client)
    {
        try
        {
            PanierDAOImpl panierDAO = new PanierDAOImpl(daoFactory);
            if(panierDAO.getPanier(client) != null)
            {
                System.out.println("Le panier existe déjà");
            }
            else
            {
                /// connexion
                Connection connexion = daoFactory.getConnection();

                /// Préparation des valeurs à insérer
                int id_panier = new Random().nextInt(1_000_000_000);
                int id_client = client.getIdentifiant();

                /// Exécution de la requête INSERT INTO de l'objet client en paramètre
                PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO panier(id_panier, id_client) VALUES ('"+id_panier+"','"+id_client+"')");
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout d'un nouveau panier impossible");
        }
    }

    @Override
    public void ajouterAuPanier(Panier panier, Client client, Article article, int quantite)
    {
        try
        {
            PanierDAOImpl panierDAO = new PanierDAOImpl(daoFactory);
            ArticleDAOImpl articleDAO = new ArticleDAOImpl(daoFactory);
            /// Vérifier si le panier existe ou si le stock d'un article est suffisant
            if(panierDAO.getPanier(client) == null || articleDAO.getArticle(article.getId()).getStock() < quantite)
            {
                System.out.println("Ajout d'un nouvel article impossible");
            }
            else
            {
                /// connexion
                Connection connexion = daoFactory.getConnection();

                /// récupération des informations saisies dans la page de commande
                int id_ligne_panier = new Random().nextInt(1_000_000_000);
                int id_panier = panier.getId();
                int id_article = article.getId();

                /// Exécution de la requête INSERT INTO pour le nouvel article à insérer
                PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO lignepanier(id_ligne_panier, id_panier, id_article, quantite) VALUES ('" + id_ligne_panier + "','" + id_panier + "', '" + id_article + "', '" + quantite + "')");
                preparedStatement.executeUpdate();

                /// Mise à jour la quantité de l'article ajouté au panier
                preparedStatement = connexion.prepareStatement("UPDATE article SET stock = stock - '"+quantite+"' WHERE id_article = '" + id_article + "'");
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout de l'article impossible");
        }
    }

    @Override
    public void supprimerDuPanier(Article article)
    {
        int quantite;
        try
        {
            /// connexion
            int id_article = article.getId();
            Connection connexion = daoFactory.getConnection();

            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT quantite FROM lignepanier WHERE id_article = '"+id_article+"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                quantite = resultSet.getInt("quantite");

                /// Exécution de la requête DELETE FROM de l'objet article en paramètre
                preparedStatement = connexion.prepareStatement("DELETE FROM lignepanier WHERE id_article = '"+id_article+"'");
                preparedStatement.executeUpdate();

                /// Mise à jour du stock de l'article
                preparedStatement = connexion.prepareStatement("UPDATE article SET stock = stock + '"+quantite+"' WHERE id_article = '"+id_article+"'");
                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Supression de l'article impossible");
        }
    }

    @Override
    public void supprimerPanier(Client client)
    {
        try
        {
            /// connexion
            Connection connexion = daoFactory.getConnection();

            /// Récupération des informations saisies dans la page de commande
            int id_client = client.getIdentifiant();

            /// Exécution de la requête DELETE FROM de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("DELETE FROM panier WHERE id_client = '"+id_client+"'");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout d'une nouvelle commande impossible");
        }
    }

    public Map<Article, Integer> panierArticles(Client client)
    {
        Map<Article, Integer> articles = new HashMap<>();
        try
        {
            Connection connexion = daoFactory.getConnection();
            /// On récupère l'id du panier à utiliser
            PreparedStatement preparedStatementPanier = connexion.prepareStatement(
                    "SELECT id_panier " +
                            "FROM panier WHERE id_client = '"+client.getIdentifiant()+"'"
            );
            ResultSet resultSetPanier = preparedStatementPanier.executeQuery();
            while (resultSetPanier.next())
            {
                int id_panier = resultSetPanier.getInt("id_panier");
                /// On récupère tous les articles du panier utilisé
                PreparedStatement preparedStatement = connexion.prepareStatement(
                        "SELECT id_panier, id_article, quantite " +
                                "FROM lignepanier WHERE id_panier = '"+id_panier+"'"
                );
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("\n=== Articles dans la base de données ===");
                while (resultSet.next()) {
                    int id_article = resultSet.getInt("id_article");
                    int quantite = resultSet.getInt("quantite");

                    ArticleDAOImpl articleDAO = new ArticleDAOImpl(daoFactory);

                    Article article = articleDAO.getArticle(id_article);
                    articles.put(article, quantite);
                }
                System.out.println("=== Fin de la liste des articles ===\n");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des articles");
        }
        return articles;
    }
}
