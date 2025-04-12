package Model;

public class RéductionClient extends Réduction {
    private Client client;

    public RéductionClient(int id, double pourcentage, String dateDebut, String dateFin, Client client) {
        super(id, pourcentage, dateDebut, dateFin);
        this.client = client;
    }

    // Getters et Setters
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}