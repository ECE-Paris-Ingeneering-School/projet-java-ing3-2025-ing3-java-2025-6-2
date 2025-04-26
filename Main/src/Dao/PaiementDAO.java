package Dao;

import Model.Client;
import Model.Paiement;

import java.util.List;

public interface PaiementDAO
{
    public void insertionPaiement(Client client, String moyen);
    public List<Paiement> getPaiementsUtilisateur(Client client);
}
