package Control;

import Dao.ArticleDAOImpl;
import Dao.DaoFactory;
import Dao.UtilisateurDAOImpl;
import Model.Article;
import Model.Utilisateurs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

import View.Fenetres;

public class FenetreControl extends JFrame
{
    /// Action des boutons de chaque fenêtre
    public void control(ActionEvent e)
    {
        Fenetres fenetres = new Fenetres();
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
        ArticleDAOImpl artdao = new ArticleDAOImpl(dao);
        JButton button = (JButton) e.getSource();
        System.out.println(button.getText());
        /// Préparation fenêtre évènement
        JLabel label_event;
        JPanel erreur_text;
        switch(button.getText())
        {
            case "Valider":
                System.out.println(fenetres.connecter.isVisible());
                if(fenetres.connecter.isVisible())
                {
                    fenetres.email = fenetres.mail_id.getText();
                    fenetres.password = fenetres.mdp_id.getText();
                    System.out.println(fenetres.email);
                    System.out.println(fenetres.password);
                    Utilisateurs user = new Utilisateurs(0, "", "", fenetres.email, fenetres.password, "");
                    Utilisateurs connect = userdao.connexionUtilisateur(user);
                    if (connect == null)
                    {
                        fenetres.event.getContentPane().removeAll(); /// Retire le contenu de la page event
                        erreur_text = new JPanel();
                        fenetres.event.setTitle("Erreur");
                        label_event = new JLabel("Erreur pendant la tentative de connexion");
                        erreur_text.add(label_event);
                        JPanel erreur_button = new JPanel();
                        fenetres.addButton(erreur_button, "Retour");
                        fenetres.event.add(erreur_text, BorderLayout.CENTER);
                        fenetres.event.add(erreur_button, BorderLayout.SOUTH);
                        fenetres.event.setVisible(true);
                    }
                    else
                    {
                        fenetres.setProfil();
                        fenetres.accueil.setVisible(true);
                        fenetres.connecter.setVisible(false);
                    }
                }
                else if (fenetres.inscrire.isVisible())
                {
                    int id = new Random().nextInt();
                    fenetres.surname = fenetres.nom.getText();
                    fenetres.name = fenetres.prenom.getText();
                    fenetres.email = fenetres.mail.getText();
                    fenetres.password = fenetres.mdp.getText();
                    fenetres.account_type = (String) fenetres.type_compte.getSelectedItem();
                    Utilisateurs new_user = new Utilisateurs(id, fenetres.surname, fenetres.name, fenetres.email, fenetres.password, fenetres.account_type);
                    userdao.ajouterUtilisateur(new_user);
                    fenetres.inscrire.setVisible(false);
                    fenetres.event.getContentPane().removeAll();
                    fenetres.event.setTitle("Inscription");
                    JPanel text = new JPanel();
                    label_event = new JLabel("Inscription terminee");
                    text.add(label_event);
                    JPanel erreur_button = new JPanel();
                    fenetres.addButton(erreur_button, "Retour");
                    fenetres.event.add(text, BorderLayout.CENTER);
                    fenetres.event.add(erreur_button, BorderLayout.SOUTH);
                    fenetres.connecter.setVisible(true);
                    fenetres.event.setVisible(true);
                }
                break;
            case "Valider l'ajout":
                int id_article = new Random().nextInt();
                fenetres.name_article = fenetres.nom_article.getText();
                fenetres.describe = fenetres.description.getText();
                fenetres.price = Float.parseFloat(fenetres.prix.getText());
                fenetres.reserve = Integer.parseInt(fenetres.stock.getText());
                fenetres.max_rabais = Integer.parseInt(fenetres.seuil_remise.getText());
                fenetres.categorie_type = (String) fenetres.categorie.getSelectedItem();
                fenetres.mark = (String) fenetres.marque.getSelectedItem();
                Article new_article = new Article(id_article, fenetres.reserve, fenetres.max_rabais, fenetres.name_article, fenetres.mark, fenetres.categorie_type, fenetres.describe, fenetres.price, true);
                artdao.ajouterArticle(new_article);
                fenetres.ajout_article.setVisible(false);
                fenetres.event.getContentPane().removeAll();
                fenetres.event.setTitle("Ajout article");
                JPanel text = new JPanel();
                label_event = new JLabel("Ajout effectué");
                text.add(label_event);
                JPanel erreur_button = new JPanel();
                fenetres.addButton(erreur_button, "Retour");
                fenetres.event.add(text, BorderLayout.CENTER);
                fenetres.event.add(erreur_button, BorderLayout.SOUTH);
                fenetres.profil.setVisible(true);
                fenetres.event.setVisible(true);
                break;
            case "Inscrire":
                fenetres.inscrire.setVisible(true);
                fenetres.connecter.setVisible(false);
                break;
            case "Profil":
                fenetres.profil.setVisible(true);
                break;
            case "Accueil":
                fenetres.accueil.setVisible(true);
                fenetres.connecter.setVisible(false);
                fenetres.profil.setVisible(false);
                fenetres.inscrire.setVisible(false);
                break;
            case "Ajouter article":
                fenetres.ajout_article.setVisible(true);
                break;
            case "Deconnexion":
                fenetres.connecter.setVisible(true);
                fenetres.accueil.setVisible(false);
                fenetres.inscrire.setVisible(false);
                fenetres.profil.setVisible(false);
                fenetres.catalogue.setVisible(false);
                break;
            case "Connexion" :
                fenetres.connecter.setVisible(true);
                fenetres.inscrire.setVisible(false);
                break;
            case "Catalogue" :
                fenetres.catalogue.setVisible(true);
                break;
            case "Valider et payer":
                fenetres.payment = (String) fenetres.payment_type.getSelectedItem();
                fenetres.num_card = fenetres.numero_carte.getText();
                fenetres.exp_date = fenetres.expiration_carte.getText();
                fenetres.cvv_number = fenetres.cvv.getText();
                System.out.println("Paiement : " + fenetres.payment +" Numero de carte : " + fenetres.num_card + " Date d'expiration : " + fenetres.exp_date + " Numero CVV : " + fenetres.cvv_number);
                break;

            case "Voir":
                JOptionPane.showMessageDialog(this,
                        "Détails du produit:\n" +
                                "Nom: " + fenetres.nom + "\n" +
                                "Propriétaire: " +   "\n" +
                                "Prix: " +    " €\n" +
                                "Stock: " +   " unités");
                break;
            case "Retour":
                if(fenetres.inscrire.isVisible())
                    fenetres.connecter.setVisible(true);
                else if(fenetres.ajout_article.isVisible())
                    fenetres.profil.setVisible(true);
                fenetres.ajout_article.setVisible(false);
                fenetres.event.setVisible(false);
                fenetres.catalogue.setVisible(false);
                break;
        }
    }
}
