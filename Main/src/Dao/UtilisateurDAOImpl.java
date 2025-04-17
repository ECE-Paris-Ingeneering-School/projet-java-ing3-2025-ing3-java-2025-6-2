package Dao;

import java.sql.*;

import Model.Utilisateurs;

public class UtilisateurDAOImpl implements UtilisateurDAO
{
    private DaoFactory daoFactory;

    // constructeur dépendant de la classe DaoFactory


    public UtilisateurDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    /** Utilisé dans le profil
     * @Récupère les informations d'un utilisateur pour les afficher dans son profil
     */
    public boolean connexionUtilisateur(Utilisateurs utilisateur)
    {
        try
        {
            /// connexion à la base de données
            Connection connexion = daoFactory.getConnection();

            /// Récupération de l'utilisateur correspondant
            PreparedStatement preparedStatement = connexion.prepareStatement("select * from utilisateur where email = '" + utilisateur.getEmail() + "' AND mdp = '" + utilisateur.getMotDePasse() + "'");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            ///traitement de l'exception
            e.printStackTrace();
            System.out.println("Création de la liste de clients impossible");
        }
        return true;
    }

    @Override
    public Utilisateurs getUtilisateur(Utilisateurs utilisateur)
    {
        return null;
    }

    /** Utilisé pour la création d'un compte
     * @Ajoute un nouvel utilisateur dans la base de données
     */
    public void ajouterUtilisateur(Utilisateurs utilisateur)
    {
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();

            /// récupération des informations saisies dans la page d'inscription
            int id_utilisateur = utilisateur.getIdentifiant();
            String nom = utilisateur.getNom();
            String prenom = utilisateur.getPrenom();
            String mail = utilisateur.getEmail();
            String mdp = utilisateur.getMotDePasse();
            String type_utilisateur = utilisateur.getType_utilisateur();

            /// Exécution de la requête INSERT INTO de l'objet client en paramètre
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO utilisateur(id_utilisateur, nom, prenom, email, mot_de_passe, type_utilisateur) VALUES ('"+id_utilisateur+"', '"+nom+"', '"+prenom+"', '"+mail+"', '"+mdp+"', '" +type_utilisateur+ "')");
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout du client impossible");
        }
    }
}
