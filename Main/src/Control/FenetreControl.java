package Control;

import Dao.*;
import Model.*;
import View.VueArticle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FenetreControl extends JFrame implements ActionListener
{
    private Fenetres fenetre; /// L'ensemble des fenêtres utilisées

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
            /** Valider est utilisé pour la connexion et l'inscription
             * (d'autres actions utilisent le même principe mais avec des noms différents pour éviter de surcharger ce cas précis)
             */
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
                        fenetre.setPanier();
                        fenetre.accueil.setVisible(true);
                        fenetre.connecter.setVisible(false);
                    }
                }
                else if (fenetre.inscrire.isVisible())
                {
                    int id = new Random().nextInt(1_000_000_000);
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
            /// Valider l'ajout d'un article (seulement pour les administrateurs)
            case "Valider l'ajout":
                /// Récupération des données saisies
                int id_article = new Random().nextInt(1_000_000_000);
                String nom = fenetre.nom_article.getText();
                String descriptionText = fenetre.description.getText();
                float prix = Float.parseFloat(fenetre.prix.getText());
                int stock = Integer.parseInt(fenetre.stock.getText());
                int seuil_remise = Integer.parseInt(fenetre.seuil_remise.getText());
                String categorie = (String) fenetre.categorie.getSelectedItem();
                String marque = (String) fenetre.marque.getSelectedItem();
                /// Création d'un nouvel objet
                Article new_article = new Article(id_article, stock, seuil_remise, nom, marque, categorie, descriptionText, prix, true);
                artdao.ajouterArticle(new_article); /// Ajout de l'objet
                fenetre.ajout_article.setVisible(false);
                /// Fenêtre pop-up mise à jour pour confirmer l'action
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
            case "Editer un article":
                fenetre.modif_article.setVisible(true);
                break;
            case "Modifier":
                /// On récupère la commande Modifier avec l'id de l'article concerné
                ArticleDAOImpl articleDAO = new ArticleDAOImpl(dao);
                String cmd = e.getActionCommand(); /// Récupère la commande + id de l'article
                if (cmd.startsWith("Modifier"))
                {
                    try
                    {
                        String idStr = cmd.substring("Modifier".length());
                        System.out.println(idStr); /// Correspond à l'id de l'article choisi (converti ensuite en entier pour les prochaines étapes)
                        int id = Integer.parseInt(idStr);
                        Article article = articleDAO.getArticle(id);
                        fenetre.setModifierArticle(article);
                        fenetre.modif.setVisible(true);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.err.println("ID invalide dans la commande : " + cmd);
                    }
                }
                break;
            case "Mettre à jour":
                /// Récupération des données saisies
                id_article = fenetre.id_article;
                nom = fenetre.nom_article.getText();
                descriptionText = fenetre.description.getText();
                prix = Float.parseFloat(fenetre.prix.getText());
                stock = Integer.parseInt(fenetre.stock.getText());
                seuil_remise = Integer.parseInt(fenetre.seuil_remise.getText());
                categorie = (String) fenetre.categorie.getSelectedItem();
                marque = (String) fenetre.marque.getSelectedItem();
                /// Création d'un nouvel objet
                new_article = new Article(id_article, stock, seuil_remise, nom, marque, categorie, descriptionText, prix, true);
                artdao.modifierArticle(new_article); /// Modification de l'objet
                fenetre.modif.setVisible(false);
                /// Fenêtre pop-up mise à jour pour confirmer l'action
                fenetre.event.getContentPane().removeAll();
                fenetre.event.setTitle("Modification d'un article");
                text = new JPanel();
                label_event = new JLabel("Modification effectué");
                text.add(label_event);
                erreur_button = new JPanel();
                fenetre.addButton(erreur_button, "Retour");
                fenetre.event.add(text, BorderLayout.CENTER);
                fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                fenetre.profil.setVisible(true);
                fenetre.event.setVisible(true);
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
            case "Articles":
                fenetre.articles.setVisible(true);
                break;
            /// Consultez les détails d'un article (en vue console)
            case "Détails":
                /// On récupère la commande Ajouter avec l'id de l'article concerné
                articleDAO = new ArticleDAOImpl(dao);
                cmd = e.getActionCommand(); /// Récupère la commande + id de l'article
                if (cmd.startsWith("Détails"))
                {
                    try
                    {
                        String idStr = cmd.substring("Détails".length());
                        System.out.println(idStr); /// Correspond à l'id de l'article choisi (converti ensuite en entier pour les prochaines étapes)
                        int id = Integer.parseInt(idStr);
                        Article article = articleDAO.getArticle(id);
                        new VueArticle().afficherDetails(article);
                    } catch (NumberFormatException ex)
                    {
                        System.err.println("ID invalide dans la commande : " + cmd);
                    }
                }
                break;
            /// Ajouter permet d'ajouter un article dans le panier de l'utilisateur
            case "Ajouter":
                /// On récupère la commande Ajouter avec l'id de l'article concerné
                articleDAO = new ArticleDAOImpl(dao);
                cmd = e.getActionCommand(); /// Récupère la commande + id de l'article
                if (cmd.startsWith("Ajouter"))
                {
                    try
                    {
                        String idStr = cmd.substring("Ajouter".length());
                        System.out.println(idStr); /// Correspond à l'id de l'article choisi (converti ensuite en entier pour les prochaines étapes)
                        int id = Integer.parseInt(idStr);
                        Article article = articleDAO.getArticle(id);
                        /// Connexion à la base de données puis à la table panier + ligne panier pour traitement. On récupère l'id du client utilisé pour l'enregistrer dans la base (clé étrangère)
                        PanierDAOImpl panierDAO = new PanierDAOImpl(dao);
                        Utilisateurs user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                        Utilisateurs connect = userdao.connexionUtilisateur(user);
                        Client client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                        panierDAO.nouveauPanier(client);
                        Panier panier = new Panier(panierDAO.getPanier(client).getId(), client);
                        panierDAO.ajouterAuPanier(panier, client, article);
                        fenetre.event.getContentPane().removeAll();
                        fenetre.event.setTitle("Article");
                        text = new JPanel();
                        label_event = new JLabel("Article ajouté au panier");
                        text.add(label_event);
                        erreur_button = new JPanel();
                        fenetre.addButton(erreur_button, "Retour");
                        fenetre.event.add(text, BorderLayout.CENTER);
                        fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.err.println("ID invalide dans la commande : " + cmd);
                    }
                }
                break;
            /// Même principe que pour Ajouter  mais dans l'autre sens, et sans l'id du client
            case "Retirer":
                articleDAO = new ArticleDAOImpl(dao);
                cmd = e.getActionCommand();
                if (cmd.startsWith("Retirer"))
                {
                    try
                    {
                        String idStr = cmd.substring("Retirer".length());
                        System.out.println(idStr);
                        int id = Integer.parseInt(idStr);
                        Article article = articleDAO.getArticle(id);
                        PanierDAOImpl panierDAO = new PanierDAOImpl(dao);
                        panierDAO.supprimerDuPanier(article);
                        fenetre.event.getContentPane().removeAll();
                        fenetre.event.setTitle("Article");
                        text = new JPanel();
                        label_event = new JLabel("Article retiré du panier");
                        text.add(label_event);
                        erreur_button = new JPanel();
                        fenetre.addButton(erreur_button, "Retour");
                        fenetre.event.add(text, BorderLayout.CENTER);
                        fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.err.println("ID invalide dans la commande : " + cmd);
                    }
                }
                break;
            case "Catalogue" :
                fenetre.catalogue.setVisible(true);
                break;
            case "Passer commande":
                fenetre.ajout_commande.setVisible(true);
                break;
            case "Payer":
                /// Connexion pour récupérer l'utilisateur actuellement en ligne
                CommandeDAOImpl comdao = new CommandeDAOImpl(dao);
                Utilisateurs user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                Utilisateurs connect = userdao.connexionUtilisateur(user);
                Client client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                String adresse = fenetre.adresse.getText();
                /// Ajout d'une nouvelle commande
                comdao.nouvelleCommande(client, adresse);
                fenetre.paiement.setVisible(true);
                fenetre.event.getContentPane().removeAll();
                fenetre.event.setTitle("Ajout article");
                text = new JPanel();
                label_event = new JLabel("Commande ajoutée");
                text.add(label_event);
                erreur_button = new JPanel();
                fenetre.addButton(erreur_button, "Retour");
                fenetre.event.add(text, BorderLayout.CENTER);
                fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                fenetre.ajout_commande.setVisible(false);
                fenetre.panier.setVisible(false);
                break;
            case "Valider et payer":
                String paiement = (String) fenetre.payment_type.getSelectedItem();
                String numero_carte = fenetre.numero_carte.getText();
                String exp_date = fenetre.expiration_carte.getText();
                int cvv = Integer.parseInt(fenetre.cvv.getText());
                System.out.println("Paiement : " + paiement +" Numero de carte : " + numero_carte + " Date d'expiration : " + exp_date + " Numero CVV : " + cvv);
                break;
            case "Annuler":
                comdao = new CommandeDAOImpl(dao);
                user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                connect = userdao.connexionUtilisateur(user);
                client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                comdao.modifierCommande(client);
                fenetre.paiement.setVisible(false);
                fenetre.paiement.setVisible(true);
                fenetre.event.getContentPane().removeAll();
                fenetre.event.setTitle("Ajout article");
                text = new JPanel();
                label_event = new JLabel("Commande ajoutée");
                text.add(label_event);
                erreur_button = new JPanel();
                fenetre.addButton(erreur_button, "Retour");
                fenetre.event.add(text, BorderLayout.CENTER);
                fenetre.event.add(erreur_button, BorderLayout.SOUTH);
                fenetre.accueil.setVisible(true);
                break;
            case "Voir":
                JOptionPane.showMessageDialog(this,
                        "Détails du produit:\n" +
                                "Nom: " + fenetre.nom + "\n" +
                                "Propriétaire: " +   "\n" +
                                "Prix: " +    " €\n" +
                                "Stock: " +   " unités");
                break;
            case "Panier":
                fenetre.panier.setVisible(true);
                break;
            case "Retour":
                if(fenetre.inscrire.isVisible())
                    fenetre.connecter.setVisible(true);
                else if(fenetre.ajout_article.isVisible())
                    fenetre.profil.setVisible(true);
                fenetre.ajout_article.setVisible(false);
                fenetre.event.setVisible(false);
                fenetre.catalogue.setVisible(false);
                fenetre.panier.setVisible(false);
                fenetre.articles.setVisible(false);
                fenetre.modif.setVisible(false);
                fenetre.modif_article.setVisible(false);
                break;
        }
    }
}
