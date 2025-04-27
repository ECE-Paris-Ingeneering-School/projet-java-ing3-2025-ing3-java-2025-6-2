package Dao;

import Model.Utilisateurs;

import java.util.List;

/** Gestion de la base de données utilisateur
 * @Par Andy et Alara
 */
public interface UtilisateurDAO
{

    /** Ajoute un nouvel utilisateur dans la base de données
     * @param utilisateur Utilisateur à ajouter
     */
    public void ajouterUtilisateur(Utilisateurs utilisateur);

    /** Utilisé pour la connexion
     * @param utilisateur L'utilisateur qui se connecte
     * @return l'utilisateur connecté (connexion normale ou récupération des informations)
     */
    public Utilisateurs connexionUtilisateur(Utilisateurs utilisateur);

    /** Compte le nombre de client
     * @return Le nombre de clients enregistrés
     * */
    public int compteClients();

    /** Récupère l'ensemble des statuts des clients et les mets à jour si nécessaire
     * @return La liste des utilisateurs
     * */
    public List<Utilisateurs> getAllClientsWithFidelity();
}
