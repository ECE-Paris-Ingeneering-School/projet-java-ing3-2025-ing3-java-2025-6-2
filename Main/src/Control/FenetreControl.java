package Control;

import Dao.ArticleDAOImpl;
import Dao.DaoFactory;
import Dao.UtilisateurDAOImpl;
import Model.Article;
import Model.Utilisateurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import Model.Fenetres;

public class FenetreControl extends JFrame implements ActionListener
{
    private Fenetres fenetre;

    public FenetreControl(Fenetres fenetre)
    {
        this.fenetre = fenetre;
    }

    /// Action des boutons de chaque fenêtre
    @Override
    public void actionPerformed(ActionEvent e)
    {
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
        ArticleDAOImpl artdao = new ArticleDAOImpl(dao);
        JButton button = (JButton) e.getSource();
        /// Préparation fenêtre évènement
        JLabel label_event;
        JPanel erreur_text;
        switch(button.getText())
        {
            case "Valider":
                if(fenetre.connecter.isVisible())
                {
                    fenetre.email = fenetre.mail_id.getText();
                    fenetre.password = fenetre.mdp_id.getText();
                    System.out.println(fenetre.email);
                    System.out.println(fenetre.password);
                    Utilisateurs user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                    Utilisateurs connect = userdao.connexionUtilisateur(user);
                    if (connect == null)
                    {
                        fenetre.event.getContentPane().removeAll(); /// Retire le contenu de la page event
                        erreur_text = new JPanel();
                        fenetre.event.setTitle("Erreur");
                        label_event = new JLabel("Erreur pendant la tentative de connexion");
                        erreur_text.add(label_event);
                        JPanel erreur_button = new JPanel();
                        fenetre.addButton(erreur_button, "Retour");
                        fenetre.event.add(erreur_text, BorderLayout.CENTER);
                        fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                        fenetre.event.setVisible(true);
                    }
                    else
                    {
                        fenetre.setProfil();
                        fenetre.accueil.setVisible(true);
                        fenetre.connecter.setVisible(false);
                    }
                }
                else if (fenetre.inscrire.isVisible())
                {
                    int id = new Random().nextInt();
                    String nom = fenetre.nom.getText();
                    String prenom = fenetre.prenom.getText();
                    String mail = fenetre.mail.getText();
                    String mot_de_passe = fenetre.mdp.getText();
                    String type_compte = (String) fenetre.type_compte.getSelectedItem();
                    Utilisateurs new_user = new Utilisateurs(id, nom, prenom, mail, mot_de_passe, type_compte);
                    userdao.ajouterUtilisateur(new_user);
                    fenetre.inscrire.setVisible(false);
                    fenetre.event.getContentPane().removeAll();
                    fenetre.event.setTitle("Inscription");
                    JPanel text = new JPanel();
                    label_event = new JLabel("Inscription terminee");
                    text.add(label_event);
                    JPanel erreur_button = new JPanel();
                    fenetre.addButton(erreur_button, "Retour");
                    fenetre.event.add(text, BorderLayout.CENTER);
                    fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                    fenetre.connecter.setVisible(true);
                    fenetre.event.setVisible(true);
                }
                break;
            case "Valider l'ajout":
                int id_article = new Random().nextInt();
                String nom = fenetre.nom_article.getText();
                String descriptionText = fenetre.description.getText();
                float prix = Float.parseFloat(fenetre.prix.getText());
                int stock = Integer.parseInt(fenetre.stock.getText());
                int seuil_remise = Integer.parseInt(fenetre.seuil_remise.getText());
                String categorie = (String) fenetre.categorie.getSelectedItem();
                String marque = (String) fenetre.marque.getSelectedItem();
                Article new_article = new Article(id_article, stock, seuil_remise, nom, marque, categorie, descriptionText, prix, true);
                artdao.ajouterArticle(new_article);
                fenetre.ajout_article.setVisible(false);
                fenetre.event.getContentPane().removeAll();
                fenetre.event.setTitle("Ajout article");
                JPanel text = new JPanel();
                label_event = new JLabel("Ajout effectué");
                text.add(label_event);
                JPanel erreur_button = new JPanel();
                fenetre.addButton(erreur_button, "Retour");
                fenetre.event.add(text, BorderLayout.CENTER);
                fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                fenetre.profil.setVisible(true);
                fenetre.event.setVisible(true);
                break;
            case "Inscrire":
                fenetre.inscrire.setVisible(true);
                fenetre.connecter.setVisible(false);
                break;
            case "Profil":
                fenetre.profil.setVisible(true);
                break;
            case "Accueil":
                fenetre.accueil.setVisible(true);
                fenetre.connecter.setVisible(false);
                fenetre.profil.setVisible(false);
                fenetre.inscrire.setVisible(false);
                break;
            case "Ajouter article":
                fenetre.ajout_article.setVisible(true);
                break;
            case "Deconnexion":
                fenetre.connecter.setVisible(true);
                fenetre.accueil.setVisible(false);
                fenetre.inscrire.setVisible(false);
                fenetre.profil.setVisible(false);
                fenetre.catalogue.setVisible(false);
                break;
            case "Connexion" :
                fenetre.connecter.setVisible(true);
                fenetre.inscrire.setVisible(false);
                break;
            case "Catalogue" :
                fenetre.catalogue.setVisible(true);
                break;
            case "Valider et payer":
                String paiement = (String) fenetre.payment_type.getSelectedItem();
                String numero_carte = fenetre.numero_carte.getText();
                String exp_date = fenetre.expiration_carte.getText();
                int cvv = Integer.parseInt(fenetre.cvv.getText());
                System.out.println("Paiement : " + paiement +" Numero de carte : " + numero_carte + " Date d'expiration : " + exp_date + " Numero CVV : " + cvv);
                break;

            case "Voir":
                JOptionPane.showMessageDialog(this,
                        "Détails du produit:\n" +
                                "Nom: " + fenetre.nom + "\n" +
                                "Propriétaire: " +   "\n" +
                                "Prix: " +    " €\n" +
                                "Stock: " +   " unités");
                break;
            case "Retour":
                if(fenetre.inscrire.isVisible())
                    fenetre.connecter.setVisible(true);
                else if(fenetre.ajout_article.isVisible())
                    fenetre.profil.setVisible(true);
                fenetre.ajout_article.setVisible(false);
                fenetre.event.setVisible(false);
                fenetre.catalogue.setVisible(false);
                break;
        }
    }
}
