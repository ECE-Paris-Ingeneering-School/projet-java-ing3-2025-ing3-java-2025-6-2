public class ArticleEnVrac extends Article {
    private double prixEnVrac;
    private int quantiteEnVrac;

    public ArticleEnVrac(int id, String nom, String marque, String categorie, double prixUnitaire, boolean disponibilite, double prixEnVrac, int quantiteEnVrac) {
        super(id, nom, marque, categorie, prixUnitaire, disponibilite);
        this.prixEnVrac = prixEnVrac;
        this.quantiteEnVrac = quantiteEnVrac;
    }

    @Override
    public void afficherDetails() {
        super.afficherDetails();
        System.out.println("Prix en vrac: " + prixEnVrac + ", Quantité en vrac: " + quantiteEnVrac);
    }

    // Getters et Setters
    public double getPrixEnVrac() { return prixEnVrac; }
    public void setPrixEnVrac(double prixEnVrac) { this.prixEnVrac = prixEnVrac; }
    public int getQuantiteEnVrac() { return quantiteEnVrac; }
    public void setQuantiteEnVrac(int quantiteEnVrac) { this.quantiteEnVrac = quantiteEnVrac; }
}