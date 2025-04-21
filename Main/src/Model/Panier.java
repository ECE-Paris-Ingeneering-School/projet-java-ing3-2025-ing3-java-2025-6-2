package Model;

import java.util.HashMap;
import java.util.Map;

public class Panier
{
    private int id;
    private Client client;
    private Map<Article, Integer> articles; // Model.Article et quantité

    public Panier(int id, Client client)
    {
        this.id = id;
        this.client = client;
        this.articles = new HashMap<>();
    }

    public void ajouterArticle(Article article, int quantite) {
        articles.put(article, quantite);
    }

    public void supprimerArticle(Article article) {
        articles.remove(article);
    }

    public float calculerPrixTotal()
    {
        float total = 0;
        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            total += entry.getKey().getPrixUnitaire() * entry.getValue();
        }
        return total;
    }

    // Getters et Setters

    public int getId()
    {
        return id;
    }
    public Client getClient()
    {
        return client;
    }
    public void setClient(Client client)
    {
        this.client = client;
    }
    public Map<Article, Integer> getArticles()
    {
        return articles;
    }
    public void setArticles(Map<Article, Integer> articles) { this.articles = articles; }
}