import Control.FenetreControl;
import Model.Fenetres;

public class Main
{
    public static void main(String[] args)
    {
        Fenetres newFenetre = new Fenetres();
        FenetreControl control = new FenetreControl(newFenetre);
        newFenetre.setControleur(control);
        /// Configuration des fenêtres
        newFenetre.setIdentification();
        newFenetre.setInscrire();
        newFenetre.setAccueil();
        newFenetre.setEvent();
        newFenetre.setPaiement();
        newFenetre.setNewArticle();
        newFenetre.setCatalogue();
        newFenetre.setListeArticles();
        newFenetre.setNewCommande();
        newFenetre.connecter.setVisible(true);
    }
}