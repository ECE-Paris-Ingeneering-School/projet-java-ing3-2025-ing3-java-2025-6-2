import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Création d'un client de test
        Client testClient = new Client("TestClient1", "client123", "Alara TANGUY", "alaratanguy@example.com");

        // Création d'un administrateur
        Administrateur admin = new Administrateur("Admin", "admin123", "Admin", "admin@example.com");

        // Initialisation de Scanner pour lire l'entrée de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Demander les identifiants
        System.out.print("Entrez votre identifiant: ");
        String identifiant = scanner.nextLine();
        System.out.print("Entrez votre mot de passe: ");
        String motDePasse = scanner.nextLine();

        // Vérification des identifiants
        if (identifiant.equals(testClient.getIdentifiant()) && motDePasse.equals(testClient.getMotDePasse())) {
            testClient.afficherInterfaceClient();
        } else if (identifiant.equals(admin.getIdentifiant()) && motDePasse.equals(admin.getMotDePasse())) {
            admin.afficherInterfaceAdministrateur();
        } else {
            System.out.println("Identifiant ou mot de passe incorrect.");
        }

        // Fermer le scanner
        scanner.close();
    }
}

// Test