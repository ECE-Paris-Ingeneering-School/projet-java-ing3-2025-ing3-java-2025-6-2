package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Utilisateurs;

public class UtilisateurDAOImpl implements UtilisateurDAO
{
    private DaoFactory daoFactory;

    /// constructeur dépendant de la classe DaoFactory
    public UtilisateurDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    /** Utilisé dans le profil
     * @Récupère les informations d'un utilisateur pour les afficher dans son profil ou dans les autres pages nécessitant l'utilisation de ses données
     */
    public Utilisateurs connexionUtilisateur(Utilisateurs utilisateur)
    {
        Utilisateurs user = null;
        try
        {
            /// connexion à la base de données
            Connection connexion = daoFactory.getConnection();
            Statement statement = connexion.createStatement();

            /// Récupération de l'utilisateur correspondant
            ResultSet resultats = statement.executeQuery("select id_utilisateur, nom, prenom, type_utilisateur from utilisateur where email = '" + utilisateur.getEmail() + "' AND mot_de_passe = '" + utilisateur.getMotDePasse() + "'");
            if(resultats.next())
            {
                int Id = resultats.getInt(1);
                String Nom = resultats.getString(2);
                String Prenom = resultats.getString(3);
                String type = resultats.getString(4);
                user = new Utilisateurs(Id, Nom, Prenom, utilisateur.getEmail(), utilisateur.getMotDePasse(), type);
            }

        }
        catch (SQLException e)
        {
            ///traitement de l'exception
            e.printStackTrace();
            System.out.println("Utilisateur inconnu");
        }
        return user;
    }

    @Override
    public UtilisateurDAO getUtilisateur()
    {
        return this;
    }

    /** Utilisé pour la création d'un compte
     * @Ajoute un nouvel utilisateur dans la base de données
     */
    public void ajouterUtilisateur(Utilisateurs utilisateur)
    {
        try
        {
            /// connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page d'inscription
            int id_utilisateur = utilisateur.getIdentifiant();
            String nom = utilisateur.getNom();
            String prenom = utilisateur.getPrenom();
            String mail = utilisateur.getEmail();
            String mdp = utilisateur.getMotDePasse();
            String type_utilisateur = utilisateur.getType_utilisateur();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO utilisateur(id_utilisateur, nom, prenom, email, mot_de_passe, type_utilisateur, fidelite) VALUES ('"+id_utilisateur+"', '"+nom+"', '"+prenom+"', '"+mail+"', '"+mdp+"', '" +type_utilisateur+ "', 'Bronze')");
            preparedStatement.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout du client impossible");
        }
    }

    public int compteClients() {
        int compte = 0;
        Connection connexion = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            preparedStatement = connexion.prepareStatement("SELECT COUNT(*) AS comptCli FROM utilisateur WHERE type_utilisateur = ?");
            preparedStatement.setString(1, "client");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                compte = resultSet.getInt("comptCli");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur pour trouver Client");
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

    public List<Utilisateurs> getAllClientsWithFidelity() {
        List<Utilisateurs> clients = new ArrayList<>();
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT u.*, " +
                            "  (SELECT COUNT(*) FROM commande c WHERE c.id_client = u.id_utilisateur) AS nb_commandes, " +
                            "  (SELECT IFNULL(SUM(montant_total),0) FROM commande c WHERE c.id_client = u.id_utilisateur) AS total_achats " +
                            "FROM utilisateur u WHERE u.type_utilisateur = 'client'"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Utilisateurs client = new Utilisateurs(
                        rs.getInt("id_utilisateur"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("mot_de_passe"),
                        rs.getString("type_utilisateur")
                );
                // Ajoute les infos fidélité
                client.setNbCommandes(rs.getInt("nb_commandes"));
                client.setTotalAchats(rs.getFloat("total_achats"));
                // Calcule le niveau de fidélité
                String niveau = "Bronze";
                if (client.getTotalAchats() > 1000)
                {
                    niveau = "Argent";
                    PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE utilisateur SET fidelite = '" + niveau + "' WHERE id_utilisateur = '"+client.getIdentifiant()+"'");
                    preparedStatement.executeUpdate();
                }
                if (client.getTotalAchats() > 3000)
                {
                    niveau = "Gold";
                    PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE utilisateur SET fidelite = '" + niveau + "' WHERE id_utilisateur = '"+client.getIdentifiant()+"'");
                    preparedStatement.executeUpdate();
                }
                if (client.getTotalAchats() > 7000)
                {
                    niveau = "Platine";
                    PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE utilisateur SET fidelite = '" + niveau + "' WHERE id_utilisateur = '"+client.getIdentifiant()+"'");
                    preparedStatement.executeUpdate();
                }
                client.setNiveauFidelite(niveau);
                clients.add(client);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return clients;
    }

}
