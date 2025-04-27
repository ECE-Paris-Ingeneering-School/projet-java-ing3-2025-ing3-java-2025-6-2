import Control.FenetreControl;
import Model.Fenetres;

public class Main
{
    public static void main(String[] args)
    {
        Fenetres newFenetre = new Fenetres(); /// L'ensemble des fenêtres
        FenetreControl control = new FenetreControl(newFenetre); /// Contrôle des fenêtres par boutons de chacune d'entre-elles
        newFenetre.setControleur(control); /// Mise en place du contrôleur
        /// Configuration de la fenêtre de login
        newFenetre.setIdentification();
        newFenetre.connecter.setVisible(true);
    }
}