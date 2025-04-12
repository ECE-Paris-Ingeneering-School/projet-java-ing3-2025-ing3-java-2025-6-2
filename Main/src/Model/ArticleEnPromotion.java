package Model;
import View.*;
import Control.*;

public class ArticleEnPromotion extends Article {
    private double pourcentageReduction;
    private String dateFinPromotion;

    public ArticleEnPromotion(int id, String nom, String marque, String categorie, double prixUnitaire, boolean disponibilite, double pourcentageReduction, String dateFinPromotion) {
        super(id, nom, marque, categorie, prixUnitaire, disponibilite);
        this.pourcentageReduction = pourcentageReduction;
        this.dateFinPromotion = dateFinPromotion;
    }

    // Getters et Setters
    public double getPourcentageReduction() { return pourcentageReduction; }
    public void setPourcentageReduction(double pourcentageReduction) { this.pourcentageReduction = pourcentageReduction; }
    public String getDateFinPromotion() { return dateFinPromotion; }
    public void setDateFinPromotion(String dateFinPromotion) { this.dateFinPromotion = dateFinPromotion; }
}