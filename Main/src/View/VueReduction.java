package View;

import Model.Réduction;
import Model.RéductionArticle;
import Model.RéductionClient;

public class VueReduction
{
    public void afficherDetails(Réduction reduction)
    {
        System.out.println("Model.Réduction: " + reduction.getPourcentage() + "%, Valide du " + reduction.getDateDebut() + " au " + reduction.getDateFin());
    }

    public void afficherDetails(RéductionArticle article)
    {
        System.out.println("Model.Réduction sur l'article: " + article.getArticle().getNom() + ", Pourcentage: " + article.getPourcentage() + "%, Valide jusqu'au: " + article.getDateFin());
    }

    public void afficherDetails(RéductionClient client)
    {
        System.out.println("Model.Réduction pour le client: " + client.getClient().getNom() + ", Pourcentage: " + client.getPourcentage() + "%, Valide jusqu'au: " + client.getDateFin());
    }
}
