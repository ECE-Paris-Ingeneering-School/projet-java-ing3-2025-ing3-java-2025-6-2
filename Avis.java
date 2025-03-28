public class Avis {
    private int id;
    private Client client;
    private Article article;
    private String commentaire;
    private int note;
    private String date;

    public Avis(int id, Client client, Article article, String commentaire, int note, String date) {
        this.id = id;
        this.client = client;
        this.article = article;
        this.commentaire = commentaire;
        this.note = note;
        this.date = date;
    }

    public void ajouterAvis(String commentaire, int note) {
        this.commentaire = commentaire;
        this.note = note;
    }

    public void modifierAvis(String nouveauCommentaire, int nouvelleNote) {
        this.commentaire = nouveauCommentaire;
        this.note = nouvelleNote;
    }

    public void supprimerAvis() {
        this.commentaire = "";
        this.note = 0;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public int getNote() { return note; }
    public void setNote(int note) { this.note = note; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}