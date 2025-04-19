package Dao;

import Model.Article;
import Model.Client;
import Model.Commande;
import Model.Panier;

import java.util.List;

public interface CommandeDAO
{
    public void nouvelleCommande(Client client, Panier panier, String adresse);
    public void ajouterDansCommandeEnCours(Commande commande, Article article, Panier panier);
    public void paiementCommande(Commande commande);
    public List<Commande> getCommandes(int limit, int id_commande);
}
