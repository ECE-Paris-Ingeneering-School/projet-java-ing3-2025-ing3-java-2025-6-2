package Model;
import View.*;
import Control.*;

public class ArticleEnPromotion extends Article {
    private double pourcentageReduction;
    private String dateFinPromotion;

    public ArticleEnPromotion(int id, int stock, int seuil_remise, String nom, String marque, String categorie, String description, float prixUnitaire, boolean disponibilite, double pourcentageReduction, String dateFinPromotion) {
        super(id, stock, seuil_remise, nom, marque, categorie, description, prixUnitaire, disponibilite);
        this.pourcentageReduction = pourcentageReduction;
        this.dateFinPromotion = dateFinPromotion;
    }

    // Getters et Setters
    public double getPourcentageReduction() { return pourcentageReduction; }
    public void setPourcentageReduction(double pourcentageReduction) { this.pourcentageReduction = pourcentageReduction; }
    public String getDateFinPromotion() { return dateFinPromotion; }
    public void setDateFinPromotion(String dateFinPromotion) { this.dateFinPromotion = dateFinPromotion; }
}