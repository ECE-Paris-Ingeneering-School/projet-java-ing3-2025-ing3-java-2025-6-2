package Control;
import View.*;
import Model.*;

public class Réduction {
    private int id;
    private double pourcentage;
    private String dateDebut;
    private String dateFin;

    public Réduction(int id, double pourcentage, String dateDebut, String dateFin) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public void afficherDetails() {
        System.out.println("Control.Réduction: " + pourcentage + "%, Valide du " + dateDebut + " au " + dateFin);
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getPourcentage() { return pourcentage; }
    public void setPourcentage(double pourcentage) { this.pourcentage = pourcentage; }
    public String getDateDebut() { return dateDebut; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
    public String getDateFin() { return dateFin; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }
}