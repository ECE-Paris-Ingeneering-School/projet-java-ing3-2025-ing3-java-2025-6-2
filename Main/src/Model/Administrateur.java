package Model;

import java.util.HashMap;
import java.util.Map;


/// Administrateur de l'application
public class Administrateur extends Utilisateurs {

    public Administrateur(String identifiant, String motDePasse, String nom, String email)
    {
        super(identifiant, motDePasse, nom, email);
    }

    public void ajouterArticle(Article article) {
        // Implémentation de l'ajout d'article
    }

    public void modifierArticle(Article article) {
        // Implémentation de la modification d'article
    }

    public void supprimerArticle(Article article) {
        // Implémentation de la suppression d'article
    }

    public void ajouterReduction(Réduction reduction) {
        // Implémentation de l'ajout de réduction
    }

    public Map<String, Double> consulterStatistiquesVentes() {
        // Implémentation de la consultation des statistiques
        return new HashMap<>();
    }
}