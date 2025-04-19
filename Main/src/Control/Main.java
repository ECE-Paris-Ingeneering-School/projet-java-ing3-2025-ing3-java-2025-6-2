package Control;

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
        newFenetre.connecter.setVisible(true);
    }
}