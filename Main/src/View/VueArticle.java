package View;

import Model.Article;
import Model.ArticleEnPromotion;
import Model.ArticleEnVrac;

public class VueArticle
{
    public void afficherDetails(Article article)
    {
        System.out.println("ID: " + article.getId() + ", Nom: " + article.getNom() + ", Marque: " + article.getMarque() + ", Catégorie: " + article.getCategorie() + ", Prix: " + article.getPrixUnitaire() + ", Disponibilité: " + article.getDisponibilite());
    }

    public void afficherDetails(Article article, ArticleEnPromotion article_promotion)
    {
        afficherDetails(article);
        System.out.println("Pourcentage de réduction: " + article_promotion.getPourcentageReduction() + ", Date de fin de promotion: " + article_promotion.getDateFinPromotion());
    }

    public void afficherDetails(Article article, ArticleEnVrac article_vrac) {
        afficherDetails(article);
        System.out.println("Prix en vrac: " + article_vrac.getPrixEnVrac() + ", Quantité en vrac: " + article_vrac.getPrixEnVrac());
    }
}
