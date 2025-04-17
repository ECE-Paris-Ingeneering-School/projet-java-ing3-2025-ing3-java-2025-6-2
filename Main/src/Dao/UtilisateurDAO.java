package Dao;

import Model.Utilisateurs;

/** Gestion de la base de données utilisateur
 */
public interface UtilisateurDAO
{
    /** Utilisé dans le profil
     * @Récupère les informations d'un utilisateur pour les afficher dans son profil
     */
    public Utilisateurs getUtilisateur(Utilisateurs utilisateur);

    /** Utilisé pour la création d'un compte
     * @Ajoute un nouvel utilisateur dans la base de données
     */
    public void ajouterUtilisateur(Utilisateurs utilisateur);

    /** Utilisé pour la connexion
     * @Permet à un utilisateur de se connecter
     */
    public Utilisateurs connexionUtilisateur(Utilisateurs utilisateur);
}
