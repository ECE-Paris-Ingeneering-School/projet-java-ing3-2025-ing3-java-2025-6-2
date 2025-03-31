import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetres implements ActionListener
{
    JPanel connecter_text, inscrire_text, accueil_text, profil_text, accueil_button, connecter_button, inscrire_button, profil_button;
    JFrame inscrire, connecter, accueil, profil; /// View.Fenetre principale
    TextField affichage, nom, prenom, mail, mdp;
    private double valeur;
    private boolean virgule = false;
    private String op;
    private double nb1, nb2, res;

    public Fenetres() /// Constructeur de chaque fenêtre
    {
        setIdentification();
        setInscrire();
        setAccueil();
        setProfil();
    }

    public void setInscrire() /// Fenêtre inscription utilisateur
    {
        inscrire = new JFrame();
        inscrire.setSize(900, 700);
        inscrire.setTitle("Inscription");
        inscrire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inscrire.setLayout(new BoxLayout(inscrire.getContentPane(), BoxLayout.Y_AXIS));
        inscrire_text = new JPanel();
        JLabel label1 = new JLabel("Nom");
        inscrire_text.add(label1);
        inscrire_button = new JPanel();
        nom = new TextField(10);
        prenom = new TextField(10);
        mail = new TextField(10);
        mdp = new TextField(10);
        inscrire_text.add(nom);
        JLabel label2 = new JLabel("Prenom");
        inscrire_text.add(label2);
        inscrire_text.add(prenom);
        JLabel label3 = new JLabel("Mail");
        inscrire_text.add(label3);
        inscrire_text.add(mail);
        JLabel label4 = new JLabel("Mdp");
        inscrire_text.add(label4);
        inscrire_text.add(mdp);
        addButton(inscrire_button, "Valider l'inscription");
        addButton(inscrire_button, "Connexion");
        inscrire.add(inscrire_text, BorderLayout.CENTER);
        inscrire.add(inscrire_button, BorderLayout.SOUTH);
        inscrire.pack();
    }

    public void setIdentification() /// Fenêtre connexion utilisateur
    {
        connecter = new JFrame();
        connecter.setSize(900, 700);
        connecter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connecter.setTitle("Connexion");
        connecter.setLayout(new BoxLayout(connecter.getContentPane(), BoxLayout.Y_AXIS));
        connecter_text = new JPanel();
        JLabel label1 = new JLabel("Mail");
        connecter_text.add(label1);
        connecter_button = new JPanel();
        mail = new TextField(10);
        mdp = new TextField(10);
        connecter_text.add(mail);
        JLabel label2 = new JLabel("Mdp");
        connecter_text.add(label2);
        connecter_text.add(mdp);
        addButton(connecter_button, "Valider");
        addButton(connecter_button, "Inscrire");
        connecter.add(connecter_text, BorderLayout.CENTER);
        connecter.add(connecter_button, BorderLayout.SOUTH);
        connecter.pack();
    }

    public void setAccueil() /// Fenêtre page d'accueil
    {
        accueil = new JFrame();
        accueil.setSize(900, 700);
        accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accueil.setTitle("Accueil");
        accueil.setLayout(new BoxLayout(accueil.getContentPane(), BoxLayout.Y_AXIS));
        accueil_text = new JPanel();
        JLabel label1 = new JLabel("texte");
        accueil_text.add(label1);
        accueil_button = new JPanel();
        JLabel label2 = new JLabel("texte2");
        accueil_text.add(label2);
        addButton(accueil_button, "Profil");
        addButton(accueil_button, "Catalogue");
        addButton(accueil_button, "Deconnexion");
        accueil.add(accueil_text, BorderLayout.CENTER);
        accueil.add(accueil_button, BorderLayout.SOUTH);
        accueil.pack();
    }

    public void setProfil() /// Fenêtre vue du profil utilisateur
    {
        profil = new JFrame();
        profil.setSize(900, 700);
        profil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profil.setTitle("Profil");
        profil.setLayout(new BoxLayout(profil.getContentPane(), BoxLayout.Y_AXIS));
        profil_text = new JPanel();
        JLabel label1 = new JLabel("texte");
        profil_text.add(label1);
        profil_button = new JPanel();
        JLabel label2 = new JLabel("texte2");
        profil_text.add(label2);
        addButton(profil_button, "Accueil");
        addButton(profil_button, "Catalogue");
        profil.add(profil_text, BorderLayout.CENTER);
        profil.add(profil_button, BorderLayout.SOUTH);
        profil.pack();
    }

    public void affichage() /// Permier affichage au démarrage (gestion ensuite par les boutons)
    {
        connecter.setVisible(true);
    }

    private void addButton(JPanel panel, String label) /// Ajout d'un bouton sur une page
    {
        JButton button = new JButton(label);
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) /// Action des boutons de chaque fenêtre
    {
        JButton button = (JButton) e.getSource();
        switch(button.getText())
        {
            case "Valider":
                accueil.setVisible(true);
                connecter.setVisible(false);
                profil.setVisible(false);
                inscrire.setVisible(false);
                break;
            case "Inscrire":
                inscrire.setVisible(true);
                connecter.setVisible(false);
                profil.setVisible(false);
                accueil.setVisible(false);
                break;
            case "Profil":
                profil.setVisible(true);
                accueil.setVisible(false);
                inscrire.setVisible(false);
                break;
            case "Accueil":
                accueil.setVisible(true);
                connecter.setVisible(false);
                profil.setVisible(false);
                break;
            case "Deconnexion":
                connecter.setVisible(true);
                accueil.setVisible(false);
                inscrire.setVisible(false);
                profil.setVisible(false);
                break;
            case "Connexion" :
                connecter.setVisible(true);
                inscrire.setVisible(false);
                profil.setVisible(false);
                accueil.setVisible(false);
                break;
            case "Valider l'inscription":
                connecter.setVisible(true);
                inscrire.setVisible(false);
                profil.setVisible(false);
                accueil.setVisible(false);
                break;
        }
    }
}
