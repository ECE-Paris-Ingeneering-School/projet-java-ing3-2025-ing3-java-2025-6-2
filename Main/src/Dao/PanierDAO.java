package Dao;

import Model.Article;
import Model.Client;
import Model.Commande;
import Model.Panier;

import java.util.List;
import java.util.Map;

/** Gestion du panier et de la table associée
 * @Par Andy
 * */
public interface PanierDAO
{
    /**Récupère un panier spécifique
     * @param client Le client connecté
     * @return Le panier recherché
     */
    public Panier getPanier(Client client);

    /**Permet de créer un nouveau panier
     * @param client Le client connecté
     */
    public void nouveauPanier(Client client);

    /**Ajoute un article au panier concerné
     * @param client Le client connecté
     * @param panier Le panier actuel
     * @param article L'article choisi
     * @param quantite La quantité désiré
     */
    public void ajouterAuPanier(Panier panier, Client client, Article article, int quantite);

    /**Retire un article au panier concerné
     * @param article Article à retirer
     */
    public void supprimerDuPanier(Article article);

    /**Supprime le panier
     * @param client Le client connecté
     * */
    public void supprimerPanier(Client client);

    /**Récupère les articles dans le panier d'un client
     * @param client Le client connecté
     * @return Sous forme Article avec quantité voulu par le client concerné
     */
    public Map<Article, Integer> panierArticles(Client client);
}
