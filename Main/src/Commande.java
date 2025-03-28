import java.util.Map;

public class Commande {
    private Client client;
    private Map<Article, Integer> articles; // Article et quantité
    private double prixTotal;
    private String date;

    public Commande(Client client, Map<Article, Integer> articles) {
        this.client = client;
        this.articles = articles;
        this.prixTotal = calculerPrixTotal();
        this.date = new java.util.Date().toString();
    }

    public double calculerPrixTotal() {
        double total = 0;
        for (Map.Entry<Article, Integer> entry : articles.entrySet()) {
            total += entry.getKey().getPrixUnitaire() * entry.getValue();
        }
        return total;
    }

    public void confirmerCommande() {
        // Implémentation de la confirmation de commande
    }

    // Getters et Setters
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Map<Article, Integer> getArticles() { return articles; }
    public void setArticles(Map<Article, Integer> articles) { this.articles = articles; }
    public double getPrixTotal() { return prixTotal; }
    public void setPrixTotal(double prixTotal) { this.prixTotal = prixTotal; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}