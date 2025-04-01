import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetres implements ActionListener
{
    JFrame inscrire, connecter, accueil, profil, event; /// Fenêtres de navigation
    TextField affichage, nom, prenom, mail, mdp, mail_id, mdp_id; /// Zones de saisie
    private String surname, name, email, password;

    public Fenetres() /// Constructeur de chaque fenêtre
    {
        setIdentification();
        setInscrire();
        setAccueil();
        setProfil();
        setEvent();
    }

    public void setInscrire() /// Fenêtre inscription utilisateur
    {
        inscrire = new JFrame();
        inscrire.setSize(900, 700);
        inscrire.setTitle("Inscription");
        inscrire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inscrire.setLayout(new BoxLayout(inscrire.getContentPane(), BoxLayout.Y_AXIS));
        JPanel inscrire_nom = new JPanel();
        JLabel label1 = new JLabel("Nom");
        inscrire_nom.add(label1);
        JPanel inscrire_button = new JPanel();
        nom = new TextField(10);
        prenom = new TextField(10);
        mail = new TextField(10);
        mdp = new TextField(10);
        inscrire_nom.add(nom);
        JPanel inscrire_prenom = new JPanel();
        JLabel label2 = new JLabel("Prenom");
        inscrire_prenom.add(label2);
        inscrire_prenom.add(prenom);
        JLabel label3 = new JLabel("Mail");
        JPanel inscrire_mail = new JPanel();
        inscrire_mail.add(label3);
        inscrire_mail.add(mail);
        JLabel label4 = new JLabel("Mot de passe");
        JPanel inscrire_mdp = new JPanel();
        inscrire_mdp.add(label4);
        inscrire_mdp.add(mdp);
        addButton(inscrire_button, "Valider");
        addButton(inscrire_button, "Connexion");
        inscrire.add(inscrire_nom);
        inscrire.add(inscrire_prenom);
        inscrire.add(inscrire_mail);
        inscrire.add(inscrire_mdp);
        inscrire.add(inscrire_button);
    }

    public void setIdentification() /// Fenêtre connexion utilisateur
    {
        connecter = new JFrame();
        connecter.setSize(900, 700);
        connecter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connecter.setTitle("Connexion");
        connecter.setLayout(new BoxLayout(connecter.getContentPane(), BoxLayout.Y_AXIS));
        JPanel connecter_mail = new JPanel();
        JLabel label1 = new JLabel("Mail");
        connecter_mail.add(label1);
        JPanel connecter_button = new JPanel();
        mail_id = new TextField(10);
        mdp_id = new TextField(10);
        connecter_mail.add(mail_id);
        JLabel label2 = new JLabel("Mot de passe");
        JPanel connecter_mdp = new JPanel();
        connecter_mdp.add(label2);
        connecter_mdp.add(mdp_id);
        addButton(connecter_button, "Valider");
        addButton(connecter_button, "Inscrire");
        connecter.add(connecter_mail);
        connecter.add(connecter_mdp);
        connecter.add(connecter_button);
    }

    public void setAccueil() /// Fenêtre page d'accueil
    {
        accueil = new JFrame();
        accueil.setSize(900, 700);
        accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accueil.setTitle("Accueil");
        accueil.setLayout(new BoxLayout(accueil.getContentPane(), BoxLayout.Y_AXIS));
        JPanel accueil_text = new JPanel();
        JLabel label1 = new JLabel("texte");
        accueil_text.add(label1);
        JPanel accueil_button = new JPanel();
        JLabel label2 = new JLabel("texte2");
        accueil_text.add(label2);
        addButton(accueil_button, "Profil");
        addButton(accueil_button, "Catalogue");
        addButton(accueil_button, "Deconnexion");
        accueil.add(accueil_button);
        accueil.add(accueil_text);
    }

    public void setProfil() /// Fenêtre vue du profil utilisateur
    {
        profil = new JFrame();
        profil.setSize(900, 700);
        profil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profil.setTitle("Profil");
        profil.setLayout(new BoxLayout(profil.getContentPane(), BoxLayout.Y_AXIS));
        JPanel profil_text = new JPanel();
        JLabel label1 = new JLabel("texte");
        profil_text.add(label1);
        JPanel profil_button = new JPanel();
        JLabel label2 = new JLabel("texte2");
        profil_text.add(label2);
        addButton(profil_button, "Accueil");
        addButton(profil_button, "Catalogue");
        profil.add(profil_text);
        profil.add(profil_button);
    }

    public void setEvent() /// Gestion des erreurs
    {
        event = new JFrame();
        event.setSize(300, 100);
        event.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void affichage() /// Premier affichage au démarrage (gestion ensuite par les boutons)
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
        /// Préparation fenêtre évènement
        JLabel label_event;
        JPanel erreur_text;
        switch(button.getText())
        {
            case "Valider":
                if(connecter.isVisible())
                {
                    email = mail_id.getText();
                    password = mdp_id.getText();
                    if (email.equals("") || password.equals(""))
                    {
                        event.getContentPane().removeAll();
                        erreur_text = new JPanel();
                        event.setTitle("Erreur");
                        label_event = new JLabel("Erreur pendant la tentative de connexion");
                        erreur_text.add(label_event);
                        JPanel erreur_button = new JPanel();
                        addButton(erreur_button, "Retour");
                        event.add(erreur_text, BorderLayout.CENTER);
                        event.add(erreur_button, BorderLayout.SOUTH);
                        event.setVisible(true);
                    }
                    else
                    {
                        accueil.setVisible(true);
                        connecter.setVisible(false);
                        profil.setVisible(false);
                        inscrire.setVisible(false);
                    }
                }
                else if (inscrire.isVisible())
                {
                    surname = nom.getText();
                    name = prenom.getText();
                    email = mail.getText();
                    password = mdp.getText();
                    System.out.println(surname + " " + name + " " + email + " " + password);
                    inscrire.setVisible(false);
                    event.getContentPane().removeAll();
                    event.setTitle("Inscription");
                    JPanel text = new JPanel();
                    label_event = new JLabel("Inscription terminee");
                    text.add(label_event);
                    JPanel erreur_button = new JPanel();
                    addButton(erreur_button, "Retour");
                    event.add(text, BorderLayout.CENTER);
                    event.add(erreur_button, BorderLayout.SOUTH);
                    connecter.setVisible(true);
                    event.setVisible(true);
                }
                break;
            case "Inscrire":
                inscrire.setVisible(true);
                connecter.setVisible(false);
                profil.setVisible(false);
                accueil.setVisible(false);
                break;
            case "Profil":
                profil.setVisible(true);
                accueil.setVisible(true);
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
            case "Catalogue" :
                if(profil.isVisible() || accueil.isVisible())
                {
                    event.getContentPane().removeAll();
                    erreur_text = new JPanel();
                    label_event = new JLabel("Erreur pendant la tentative d'affichage");
                    erreur_text.add(label_event);
                    JPanel erreur_button = new JPanel();
                    addButton(erreur_button, "Retour");
                    event.add(erreur_text, BorderLayout.CENTER);
                    event.add(erreur_button, BorderLayout.SOUTH);
                    event.setVisible(true);
                }
                break;
            case "Retour":
                if(inscrire.isVisible())
                    connecter.setVisible(true);
                event.setVisible(false);
        }
    }
}
