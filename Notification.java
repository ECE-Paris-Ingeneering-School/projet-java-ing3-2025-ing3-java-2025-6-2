public class Notification {
    private int id;
    private Client client;
    private String message;
    private String date;
    private boolean lu;

    public Notification(int id, Client client, String message, String date) {
        this.id = id;
        this.client = client;
        this.message = message;
        this.date = date;
        this.lu = false;
    }

    public void envoyerNotification(String message) {
        this.message = message;
        this.lu = false;
    }

    public void marquerCommeLu() {
        this.lu = true;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public boolean isLu() { return lu; }
    public void setLu(boolean lu) { this.lu = lu; }
}