package Dao;

import Model.Client;
import Model.Paiement;

import java.util.List;

/** Gestion des paiements et de la table associée
 * @Par Andy
 * */
public interface PaiementDAO
{
    /**Ajout d'un nouveau paiement
     * @param client Le client passant commande
     * @param moyen Le moyen de paiement utilisé
     * */
    public void insertionPaiement(Client client, String moyen);

    /**Récupère les paiements effectués
     * @param client Le client connecté
     * @return L'ensemble des paiements réalisés par un client
     * */
    public List<Paiement> getPaiementsUtilisateur(Client client);
}
