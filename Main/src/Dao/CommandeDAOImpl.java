package Dao;

import Model.Article;
import Model.Client;
import Model.Commande;
import Model.Panier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CommandeDAOImpl implements CommandeDAO
{
    private DaoFactory daoFactory;

    public CommandeDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    @Override
    public void nouvelleCommande(Client client, String adresse)
    {
        try
        {
            PanierDAOImpl panier = new PanierDAOImpl(daoFactory);
            Panier panier_client = panier.getPanier(client);
            /// connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_commande = new Random().nextInt(1_000_000_000);

            /// Exécution de la requête INSERT INTO de l'objet client, de son panier et de l'adresse en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO commande(id_commande, id_client, statut, adresse_livraison, montant_total) VALUES ('"+id_commande+"','"+client.getIdentifiant()+"', 'en attente', '"+adresse+"', '"+panier_client.calculerPrixTotal()+"')");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout d'une nouvelle commande impossible");
        }
    }

    @Override
    public void ajouterDansCommandeEnCours(Commande commande, Panier panier)
    {
        Map<Article, Integer> articlesEnCours = new LinkedHashMap<>();
        try
        {
            /// connexion
            Connection connexion = daoFactory.getConnection();
            ArticleDAOImpl articleDAO = new ArticleDAOImpl(daoFactory);
            PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * from lignepanier WHERE id_panier = '"+panier.getId()+"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                int id_article = resultSet.getInt("id_article");
                Article article = articleDAO.getArticle(id_article);
                int quantite = resultSet.getInt("quantite");
                articlesEnCours.put(article, quantite);

                Article article_a_ajouter = articlesEnCours.keySet().iterator().next();
                int quantite_ajouter = articlesEnCours.get(article);
                PreparedStatement statement = connexion.prepareStatement("SELECT prix FROM article WHERE id_article = '"+article_a_ajouter.getId()+"'");
                ResultSet resultSet1 = statement.executeQuery();
                if (resultSet1.next())
                {
                    int prix = resultSet1.getInt("prix");
                    int id_ligne_commande = new Random().nextInt(1_000_000_000);

                    /// Exécution de la requête INSERT INTO de l'objet commande en paramètre
                    preparedStatement = connexion.prepareStatement("INSERT INTO lignecommande(id_ligne_commande, id_commande, id_article, quantite, prix_unitaire, prix_apres_remise) VALUES ('" + id_ligne_commande + "','" + commande.getId() + "', '" + id_article + "', '" + quantite_ajouter + "', '" + prix + "', '" + quantite_ajouter*prix + "')");
                    preparedStatement.executeUpdate();

                    /// Mise à jour
                    preparedStatement = connexion.prepareStatement("UPDATE commande SET montant_total = '"+quantite_ajouter*prix+"' WHERE id_commande = '"+commande.getId()+"'");
                    preparedStatement.executeUpdate();

                }
                /// récupération des informations saisies dans la page de commande
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout de l'article dans la commande impossible");
        }
    }

    public void modifierCommande(Client client)
    {
        try
        {
            /// connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page d'inscription
            int id_client = client.getIdentifiant();

            /// Mise à jour du statut de la commande en mode annulée
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE commande " +
                            "SET statut = 'annulée' WHERE id_client = '"+id_client+"'"
            );

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Modification de la commande impossible");
        }
    }

    @Override
    public void paiementCommande(Commande commande)
    {
        try
        {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_commande = commande.getId();

            /// Mise à jour du statut en mode payé
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE commande SET statut = 'payée' WHERE id_commande = '"+id_commande+"'");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout du client impossible");
        }
    }

    @Override
    public List<Commande> getCommandes(int limit, int id_commande)
    {
        List<Commande> articles = new ArrayList<>();
        Map<Article, Integer> liste = new HashMap<>();
        try
        {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "SELECT *" +
                            "FROM lignecommande WHERE id_commande = '"+id_commande+"' ORDER BY id_ligne_commande DESC LIMIT ?"
            );
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                id_commande = resultSet.getInt("id_commande");
                int quantite = resultSet.getInt("quantite");
            }

            PreparedStatement preparedStatementcommande = connexion.prepareStatement(
                    "SELECT id_commande, id_client, date_commande, statut, montant_total" +
                            "FROM commande ORDER BY id_commande DESC LIMIT ?"
            );
            preparedStatementcommande.setInt(1, limit);
            ResultSet resultSetcommande = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_client = resultSet.getInt("id_client");

                /// Recherche du client
                PreparedStatement preparedStatementClient = connexion.prepareStatement(
                        "SELECT nom, prenom, email, mot_de_passe, type_utilisateur" +
                                "FROM utilisateur WHERE id_utilisateur = '"+id_client+"'");

                ResultSet resultSetClient = preparedStatementClient.executeQuery();

                if(resultSetClient.next())
                {
                    String Nom = resultSetClient.getString(1);
                    String Prenom = resultSetClient.getString(2);
                    String email = resultSetClient.getString(3);
                    String mot_de_passe = resultSetClient.getString(4);
                    String type_utilisateur = resultSetClient.getString(5);
                    Client client = new Client(id_client, Nom, Prenom, email, mot_de_passe, type_utilisateur);
                    Commande nextCommmande = new Commande(id_commande, client, liste);
                    articles.add(nextCommmande);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des nouveaux articles");
        }
        return articles;
    }

    public Commande getCommande(Client client)
    {
        Commande commande = null;
        Map<Article, Integer> liste = new HashMap<>();
        try
        {
            Connection connexion = daoFactory.getConnection();
            PanierDAOImpl panierDAO = new PanierDAOImpl(daoFactory);
            ArticleDAOImpl articleDAO = new ArticleDAOImpl(daoFactory);
            /// Récupération de l'id du panier en fonction du client
            PreparedStatement preparedStatementCom = connexion.prepareStatement(
                    "SELECT * " +
                            "FROM commande WHERE id_client = '" + client.getIdentifiant() + "'"
            );
            ResultSet resultSetPanier = preparedStatementCom.executeQuery();
            if(resultSetPanier.next())
            {
                Panier panier = panierDAO.getPanier(client);
                int id_commande = resultSetPanier.getInt("id_commande");
                PreparedStatement preparedStatementPanier = connexion.prepareStatement(
                        "SELECT * " +
                                "FROM lignepanier WHERE id_panier = '" + panier.getId() + "'"
                );
                ResultSet resultSetPanier2 = preparedStatementPanier.executeQuery();
                while(resultSetPanier2.next())
                {
                    int id_article = resultSetPanier2.getInt("id_article");
                    Article article = articleDAO.getArticle(id_article);
                    int quantite = resultSetPanier2.getInt("quantite");
                    liste.put(article, quantite);
                }
                commande = new Commande(id_commande, client, liste);
                return commande;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout d'une nouvelle commande impossible");
        }
        return commande;
    }
}
