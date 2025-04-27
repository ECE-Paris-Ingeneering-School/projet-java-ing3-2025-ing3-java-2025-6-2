package Dao;

import Model.Article;
import Model.Client;
import Model.Commande;
import Model.Panier;

import java.util.List;

/** Gestion des commandes et de la table associée
 * @Par Andy et Octave
 * */
public interface CommandeDAO
{
    /**Permet de créer une nouvelle commande
     * @param client Client concerné
     * @param adresse Adresse saisie
     * @return Nouvelle commande créée
     */
    public Commande nouvelleCommande(Client client, String adresse);

    /** Ajoute les articles du panier dans la commande en cours
     * @param commande Commande nouvellement créée
     * @param panier Le dernier panier enregistré
     * */
    public void ajouterDansCommandeEnCours(Commande commande, Panier panier);

    /** Modifie les informations de la commande (généralement son statut)
     * @param client Le client passant commande
     */
    public void modifierCommande(Client client);

    /** Met à jour le statut de la commande
     * @param commande La commande nouvellement créée et traitée
     * */
    public void paiementCommande(Commande commande);

    /** Récupère une commande spécifique
     * @param client Le client connecté
     * @return La commande recherchée
     * */
    public Commande getCommande(Client client);

    public List<Commande> getCommandes(int limit, int id_commande);
}
