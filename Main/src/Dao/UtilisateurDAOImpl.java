package Dao;

import java.sql.*;

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
