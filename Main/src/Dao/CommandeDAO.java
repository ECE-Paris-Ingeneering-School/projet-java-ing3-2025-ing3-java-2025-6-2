package Dao;

import Model.Article;
import Model.Client;
import Model.Commande;
import Model.Panier;

import java.util.List;

public interface CommandeDAO
{
    /**Permet de créer une nouvelle commande*
     * @A partir du client, de son panier er de l'adresse saisie
     */
    public void nouvelleCommande(Client client, String adresse);
    public void ajouterDansCommandeEnCours(Commande commande, Panier panier);
    /** Modifie les informations de la commande (généralement son statut)
     * @Utilise le client concerné
     */
    public void modifierCommande(Client client);
    public void paiementCommande(Commande commande);
    public Commande getCommande(Client client);
    public List<Commande> getCommandes(int limit, int id_commande);
}
