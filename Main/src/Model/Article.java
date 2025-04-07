package Model;
import View.*;
import Control.*;

public class Article {
    private int id;
    private String nom;
    private String marque;
    private String categorie;
    private double prixUnitaire;
    private boolean disponibilite;

    public Article(int id, String nom, String marque, String categorie, double prixUnitaire, boolean disponibilite) {
        this.id = id;
        this.nom = nom;
        this.marque = marque;
        this.categorie = categorie;
        this.prixUnitaire = prixUnitaire;
        this.disponibilite = disponibilite;
    }

    public void afficherDetails() {
        System.out.println("ID: " + id + ", Nom: " + nom + ", Marque: " + marque + ", Catégorie: " + categorie + ", Prix: " + prixUnitaire + ", Disponibilité: " + disponibilite);
    }

    public boolean verifierDisponibilite(String dates) {
        // Implémentation de la vérification de disponibilité
        return disponibilite;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public boolean getDisponibilite() { return disponibilite; }
    public void setDisponibilite(boolean disponibilite) { this.disponibilite = disponibilite; }
}