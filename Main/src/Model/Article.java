package Model;

public class Article
{
    private int id, stock, seuil_remise;
    private String nom, marque, categorie, description;
    private float prixUnitaire;
    private boolean disponibilite;
    private String image;

    public Article(int id, int stock, int seuil_remise, String nom, String marque, String categorie, String description, float prixUnitaire, boolean disponibilite)
    {
        this.id = id;
        this.stock = stock;
        this.seuil_remise = seuil_remise;
        this.nom = nom;
        this.marque = marque;
        this.categorie = categorie;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.disponibilite = disponibilite;
        this.image = "Main/src/View/image/default_product.png"; // Default image path
    }

    public boolean verifierDisponibilite(String dates)
    {
        // Implémentation de la vérification de disponibilité
        return disponibilite;
    }

    // Getters et Setters
    public int getId()
    {
        return id;
    }
    public String getNom()
    {
        return nom;
    }
    public String getMarque()
    {
        return marque;
    }
    public String getCategorie()
    {
        return categorie;
    }
    public float getPrixUnitaire()
    {
        return prixUnitaire;
    }
    public boolean getDisponibilite()
    {
        return disponibilite;
    }

    public int getStock()
    {
        return stock;
    }

    public int getSeuil_remise()
    {
        return seuil_remise;
    }

    public String getDescription()
    {
        return description;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
}