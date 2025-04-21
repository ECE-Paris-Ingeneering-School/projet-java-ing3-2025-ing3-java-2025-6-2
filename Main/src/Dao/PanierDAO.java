package Dao;

import Model.Article;
import Model.Client;
import Model.Commande;
import Model.Panier;

import java.util.List;

/// Gestion du panier dans la base de données
public interface PanierDAO
{
    public Panier getPanier(Client client);
    /**Création d'un nouveau pannier
     */
    public void nouveauPanier(Client client);
    /// Ajoute un article dans le panier actuel
    public void ajouterAuPanier(Panier panier, Article article);
    /// Supprime un article dans le panier actuel
    public void supprimerDuPanier(Article article);
    public void supprimerPanier(Client client);
}
