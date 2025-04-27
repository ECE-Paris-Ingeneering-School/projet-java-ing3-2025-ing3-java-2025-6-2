import Control.FenetreControl;
import Model.Fenetres;

public class Main
{
    public static void main(String[] args)
    {
        String recherche = "";
        Fenetres newFenetre = new Fenetres();
        FenetreControl control = new FenetreControl(newFenetre);
        newFenetre.setControleur(control);
        /// Configuration des fenêtres
        newFenetre.setIdentification();
        newFenetre.setInscrire();
        newFenetre.setAccueil();
        newFenetre.setPaiement();
        newFenetre.setNewArticle();
        newFenetre.setModifArticle();
        newFenetre.setCatalogue( recherche);
        newFenetre.setListeArticles();
        newFenetre.setNewCommande();
        newFenetre.connecter.setVisible(true);
    }
}