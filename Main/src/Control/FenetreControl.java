package Control;

import Dao.*;
import Model.*;
import View.VueArticle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
        switch(button.getText())
        {
            /** Valider est utilisé pour la connexion et l'inscription
             * (d'autres actions utilisent le même principe mais avec des noms différents pour éviter de surcharger ce cas précis)
             */
            case "Se connecter":
                fenetre.email = fenetre.mail_id.getText();
                fenetre.password = fenetre.mdp_id.getText();
                System.out.println(fenetre.email);
                System.out.println(fenetre.password);
                Utilisateurs user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                Utilisateurs connect = userdao.connexionUtilisateur(user);
                if (connect == null)
                {
                    fenetre.setEvent("Erreur", "Erreur pendant la tentative de connexion");
                    fenetre.event.setVisible(true);
                }
                else
                {
                    fenetre.setProfil();
                    fenetre.setPanier();
                    fenetre.accueil.setVisible(true);
                    fenetre.connecter.setVisible(false);
                }
                break;
            case "Valider":
                if (fenetre.inscrire.isVisible())
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
                    fenetre.setEvent("Inscription", "Inscription terminee");
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
                fenetre.setEvent("Ajout article", "Ajout d'un article effectué");
                fenetre.profil.setVisible(true);
                fenetre.event.setVisible(true);
                break;
            case "S'inscrire":
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
                fenetre.setEvent("Modification d'un article", "Modification effectuée");
                fenetre.profil.setVisible(true);
                fenetre.event.setVisible(true);
                break;
            case "Gerer les dossiers clients":
                UtilisateurDAOImpl utilisateurDAO = new UtilisateurDAOImpl(dao);
                List<Utilisateurs> utilisateursList = utilisateurDAO.getAllClientsWithFidelity();
                fenetre.setGestionClient(utilisateursList);
                fenetre.gestionClient.setVisible(true);
                break;
            case "Deconnexion":
                fenetre.connecter.setVisible(true);
                fenetre.accueil.setVisible(false);
                fenetre.inscrire.setVisible(false);
                fenetre.profil.setVisible(false);
                fenetre.catalogue.setVisible(false);
                fenetre.panierFrame.setVisible(false);
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
                        fenetre.afficherDetailsArticle(article);
                    } catch (NumberFormatException ex)
                    {
                        System.err.println("ID invalide dans la commande : " + cmd);
                    }
                }
                break;
            /// Ajouter permet d'ajouter un article dans le panier de l'utilisateur
            case "Ajouter au panier":
                articleDAO = new ArticleDAOImpl(dao);
                cmd = e.getActionCommand();

                if (cmd.startsWith("Ajouter au panier")) {
                    String idStr = cmd.substring("Ajouter au panier".length()).trim();
                    try
                    {
                        int id = Integer.parseInt(idStr);
                        Article article = articleDAO.getArticle(id);
                        user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                        connect = userdao.connexionUtilisateur(user);
                        Client client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                        int quantite_voulu = Integer.parseInt(fenetre.quantite.getText());
                        PanierDAOImpl panierDAO = new PanierDAOImpl(dao);
                        panierDAO.nouveauPanier(client);
                        Panier panier = panierDAO.getPanier(client);
                        panierDAO.ajouterAuPanier(panier, client, article, quantite_voulu);
                        fenetre.setEvent("Article", "Article ajouté au panier");
                        fenetre.event.setVisible(true);
                    }
                    catch (Exception ex)
                    {
                        System.err.println("Une erreur est survenue lors de l’ajout au panier : " + ex.getMessage());
                        ex.printStackTrace();
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
                        fenetre.setEvent("Article", "Article retiré du panier");
                        fenetre.event.setVisible(true);
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
            case "Commander":
                fenetre.ajout_commande.setVisible(true);
                break;
            case "Payer":
                /// Connexion pour récupérer l'utilisateur actuellement en ligne
                CommandeDAOImpl comdao = new CommandeDAOImpl(dao);
                PanierDAOImpl panierDAO = new PanierDAOImpl(dao);
                user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                connect = userdao.connexionUtilisateur(user);
                Client client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                String adresse = fenetre.adresse.getText();
                Panier panier = panierDAO.getPanier(client);
                /// Ajout d'une nouvelle commande
                comdao.nouvelleCommande(client, adresse);
                Commande commande = comdao.getCommande(client);
                comdao.ajouterDansCommandeEnCours(commande, panier);
                fenetre.paiement.setVisible(true);
                fenetre.setEvent("Ajout commande", "Commande ajoutée");
                fenetre.event.setVisible(true);
                fenetre.ajout_commande.setVisible(false);
                fenetre.panierFrame.setVisible(false);
                break;
            case "Valider et payer":
                String paiement = (String) fenetre.payment_type.getSelectedItem();
                int numero_carte = Integer.parseInt(fenetre.numero_carte.getText());
                String exp_date = fenetre.expiration_carte.getText();
                int cvv = Integer.parseInt(fenetre.cvv.getText());
                System.out.println("Paiement : " + paiement +" Numero de carte : " + numero_carte + " Date d'expiration : " + exp_date + " Numero CVV : " + cvv);
                comdao = new CommandeDAOImpl(dao);
                panierDAO = new PanierDAOImpl(dao);
                user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                connect = userdao.connexionUtilisateur(user);
                client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                Commande commande1 = comdao.getCommande(client);
                comdao.paiementCommande(commande1);
                panierDAO.supprimerPanier(client);
                Paiement paiement1 = new Paiement(0, commande1, commande1.getPrixTotal(), commande1.getStatut(), paiement, commande1.getDate());
                PaiementDAOImpl paiementDAO = new PaiementDAOImpl(dao);
                paiementDAO.insertionPaiement(client, paiement);
                fenetre.setEvent("Paiement commande", "Paiement effectué");
                fenetre.event.setVisible(true);
                fenetre.paiement.setVisible(false);
                break;
            case "Annuler":
                comdao = new CommandeDAOImpl(dao);
                user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                connect = userdao.connexionUtilisateur(user);
                client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                comdao.modifierCommande(client);
                fenetre.paiement.setVisible(false);
                fenetre.setEvent("Commande", "Commande annulée");
                fenetre.event.setVisible(false);
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
                fenetre.panierFrame.setVisible(true);
                break;
            case "Statistiques":
                fenetre.stats();
                fenetre.stats.setVisible(true);
                fenetre.profil.setVisible(false);
                break;
            case "Historique":
                dao = DaoFactory.getInstance("ecommerce_db", "root", "");
                paiementDAO = new PaiementDAOImpl(dao);
                user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                connect = userdao.connexionUtilisateur(user);
                client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                List<Paiement> paiements = paiementDAO.getPaiementsUtilisateur(client);
                fenetre.setHistoriquePaiement(paiements);
                fenetre.histoPaiement.setVisible(true);
                break;
            case "Retour":
                if(fenetre.inscrire.isVisible())
                    fenetre.connecter.setVisible(true);
                else if(fenetre.ajout_article.isVisible())
                    fenetre.profil.setVisible(true);
                else if(fenetre.gestionClient.isVisible() || fenetre.histoPaiement.isVisible())
                    fenetre.profil.setVisible(true);
                fenetre.ajout_article.setVisible(false);
                fenetre.event.setVisible(false);
                fenetre.catalogue.setVisible(false);
                fenetre.panierFrame.setVisible(false);
                fenetre.articles.setVisible(false);
                fenetre.modif.setVisible(false);
                fenetre.modif_article.setVisible(false);
                fenetre.stats.setVisible(false);
                fenetre.profil.setVisible(false);
                fenetre.ajout_commande.setVisible(false);
                fenetre.gestionClient.setVisible(false);
                fenetre.histoPaiement.setVisible(false);
                break;
        }
    }
}
