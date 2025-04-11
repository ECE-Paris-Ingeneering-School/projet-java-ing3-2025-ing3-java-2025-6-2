package View;
import Model.*;
import Control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/** Fenetres constitue l'ensemble des interfaces graphiques utilisées au cour du projet
 * @L'ensemble des liens entre elles sont effectuées ici
 */
public class Fenetres implements ActionListener
{
    JFrame inscrire, connecter, accueil, profil, event, paiement; /// Fenêtres de navigation
    TextField affichage, nom, prenom, mail, mdp, mail_id, mdp_id, numero_carte, expiration_carte, cvv; /// Zones de saisie
    private String surname, name, email, password; /// Inscription dans la database
    JComboBox<String> payment_type; /// Choix du mode de paiement
    private String payment, num_card, exp_date, cvv_number; /// Effectuer le paiement

    /// Constructeur de chaque fenêtre
    public Fenetres()
    {
        setIdentification();
        setInscrire();
        setAccueil();
        setProfil();
        setEvent();
        setPaiement();
    }

    /// Fenêtre inscription utilisateur
    public void setInscrire()
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

    /// Fenêtre connexion utilisateur
    public void setIdentification()
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

    /// Fenêtre page d'accueil
    public void setAccueil()
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

    /// Fenêtre vue du profil utilisateur
    public void setProfil()
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

    /// Gestion des erreurs
    public void setEvent()
    {
        event = new JFrame();
        event.setSize(300, 100);
        event.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /// Paiement d'un article
    public void setPaiement()
    {
        paiement = new JFrame();
        paiement.setSize(900, 700);
        paiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paiement.setTitle("Paiement");
        paiement.setLayout(new BoxLayout(paiement.getContentPane(), BoxLayout.Y_AXIS));
        JPanel paiement_text = new JPanel();
        JLabel label1 = new JLabel("Moyen de paiement");
        paiement_text.add(label1);
        String[] moyen_paiement = {"Visa", "Mastercard", "American Express", "PayPal"}; /// Liste des moyens de paiement
        payment_type = new JComboBox<>(moyen_paiement); /// Liste associée
        payment_type.setSelectedIndex(0);
        payment_type.setBounds(50, 50, 100, 20);
        paiement_text.add(payment_type);
        JPanel num_carte = new JPanel();
        JLabel label2 = new JLabel("Numero de carte");
        num_carte.add(label2);
        numero_carte = new TextField(10);
        num_carte.add(numero_carte);
        JPanel expiration = new JPanel();
        JLabel label3 = new JLabel("Date d'expiration");
        expiration.add(label3);
        expiration_carte = new TextField(10);
        expiration.add(expiration_carte);
        JPanel cvv_text = new JPanel();
        JLabel label4 = new JLabel("CVV");
        cvv_text.add(label4);
        cvv = new TextField(10);
        cvv_text.add(cvv);
        JPanel paiement_button = new JPanel();
        addButton(paiement_button, "Valider et payer");
        paiement.add(paiement_text);
        paiement.add(num_carte);
        paiement.add(expiration);
        paiement.add(cvv_text);
        paiement.add(paiement_button);
    }

    /// Premier affichage au démarrage (gestion ensuite par les boutons)
    public void affichage()
    {
        paiement.setVisible(true);
    }

    /// Ajout d'un bouton sur une page
    private void addButton(JPanel panel, String label)
    {
        JButton button = new JButton(label);
        button.addActionListener(this);
        panel.add(button);
    }

    /// Action des boutons de chaque fenêtre
    @Override
    public void actionPerformed(ActionEvent e)
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
                        event.getContentPane().removeAll(); /// Retire le contenu de la page event
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
                    event.getContentPane().removeAll(); /// Retire le contenu de la page event
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
            case "Valider et payer":
                payment = (String) payment_type.getSelectedItem();
                num_card = numero_carte.getText();
                exp_date = expiration_carte.getText();
                cvv_number = cvv.getText();
                System.out.println("Paiement : " + payment +" Numero de carte : " + num_card + " Date d'expiration : " + exp_date + " Numero CVV : " + cvv_number);
            case "Retour":
                if(inscrire.isVisible())
                    connecter.setVisible(true);
                event.setVisible(false);
        }
    }
}
