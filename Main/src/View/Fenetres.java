package View;
import Model.*;
import Control.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Dao.*;

/** Fenetres constitue l'ensemble des interfaces graphiques utilisées au cour du projet
 * @L'ensemble des liens entre elles sont effectuées ici
 */
public class Fenetres extends Component implements ActionListener
{
    JFrame inscrire, connecter, accueil, profil, event, paiement, catalogue; /// Fenêtres de navigation
    TextField nom, prenom, mail, mdp, mail_id, mdp_id, numero_carte, expiration_carte, cvv; /// Zones de saisie
    private String account_type, surname, name, email, password; /// Inscription dans la database
    JComboBox<String> type_compte, payment_type; /// Choix du mode de paiement et du type de compte
    private String payment, num_card, exp_date, cvv_number; /// Effectuer le paiement
    private JPanel PTitre, Liste, PannelRetour;
    private JButton Retour;
    private JLabel titrre;
    private JScrollPane Scroll;
    private Random random = new Random();

    /// Constructeur de chaque fenêtre
    public Fenetres()
    {
        setIdentification();
        setInscrire();
        setAccueil();
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
        JLabel label1 = new JLabel("Type de compte");
        inscrire.add(label1);
        String[] type = {"admin", "client"};
        type_compte = new JComboBox<>(type); /// Liste associée
        type_compte.setSelectedIndex(0);
        type_compte.setBounds(50, 50, 100, 20);
        inscrire.add(type_compte);
        JPanel inscrire_nom = new JPanel();
        JLabel label2 = new JLabel("Nom");
        inscrire_nom.add(label2);
        JPanel inscrire_button = new JPanel();
        nom = new TextField(10);
        prenom = new TextField(10);
        mail = new TextField(10);
        mdp = new TextField(10);
        inscrire_nom.add(nom);
        JPanel inscrire_prenom = new JPanel();
        JLabel label3 = new JLabel("Prenom");
        inscrire_prenom.add(label3);
        inscrire_prenom.add(prenom);
        JLabel label4 = new JLabel("Mail");
        JPanel inscrire_mail = new JPanel();
        inscrire_mail.add(label4);
        inscrire_mail.add(mail);
        JLabel label5 = new JLabel("Mot de passe");
        JPanel inscrire_mdp = new JPanel();
        inscrire_mdp.add(label5);
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
        /// Appel de la requête connexion pour utiliser le profil actuel
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
        Utilisateurs user = new Utilisateurs(0, "", "", email, password, "");
        Utilisateurs user_actuel = userdao.connexionUtilisateur(user);
        /// Création de la page avec les informations correspondantes
        profil = new JFrame();
        profil.setSize(900, 700);
        profil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profil.setTitle("Profil");
        profil.setLayout(new BoxLayout(profil.getContentPane(), BoxLayout.Y_AXIS));
        JPanel profil_text = new JPanel();
        JLabel label1 = new JLabel("Bonjour " + user_actuel.getNom() + " " +user_actuel.getPrenom());
        profil_text.add(label1);
        JPanel profil_button = new JPanel();
        JLabel label2 = new JLabel("Vous êtes un "+user_actuel.getType_utilisateur());
        profil_text.add(label2);
        addButton(profil_button, "Accueil");
        addButton(profil_button, "Catalogue");
        /// Cas pour un administrateur (gérer les articles, les rabais, les dossiers clients, statistiques)
        if(user_actuel.getType_utilisateur().equals("admin"))
        {
            addButton(profil_button, "Ajouter article");
            addButton(profil_button, "Modifier un article");
            addButton(profil_button, "Gerer les dossiers clients");
            addButton(profil_button, "Statistiques");
        }
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
        connecter.setVisible(true);
    }

    /// Ajout d'un bouton sur une page
    private void addButton(JPanel panel, String label)
    {
        JButton button = new JButton(label);
        button.addActionListener(this);
        panel.add(button);
    }

    public void setCatalogue()
    {
        catalogue = new JFrame();
        catalogue.setTitle("Catalogue");
        catalogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        catalogue.setSize(1200, 800);
        catalogue.setLocationRelativeTo(null);
        catalogue.setLayout(new BorderLayout());

        Titre();
        Produits();
        creerRetour();

        catalogue.add(PTitre, BorderLayout.NORTH);
        catalogue.add(Scroll, BorderLayout.CENTER);
        catalogue.add(PannelRetour, BorderLayout.SOUTH);
    }

    private void Titre() {
        PTitre = new JPanel();
        PTitre.setPreferredSize(new Dimension(getWidth(), 100));
        PTitre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titrre = new JLabel("Catalogue", SwingConstants.CENTER);
        titrre.setFont(new Font("Arial", Font.BOLD, 32));
        PTitre.setLayout(new BorderLayout());
        PTitre.add(titrre, BorderLayout.CENTER);
    }

    private void Produits() {
        Liste = new JPanel();
        Liste.setBackground(Color.WHITE);
        Liste.setLayout(new GridLayout(0, 3, 20, 20)); // 3 colonnes, espacement 20px

        // Noms et prix aléatoires pour l'exemple
        String[] produits = {"Rétroviseur", "Pied de Table", "Coussin", "Nid d'Abeille", "Larme", "Guillotine", "Vent", "Argent"};
        String[] proprietaires = {"Jean Pard", "Pierre Moulin", "Akim Lemahfouf", "Vitalie Pristine", "Maggie Smith", "Bernard Arnaud", "Elon Musk", "Elizabeth II"};

        for (int i = 1; i <= 12; i++) {
            JPanel PanelProduits = CreerListe(
                    produits[random.nextInt(produits.length)],
                    proprietaires[random.nextInt(proprietaires.length)],
                    random.nextInt(900) + 100, // Prix entre 100 et 1000
                    random.nextInt(50) // Stock entre 0 et 50
            );
            Liste.add(PanelProduits);
        }

        Scroll = new JScrollPane(Liste);
        Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private JPanel CreerListe(String nom, String proprietaire, int prix, int stock) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // 1. Titre du produit
        JLabel titleLabel = new JLabel(nom, SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(titleLabel);

        p.add(Box.createRigidArea(new Dimension(0, 10))); // Espace

        // 2. Propriétaire
        JLabel ownerLabel = new JLabel("Propriétaire: " + proprietaire);
        ownerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(ownerLabel);

        // 3. Prix
        JLabel priceLabel = new JLabel("Prix: " + prix + " €");
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(priceLabel);

        // 4. Stock
        JLabel stockLabel = new JLabel("Stock: " + stock + " unités");
        stockLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(stockLabel);

        p.add(Box.createRigidArea(new Dimension(0, 15))); // Espace

        // 5. Image
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(300, 200));
        imagePanel.setBackground(random.nextBoolean() ? new Color(70, 130, 180) : new Color(220, 60, 60));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(imagePanel);

        p.add(Box.createRigidArea(new Dimension(0, 20))); // Espace

        // 6. Voir
        JButton Voir = new JButton("Voir");
        Voir.setAlignmentX(Component.CENTER_ALIGNMENT);
        Voir.setPreferredSize(new Dimension(120, 40));
        Voir.setMaximumSize(new Dimension(120, 40));

        p.add(Voir);

        return p;
    }

    private void creerRetour() {
        PannelRetour = new JPanel();
        PannelRetour.setBackground(new Color(240, 240, 240));
        PannelRetour.setPreferredSize(new Dimension(getWidth(), 100));
        PannelRetour.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Retour = new JButton("Retour");
        Retour.setPreferredSize(new Dimension(150, 50));
        PannelRetour.setLayout(new GridBagLayout());
        PannelRetour.add(Retour);
    }

    /// Action des boutons de chaque fenêtre
    @Override
    public void actionPerformed(ActionEvent e)
    {
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
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
                    Utilisateurs user = new Utilisateurs(0, "", "", email, password, "");
                    Utilisateurs connect = userdao.connexionUtilisateur(user);
                    if (connect == null)
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
                        setProfil();
                        setCatalogue();
                        accueil.setVisible(true);
                        connecter.setVisible(false);
                    }
                }
                else if (inscrire.isVisible())
                {
                    int id = new Random().nextInt();
                    surname = nom.getText();
                    name = prenom.getText();
                    email = mail.getText();
                    password = mdp.getText();
                    account_type = (String) type_compte.getSelectedItem();
                    Utilisateurs new_user = new Utilisateurs(id, surname, name, email, password, account_type);
                    System.out.println(surname + " " + name + " " + email + " " + password);
                    userdao.ajouterUtilisateur(new_user);
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
                break;
            case "Profil":
                profil.setVisible(true);
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
                catalogue.setVisible(false);
                break;
            case "Connexion" :
                connecter.setVisible(true);
                inscrire.setVisible(false);
                break;
            case "Catalogue" :
                catalogue.setVisible(true);
                break;
            case "Valider et payer":
                payment = (String) payment_type.getSelectedItem();
                num_card = numero_carte.getText();
                exp_date = expiration_carte.getText();
                cvv_number = cvv.getText();
                System.out.println("Paiement : " + payment +" Numero de carte : " + num_card + " Date d'expiration : " + exp_date + " Numero CVV : " + cvv_number);
                break;

            case "Voir":
                JOptionPane.showMessageDialog(this,
                        "Détails du produit:\n" +
                                "Nom: " + nom + "\n" +
                                "Propriétaire: " +   "\n" +
                                "Prix: " +    " €\n" +
                                "Stock: " +   " unités");
                break;
            case "Retour":
                if(inscrire.isVisible())
                    connecter.setVisible(true);
                event.setVisible(false);
                if(catalogue.isVisible())
                    catalogue.setVisible(false);
                break;
        }
    }
}
