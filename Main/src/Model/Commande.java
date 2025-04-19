package Model;

import java.util.Map;

public class Commande
{
    private int id;
    private Client client;
    private Map<Article, Integer> articles; // Model.Article et quantité
    private float prixTotal;
    private String date;

    public Commande(int id, Client client, Map<Article, Integer> articles) {
        this.id = id;
        this.client = client;
        this.articles = articles;
        this.prixTotal = calculerPrixTotal();
        this.date = new java.util.Date().toString();
    }

    public float calculerPrixTotal() {
        float total = 0;
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
    public int getId() {
        return id;
    }
    public Map<Article, Integer> getArticles() { return articles; }
    public float getPrixTotal() { return prixTotal; }
    public String getDate() { return date; }
}