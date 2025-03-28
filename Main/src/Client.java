import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Client extends Utilisateurs {
    private boolean statut; // true: ancien, false: nouveau
    private final List<Commande> historiqueDesCommandes;

    public Client(String identifiant, String motDePasse, String nom, String email) {
        super(identifiant, motDePasse, nom, email);
        this.statut = false; // Nouveau client par défaut
        this.historiqueDesCommandes = new ArrayList<>();
    }

    public void ajouterAUPanier(Article article, int quantite) {
        // Implémentation de l'ajout au panier
    }

    public Commande passerCommande() {
        // Implémentation de la commande
        return new Commande(this, new HashMap<>());
    }

    public void afficherInterfaceClient() {
        System.out.println("Bienvenue dans l'interface client!");
        // Ajouter ici les fonctionnalités de l'interface client
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