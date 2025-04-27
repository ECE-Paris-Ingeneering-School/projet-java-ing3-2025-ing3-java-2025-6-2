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
    private Fenetres fenetre; /// L'ensemble des fenêtres et variables utilisées (champs, JFrame et autres variables si nécessaires)

    public FenetreControl(Fenetres fenetre)
    {
        this.fenetre = fenetre;
    }

    /** Action des boutons de chaque fenêtre
     * @Pour ouvrir une fenêtre, mise en place de l'affichage et ouverture de la fenêtre
     * @Après la fermeture d'une fenêtre, on vide son contenu (pour les principales) ou on garde la fenêtre telle quelle pour une mise à jour ultérieure (pour event)
     * */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
        ArticleDAOImpl artdao = new ArticleDAOImpl(dao);
        JButton button = (JButton) e.getSource();
        switch(button.getText())
        {
            /// Connexion à un compte
            case "Se connecter":
                fenetre.email = fenetre.mail_id.getText();
                fenetre.password = fenetre.mdp_id.getText();
                System.out.println(fenetre.email);
                System.out.println(fenetre.password);
                Utilisateurs user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                Utilisateurs connect = userdao.connexionUtilisateur(user);
                if (connect == null) /// Vérification avec database
                {
                    fenetre.setEvent("Erreur", "Erreur pendant la tentative de connexion");
                    fenetre.event.setVisible(true);
                }
                else
                {
                    /// Mise en place de certaines fenêtres
                    fenetre.setAccueil();
                    fenetre.accueil.setVisible(true);
                    fenetre.connecter.setVisible(false);
                    fenetre.connecter.getContentPane().removeAll();
                }
                break;
            /// Validation d'une inscription
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
                    fenetre.inscrire.getContentPane().removeAll();
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
                fenetre.ajout_article.getContentPane().removeAll();
                /// Fenêtre pop-up mise à jour pour confirmer l'action
                fenetre.setEvent("Ajout article", "Ajout d'un article effectué");
                fenetre.profil.setVisible(true);
                fenetre.event.setVisible(true);
                break;
            /// Inscription d'un nouveau client
            case "S'inscrire":
                fenetre.setInscrire();
                fenetre.inscrire.setVisible(true);
                fenetre.connecter.setVisible(false);
                fenetre.connecter.getContentPane().removeAll();
                break;
            /// Vue du profil
            case "Profil":
                fenetre.setProfil();
                fenetre.profil.setVisible(true);
                break;
            /// Vue de l'accueil
            case "Accueil":
                fenetre.setAccueil();
                fenetre.accueil.setVisible(true);
                fenetre.connecter.setVisible(false);
                fenetre.connecter.getContentPane().removeAll();
                fenetre.profil.setVisible(false);
                fenetre.profil.getContentPane().removeAll();
                break;
            /// Ajout d'un article
            case "Ajouter article":
                fenetre.setNewArticle();
                fenetre.ajout_article.setVisible(true);
                break;
            /// Modification d'un article
            case "Editer un article":
                fenetre.setModifArticle();
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
                fenetre.modif_article.setVisible(false);
                fenetre.modif.getContentPane().removeAll();
                fenetre.modif_article.getContentPane().removeAll();
                /// Fenêtre pop-up mise à jour pour confirmer l'action
                fenetre.setEvent("Modification d'un article", "Modification effectuée");
                fenetre.profil.setVisible(true);
                fenetre.event.setVisible(true);
                break;
            /// Vue des dossiers clients
            case "Gerer les dossiers clients":
                UtilisateurDAOImpl utilisateurDAO = new UtilisateurDAOImpl(dao);
                List<Utilisateurs> utilisateursList = utilisateurDAO.getAllClientsWithFidelity();
                fenetre.setGestionClient(utilisateursList);
                fenetre.gestionClient.setVisible(true);
                break;
            /// Vue articles
            case "Articles":
                fenetre.setListeArticles();
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
                        String quant= fenetre.quantite.getText();
                        if (quant.isEmpty()){
                            quant ="1";
                        }
                        int quantite_voulu = Integer.parseInt(quant);
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
            /// Même principe que pour Ajouter mais dans l'autre sens, et sans l'id du client
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
            case "Envoyer":
                ArticleDAOImpl articleDAOImpl = new ArticleDAOImpl(dao);
                cmd = e.getActionCommand(); /// Récupère la commande + id de l'article
                if (cmd.startsWith("Envoyer"))
                {
                    try
                    {
                        String idStr = cmd.substring("Envoyer".length()).trim();
                        System.out.println("Insert : "+idStr); /// Correspond à l'id de l'article choisi (converti ensuite en entier pour les prochaines étapes)
                        int id = Integer.parseInt(idStr);
                        Article article = articleDAOImpl.getArticle(id);

                        user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                        connect = userdao.connexionUtilisateur(user);
                        Client client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                        /// Récupérer le client connecté
                        String commentaire = fenetre.commentaireField.getText();
                        int note = (int) fenetre.noteBox.getSelectedItem();
                        System.out.println("Note pour "+article.getId()+":"+note);
                        Avis avis = new Avis(new java.util.Random().nextInt(1_000_000_000), client, article, commentaire, note, java.time.LocalDate.now().toString());
                        article.ajouterAvis(avis);
                        /// Ajouter en base via DAO
                        AvisDAO avisDAO = new AvisDAOImpl(dao);
                        avisDAO.ajouterAvis(avis, client, article);
                        /// Rafraîchir l'affichage
                        fenetre.avisPanel.removeAll();
                        fenetre.setAvisPanel(article);
                        fenetre.avisPanel.revalidate();
                        fenetre.avisPanel.repaint();
                    }
                    catch (NumberFormatException ex)
                    {
                        System.err.println("ID invalide dans la commande : " + cmd);
                    }
                }
                break;
            /// Vue avis client
            case "Avis":

                break;
            /// Vue catalogue
            case "Catalogue" :
                fenetre.setCatalogue("");
                fenetre.catalogue.setVisible(true);
                break;
            /// Passer à la commande
            case "Commander":
                fenetre.setNewCommande();
                fenetre.ajout_commande.setVisible(true);
                break;
            /// Procéder au paiement
            case "Payer":
                fenetre.setPaiement();
                /// Connexion pour récupérer l'utilisateur actuellement en ligne
                CommandeDAOImpl comdao = new CommandeDAOImpl(dao);
                PanierDAOImpl panierDAO = new PanierDAOImpl(dao);
                user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                connect = userdao.connexionUtilisateur(user);
                Client client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                String adresse = fenetre.adresse.getText();
                Panier panier = panierDAO.getPanier(client);
                /// Ajout d'une nouvelle commande

                Commande nouveau = comdao.nouvelleCommande(client, adresse);
                comdao.ajouterDansCommandeEnCours(nouveau, panier);
                fenetre.setPaiement();
                fenetre.paiement.setVisible(true);
                fenetre.setEvent("Ajout commande", "Commande ajoutée");
                fenetre.event.setVisible(true);
                fenetre.ajout_commande.setVisible(false);
                fenetre.panierFrame.setVisible(false);
                fenetre.ajout_commande.getContentPane().removeAll();
                fenetre.panierFrame.getContentPane().removeAll();
                break;
            /// Validation du paiement
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
                PaiementDAOImpl paiementDAO = new PaiementDAOImpl(dao);
                paiementDAO.insertionPaiement(client, paiement);
                fenetre.setEvent("Paiement commande", "Paiement effectué");
                fenetre.event.setVisible(true);
                fenetre.paiement.setVisible(false);
                fenetre.paiement.getContentPane().removeAll();
                break;
            /// Annuler une commande
            case "Annuler":
                comdao = new CommandeDAOImpl(dao);
                user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                connect = userdao.connexionUtilisateur(user);
                client = new Client(connect.getIdentifiant(), connect.getNom(), connect.getPrenom(), connect.getEmail(), connect.getMotDePasse(), connect.getType_utilisateur());
                comdao.modifierCommande(client);
                fenetre.paiement.setVisible(false);
                fenetre.paiement.getContentPane().removeAll();
                fenetre.setEvent("Commande", "Commande annulée");
                fenetre.event.setVisible(false);
                fenetre.setAccueil();
                fenetre.accueil.setVisible(true);
                break;
            /// Voir l'historique du='une commande
            case "Voir":
                cmd = e.getActionCommand();
                if (cmd.startsWith("Voir"))
                {
                    try
                    {
                        String idStr = cmd.substring("Voir".length());
                        System.out.println(idStr);
                        int id = Integer.parseInt(idStr);
                        user = new Utilisateurs(0, "", "", fenetre.email, fenetre.password, "");
                        Utilisateurs user_actuel = userdao.connexionUtilisateur(user);
                        client = new Client(user_actuel.getIdentifiant(), user_actuel.getNom(),
                                user_actuel.getPrenom(), user_actuel.getEmail(),
                                user_actuel.getMotDePasse(), user_actuel.getType_utilisateur());
                        CommandeDAOImpl commandeDAO = new CommandeDAOImpl(dao);
                        Commande newcommande = commandeDAO.getCommande(client);
                        fenetre.setHistoCommande(newcommande);
                        fenetre.histoCommande.setVisible(true);
                    }
                    catch (NumberFormatException ex)
                    {
                        System.err.println("ID invalide dans la commande : " + cmd);
                    }
                }
                break;
            /// Vue du panier
            case "Panier":
                fenetre.setPanier();
                fenetre.panierFrame.setVisible(true);
                break;
            /// Vue des statistiques
            case "Statistiques":
                fenetre.stats();
                fenetre.stats.setVisible(true);
                break;
            /// Vue de l'historique
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
            /// Fermeture d'une fenêtre
            case "Retour":
                if(fenetre.promo.isVisible())
                {
                    fenetre.promo.setVisible(false);
                    fenetre.promo.getContentPane().removeAll();
                }
                else if (fenetre.modif.isVisible())
                {
                    fenetre.modif.setVisible(false);
                    fenetre.modif.getContentPane().removeAll();
                }
                else if (fenetre.modif_article.isVisible())
                {
                    fenetre.modif_article.setVisible(false);
                    fenetre.modif_article.getContentPane().removeAll();
                }
                else if (fenetre.ajout_article.isVisible())
                {
                    fenetre.ajout_article.setVisible(false);
                    fenetre.ajout_article.getContentPane().removeAll();
                }
                else if (fenetre.histoCommande.isVisible())
                {
                    fenetre.histoCommande.setVisible(false);
                    fenetre.histoCommande.getContentPane().removeAll();
                }
                else if (fenetre.histoPaiement.isVisible())
                {
                    fenetre.histoPaiement.setVisible(false);
                    fenetre.histoPaiement.getContentPane().removeAll();
                }
                else if (fenetre.gestionClient.isVisible())
                {
                    fenetre.gestionClient.setVisible(false);
                    fenetre.gestionClient.getContentPane().removeAll();
                }
                else if (fenetre.stats.isVisible())
                {
                    fenetre.stats.setVisible(false);
                    fenetre.stats.getContentPane().removeAll();
                }
                else if (fenetre.profil.isVisible())
                {
                    fenetre.profil.setVisible(false);
                    fenetre.profil.getContentPane().removeAll();
                }
                else if(fenetre.articles.isVisible())
                {
                    fenetre.articles.setVisible(false);
                    fenetre.articles.getContentPane().removeAll();
                }
                else if(fenetre.panierFrame.isVisible())
                {
                    fenetre.panierFrame.setVisible(false);
                    fenetre.panierFrame.getContentPane().removeAll();
                }
                else if (fenetre.catalogue.isVisible())
                {
                    fenetre.catalogue.setVisible(false);
                    fenetre.catalogue.getContentPane().removeAll();
                }
                else if(fenetre.ajout_commande.isVisible())
                {
                    fenetre.ajout_commande.setVisible(false);
                    fenetre.ajout_commande.getContentPane().removeAll();
                }
                break;
            /// Fermeture alternative
            case "Fermer":
                if(fenetre.inscrire.isVisible())
                {
                    fenetre.setIdentification();
                    fenetre.connecter.setVisible(true);
                    fenetre.inscrire.setVisible(false);
                }
                if(fenetre.inscrire != null)
                {
                    fenetre.inscrire.getContentPane().removeAll();
                }
                fenetre.event.setVisible(false);
                break;
            /// Vue promotion
            case "Promotions":
                fenetre.setArticlePromo();
                fenetre.promo.setVisible(true);
            case "Rechercher":
                String recherche = fenetre.RechercheEnCours.getText().toLowerCase().trim();
                System.out.println("Texte de la recherche : " + recherche);

                // Si la recherche n'est pas vide, on effectue la recherche
                System.out.println("Exécution de la recherche...");
                // Appel de la méthode pour afficher les résultats filtrés avec la recherche
                fenetre.setCatalogue(recherche);

                // Forcer la réactualisation de l'affichage
                fenetre.catalogue.revalidate();
                fenetre.catalogue.repaint();
                fenetre.catalogue.setVisible(true);
                System.out.println("Catalogue réactualisé");


                // Réinitialiser le champ de recherche après l'action
                fenetre.RechercheEnCours.setText(""); // Vide le champ après la recherche

                break;
            /// Déconnexion du compte
            case "Deconnexion":
                fenetre.setIdentification();
                fenetre.connecter.setVisible(true);

                fenetre.ajout_article.setVisible(false);
                fenetre.catalogue.setVisible(false);
                fenetre.panierFrame.setVisible(false);
                fenetre.articles.setVisible(false);
                fenetre.modif.setVisible(false);
                fenetre.modif_article.setVisible(false);
                fenetre.stats.setVisible(false);
                fenetre.profil.setVisible(false);
                fenetre.gestionClient.setVisible(false);
                fenetre.histoPaiement.setVisible(false);
                fenetre.histoCommande.setVisible(false);
                fenetre.promo.setVisible(false);
                fenetre.accueil.setVisible(false);
                if(fenetre.ajout_commande != null)
                {
                    fenetre.ajout_commande.setVisible(false);
                    fenetre.ajout_commande.getContentPane().removeAll();
                }

                fenetre.ajout_article.getContentPane().removeAll();
                fenetre.catalogue.getContentPane().removeAll();
                fenetre.panierFrame.getContentPane().removeAll();
                fenetre.articles.getContentPane().removeAll();
                fenetre.modif.getContentPane().removeAll();
                fenetre.modif_article.getContentPane().removeAll();
                fenetre.stats.getContentPane().removeAll();
                fenetre.profil.getContentPane().removeAll();
                fenetre.gestionClient.getContentPane().removeAll();
                fenetre.histoPaiement.getContentPane().removeAll();
                fenetre.histoCommande.getContentPane().removeAll();
                fenetre.promo.getContentPane().removeAll();
                fenetre.accueil.getContentPane().removeAll();
                break;
        }
    }
}