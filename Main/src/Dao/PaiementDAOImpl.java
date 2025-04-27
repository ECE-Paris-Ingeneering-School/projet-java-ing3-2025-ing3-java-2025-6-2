package Dao;

import Model.Client;
import Model.Commande;
import Model.Paiement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaiementDAOImpl implements PaiementDAO
{
    private DaoFactory daoFactory;

    public PaiementDAOImpl(DaoFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public void insertionPaiement(Client client, String moyen)
    {
        try
        {
            /// Récupération de la commande en cours et ajout du paiement
            CommandeDAOImpl commandeDAOImpl = new CommandeDAOImpl(daoFactory);
            Commande commande = commandeDAOImpl.getCommande(client);
            Connection connexion = daoFactory.getConnection();
            int id_paiement = new Random().nextInt(1_000_000_000);
            PreparedStatement ps = connexion.prepareStatement(
                    "INSERT INTO paiement(id_paiement, id_utilisateur, montant, moyen, statut) VALUES ('"+id_paiement+"','"+client.getIdentifiant()+"', '"+commande.getPrixTotal()+"', '"+moyen+"', 'payée')"
            );
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Ajout du paiement impossible");
        }
    }

    public List<Paiement> getPaiementsUtilisateur(Client client)
    {
        List<Paiement> paiements = new ArrayList<>(); /// Liste des paiements à récupérer
        try {
            CommandeDAOImpl commandeDAOImpl = new CommandeDAOImpl(daoFactory);
            Commande commande = commandeDAOImpl.getCommande(client);
            Connection connexion = daoFactory.getConnection();
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT id_paiement, date_commande, montant, moyen, statut FROM paiement WHERE id_utilisateur = '"+client.getIdentifiant()+"' ORDER BY date_commande DESC"
            );
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paiements.add(new Paiement(
                        rs.getInt("id_paiement"),
                        commande,
                        rs.getFloat("montant"),
                        rs.getString("statut"),
                        rs.getString("moyen"),
                        rs.getString("date_commande")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paiements;
    }
}
