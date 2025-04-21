package Model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Client extends Utilisateurs
{
    private boolean statut; // true: ancien, false: nouveau
    private final List<Commande> historiqueDesCommandes;

    public Client(int identifiant, String nom, String prenom, String email, String motDePasse, String type_utilisateur)
    {
        super(identifiant, nom, prenom, email, motDePasse, type_utilisateur);
        this.statut = false; // Nouveau client par défaut
        this.historiqueDesCommandes = new ArrayList<>();
    }

    public void ajouterAUPanier(Article article, int quantite) {
        // Implémentation de l'ajout au panier
    }

    public Commande passerCommande() {
        // Implémentation de la commande
        return new Commande(0, this, new HashMap<>());
    }

    // Getters et Setters
    public boolean getStatut() {
        return statut;
    }

    public void setStatut(boolean statut) {
        this.statut = statut;
    }

    public List<Commande> getHistoriqueDesCommandes() {
        return historiqueDesCommandes;
    }
}