package Model;
import View.*;
import Control.*;

public class Utilisateurs
{
    private String identifiant;
    private String motDePasse;
    private String nom;
    private String email;

    public Utilisateurs(String identifiant, String motDePasse, String nom, String email)
    {
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.email = email;
    }

    public boolean seConnecter(String identifiant, String motDePasse)
    {
        return this.identifiant.equals(identifiant) && this.motDePasse.equals(motDePasse);
    }

    public void seDeconnecter()
    {
        System.out.println("Utilisateur déconnecté.");
    }

    // Getters et Setters
    public String getIdentifiant()
    { return identifiant; }

    public void setIdentifiant(String identifiant)
    { this.identifiant = identifiant; }

    public String getMotDePasse()
    { return motDePasse; }

    public void setMotDePasse(String motDePasse)
    { this.motDePasse = motDePasse; }

    public String getNom()
    { return nom; }

    public void setNom(String nom)
    { this.nom = nom; }

    public String getEmail()
    { return email; }

    public void setEmail(String email)
    { this.email = email; }
}