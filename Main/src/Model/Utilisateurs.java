package Model;
import View.*;
import Control.*;

public class Utilisateurs
{
    private int identifiant;
    private String nom, prenom, email, motDePasse, type_utilisateur;

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
}