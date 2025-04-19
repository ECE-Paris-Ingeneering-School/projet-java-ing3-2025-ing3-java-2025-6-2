package Dao;

import Model.Commande;

import java.util.List;

public interface CommandeDAO
{
    public void paiementCommande(Commande commande);
    public List<Commande> getCommandes(int limit, int id_commande);
}
