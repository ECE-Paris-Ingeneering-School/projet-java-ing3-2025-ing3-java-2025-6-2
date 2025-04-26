package Model;
import View.*;
import Control.*;

public class Utilisateurs
{
    private int identifiant, nbCommandes;
    private float TotalAchats;
    private String nom, prenom, email, motDePasse, type_utilisateur, NiveauFidelite;

    public Utilisateurs(int identifiant, String nom, String prenom, String email, String motDePasse, String type_utilisateur)
    {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.type_utilisateur = type_utilisateur;
    }

    // Getters et Setters
    public int getIdentifiant()
    {
        return identifiant;
    }

    public String getNom()
    {
        return nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public String getEmail()
    {
        return email;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public String getType_utilisateur()
    {
        return type_utilisateur;
    }

    public void setNbCommandes(int nbCommandes) {
        this.nbCommandes = nbCommandes;
    }

    public int getNbCommandes() {
        return nbCommandes;
    }

    public float getTotalAchats() {
        return TotalAchats;
    }

    public void setTotalAchats(float totalAchats) {
        this.TotalAchats = totalAchats;
    }

    public String getNiveauFidelite() {
        return NiveauFidelite;
    }
    public void setNiveauFidelite(String niveauFidelite) {
        this.NiveauFidelite = niveauFidelite;
    }
}