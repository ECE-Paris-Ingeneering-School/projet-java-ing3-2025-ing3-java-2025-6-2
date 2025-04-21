package Model;

public class RéductionArticle extends Réduction
{
    private Article article;

    public RéductionArticle(int id, double pourcentage, String dateDebut, String dateFin, Article article)
    {
        super(id, pourcentage, dateDebut, dateFin);
        this.article = article;
    }

    // Getters et Setters
    public Article getArticle()
    {
        return article;
    }
    public void setArticle(Article article)
    {
        this.article = article;
    }
}