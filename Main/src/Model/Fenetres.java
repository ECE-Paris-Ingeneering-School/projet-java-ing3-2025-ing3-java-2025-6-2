package Model;

import Dao.*;
import Control.FenetreControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.File;

import Util.ImageManager;
import Exception.ApplicationException;
import Exception.ErrorType;

/** Fenetres constitue l'ensemble des interfaces graphiques utilisées au cour du projet
 * @L'ensemble des liens entre elles sont effectuées ici
 */
public class Fenetres extends JFrame
{
    /// Constante pour la couleur de fond
    private static final Color BACKGROUND_COLOR = new Color(128, 20, 41); // Rouge bordeaux

    /// Fenêtres de navigation
    public JFrame inscrire, connecter, accueil, profil, event, paiement, catalogue, ajout_article, articles, ajout_commande, modif, modif_article, stats, histoPaiement, gestionClient, histoCommande, promo;
    public JFrame panierFrame;
    /// Zones de saisie connexion et inscriptions
    public TextField nom, prenom, mail, mdp, mail_id, mdp_id;
    /// Zone de saisie de l'adresse pour commande (récupération des autres paramètres par le profil utilisé)
    public TextField adresse;
    /// Zones de saisies paiement
    public TextField numero_carte, expiration_carte, cvv;
    /// Inscription utilisateur dans la database
    public String email, password;

    /// Gestion articles
    public TextField nom_article, description, prix, stock, seuil_remise, quantite; /// Zones de saisies article
    public JComboBox<String> type_compte, payment_type, categorie, marque; /// Choix du mode de paiement et du type de compte
    public int id_article;

    /// Fenetre catalogue
    public JPanel Liste, PannelRetour;
    public JScrollPane Scroll;

    public ArticleDAO articleDao;
    private FenetreControl fenetreControl; /// Controleur des fenêtres (gestion des boutons dans FenetreControl)
    public Article article;

    public List<LigneCommande> panier; // Changed to public
    public JLabel panierLabel;

    /// Constructeur de chaque fenêtre
    public Fenetres()
    {
        /// Initialisation de toutes les fenêtres
        inscrire = new JFrame();
        connecter = new JFrame();
        accueil = new JFrame();
        profil = new JFrame();
        event = new JFrame();
        paiement = new JFrame();
        catalogue = new JFrame();
        ajout_article = new JFrame();
        articles = new JFrame();
        panier = new ArrayList<>();
        panierLabel = new JLabel("Panier (0)");
        panierFrame = new JFrame(); // Initialize panierFrame
        modif = new JFrame();
        modif_article = new JFrame();
        stats = new JFrame();
        histoPaiement = new JFrame();
        gestionClient = new JFrame();
        histoCommande = new JFrame();
        promo = new JFrame();

        /// Configuration des fenêtres
        inscrire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connecter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        event.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        catalogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ajout_article.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        histoPaiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gestionClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        histoCommande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        promo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /// Initialisation des autres composants
        DaoFactory daoFactory = DaoFactory.getInstance("ecommerce_db", "root", "");
        articleDao = new ArticleDAOImpl(daoFactory);

        // Application de la couleur de fond à toutes les fenêtres
        inscrire.getContentPane().setBackground(BACKGROUND_COLOR);
        connecter.getContentPane().setBackground(BACKGROUND_COLOR);
        accueil.getContentPane().setBackground(BACKGROUND_COLOR);
        profil.getContentPane().setBackground(BACKGROUND_COLOR);
        event.getContentPane().setBackground(BACKGROUND_COLOR);
        paiement.getContentPane().setBackground(BACKGROUND_COLOR);
        catalogue.getContentPane().setBackground(BACKGROUND_COLOR);
        ajout_article.getContentPane().setBackground(BACKGROUND_COLOR);
        articles.getContentPane().setBackground(BACKGROUND_COLOR);
        panierFrame.getContentPane().setBackground(BACKGROUND_COLOR);
        modif.getContentPane().setBackground(BACKGROUND_COLOR);
        modif_article.getContentPane().setBackground(BACKGROUND_COLOR);
        stats.getContentPane().setBackground(BACKGROUND_COLOR);
        histoPaiement.getContentPane().setBackground(BACKGROUND_COLOR);
        gestionClient.getContentPane().setBackground(BACKGROUND_COLOR);
        histoCommande.getContentPane().setBackground(BACKGROUND_COLOR);
        promo.getContentPane().setBackground(BACKGROUND_COLOR);
    }

    /// Mise en place du controleur
    public void setControleur(FenetreControl controleur)
    {
        this.fenetreControl = controleur;
    }

    /// Fenêtre inscription utilisateur
    public void setInscrire()
    {
        inscrire.setSize(900, 700);
        inscrire.setTitle("Inscription");
        inscrire.setLayout(new GridBagLayout());
        inscrire.getContentPane().setBackground(BACKGROUND_COLOR);

        /// Panel principal blanc
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));
        mainPanel.setMaximumSize(new Dimension(400, 600));

        /// Logo
        try {
            ImageIcon logoIcon = new ImageIcon("Main/src/View/image/Logo.png");
            Image img = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(img));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(logoLabel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        } catch (Exception e) {
            System.err.println("Erreur de chargement du logo");
        }

        /// Titre Inscription
        JLabel titleLabel = new JLabel("Inscription");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        /// Type de compte
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.Y_AXIS));
        typePanel.setBackground(Color.WHITE);
        JLabel typeLabel = new JLabel("Type de compte");
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        String[] type = {"client", "admin"};
        type_compte = new JComboBox<>(type);
        type_compte.setFont(new Font("Arial", Font.PLAIN, 14));
        type_compte.setPreferredSize(new Dimension(300, 35));
        type_compte.setBackground(Color.WHITE);
        typePanel.add(typeLabel);
        typePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        typePanel.add(type_compte);
        mainPanel.add(typePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        /// Nom
        JPanel nomPanel = new JPanel();
        nomPanel.setLayout(new BoxLayout(nomPanel, BoxLayout.Y_AXIS));
        nomPanel.setBackground(Color.WHITE);
        JLabel nomLabel = new JLabel("Nom");
        nomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nom = new TextField(20);
        nom.setPreferredSize(new Dimension(300, 35));
        nomPanel.add(nomLabel);
        nomPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        nomPanel.add(nom);
        mainPanel.add(nomPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        /// Prénom
        JPanel prenomPanel = new JPanel();
        prenomPanel.setLayout(new BoxLayout(prenomPanel, BoxLayout.Y_AXIS));
        prenomPanel.setBackground(Color.WHITE);
        JLabel prenomLabel = new JLabel("Prénom");
        prenomLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        prenom = new TextField(20);
        prenom.setPreferredSize(new Dimension(300, 35));
        prenomPanel.add(prenomLabel);
        prenomPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        prenomPanel.add(prenom);
        mainPanel.add(prenomPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        /// Email
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mail = new TextField(20);
        mail.setPreferredSize(new Dimension(300, 35));
        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        emailPanel.add(mail);
        mainPanel.add(emailPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        /// Mot de passe
        JPanel mdpPanel = new JPanel();
        mdpPanel.setLayout(new BoxLayout(mdpPanel, BoxLayout.Y_AXIS));
        mdpPanel.setBackground(Color.WHITE);
        JLabel mdpLabel = new JLabel("Password");
        mdpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        mdp = new TextField(20);
        mdp.setEchoChar('•');
        mdp.setPreferredSize(new Dimension(300, 35));
        mdpPanel.add(mdpLabel);
        mdpPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mdpPanel.add(mdp);
        mainPanel.add(mdpPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        /// Boutons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.setBackground(Color.WHITE);

        JButton validerButton = new JButton("Valider");
        validerButton.setBackground(Color.BLACK);
        validerButton.setForeground(Color.WHITE);
        validerButton.setFont(new Font("Arial", Font.BOLD, 14));
        validerButton.setPreferredSize(new Dimension(120, 35));
        validerButton.setBorderPainted(false);
        validerButton.setFocusPainted(false);
        validerButton.addActionListener(fenetreControl);

        JButton retourButton = new JButton("Retour");
        retourButton.setBackground(new Color(240, 240, 240));
        retourButton.setForeground(Color.BLACK);
        retourButton.setFont(new Font("Arial", Font.PLAIN, 14));
        retourButton.setPreferredSize(new Dimension(120, 35));
        retourButton.setBorderPainted(false);
        retourButton.setFocusPainted(false);
        retourButton.addActionListener(fenetreControl);

        buttonsPanel.add(validerButton);
        buttonsPanel.add(retourButton);
        mainPanel.add(buttonsPanel);

        inscrire.add(mainPanel);
    }

    /// Fenêtre connexion utilisateur
    public void setIdentification()
    {
        connecter.setSize(900, 700);
        connecter.setTitle("Connexion");

        /// Use GridBagLayout for better control over component placement
        connecter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        /// Fenetre principale
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));

        /// Champ Titre
        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        /// Champ Email
        JPanel emailPanel = new JPanel();
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.Y_AXIS));
        emailPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(new Color(100, 100, 100));
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mail_id = new StyledTextField(20);
        mail_id.setPreferredSize(new Dimension(300, 35));
        emailPanel.add(emailLabel);
        emailPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        emailPanel.add(mail_id);
        mainPanel.add(emailPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        /// Champ Mot de Passe
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(100, 100, 100));
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mdp_id = new StyledTextField(20);
        mdp_id.setPreferredSize(new Dimension(300, 35));
        mdp_id.setEchoChar('•');  /// Modern bullet point for password
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        passwordPanel.add(mdp_id);
        mainPanel.add(passwordPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 35)));

        /// Buttons panel with elegant styling
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton validerButton = new StyledButton("Se connecter", true);
        JButton inscrireButton = new StyledButton("S'inscrire", false);

        validerButton.addActionListener(fenetreControl);
        buttonsPanel.add(validerButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        inscrireButton.addActionListener(fenetreControl);
        buttonsPanel.add(inscrireButton);

        mainPanel.add(buttonsPanel);

        /// Add main panel to frame with proper sizing
        mainPanel.setMaximumSize(new Dimension(400, 450));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        connecter.add(mainPanel, gbc);
    }

    /// Fenêtre page d'accueil
    public void setAccueil()
    {
        /// Configuration de la fenêtre principale
        accueil.setSize(1200, 800);
        accueil.setTitle("Accueil - Maison Close");
        accueil.setLayout(new BorderLayout());
        accueil.getContentPane().removeAll();
        accueil.getContentPane().setBackground(BACKGROUND_COLOR);

        /// Barre de navigation
        JPanel navBar = createNavigationBar();
        accueil.add(navBar, BorderLayout.NORTH);

        /// Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /// En-tête
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Bienvenue sur Maison Close");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel("Votre marketplace d'art en ligne");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        descLabel.setForeground(Color.WHITE);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(descLabel);

        /// Section catégories
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        categoriesPanel.setBackground(Color.WHITE);
        categoriesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] categories = {"Peintures", "Sculptures", "Photographie", "Art numérique"};
        for (String category : categories)
        {
            JButton categoryButton = new JButton(category);
            categoryButton.setFont(new Font("Arial", Font.PLAIN, 16));
            categoryButton.setBackground(new Color(240, 240, 240));
            categoryButton.setBorderPainted(false);
            categoryButton.addActionListener(e -> catalogue.setVisible(true));
            categoriesPanel.add(categoryButton);
        }

        /// Assemblage final
        mainPanel.add(headerPanel);
        mainPanel.add(categoriesPanel);

        /// Ajout du panel principal à la fenêtre
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        accueil.add(scrollPane, BorderLayout.CENTER);

        /// Rafraîchir la fenêtre
        accueil.revalidate();
        accueil.repaint();
    }

    /// Accueil : Barre de navigation
    private JPanel createNavigationBar()
    {
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(Color.WHITE);
        navBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        /// Logo et recherche (gauche)
        JPanel leftSection = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        leftSection.setBackground(Color.WHITE);

        /// Chargement et redimensionnement du logo
        ImageIcon originalIcon = new ImageIcon("Main/src/View/image/Logo.png");
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(100, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel logoLabel = new JLabel(resizedIcon);

        JTextField searchField = new JTextField(30);
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        leftSection.add(logoLabel);
        leftSection.add(searchField);

        /// Menu principal (centre)
        JPanel centerSection = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        centerSection.setBackground(Color.WHITE);

        /// Création des boutons avec ActionListener
        JButton articlesButton = createNavButton("Articles", e -> fenetreControl.actionPerformed(e));

        JButton promotionsButton = createNavButton("Promotions", e -> fenetreControl.actionPerformed(e));

        JButton catalogueButton = createNavButton("Catalogue", e -> fenetreControl.actionPerformed(e));

        centerSection.add(articlesButton);
        centerSection.add(promotionsButton);
        centerSection.add(catalogueButton);

        /// Panier et login (droite)
        JPanel rightSection = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightSection.setBackground(Color.WHITE);

        JButton profileButton = createNavButton("Profil", e -> fenetreControl.actionPerformed(e));

        JButton loginButton = createNavButton("Deconnexion", e -> fenetreControl.actionPerformed(e));

        JButton cartButton = createNavButton("Panier", e -> fenetreControl.actionPerformed(e));

        rightSection.add(cartButton);
        rightSection.add(loginButton);
        rightSection.add(profileButton);

        navBar.add(leftSection, BorderLayout.WEST);
        navBar.add(centerSection, BorderLayout.CENTER);
        navBar.add(rightSection, BorderLayout.EAST);

        return navBar;
    }

    /// Accueil : Création d'un bouton de navigation
    private JButton createNavButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(new Color(50, 50, 50));
        button.addActionListener(listener);
        return button;
    }

    private JPanel createCategoryCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setPreferredSize(new Dimension(250, 150));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        card.add(titleLabel, BorderLayout.NORTH);

        return card;
    }

    /// Méthode pour créer une carte de produit avec image
    private JPanel createProductCard(Article article) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        p.setMaximumSize(new Dimension(200, 300));

        // Image
        String imagePath = getImageFileName(article.getNom(), article.getCategorie());
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(imageLabel);
        p.add(Box.createRigidArea(new Dimension(0, 10)));

        // Panel d'informations
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        // Nom de l'article
        JLabel nameLabel = new JLabel(article.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Description
        JLabel descLabel = new JLabel("<html><div style='width:150px'>" + article.getDescription() + "</div></html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(descLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        p.add(infoPanel);
        p.add(Box.createRigidArea(new Dimension(0, 10)));

        // Boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);

        JButton detailsButton = new JButton("Détails");
        detailsButton.setActionCommand("Détails" + article.getId());
        detailsButton.addActionListener(fenetreControl);

        JButton addButton = new JButton("Ajouter");
        addButton.setActionCommand("Ajouter" + article.getId());
        addButton.addActionListener(fenetreControl);

        buttonPanel.add(detailsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(addButton);

        p.add(buttonPanel);
        p.add(Box.createRigidArea(new Dimension(0, 10)));

        return p;
    }

    public void articlepromo()
    {
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
        Utilisateurs user = new Utilisateurs(0, "", "", email, password, "");
        Utilisateurs user_actuel = userdao.connexionUtilisateur(user);

        promo.setSize(1200, 800);
        promo.setTitle("Articles en Promotion - Maison Close");
        promo.setLayout(new BorderLayout());
        promo.getContentPane().setBackground(BACKGROUND_COLOR);

        // Barre de navigation
        JPanel navBar = createNavigationBar();
        promo.add(navBar, BorderLayout.NORTH);

        // Panel principal avec fond bordeaux
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel du contenu avec fond blanc
        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JPanel productsGrid = new JPanel(new GridLayout(0, 4, 15, 15));
        productsGrid.setBackground(Color.WHITE);

        try {
            ArticleDAOImpl articleDAO = new ArticleDAOImpl(dao);
            java.util.List<Article> articles = articleDAO.getArticlesEnPromotion();

            for (Article article : articles) {
                JPanel productCard = createProductCard(article); // Créer la carte pour chaque article
                productsGrid.add(productCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement des articles en promotion : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        // Ajouter la grille des produits dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(productsGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        innerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(innerPanel, BorderLayout.CENTER);

        // Ajouter le JScrollPane à la fenêtre promo
        promo.add(mainPanel, BorderLayout.CENTER);

        // Bouton Retour en bas de la section promo
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        JButton returnButton = new JButton("Retour");
        returnButton.addActionListener(fenetreControl); // ActionListener pour le bouton Retour
        bottomPanel.add(returnButton);
        promo.add(bottomPanel, BorderLayout.SOUTH);
    }

    /// Fenêtre vue du profil utilisateur
    public void setProfil()
    {
        /// Appel de la requête connexion pour utiliser le profil actuel
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
        Utilisateurs user = new Utilisateurs(0, "", "", email, password, "");
        Utilisateurs user_actuel = userdao.connexionUtilisateur(user);

        profil.setSize(1200, 800);
        profil.setTitle("Profil - Maison Close");
        profil.setLayout(new BorderLayout());
        profil.getContentPane().setBackground(BACKGROUND_COLOR);

        // Barre de navigation
        JPanel navBar = createNavigationBar();
        profil.add(navBar, BorderLayout.NORTH);

        // Panel principal avec fond bordeaux
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel du contenu avec fond blanc
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Panel pour la photo et les informations
        JPanel profileInfoPanel = new JPanel(new BorderLayout(30, 0));
        profileInfoPanel.setBackground(Color.WHITE);

        // Photo de profil
        JPanel photoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(240, 240, 240));
                g.fillRect(0, 0, 200, 200);
            }
        };
        photoPanel.setPreferredSize(new Dimension(200, 200));
        photoPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));

        // Informations du profil
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        // Style pour les labels
        Font titleFont = new Font("Arial", Font.BOLD, 24);
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font valueFont = new Font("Arial", Font.PLAIN, 16);

        JLabel titleLabel = new JLabel("Informations du profil");
        titleLabel.setFont(titleFont);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Nom
        JPanel nomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nomPanel.setBackground(Color.WHITE);
        JLabel nomLabel = new JLabel("Nom: ");
        nomLabel.setFont(labelFont);
        JLabel nomValue = new JLabel(user_actuel.getNom());
        nomValue.setFont(valueFont);
        nomPanel.add(nomLabel);
        nomPanel.add(nomValue);
        infoPanel.add(nomPanel);

        // Prénom
        JPanel prenomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        prenomPanel.setBackground(Color.WHITE);
        JLabel prenomLabel = new JLabel("Prénom: ");
        prenomLabel.setFont(labelFont);
        JLabel prenomValue = new JLabel(user_actuel.getPrenom());
        prenomValue.setFont(valueFont);
        prenomPanel.add(prenomLabel);
        prenomPanel.add(prenomValue);
        infoPanel.add(prenomPanel);

        // Email
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.setBackground(Color.WHITE);
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(labelFont);
        JLabel emailValue = new JLabel(user_actuel.getEmail());
        emailValue.setFont(valueFont);
        emailPanel.add(emailLabel);
        emailPanel.add(emailValue);
        infoPanel.add(emailPanel);

        // Type de compte
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setBackground(Color.WHITE);
        JLabel typeLabel = new JLabel("Type de compte: ");
        typeLabel.setFont(labelFont);
        JLabel typeValue = new JLabel(user_actuel.getType_utilisateur());
        typeValue.setFont(valueFont);
        typePanel.add(typeLabel);
        typePanel.add(typeValue);
        infoPanel.add(typePanel);

        profileInfoPanel.add(photoPanel, BorderLayout.WEST);
        profileInfoPanel.add(infoPanel, BorderLayout.CENTER);

        contentPanel.add(profileInfoPanel, BorderLayout.NORTH);

        JPanel buttongen = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttongen.setBackground(Color.WHITE);

        JButton HistoButton = new JButton("Historique");
        HistoButton.setFont(new Font("Arial", Font.PLAIN, 14));
        HistoButton.setBackground(new Color(51, 122, 183));
        HistoButton.setForeground(Color.WHITE);
        HistoButton.setBorderPainted(false);
        HistoButton.setFocusPainted(false);
        HistoButton.addActionListener(fenetreControl);
        buttongen.add(HistoButton);

        contentPanel.add(buttongen, BorderLayout.SOUTH);

        // Panel pour les boutons d'action (uniquement pour les admins)
        if (user_actuel.getType_utilisateur().equals("admin")) {
            JPanel actionPanel = new JPanel();
            actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
            actionPanel.setBackground(Color.WHITE);
            actionPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

            JLabel actionTitle = new JLabel("Actions administrateur");
            actionTitle.setFont(titleFont);
            actionTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
            actionPanel.add(actionTitle);
            actionPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            String[] adminActions = {
                    "Ajouter article",
                    "Editer un article",
                    "Gerer les dossiers clients",
                    "Statistiques"
            };
            JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
            buttonsPanel.setBackground(Color.WHITE);

            for (String action : adminActions) {
                JButton actionButton = new JButton(action);
                actionButton.setFont(new Font("Arial", Font.PLAIN, 14));
                actionButton.setBackground(new Color(51, 122, 183));
                actionButton.setForeground(Color.WHITE);
                actionButton.setBorderPainted(false);
                actionButton.setFocusPainted(false);
                actionButton.addActionListener(fenetreControl);
                buttonsPanel.add(actionButton);
            }

            actionPanel.add(buttonsPanel);
            contentPanel.add(actionPanel, BorderLayout.CENTER);
        }

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Bouton Retour en bas
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        JButton returnButton = new JButton("Retour");
        returnButton.addActionListener(fenetreControl);
        bottomPanel.add(returnButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        profil.add(mainPanel);
    }

    /// Gestion des erreurs
    public void setEvent(String titre, String description)
    {
        event.setSize(300, 100);
        event.getContentPane().removeAll();
        event.setTitle(titre);
        JPanel text = new JPanel();
        JLabel label_event = new JLabel(description);
        text.add(label_event);
        JPanel erreur_button = new JPanel();
        addButton(erreur_button, "Retour");
        event.add(text, BorderLayout.CENTER);
        event.add(erreur_button, BorderLayout.SOUTH);
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
        addButton(paiement_button, "Annuler");
        paiement.add(paiement_text);
        paiement.add(num_carte);
        paiement.add(expiration);
        paiement.add(cvv_text);
        paiement.add(paiement_button);
    }

    /// Ajout d'un bouton sur une page
    public void addButton(JPanel panel, String label)
    {
        JButton button = new JButton(label);
        if (fenetreControl != null)
        {
            button.addActionListener(fenetreControl);
        }
        panel.add(button);
    }

    /// Vue Articles
    public void setListeArticles()
    {
        articles.setTitle("Articles");
        articles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        articles.setSize(1200, 800);
        articles.setLocationRelativeTo(null);
        articles.setLayout(new BorderLayout());
        articles.getContentPane().setBackground(BACKGROUND_COLOR);

        // Barre de navigation
        JPanel navBar = createNavigationBar();
        articles.add(navBar, BorderLayout.NORTH);

        // Panel principal avec fond bordeaux
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel pour la liste verticale des articles avec fond blanc
        JPanel articlesList = new JPanel();
        articlesList.setLayout(new BoxLayout(articlesList, BoxLayout.Y_AXIS));
        articlesList.setBackground(Color.WHITE);
        articlesList.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        try {
            DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
            ArticleDAOImpl articleDAO = new ArticleDAOImpl(dao);
            java.util.List<Article> articles = articleDAO.listerArticles();

            for (Article article : articles) {
                JPanel articlePanel = createArticlePanel(article);
                articlesList.add(articlePanel);
                articlesList.add(Box.createRigidArea(new Dimension(0, 10))); // Espacement entre les articles
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement des articles : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(articlesList);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bouton Retour en bas
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        JButton returnButton = new JButton("Retour");
        returnButton.addActionListener(fenetreControl);
        bottomPanel.add(returnButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        articles.add(mainPanel);
    }

    private JPanel createArticlePanel(Article article) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Panel gauche pour l'image
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(200, 200));
        imagePanel.setBackground(Color.WHITE);

        String imagePath = getImageFileName(article.getNom(), article.getCategorie());
        String defaultImagePath = "Main/src/View/image/default_product.png";

        try {
            // Construire le chemin complet de l'image
            String fullImagePath = imagePath != null ?
                    String.format("Main/src/View/image/%s/%s",
                            article.getCategorie().toLowerCase()
                                    .replace("é", "e")
                                    .replace("è", "e")
                                    .replace("à", "a")
                                    .replace("ù", "u")
                                    .replace("ç", "c"),
                            imagePath) :
                    defaultImagePath;

            // Vérifier si le fichier existe
            File imageFile = new File(fullImagePath);
            if (!imageFile.exists()) {
                System.out.println("Image non trouvée: " + fullImagePath);
                fullImagePath = defaultImagePath;
            }

            // Charger et redimensionner l'image
            ImageIcon imageIcon = new ImageIcon(fullImagePath);
            Image image = imageIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(imageLabel);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image pour " + article.getNom() + ": " + e.getMessage());
            // Utiliser une image par défaut en cas d'erreur
            ImageIcon defaultIcon = new ImageIcon(defaultImagePath);
            Image defaultImage = defaultIcon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            JLabel defaultLabel = new JLabel(new ImageIcon(defaultImage));
            defaultLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(defaultLabel);
        }

        panel.add(imagePanel, BorderLayout.WEST);

        // Panel central pour les informations
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(article.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        JLabel descLabel = new JLabel("<html><div style='width:500px'>" + article.getDescription() + "</div></html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel priceLabel = new JLabel("Prix : " + article.getPrixUnitaire() + " €");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel stockLabel = new JLabel("Stock disponible : " + article.getStock());
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(stockLabel);

        panel.add(infoPanel);

        // Panel droit pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.WHITE);

        JButton detailsButton = new JButton("Détails");
        detailsButton.setActionCommand("Détails" + article.getId());
        detailsButton.addActionListener(fenetreControl);

        JPanel panel_quantite = new JPanel();
        JLabel label_quantite = new JLabel("Quantité");
        panel_quantite.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel_quantite.add(label_quantite);
        quantite = new TextField(10);
        panel_quantite.add(quantite);
        buttonPanel.add(panel_quantite);


        JButton addButton = new JButton("Ajouter au panier");
        addButton.setActionCommand("Ajouter au panier" + article.getId());
        addButton.addActionListener(fenetreControl);

        buttonPanel.add(detailsButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(addButton);

        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }

    /// Méthode pour obtenir le nom du fichier image correspondant
    private String getImageFileName(String nomArticle, String categorie) {
        // Normalisation de la catégorie
        String categorieNormalisee = categorie.toLowerCase()
                .replace("vêtements", "vetements")
                .replace("vêtement", "vetements")
                .replace("é", "e")
                .replace("è", "e")
                .replace("à", "a")
                .replace("ù", "u")
                .replace("ç", "c");

        // Normalisation du nom
        String nom = nomArticle.toLowerCase()
                .replace("é", "e")
                .replace("è", "e")
                .replace("à", "a")
                .replace("ù", "u")
                .replace("ç", "c");

        // Pour chaque catégorie, retourner le nom exact du fichier
        switch (categorieNormalisee) {
            case "nourriture":
                if (nom.contains("cafe") || nom.contains("café")) return "cafe_bio.png";
                if (nom.contains("chocolat")) return "chocolat_noir.png";
                if (nom.contains("miel")) return "miel_lavande.png";
                if (nom.contains("pates") || nom.contains("pâtes")) return "pates_completes.png";
                if (nom.contains("huile")) return "huile_olive.png";
                if (nom.contains("confiture")) return "confiture_framboise.png";
                if (nom.contains("the") || nom.contains("thé")) return "the_matcha.png";
                if (nom.contains("biscuit")) return "biscuits_amandes.png";
                break;

            case "vetements":
                if (nom.contains("t-shirt") || nom.contains("tshirt")) return "tshirt_coton.png";
                if (nom.contains("jean")) return "jean_slim.png";
                if (nom.contains("robe")) return "robe_ete.png";
                if (nom.contains("veste")) return "veste_laine.png";
                if (nom.contains("chaussures")) return "chaussures_running.png";
                if (nom.contains("chemise")) return "chemise_lin.png";
                if (nom.contains("legging")) return "legging_yoga.png";
                if (nom.contains("manteau")) return "manteau_hiver.png";
                break;

            case "livres":
                if (nom.contains("petit prince")) return "petit_prince.png";
                if (nom.contains("1984")) return "1984_livre.png";
                if (nom.contains("art") && nom.contains("guerre")) return "art_guerre.png";
                if (nom.contains("cuisine") || nom.contains("vegetarienne")) return "cuisine_vegetarienne.png";
                if (nom.contains("guide") && nom.contains("japon")) return "guide_japon.png";
                if (nom.contains("seigneur")) return "seigneur_anneaux.png";
                if (nom.contains("astrophysics")) return "astrophysics.png";
                if (nom.contains("pouvoir") && nom.contains("moment")) return "pouvoir_moment.png";
                break;

            case "decoration":
                if (nom.contains("joconde")) return "joconde.png";
                if (nom.contains("nuit") || nom.contains("etoilee")) return "nuit_etoilee.png";
                if (nom.contains("liberte")) return "liberte_peuple.png";
                if (nom.contains("guernica")) return "guernica.png";
                if (nom.contains("napoleon")) return "napoleon.png";
                if (nom.contains("jeanne")) return "jeanne_arc.png";
                if (nom.contains("marie")) return "marie_antoinette.png";
                if (nom.contains("sphere")) return "sphere_armillaire.png";
                break;
        }

        return "default_product.png";
    }

    /// Articles : Bouton retour
    private void creerRetour() {
        PannelRetour = new JPanel();
        PannelRetour.setBackground(new Color(240, 240, 240));
        PannelRetour.setPreferredSize(new Dimension(getWidth(), 100));
        PannelRetour.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PannelRetour.setLayout(new GridBagLayout());
        addButton(PannelRetour, "Retour");
    }

    /// Vue Panier
    public void setPanier() {
        panierFrame.setTitle("Panier");
        panierFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panierFrame.setSize(1200, 800);
        panierFrame.setLocationRelativeTo(null);
        panierFrame.setLayout(new BorderLayout());
        panierFrame.getContentPane().setBackground(BACKGROUND_COLOR);

        /// Barre de navigation
        JPanel navBar = createNavigationBar();
        panierFrame.add(navBar, BorderLayout.NORTH);

        /// Panel principal avec fond bordeaux
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /// Panel du contenu avec fond blanc
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /// Panel gauche pour les articles du panier
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE);

        /// Titre "Cart"
        JLabel cartTitle = new JLabel("Panier");
        cartTitle.setFont(new Font("Arial", Font.BOLD, 24));
        cartTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        cartItemsPanel.add(cartTitle);

        /// Chargement du panier
        try {
            DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
            UtilisateurDAOImpl userdao = new UtilisateurDAOImpl(dao);
            Utilisateurs user = new Utilisateurs(0, "", "", email, password, "");
            Utilisateurs user_actuel = userdao.connexionUtilisateur(user);
            Client client = new Client(user_actuel.getIdentifiant(), user_actuel.getNom(),
                    user_actuel.getPrenom(), user_actuel.getEmail(),
                    user_actuel.getMotDePasse(), user_actuel.getType_utilisateur());

            PanierDAOImpl panierDao = new PanierDAOImpl(dao);
            Map<Article, Integer> articles_liste = panierDao.panierArticles(client);
            List<Article> articles = new ArrayList<>();
            List<Integer> liste_quantite = new ArrayList<>();
            /// Regrouper Article avec sa quantité utilisé par l'utilisateur
            for(int i = 0 ; i < articles_liste.size() ; i++)
            {
                articles.add(articles_liste.keySet().iterator().next());
                liste_quantite.add(articles_liste.get(articles_liste.keySet().iterator().next()));
            }

            System.out.println();
            double total = 0.0;

            for (Article article : articles)
            {
                JPanel itemPanel = createCartItemPanel(article);
                cartItemsPanel.add(itemPanel);
                cartItemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                total += article.getPrixUnitaire()*liste_quantite.get(liste_quantite.size()-1); /// Prix unitaire * quantité voulue par l'utilisateur
            }

            /// Panel droit pour le résumé
            JPanel summaryPanel = new JPanel();
            summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
            summaryPanel.setBackground(Color.WHITE);
            summaryPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            summaryPanel.setPreferredSize(new Dimension(300, 0));

            /// Sous-total
            JPanel subtotalPanel = new JPanel(new BorderLayout());
            subtotalPanel.setBackground(Color.WHITE);
            subtotalPanel.add(new JLabel("Sous-total"), BorderLayout.WEST);
            subtotalPanel.add(new JLabel(String.format("%.2f €", total)), BorderLayout.EAST);
            summaryPanel.add(subtotalPanel);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            /// Livraison
            JPanel shippingPanel = new JPanel(new BorderLayout());
            shippingPanel.setBackground(Color.WHITE);
            shippingPanel.add(new JLabel("Livraison"), BorderLayout.WEST);
            shippingPanel.add(new JLabel("Gratuit"), BorderLayout.EAST);
            summaryPanel.add(shippingPanel);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            /// Total
            JPanel totalPanel = new JPanel(new BorderLayout());
            totalPanel.setBackground(Color.WHITE);
            JLabel totalLabel = new JLabel("Total");
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel totalAmount = new JLabel(String.format("%.2f €", total));
            totalAmount.setFont(new Font("Arial", Font.BOLD, 16));
            totalPanel.add(totalLabel, BorderLayout.WEST);
            totalPanel.add(totalAmount, BorderLayout.EAST);
            summaryPanel.add(totalPanel);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            /// Bouton Commander
            JButton checkoutButton = new JButton("Commander");
            checkoutButton.setBackground(new Color(0, 123, 255));
            checkoutButton.setForeground(Color.WHITE);
            checkoutButton.setFont(new Font("Arial", Font.BOLD, 14));
            checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            checkoutButton.setBorderPainted(false);
            checkoutButton.setPreferredSize(new Dimension(200, 40));
            checkoutButton.addActionListener(fenetreControl);

            /// Bouton Retour
            JButton returnButton = new JButton("Retour");
            returnButton.setBackground(new Color(0, 123, 255));
            returnButton.setForeground(Color.WHITE);
            returnButton.setFont(new Font("Arial", Font.BOLD, 14));
            returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnButton.setBorderPainted(false);
            returnButton.setPreferredSize(new Dimension(200, 40));
            returnButton.addActionListener(fenetreControl);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.WHITE);

            buttonPanel.add(checkoutButton);
            buttonPanel.add(Box.createVerticalStrut(10));
            buttonPanel.add(returnButton);

            summaryPanel.add(buttonPanel);

            /// Ajout des panels au contentPanel
            contentPanel.add(new JScrollPane(cartItemsPanel), BorderLayout.CENTER);
            contentPanel.add(summaryPanel, BorderLayout.EAST);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement du panier : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        panierFrame.add(mainPanel);
    }

    private JPanel createCartItemPanel(Article article) {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 0, 10, 0)
        ));

        // Image du produit
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(100, 100));

        String defaultImagePath = "Main/src/View/image/default_product.png";

        try {
            // Normaliser la catégorie pour le chemin du dossier
            String categorieDossier = article.getCategorie().toLowerCase()
                    .replace("vêtements", "vetements")
                    .replace("vêtement", "vetements")
                    .replace("é", "e")
                    .replace("è", "e")
                    .replace("à", "a")
                    .replace("ù", "u")
                    .replace("ç", "c")
                    .replace("ê", "e");

            // Obtenir le nom du fichier image
            String imageFileName = getImageFileName(article.getNom(), article.getCategorie());

            // Construire le chemin complet
            String fullImagePath;
            if (imageFileName.equals("default_product.png")) {
                fullImagePath = defaultImagePath;
            } else {
                fullImagePath = String.format("Main/src/View/image/%s/%s", categorieDossier, imageFileName);
            }

            // Vérifier si le fichier existe
            File imageFile = new File(fullImagePath);
            if (!imageFile.exists()) {
                System.out.println("Image non trouvée: " + fullImagePath + ", utilisation de l'image par défaut");
                fullImagePath = defaultImagePath;
            }

            ImageIcon imageIcon = new ImageIcon(fullImagePath);
            Image image = imageIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
            ImageIcon defaultIcon = new ImageIcon(defaultImagePath);
            Image defaultImage = defaultIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            JLabel defaultLabel = new JLabel(new ImageIcon(defaultImage));
            defaultLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(defaultLabel, BorderLayout.CENTER);
        }

        // Informations de l'article
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(article.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // Sélecteur de quantité
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        quantityPanel.setBackground(Color.WHITE);
        JComboBox<Integer> quantitySelector = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        quantitySelector.setPreferredSize(new Dimension(60, 25));
        quantityPanel.add(quantitySelector);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(quantityPanel);

        // Prix
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pricePanel.setBackground(Color.WHITE);
        JLabel priceLabel = new JLabel(String.format("%.2f €", article.getPrixUnitaire()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pricePanel.add(priceLabel);

        itemPanel.add(imagePanel, BorderLayout.WEST);
        itemPanel.add(infoPanel, BorderLayout.CENTER);
        itemPanel.add(pricePanel, BorderLayout.EAST);

        return itemPanel;
    }

    /// Vue nouvelle commande (adresse à renseigner, le reste est récupéré)
    public void setNewCommande()
    {
        ajout_commande = new JFrame();
        ajout_commande.setSize(900, 700);
        ajout_commande.setTitle("Commande");
        ajout_commande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ajout_commande.getContentPane().setBackground(BACKGROUND_COLOR);
        ajout_commande.setLayout(new BoxLayout(ajout_commande.getContentPane(), BoxLayout.Y_AXIS));
        JPanel inscrire_adresse = new JPanel();
        JLabel label3 = new JLabel("Nom");
        inscrire_adresse.add(label3);
        JPanel ajout_article_button = new JPanel();
        adresse = new TextField(10);
        inscrire_adresse.add(adresse);
        addButton(ajout_article_button, "Payer");
        addButton(ajout_article_button, "Retour");
        ajout_commande.add(inscrire_adresse);
        ajout_commande.add(ajout_article_button);
    }

    /// Vue modif Article
    public void setModifArticle()
    {
        modif_article.setTitle("Modifier un article");
        modif_article.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modif_article.setSize(1200, 800);
        modif_article.setLocationRelativeTo(null);
        modif_article.setLayout(new BorderLayout());
        modif_article.getContentPane().setBackground(BACKGROUND_COLOR);

        /// Ajout de la barre de navigation
        JPanel navBar = createNavigationBar();
        modif_article.add(navBar, BorderLayout.NORTH);

        /// Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        /// Titre
        mainPanel.add(createTitlePanel("Sélectionnez l'article à modifier"), BorderLayout.NORTH);

        /// Contenu
        ProduitsModif();
        creerRetour();

        mainPanel.add(Scroll, BorderLayout.CENTER);
        mainPanel.add(PannelRetour, BorderLayout.SOUTH);
        modif_article.add(mainPanel);
    }

    /// Vue Modification d'un produit (pour admin)
    private void ProduitsModif()
    {
        try
        {
            Liste = new JPanel();
            Liste.setBackground(Color.WHITE);
            Liste.setLayout(new GridLayout(0, 3, 20, 20));

            /// Création du panneau de recherche et filtres
            JPanel searchPanel = new JPanel();
            searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
            searchPanel.setBackground(Color.WHITE);
            searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            /// Barre de recherche
            JPanel searchBarPanel = new JPanel();
            searchBarPanel.setLayout(new BoxLayout(searchBarPanel, BoxLayout.X_AXIS));
            searchBarPanel.setBackground(Color.WHITE);

            JTextField searchField = new JTextField(20);
            searchField.setPreferredSize(new Dimension(300, 35));
            searchField.setFont(new Font("Arial", Font.PLAIN, 14));
            searchField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));

            JButton searchButton = new JButton("Rechercher");
            searchButton.setBackground(new Color(51, 122, 183));
            searchButton.setForeground(Color.WHITE);
            searchButton.setFont(new Font("Arial", Font.BOLD, 14));
            searchButton.setBorderPainted(false);
            searchButton.setFocusPainted(false);
            searchButton.setPreferredSize(new Dimension(120, 35));

            searchBarPanel.add(searchField);
            searchBarPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            searchBarPanel.add(searchButton);

            /// Filtres par catégorie
            JPanel filterPanel = new JPanel();
            filterPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            filterPanel.setBackground(Color.WHITE);

            String[] categories = {"Tous", "Nourriture", "Vêtements", "Livres", "Décoration"};
            JComboBox<String> categoryFilter = new JComboBox<>(categories);
            categoryFilter.setFont(new Font("Arial", Font.PLAIN, 14));
            categoryFilter.setPreferredSize(new Dimension(150, 35));

            filterPanel.add(new JLabel("Catégorie: "));
            filterPanel.add(categoryFilter);

            searchPanel.add(searchBarPanel);
            searchPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            searchPanel.add(filterPanel);

            DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");

            ArticleDAOImpl articleDAO = new ArticleDAOImpl(dao);

            java.util.List<Article> articles = articleDAO.listerArticles();

            /// Affichage des articles
            for (Article article : articles)
            {
                JPanel articlePanel = modifArticle(article);
                Liste.add(articlePanel);
            }

            /// Ajout des composants au catalogue
            catalogue.add(searchPanel, BorderLayout.NORTH);
            Scroll = new JScrollPane(Liste);
            Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            Scroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            catalogue.add(Scroll, BorderLayout.CENTER);

            /// Gestion des événements de recherche
            searchButton.addActionListener(e -> {
                String searchText = searchField.getText().toLowerCase();
                String selectedCategory = (String)categoryFilter.getSelectedItem();

                Liste.removeAll();
                for (Article article : articles)
                {
                    boolean matchesSearch = article.getNom().toLowerCase().contains(searchText) ||
                            article.getDescription().toLowerCase().contains(searchText);
                    boolean matchesCategory = selectedCategory.equals("Tous") ||
                            article.getCategorie().equals(selectedCategory);

                    if (matchesSearch && matchesCategory)
                    {
                        JPanel articlePanel = modifArticle(article);
                        Liste.add(articlePanel);
                    }
                }
                Liste.revalidate();
                Liste.repaint();
            });

            categoryFilter.addActionListener(e -> {
                String selectedCategory = (String)categoryFilter.getSelectedItem();
                searchButton.doClick();
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
            /// Afficher un message d'erreur à l'utilisateur
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement du catalogue : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /// Modifier un article (seulement pour les admins)
    private JPanel modifArticle(Article article)
    {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        /// 1. Image du produit
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(250, 200));
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setLayout(new BorderLayout());

        /// Chargement de l'image en fonction de la catégorie
        String defaultImagePath = "Main/src/View/image/default_product.png";

        /// Convertir la catégorie en version sans accent pour le chemin du dossier
        String categorieDossier = article.getCategorie().toLowerCase()
                .replace("é", "e")
                .replace("è", "e")
                .replace("à", "a")
                .replace("ù", "u")
                .replace("ç", "c")
                .replace("ê", "e");

        String imagePath = defaultImagePath;

        /// Obtenir le nom du fichier image en fonction de la catégorie
        String fileName = getImageFileName(article.getNom(), article.getCategorie());
        if (fileName != null) {
            /// Utiliser la catégorie sans accent pour le chemin
            imagePath = String.format("Main/src/View/image/%s/%s", categorieDossier, fileName);
            //System.out.println("Tentative de chargement de l'image: " + imagePath);
        }

        try {
            File imageFile = new File(imagePath);
            ImageIcon imageIcon;

            if (imageFile.exists()) {
                imageIcon = new ImageIcon(imagePath);
                //System.out.println("Image trouvée et chargée: " + imagePath);
            } else {
                //System.out.println("Image non trouvée: " + imagePath + ", utilisation de l'image par défaut");
                imageIcon = new ImageIcon(defaultImagePath);
            }

            // Redimensionner l'image
            Image img = imageIcon.getImage();
            int originalWidth = imageIcon.getIconWidth();
            int originalHeight = imageIcon.getIconHeight();

            if (originalWidth > 0 && originalHeight > 0) {
                double ratio = Math.min(230.0 / originalWidth, 180.0 / originalHeight);
                int newWidth = (int) (originalWidth * ratio);
                int newHeight = (int) (originalHeight * ratio);

                Image newImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(newImg));
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                imagePanel.add(imageLabel, BorderLayout.CENTER);
            } else {
                throw new Exception("Image invalide");
            }
        } catch (Exception e) {
            //System.out.println("Erreur lors du chargement de l'image pour " + article.getNom() + ": " + e.getMessage());
            JLabel placeholder = new JLabel(article.getNom().substring(0, 1).toUpperCase());
            placeholder.setFont(new Font("Arial", Font.BOLD, 48));
            placeholder.setForeground(new Color(200, 200, 200));
            placeholder.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(placeholder, BorderLayout.CENTER);
        }

        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(imagePanel);
        p.add(Box.createRigidArea(new Dimension(0, 10)));

        // Informations du produit
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Nom du produit
        JLabel nameLabel = new JLabel(article.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(nameLabel);

        // Prix et remise
        if (article.getSeuil_remise() > 0) {
            JLabel discountLabel = new JLabel("-" + article.getSeuil_remise() + "%");
            discountLabel.setForeground(Color.RED);
            discountLabel.setFont(new Font("Arial", Font.BOLD, 12));
            discountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            infoPanel.add(discountLabel);
            infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JLabel priceLabel = new JLabel(String.format("%.2f €", article.getPrixUnitaire()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Stock
        JLabel stockLabel = new JLabel("Stock: " + article.getStock());
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        stockLabel.setForeground(new Color(100, 100, 100));
        stockLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        infoPanel.add(stockLabel);

        p.add(infoPanel);
        p.add(Box.createRigidArea(new Dimension(0, 10)));

        // Boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton detailsButton = new JButton("Détails");
        detailsButton.setActionCommand("Détails" + article.getId());
        detailsButton.addActionListener(fenetreControl);

        JButton modifierButton = new JButton("Modifier");
        modifierButton.setActionCommand("Modifier" + article.getId());
        modifierButton.addActionListener(fenetreControl);
        buttonPanel.add(detailsButton);
        buttonPanel.add(modifierButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(buttonPanel);

        return p;
    }

    /// Modification d'un article (seulement pour les admins), sur la même base que ajouter un article
    public void setModifierArticle(Article article)
    {
        id_article = article.getId();
        modif = new JFrame();
        modif.setSize(900, 700);
        modif.setTitle("Modification d'un article");
        modif.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modif.getContentPane().setBackground(BACKGROUND_COLOR);
        modif.setLayout(new BoxLayout(modif.getContentPane(), BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Categorie");
        modif.add(label1);
        String[] type = {"Electromenager", "Nourriture", "Necessite"};
        categorie = new JComboBox<>(type); /// Liste associée
        categorie.setSelectedIndex(0);
        categorie.setBounds(50, 50, 100, 20);
        modif.add(categorie);
        JLabel label2 = new JLabel("Marque");
        modif.add(label2);
        String[] type2 = {"Bosch", "Samsung", "Nestlé"};
        marque = new JComboBox<>(type2); /// Liste associée
        marque.setSelectedIndex(0);
        marque.setBounds(50, 50, 100, 20);
        modif.add(marque);
        JPanel inscrire_nom = new JPanel();
        JLabel label3 = new JLabel("Nom");
        inscrire_nom.add(label3);
        JPanel ajout_article_button = new JPanel();
        nom_article = new TextField(10);
        prix = new TextField(10);
        stock = new TextField(10);
        description = new TextField(10);
        seuil_remise = new TextField(10);
        nom_article.setText(article.getNom());
        description.setText(article.getDescription());
        prix.setText(String.valueOf(article.getPrixUnitaire()));
        stock.setText(String.valueOf(article.getStock()));
        seuil_remise.setText(String.valueOf(article.getSeuil_remise()));
        categorie.setSelectedItem(article.getCategorie());
        marque.setSelectedItem(article.getMarque());
        inscrire_nom.add(nom_article);
        JPanel inscrire_prix = new JPanel();
        JLabel label4 = new JLabel("Prix");
        inscrire_prix.add(label4);
        inscrire_prix.add(prix);
        JLabel label5 = new JLabel("Stock");
        JPanel inscrire_stock = new JPanel();
        inscrire_stock.add(label5);
        inscrire_stock.add(stock);
        JLabel label6 = new JLabel("Seuil de remise");
        JPanel inscrire_remise = new JPanel();
        inscrire_remise.add(label6);
        inscrire_remise.add(seuil_remise);
        JPanel decrire = new JPanel();
        JLabel label7 = new JLabel("Description");
        decrire.add(label7);
        decrire.add(description);
        addButton(ajout_article_button, "Mettre à jour");
        addButton(ajout_article_button, "Retour");
        modif.add(inscrire_nom);
        modif.add(inscrire_prix);
        modif.add(inscrire_stock);
        modif.add(inscrire_remise);
        modif.add(decrire);
        modif.add(ajout_article_button);
    }

    public void stats() {
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        stats = new JFrame();
        stats.setSize(500, 400);
        stats.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stats.setTitle("Profil");
        stats.getContentPane().setBackground(BACKGROUND_COLOR);
        stats.setLayout(new BorderLayout());
        float pmax, pmin = 0;
        int nombreClients = 0;
        int nbarticle = 0;

        JPanel titre = new JPanel();
        titre.setLayout(new FlowLayout(FlowLayout.CENTER));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel Titre = new JLabel("STATISTIQUES", SwingConstants.CENTER);
        Titre.setFont(new Font("Arial", Font.BOLD, 18));
        titre.setForeground(Color.WHITE);
        titre.setBackground(Color.BLACK);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setOpaque(true);
        titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        titre.setPreferredSize(new Dimension(300, 50));
        titre.setMaximumSize(new Dimension(300, 50));
        titre.setMinimumSize(new Dimension(300, 50));
        titre.setLayout(new BorderLayout());
        titre.add(Titre, BorderLayout.CENTER);

        stats.add(titre, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        centerPanel.add(new JLabel("Nombre de client : "));
        UtilisateurDAOImpl utilisateurDAO = new UtilisateurDAOImpl(dao);
        nombreClients = utilisateurDAO.compteClients();
        centerPanel.add(new JLabel(String.valueOf(nombreClients)));
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(new JLabel("Nombre de produits :"));
        ArticleDAOImpl articleDAO = new ArticleDAOImpl(dao);
        nbarticle = articleDAO.compteArticle();
        centerPanel.add(new JLabel(String.valueOf(nbarticle)));
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(new JLabel("Prix maximum : "));
        pmax = articleDAO.Articlemax();
        centerPanel.add(new JLabel(String.valueOf(pmax)));
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(new JLabel("Prix minimum : "));
        pmin = articleDAO.Articlemin();
        centerPanel.add(new JLabel(String.valueOf(pmin)));
        centerPanel.add(Box.createVerticalStrut(15));
        stats.add(centerPanel, BorderLayout.CENTER);

        JPanel pied_page = new JPanel();
        pied_page.setLayout(new FlowLayout(FlowLayout.CENTER));
        pied_page.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JButton retour = new JButton("Retour");
        retour.addActionListener(e -> fenetreControl.actionPerformed(e));
        pied_page.add(retour);
        stats.add(pied_page, BorderLayout.SOUTH);

        stats.revalidate();
        stats.repaint();

        // TODO Implementer Stats, voirs si il n'y a pas besoin de rajouter une valeur dans la table de donnée qui compte le nombre d'achat total.

    }

    private JPanel createTitlePanel(String title) {
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(getWidth(), 100));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(50, 50, 50));

        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        return titlePanel;
    }

    /// Vue nouvelle article (seulement pour les administrateurs)
    public void setNewArticle()
    {
        ajout_article.setSize(900, 700);
        ajout_article.setTitle("Nouvel Article");
        ajout_article.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ajout_article.getContentPane().setBackground(BACKGROUND_COLOR);
        ajout_article.setLayout(new BoxLayout(ajout_article.getContentPane(), BoxLayout.Y_AXIS));

        // Catégorie
        JLabel labelCategorie = new JLabel("Categorie");
        ajout_article.add(labelCategorie);
        String[] categories = {"Electromenager", "Nourriture", "Necessite"};
        categorie = new JComboBox<>(categories);
        categorie.setSelectedIndex(0);
        categorie.setBounds(50, 50, 100, 20);
        ajout_article.add(categorie);

        // Marque
        JLabel labelMarque = new JLabel("Marque");
        ajout_article.add(labelMarque);
        String[] marques = {"Bosch", "Samsung", "Nestlé"};
        marque = new JComboBox<>(marques);
        marque.setSelectedIndex(0);
        marque.setBounds(50, 50, 100, 20);
        ajout_article.add(marque);

        // Nom
        JPanel panelNom = new JPanel();
        JLabel labelNom = new JLabel("Nom");
        panelNom.add(labelNom);
        nom_article = new TextField(10);
        panelNom.add(nom_article);
        ajout_article.add(panelNom);

        // Prix
        JPanel panelPrix = new JPanel();
        JLabel labelPrix = new JLabel("Prix");
        panelPrix.add(labelPrix);
        prix = new TextField(10);
        panelPrix.add(prix);
        ajout_article.add(panelPrix);

        // Stock
        JPanel panelStock = new JPanel();
        JLabel labelStock = new JLabel("Stock");
        panelStock.add(labelStock);
        stock = new TextField(10);
        panelStock.add(stock);
        ajout_article.add(panelStock);

        // Seuil de remise
        JPanel panelRemise = new JPanel();
        JLabel labelRemise = new JLabel("Seuil de remise");
        panelRemise.add(labelRemise);
        seuil_remise = new TextField(10);
        panelRemise.add(seuil_remise);
        ajout_article.add(panelRemise);

        // Description
        JPanel panelDescription = new JPanel();
        JLabel labelDescription = new JLabel("Description");
        panelDescription.add(labelDescription);
        description = new TextField(10);
        panelDescription.add(description);
        ajout_article.add(panelDescription);

        // Boutons
        JPanel panelBoutons = new JPanel();
        addButton(panelBoutons, "Valider l'ajout");
        addButton(panelBoutons, "Retour");
        ajout_article.add(panelBoutons);
    }

    /// Vue du catalogue
    public void setCatalogue() {
        catalogue.setSize(1200, 800);
        catalogue.setTitle("Catalogue");
        catalogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        catalogue.setLayout(new BorderLayout());
        catalogue.getContentPane().setBackground(BACKGROUND_COLOR);

        // Barre de navigation
        JPanel navBar = createNavigationBar();
        catalogue.add(navBar, BorderLayout.NORTH);

        // Panel principal avec fond bordeaux
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel du contenu avec fond blanc
        JPanel innerPanel = new JPanel(new BorderLayout());
        innerPanel.setBackground(Color.WHITE);
        innerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Barre de recherche en haut
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.WHITE);

        JTextField searchField = new JTextField(40);
        searchField.setPreferredSize(new Dimension(600, 35));

        JButton searchButton = new JButton("Rechercher");
        searchButton.setBackground(new Color(51, 122, 183));
        searchButton.setForeground(Color.WHITE);
        searchButton.setPreferredSize(new Dimension(120, 35));
        searchButton.setBorderPainted(false);

        searchPanel.add(searchField);
        searchPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchPanel.add(searchButton);

        // Panel des catégories
        JPanel categoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        categoryPanel.setBackground(Color.WHITE);
        JLabel catLabel = new JLabel("Catégorie:");
        String[] categories = {"Tous", "Nourriture", "Vêtements", "Livres", "Décoration"};
        JComboBox<String> categoryFilter = new JComboBox<>(categories);
        categoryFilter.setPreferredSize(new Dimension(200, 30));
        categoryPanel.add(catLabel);
        categoryPanel.add(categoryFilter);

        // Panel du haut combinant recherche et catégories
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(searchPanel, BorderLayout.NORTH);
        topPanel.add(categoryPanel, BorderLayout.CENTER);
        innerPanel.add(topPanel, BorderLayout.NORTH);

        // Panel principal avec filtres à gauche et grille de produits à droite
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Filtres à gauche
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 10, 10, 20)
        ));
        filterPanel.setPreferredSize(new Dimension(200, 0));

        // Titre des filtres
        JLabel filterTitle = new JLabel("Filtre");
        filterTitle.setFont(new Font("Arial", Font.BOLD, 16));
        filterPanel.add(filterTitle);
        filterPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ajout des différents filtres
        String[] filterCategories = {"Prix", "Disponibilité", "Nouveautés", "Prix bas"};
        for (String filter : filterCategories) {
            JCheckBox checkbox = new JCheckBox(filter);
            checkbox.setBackground(Color.WHITE);
            filterPanel.add(checkbox);
            filterPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        contentPanel.add(filterPanel, BorderLayout.WEST);

        // Grille de produits à droite
        JPanel productsGrid = new JPanel(new GridLayout(0, 4, 15, 15));
        productsGrid.setBackground(Color.WHITE);

        try {
            DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
            ArticleDAOImpl articleDAO = new ArticleDAOImpl(dao);
            java.util.List<Article> articles = articleDAO.listerArticles();

            for (Article article : articles) {
                JPanel productCard = createProductCard(article);
                productsGrid.add(productCard);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement du catalogue : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(productsGrid);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        innerPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(innerPanel, BorderLayout.CENTER);

        // Bouton Retour en bas
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        JButton returnButton = new JButton("Retour");
        returnButton.addActionListener(fenetreControl);
        bottomPanel.add(returnButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        catalogue.add(mainPanel);
    }

    public void afficherDetailsArticle(Article article) {
        // Créer une nouvelle fenêtre pour les détails
        JDialog detailsDialog = new JDialog(this, "Détails du produit", true);
        detailsDialog.setLayout(new BorderLayout(10, 10));
        detailsDialog.setSize(400, 500);
        detailsDialog.setLocationRelativeTo(this);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        // Image du produit
        try {
            ImageIcon imageIcon = new ImageIcon(article.getImage());
            Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(imageLabel);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image: " + e.getMessage());
        }

        // Informations détaillées
        JLabel nameLabel = new JLabel(article.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(nameLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextArea descriptionArea = new JTextArea(article.getDescription());
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setLineWrap(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(descriptionArea);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Prix et remise
        if (article.getSeuil_remise() > 0) {
            JLabel discountLabel = new JLabel("Remise: -" + article.getSeuil_remise() + "%");
            discountLabel.setForeground(Color.RED);
            discountLabel.setFont(new Font("Arial", Font.BOLD, 14));
            discountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(discountLabel);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        JLabel priceLabel = new JLabel(String.format("Prix: %.2f €", article.getPrixUnitaire()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(priceLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel stockLabel = new JLabel("Stock disponible: " + article.getStock());
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stockLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(stockLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Bouton Fermer
        JButton closeButton = new JButton("Fermer");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> detailsDialog.dispose());
        contentPanel.add(closeButton);

        detailsDialog.add(contentPanel);
        detailsDialog.setVisible(true);
    }

    /// Vue historique des paiements
    public void setHistoriquePaiement(List<Paiement> paiements)
    {
        histoPaiement.setTitle("Historique des paiements");
        histoPaiement.setSize(700, 400);
        histoPaiement.setLocationRelativeTo(null);

        String[] columns = {"Numéro de commande", "Date", "Montant", "Moyen de paiement", "Statut", ""};
        Object[][] data = new Object[paiements.size()][columns.length];
        for (int i = 0; i < paiements.size(); i++) {
            Paiement p = paiements.get(i);
            JButton boutonVoir = new JButton("Voir");
            boutonVoir.setActionCommand("Voir" + p.getCommande().getId());
            boutonVoir.addActionListener(e -> fenetreControl.actionPerformed(e));
            data[i][0] = String.valueOf(p.getCommande().getId());
            data[i][1] = p.getDate();
            data[i][2] = String.format("%.2f €", p.getMontant());
            data[i][3] = p.getMoyen();
            data[i][4] = p.getStatut();
            data[i][5] = boutonVoir;
        }
        JTable table = new JTable(new javax.swing.table.DefaultTableModel(data, columns))
        {
            public Class<?> getColumnClass(int column) {
            return (column == 5) ? JButton.class : Object.class; // Important pour afficher le bouton
        }
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Seul le bouton est éditable
            }
        };

        // Renderer pour afficher correctement les JButton
        table.getColumn("").setCellRenderer((table1, value, isSelected, hasFocus, row, column) -> (Component) value);

        table.getColumn("").setCellEditor(new DefaultCellEditor(new JCheckBox())
        {
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
            {
                return (Component) value;
            }
        });

        histoPaiement.add(new JScrollPane(table), BorderLayout.CENTER);

        creerRetour();
        histoPaiement.add(PannelRetour, BorderLayout.SOUTH);
    }

    /// Vue Historique commande
    public void setHistoCommande(Commande commande)
    {
        histoCommande.setTitle("Historique");
        histoCommande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        histoCommande.setSize(1200, 800);
        histoCommande.setLocationRelativeTo(null);
        histoCommande.setLayout(new BorderLayout());
        histoCommande.getContentPane().setBackground(BACKGROUND_COLOR);

        /// Panel principal avec fond bordeaux
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /// Panel du contenu avec fond blanc
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /// Panel gauche pour les articles du panier
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setBackground(Color.WHITE);

        /// Chargement du panier
        try {
            DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
            Map<Article, Integer> articles_liste = commande.getArticles();
            List<Article> articles = new ArrayList<>();
            List<Integer> liste_quantite = new ArrayList<>();
            /// Titre "Cart"
            JLabel cartTitle = new JLabel("Commande n°"+commande.getId());
            cartTitle.setFont(new Font("Arial", Font.BOLD, 24));
            cartTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            cartItemsPanel.add(cartTitle);
            /// Regrouper Article avec sa quantité utilisé par l'utilisateur
            for(int i = 0 ; i < articles_liste.size() ; i++)
            {
                articles.add(articles_liste.keySet().iterator().next());
                liste_quantite.add(articles_liste.get(articles_liste.keySet().iterator().next()));
            }

            System.out.println();
            double total = 0.0;

            for (Article article : articles)
            {
                JPanel itemPanel = createCartItemPanel(article);
                cartItemsPanel.add(itemPanel);
                cartItemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
                total += article.getPrixUnitaire()*liste_quantite.get(liste_quantite.size()-1); /// Prix unitaire * quantité voulue par l'utilisateur
            }

            /// Panel droit pour le résumé
            JPanel summaryPanel = new JPanel();
            summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
            summaryPanel.setBackground(Color.WHITE);
            summaryPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
            summaryPanel.setPreferredSize(new Dimension(300, 0));

            /// Sous-total
            JPanel subtotalPanel = new JPanel(new BorderLayout());
            subtotalPanel.setBackground(Color.WHITE);
            subtotalPanel.add(new JLabel("Sous-total"), BorderLayout.WEST);
            subtotalPanel.add(new JLabel(String.format("%.2f €", total)), BorderLayout.EAST);
            summaryPanel.add(subtotalPanel);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            /// Livraison
            JPanel shippingPanel = new JPanel(new BorderLayout());
            shippingPanel.setBackground(Color.WHITE);
            shippingPanel.add(new JLabel("Livraison"), BorderLayout.WEST);
            shippingPanel.add(new JLabel("Gratuit"), BorderLayout.EAST);
            summaryPanel.add(shippingPanel);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            /// Total
            JPanel totalPanel = new JPanel(new BorderLayout());
            totalPanel.setBackground(Color.WHITE);
            JLabel totalLabel = new JLabel("Total");
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            JLabel totalAmount = new JLabel(String.format("%.2f €", total));
            totalAmount.setFont(new Font("Arial", Font.BOLD, 16));
            totalPanel.add(totalLabel, BorderLayout.WEST);
            totalPanel.add(totalAmount, BorderLayout.EAST);
            summaryPanel.add(totalPanel);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 20)));

            /// Bouton Retour
            JButton returnButton = new JButton("Retour");
            returnButton.setBackground(new Color(0, 123, 255));
            returnButton.setForeground(Color.WHITE);
            returnButton.setFont(new Font("Arial", Font.BOLD, 14));
            returnButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            returnButton.setBorderPainted(false);
            returnButton.setPreferredSize(new Dimension(200, 40));
            returnButton.addActionListener(fenetreControl);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
            buttonPanel.setBackground(Color.WHITE);

            buttonPanel.add(Box.createVerticalStrut(10));
            buttonPanel.add(returnButton);

            summaryPanel.add(buttonPanel);

            /// Ajout des panels au contentPanel
            contentPanel.add(new JScrollPane(cartItemsPanel), BorderLayout.CENTER);
            contentPanel.add(summaryPanel, BorderLayout.EAST);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Erreur lors du chargement du panier : " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        histoCommande.add(mainPanel);
    }

    public void setGestionClient(List<Utilisateurs> clients)
    {
        gestionClient.setTitle("Gestion des clients & fidélité");
        gestionClient.setSize(900, 500);
        gestionClient.setLocationRelativeTo(null);

        String[] columns = {"Nom", "Prénom", "Email", "Commandes", "Total Achats", "Niveau", "Promo"};
        String[][] data = new String[clients.size()][columns.length];
        for (int i = 0; i < clients.size(); i++) {
            Utilisateurs c = clients.get(i);
            data[i][0] = c.getNom();
            data[i][1] = c.getPrenom();
            data[i][2] = c.getEmail();
            data[i][3] = String.valueOf(c.getNbCommandes());
            data[i][4] = String.format("%.2f €", c.getTotalAchats());
            data[i][5] = c.getNiveauFidelite();
            data[i][6] = String.valueOf((c.getNiveauFidelite().equals("\"Gold\") || c.getNiveauFidelite().equals(\"Platine\")) ? \"-10%\" : \"-5%\""))); // exemple
        }
        JTable table = new JTable(data, columns);
        gestionClient.add(new JScrollPane(table), BorderLayout.CENTER);

        creerRetour();
        gestionClient.add(PannelRetour, BorderLayout.SOUTH);
    }
}
