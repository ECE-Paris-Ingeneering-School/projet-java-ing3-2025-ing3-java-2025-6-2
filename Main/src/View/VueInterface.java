package View;

import Model.Administrateur;
import Model.Client;

public class VueInterface
{
    public void afficherInterface(Client client)
    {
        System.out.println("Bonjour "+client.getNom());
        // Ajouter ici les fonctionnalités de l'interface client
    }

    public void afficherInterface(Administrateur admin)
    {
        System.out.println("Bonjour "+admin.getNom());
        // Ajouter ici les fonctionnalités de l'interface administrateur
    }
}
