package Model;

import Control.FenetreControl;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

/** Fenetre constitue l'ensemble des fenêtres utilisées dans ce projet
 * @Réalisé ensemble
 * */
public interface Fenetre
{
    /** Mise en place du controleur*
     * @Par Andy
     * @param controleur Le controleur de boutons
     */
    public void setControleur(FenetreControl controleur);

    /** Fenêtre inscription utilisateur
     * @Par Andy et Alara
     * */
    public void setInscrire();

    /** Fenêtre connexion utilisateur
     * @Par Andy et Alara
     */
    public void setIdentification();

    /** Fenêtre page d'accueil
     * @Par Andy et Alara
     */
    public void setAccueil();

    /** Fenêtre vue du profil utilisateur
     * @Réalisé ensemble
     */
    public void setProfil();

    /** Gestion de certains événements (côté réaliste et débuggage)
     * @Par Andy
     * @param titre Titre de la fenêtre
     * @param description Description de la fenêtre
     */
    public void setEvent(String titre, String description);

    /** Fenetres principales : Barre de navigation
     * @Par Alara
     * @return Panel de la barre de navigation
     */
    public JPanel createNavigationBar();

    /** Fenetres principales : Création d'un bouton de navigation
     * @Par Alara
     * @param text Texte sur le bouton
     * @param listener Action du bouton
     * @return Le bouton créé
     */
    public JButton createNavButton(String text, ActionListener listener);

    /** Méthode pour créer une carte de produit avec image
     * @Par Alara
     * @param article Article à utiliser
     * @return Le panel de l'article
     */
    public JPanel createProductCard(Article article);

    /** Page promotions
     * @Par Octave
     */
    public void setArticlePromo();

    /** Paiement d'un article
     * @Par Andy
     */
    public void setPaiement();

    /** Ajout d'un bouton sur une page
     * @Par Andy
     * @param panel Le panel à utiliser
     * @param label Le label à ajouter
     */
    public void addButton(JPanel panel, String label);

    /** Vue Articles
     * @Réalisé ensemble
     */
    public void setListeArticles();

    /** Carte d'identité d'un article
     * @Par Octave et Alara
     * @param article L'article à utiliser
     * @return Le panel associé à l'article
     */
    public JPanel createArticlePanel(Article article);

    /** Méthode pour obtenir le nom du fichier image correspondant
     * @Par Alara
     * @param nomArticle Le nom de l'article
     * @param categorie La catégorie de l'article
     * @return Le chemin de l'image
     */
    public String getImageFileName(String nomArticle, String categorie);

    /** Pour certaines fenêtres : Bouton retour
     * @Par Octave
     */
    public void creerRetour();

    /**Vue Panier
     * @Par Andy et Alara
     */
    public void setPanier();

    /** Carte d'identité d'un produit
     * @Par Alara
     * @param article L'article à utiliser
     * @return Le panel de l'article
     */
    public JPanel createCartItemPanel(Article article);

    /** Vue nouvelle commande (adresse à renseigner, le reste est récupéré)
     * @Par Andy
     */
    public void setNewCommande();

    /** Vue modif Article
     * @PRéalisé ensemble
     */
    public void setModifArticle();

    /** Vue Modification d'un produit (pour admin)
     * @Par Octave et Alara
     */
    public void ProduitsModif();

    /** Modifier un article (seulement pour les admins)
     * @Par Andy et Alara
     * @param article Article
     * @return Le panel de l'article
     */
    public JPanel modifArticle(Article article);

    /** Modification d'un article (seulement pour les admins), sur la même base que ajouter un article
     * @Par Andy
     * @param article Article à modifier
     */
    public void setModifierArticle(Article article);

    /** Affichage des statistiques
     * @Par Octave
     */
    public void stats();

    /** Titre d'un panel d'une page
     * @Par Octave
     * @param title Titre à utiliser
     * @return Le panel avec le titre
     */
    public JPanel createTitlePanel(String title);

    /** Vue nouvelle article (seulement pour les administrateurs)
     * @Par Andy
     */
    public void setNewArticle();

    /** Vue du catalogue
     * @Par Octave et Alara
     * @param recherche La recherche à effectuer
     */
    public void setCatalogue(String recherche);

    /** Vue détail d'un article spécifique
     * @param article Article à afficher
     */
    public void afficherDetailsArticle(Article article);

    /** Vue historique des paiements
     * @Par Andy et Alara
     * @param paiements Liste des paiements effectués
     */
    public void setHistoriquePaiement(List<Paiement> paiements);

    /** Vue Historique commande
     * @Par Andy et Alara
     * @param commande Commande à afficher
     */
    public void setHistoCommande(Commande commande);

    /** Vue générale de l'ensemble des clients enregistrés
     * @Par Andy
     * @param clients Liste des utilisateurs
     */
    public void setGestionClient(List<Utilisateurs> clients);

    /** Créé les panels pour les avis
     * @Par Alara
     * @param article Article à utiliser
     */
    public void setAvisPanel(Article article);
}
