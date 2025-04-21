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
    public void nouvelleCommande(Client client, Panier panier, String adresse)
    {
        try
        {
            /// connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_commande = new Random().nextInt();

            /// Exécution de la requête INSERT INTO de l'objet client, de son panier et de l'adresse en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO commande(id_commande, id_client, statut, adresse_livraison, montant_total) VALUES ('"+id_commande+"','"+client.getIdentifiant()+"', 'en attente', '"+adresse+"', '"+panier.calculerPrixTotal()+"')");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout d'une nouvelle commande impossible");
        }
    }

    @Override
    public void ajouterDansCommandeEnCours(Commande commande, Article article)
    {
        try
        {
            /// connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page de commande
            int id_ligne_commande = new Random().nextInt();
            int id_commande = commande.getId();
            int id_article = article.getId();
            int quantite = commande.getArticles().size();
            float prix_unitaire = article.getPrixUnitaire();
            float montant_total = commande.getPrixTotal();

            /// Exécution de la requête INSERT INTO de l'objet commande et de l'article en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO lignecommande(id_ligne_pcommande, id_commande, id_article, quantite, prix_unitaire, prix_apres_remise) VALUES ('"+id_ligne_commande+"','"+id_commande+"', '"+id_article+"', '"+quantite+"', '"+prix_unitaire+"','"+montant_total+"')");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout de l'article impossible");
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
                            "SET (statut = 'annulée') WHERE id_client = '"+id_client+"'"
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
            PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE commande SET (statut = 'payé') WHERE id_commande = '"+id_commande+"'");
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
                    String date_commande = resultSet.getString("date_commande");
                    float prix = resultSet.getFloat("montant_total");
                    //Commande nextCommmande = new Commande(client, liste);
                    //articles.add(nextCommmande);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des nouveaux articles");
        }
        return articles;
    }
}
