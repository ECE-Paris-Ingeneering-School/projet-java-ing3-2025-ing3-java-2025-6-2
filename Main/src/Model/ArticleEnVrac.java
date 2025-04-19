package Model;

public class ArticleEnVrac extends Article {
    private double prixEnVrac;
    private int quantiteEnVrac;

    public ArticleEnVrac(int id, int stock, int seuil_remise, String nom, String marque, String categorie, String description, float prixUnitaire, boolean disponibilite, double prixEnVrac, int quantiteEnVrac) {
        super(id, stock, seuil_remise, nom, marque, categorie, description, prixUnitaire, disponibilite);
        this.prixEnVrac = prixEnVrac;
        this.quantiteEnVrac = quantiteEnVrac;
    }

    // Getters et Setters
    public double getPrixEnVrac() { return prixEnVrac; }
    public void setPrixEnVrac(double prixEnVrac) { this.prixEnVrac = prixEnVrac; }
    public int getQuantiteEnVrac() { return quantiteEnVrac; }
    public void setQuantiteEnVrac(int quantiteEnVrac) { this.quantiteEnVrac = quantiteEnVrac; }
}