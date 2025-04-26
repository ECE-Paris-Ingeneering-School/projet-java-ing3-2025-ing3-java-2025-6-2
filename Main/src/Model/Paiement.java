package Model;

public class Paiement
{
    private int id;
    private Commande commande;
    private float montant;
    private String statut; // en attente, payé, annulé
    private String moyen;
    private String date;

    public Paiement(int id, Commande commande, float montant, String statut, String moyen, String date)
    {
        this.id = id;
        this.commande = commande;
        this.montant = montant;
        this.statut = statut;
        this.moyen = moyen;
        this.date = date;
    }

    public void confirmerPaiement() {
        this.statut = "payé";
    }

    public void annulerPaiement() {
        this.statut = "annulé";
    }

    // Getters et Setters
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public Commande getCommande()
    {
        return commande;
    }
    public void setCommande(Commande commande)
    {
        this.commande = commande;
    }
    public double getMontant() { return montant; }
    public void setMontant(float montant) { this.montant = montant; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getMoyen() {
        return moyen;
    }
}