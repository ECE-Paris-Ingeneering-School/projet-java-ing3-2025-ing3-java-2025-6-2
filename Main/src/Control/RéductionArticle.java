package Control;
import View.*;
import Model.*;

public class RéductionArticle extends Réduction {
    private Article article;

    public RéductionArticle(int id, double pourcentage, String dateDebut, String dateFin, Article article) {
        super(id, pourcentage, dateDebut, dateFin);
        this.article = article;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Control.Réduction sur l'article: " + article.getNom() + ", Pourcentage: " + getPourcentage() + "%, Valide jusqu'au: " + getDateFin());
    }

    // Getters et Setters
    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
}