import java.util.HashMap;
import java.util.Map;

public class Panier {
    private Client client;
    private Map<Article, Integer> articles; // Article et quantité

    public Panier(Client client) {
        this.client = client;
        this.articles = new HashMap<>();
    }

    public void ajouterArticle(Article article, int quantite) {
        articles.put(article, quantite);
    }

    public void supprimerArticle(Article article) {
        articles.remove(article);
    }

    public double calculerPrixTotal() {
        double total = 0;
        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            total += entry.getKey().getPrixUnitaire() * entry.getValue();
        }
        return total;
    }

    // Getters et Setters
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Map<Article, Integer> getArticles() { return articles; }
    public void setArticles(Map<Article, Integer> articles) { this.articles = articles; }
}