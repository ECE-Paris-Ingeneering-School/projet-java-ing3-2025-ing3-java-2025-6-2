package Dao;

import Model.Article;
import Model.Client;
import Model.Commande;
import Model.Panier;

import java.util.List;

/// Gestion du panier dans la base de données
public interface PanierDAO
{
    /**Récupère un panier spécifique
     * @Utilise le client concerné
     */
    public Panier getPanier(Client client);
    /**Permet de créer un nouveau panier
     * @Utilise le client concerné
     */
    public void nouveauPanier(Client client);
    /**Ajoute un article au panier concerné
     * @Utilise le client concerné et son panier actuel
     */
    public void ajouterAuPanier(Panier panier, Article article);
    /**Retire un article au panier concerné
     * @Utilise l'article à retirer
     */
    public void supprimerDuPanier(Article article);
    /// Supprime le panier
    public void supprimerPanier(Client client);
    /**Récupère les articles dans le panier d'un client
     * @Utilise le client concerné
     */
    public List<Article> panierArticles(Client client);
}
