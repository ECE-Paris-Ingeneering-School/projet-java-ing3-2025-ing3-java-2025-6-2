package Model;
import Dao.ArticleDAO;
import Dao.ArticleDAOImpl;
import Dao.DaoFactory;
import Dao.UtilisateurDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;
import java.io.File;

import Control.FenetreControl;
import View.VueArticle;

/** Fenetres constitue l'ensemble des interfaces graphiques utilisées au cour du projet
 * @L'ensemble des liens entre elles sont effectuées ici
 */
public class Fenetres extends Component
{
    public JFrame inscrire, connecter, accueil, profil, event, paiement, catalogue, ajout_article, panier; /// Fenêtres de navigation
    public TextField nom, prenom, mail, mdp, mail_id, mdp_id; /// Zones de saisie connexion et inscriptions
    public TextField numero_carte, expiration_carte, cvv; /// Zones de saisies paiement
    public String email, password; /// Inscription utilisateur dans la database

    /// Gestion articles
    public TextField nom_article, description, prix, stock, seuil_remise; /// Zones de saisies article
    public JComboBox<String> type_compte, payment_type, categorie, marque; /// Choix du mode de paiement et du type de compte

    /// Fenetre catalogue
    public JPanel PTitre, Liste, PannelRetour;
    public JLabel titrre;
    public JScrollPane Scroll;
    public Random random = new Random();

    public ArticleDAO articleDao;
    private FenetreControl fenetreControl;

    /// Constructeur de chaque fenêtre
    public Fenetres()
    {
        // Initialisation de toutes les fenêtres
        inscrire = new JFrame();
        connecter = new JFrame();
        accueil = new JFrame();
        profil = new JFrame();
        event = new JFrame();
        paiement = new JFrame();
        catalogue = new JFrame();
        ajout_article = new JFrame();

        // Configuration des fenêtres
        inscrire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connecter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        accueil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        event.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        paiement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        catalogue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ajout_article.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /// Initialisation des autres composants
        DaoFactory daoFactory = DaoFactory.getInstance("ecommerce_db", "root", "");
        articleDao = new ArticleDAOImpl(daoFactory);
    }

    public void setControleur(FenetreControl controleur)
    {
        this.fenetreControl = controleur;
    }

    /// Fenêtre inscription utilisateur
    public void setInscrire()
    {
        inscrire.setSize(900, 700);
        inscrire.setTitle("Inscription");
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
        connecter.setSize(900, 700);
        connecter.setTitle("Connexion");
        
        // Set background color to a soft gray
        connecter.getContentPane().setBackground(new Color(245, 245, 245));
        
        // Use GridBagLayout for better control over component placement
        connecter.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Create main panel with modern styling
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));
        
        // Title with elegant styling
        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        
        // Custom text field style
        class StyledTextField extends TextField
        {
            StyledTextField(int columns)
            {
                super(columns);
                setBackground(new Color(250, 250, 250));
                setForeground(new Color(60, 60, 60));
                setFont(new Font("Arial", Font.PLAIN, 14));
            }
        }
        
        // Email field with modern styling
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
        
        // Password field with modern styling
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
        passwordPanel.setBackground(Color.WHITE);
        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(100, 100, 100));
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mdp_id = new StyledTextField(20);
        mdp_id.setPreferredSize(new Dimension(300, 35));
        mdp_id.setEchoChar('•');  // Modern bullet point for password
        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        passwordPanel.add(mdp_id);
        mainPanel.add(passwordPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 35)));
        
        // Buttons panel with elegant styling
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JButton validerButton = new StyledButton("Valider", true);
        JButton inscrireButton = new StyledButton("Inscrire", false);
        

        validerButton.addActionListener(fenetreControl);
        buttonsPanel.add(validerButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        inscrireButton.addActionListener(fenetreControl);
        buttonsPanel.add(inscrireButton);
        
        mainPanel.add(buttonsPanel);
        
        // Add main panel to frame with proper sizing
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
        // Configuration de la fenêtre principale
        accueil.setSize(1200, 800);
        accueil.setTitle("Accueil - Maison Close");
        accueil.setLayout(new BorderLayout());
        accueil.getContentPane().removeAll();

        // Barre de navigation
        JPanel navBar = createNavigationBar();
        accueil.add(navBar, BorderLayout.NORTH);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // En-tête
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(240, 240, 240));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Bienvenue sur Maison Close");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel("Votre marketplace d'art en ligne");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        headerPanel.add(descLabel);

        // Section catégories
        JPanel categoriesPanel = new JPanel();
        categoriesPanel.setLayout(new GridLayout(2, 2, 20, 20));
        categoriesPanel.setBackground(Color.WHITE);
        categoriesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] categories = {"Peintures", "Sculptures", "Photographie", "Art numérique"};
        for (String category : categories) {
            JButton categoryButton = new JButton(category);
            categoryButton.setFont(new Font("Arial", Font.PLAIN, 16));
            categoryButton.setBackground(new Color(240, 240, 240));
            categoryButton.setBorderPainted(false);
            categoryButton.addActionListener(e -> {
                catalogue.setVisible(true);
            });
            categoriesPanel.add(categoryButton);
        }

        // Assemblage final
        mainPanel.add(headerPanel);
        mainPanel.add(categoriesPanel);

        // Ajout du panel principal à la fenêtre
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        accueil.add(scrollPane, BorderLayout.CENTER);

        // Rafraîchir la fenêtre
        accueil.revalidate();
        accueil.repaint();
    }

    private JPanel createNavigationBar() {
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(Color.WHITE);
        navBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));

        // Logo et recherche (gauche)
        JPanel leftSection = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        leftSection.setBackground(Color.WHITE);
        
        // Chargement et redimensionnement du logo
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

        // Menu principal (centre)
        JPanel centerSection = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 1));
        centerSection.setBackground(Color.WHITE);
        
        // Création des boutons avec ActionListener
        JButton articlesButton = createNavButton("Articles", e -> {
            catalogue.setVisible(true);
        });
        
        JButton promotionsButton = createNavButton("Promotions", e -> {
            // TODO: Implémenter la vue des promotions
            //System.out.println("Vue promotions à implémenter");
        });
        
        JButton catalogueButton = createNavButton("Catalogue", e -> {
            catalogue.setVisible(true);
        });

        centerSection.add(articlesButton);
        centerSection.add(promotionsButton);
        centerSection.add(catalogueButton);

        // Panier et login (droite)
        JPanel rightSection = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        rightSection.setBackground(Color.WHITE);
        
        JButton profileButton = createNavButton("Profil", e -> {
            profil.setVisible(true);
            accueil.setVisible(false);
        });

        JButton loginButton = createNavButton("Deconnexion", e -> {
            connecter.setVisible(true);
            accueil.setVisible(false);
        });
        
        JButton cartButton = createNavButton("Panier", e -> {
            panier.setVisible(true);
        });
        
        rightSection.add(cartButton);
        rightSection.add(loginButton);
        rightSection.add(profileButton);

        navBar.add(leftSection, BorderLayout.WEST);
        navBar.add(centerSection, BorderLayout.CENTER);
        navBar.add(rightSection, BorderLayout.EAST);

        return navBar;
    }

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

    private JPanel createProductCard(Article article) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));

        // Image du produit
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(200, 200));
        imagePanel.setBackground(Color.WHITE);
        
        try {
            String imagePath = getImageFileName(article.getNom(), article.getCategorie());
            if (imagePath != null) {
                ImageIcon icon = new ImageIcon("Main/src/View/image/" + article.getCategorie().toLowerCase() + "/" + imagePath);
                Image scaledImage = icon.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
                imagePanel.add(imageLabel);
            } else {
                JLabel placeholderLabel = new JLabel("No Image");
                placeholderLabel.setPreferredSize(new Dimension(180, 180));
                placeholderLabel.setHorizontalAlignment(JLabel.CENTER);
                imagePanel.add(placeholderLabel);
            }
        } catch (Exception e) {
            JLabel errorLabel = new JLabel("Image Error");
            errorLabel.setPreferredSize(new Dimension(180, 180));
            errorLabel.setHorizontalAlignment(JLabel.CENTER);
            imagePanel.add(errorLabel);
        }

        card.add(imagePanel);

        // Informations produit
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel(article.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel priceLabel = new JLabel(String.format("%.2f €", article.getPrixUnitaire()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        if (article.getSeuil_remise() > 0) {
            JLabel discountLabel = new JLabel("-" + article.getSeuil_remise() + "%");
            discountLabel.setForeground(Color.RED);
            infoPanel.add(discountLabel);
        }
        
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(priceLabel);
        
        card.add(infoPanel);
        return card;
    }

    private JPanel createPromoSection() {
        JPanel promoPanel = new JPanel(new BorderLayout());
        promoPanel.setBackground(new Color(245, 245, 245));
        promoPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        promoPanel.setPreferredSize(new Dimension(0, 200));

        JLabel promoTitle = new JLabel("Offres Spéciales");
        promoTitle.setFont(new Font("Arial", Font.BOLD, 24));
        
        JLabel promoDesc = new JLabel("Jusqu'à 50% de réduction sur une sélection d'articles");
        promoDesc.setFont(new Font("Arial", Font.PLAIN, 16));
        
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(245, 245, 245));
        textPanel.add(promoTitle);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(promoDesc);
        
        promoPanel.add(textPanel, BorderLayout.CENTER);

        return promoPanel;
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
        profil.setSize(500, 400);
        profil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        profil.setTitle("Profil");
        profil.setLayout(new BorderLayout());
        String Nom = "FF";
        String Prenom = "bebreb";
        int Age = 19;
        double Thune = 1000;


        JPanel profil_button = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton(profil_button, "Accueil");
        addButton(profil_button, "Catalogue");
        profil.add(profil_button, BorderLayout.NORTH);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(new JLabel("TITRE"));
        profil.add(titlePanel, BorderLayout.PAGE_START);

        JPanel panel_info = new JPanel(new BorderLayout());

        JPanel profil_photo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(20, 20, 175, 200);
            }
        };
        profil_photo.setPreferredSize(new Dimension(175, 200));
        JPanel profil_text = new JPanel();
        profil_text.setLayout(new BoxLayout(profil_text, BoxLayout.Y_AXIS));
        profil_text.setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 0));

        //A dynnamiser

        profil_text.add(new JLabel("Nom: " + Nom));
        profil_text.add(Box.createVerticalStrut(10));
        profil_text.add(new JLabel("Prénom: " + Prenom));
        profil_text.add(Box.createVerticalStrut(10));
        profil_text.add(new JLabel("Âge: " + Age));
        profil_text.add(Box.createVerticalStrut(10));
        profil_text.add(new JLabel("Argent: " + Thune + " €"));
        panel_info.add(profil_photo, BorderLayout.WEST);
        panel_info.add(profil_text, BorderLayout.CENTER);

        JPanel pied_page = new JPanel();
        pied_page.setLayout(new BoxLayout(pied_page, BoxLayout.Y_AXIS));
        pied_page.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));


        if (user_actuel.getType_utilisateur().equals("admin")) {
            addButton(pied_page, "Ajouter article");
            addButton(pied_page, "Modifier un article");
            addButton(pied_page, "Gerer les dossiers clients");
            addButton(pied_page, "Statistiques");
        }
        pied_page.add(Box.createVerticalStrut(10));
        pied_page.add(new JLabel("FIN"));
        pied_page.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel PASDEBUGDEPOSITION = new JPanel();
        PASDEBUGDEPOSITION.setLayout(new BoxLayout(PASDEBUGDEPOSITION, BoxLayout.Y_AXIS));
        PASDEBUGDEPOSITION.add(titlePanel);
        PASDEBUGDEPOSITION.add(panel_info);
        PASDEBUGDEPOSITION.add(Box.createVerticalGlue());
        PASDEBUGDEPOSITION.add(pied_page);

        profil.add(PASDEBUGDEPOSITION, BorderLayout.CENTER);
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

    private void Produits()
    {
        try {
            Liste = new JPanel();
            Liste.setBackground(Color.WHITE);
            Liste.setLayout(new GridLayout(0, 3, 20, 20));

            // Création du panneau de recherche et filtres
            JPanel searchPanel = new JPanel();
            searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
            searchPanel.setBackground(Color.WHITE);
            searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Barre de recherche
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

            // Filtres par catégorie
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

            //System.out.println("Tentative de connexion à la base de données...");
            // Récupération des articles depuis la base de données
            DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
            //System.out.println("DaoFactory créé avec succès");
            
            ArticleDAOImpl articleDAO = new ArticleDAOImpl(dao);
            //System.out.println("ArticleDAOImpl créé avec succès");
            
            java.util.List<Article> articles = articleDAO.listerArticles();
            //System.out.println("Nombre d'articles récupérés : " + articles.size());

            // Affichage des articles
            for (Article article : articles) {
                //System.out.println("Ajout de l'article : " + article.getNom());
                JPanel articlePanel = CreerListe(article);
                Liste.add(articlePanel);
            }

            // Ajout des composants au catalogue
            catalogue.add(searchPanel, BorderLayout.NORTH);
            Scroll = new JScrollPane(Liste);
            Scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            Scroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            catalogue.add(Scroll, BorderLayout.CENTER);

            // Gestion des événements de recherche
            searchButton.addActionListener(e -> {
                String searchText = searchField.getText().toLowerCase();
                String selectedCategory = (String)categoryFilter.getSelectedItem();
                
                Liste.removeAll();
                for (Article article : articles) {
                    boolean matchesSearch = article.getNom().toLowerCase().contains(searchText) ||
                                          article.getDescription().toLowerCase().contains(searchText);
                    boolean matchesCategory = selectedCategory.equals("Tous") || 
                                            article.getCategorie().equals(selectedCategory);
                    
                    if (matchesSearch && matchesCategory) {
                        JPanel articlePanel = CreerListe(article);
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
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Erreur lors du chargement du catalogue : " + e.getMessage());
            // Afficher un message d'erreur à l'utilisateur
            JOptionPane.showMessageDialog(this,
                "Erreur lors du chargement du catalogue : " + e.getMessage(),
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private JPanel CreerListe(Article article) {
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        // 1. Image du produit
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(250, 200));
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setLayout(new BorderLayout());
        
        // Chargement de l'image en fonction de la catégorie
        String defaultImagePath = "Main/src/View/image/default_product.png";
        
        // Convertir la catégorie en version sans accent pour le chemin du dossier
        String categorieDossier = article.getCategorie().toLowerCase()
            .replace("é", "e")
            .replace("è", "e")
            .replace("à", "a")
            .replace("ù", "u")
            .replace("ç", "c")
            .replace("ê", "e");
            
        String imagePath = defaultImagePath;

        // Obtenir le nom du fichier image en fonction de la catégorie
        String fileName = getImageFileName(article.getNom(), article.getCategorie());
        if (fileName != null) {
            // Utiliser la catégorie sans accent pour le chemin
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
        p.add(Box.createRigidArea(new Dimension(0, 15)));

        // 2. Nom du produit
        JLabel nameLabel = new JLabel(article.getNom(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(50, 50, 50));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(nameLabel);
        p.add(Box.createRigidArea(new Dimension(0, 10)));

        // 3. Marque
        JLabel brandLabel = new JLabel("Marque: " + article.getMarque());
        brandLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        brandLabel.setForeground(new Color(100, 100, 100));
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(brandLabel);

        // 4. Catégorie
        JLabel categoryLabel = new JLabel("Catégorie: " + article.getCategorie());
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        categoryLabel.setForeground(new Color(100, 100, 100));
        categoryLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(categoryLabel);

        // 5. Prix
        JLabel priceLabel = new JLabel(String.format("%.2f €", article.getPrixUnitaire()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(priceLabel);

        // 6. Stock
        JLabel stockLabel = new JLabel("Stock: " + article.getStock() + " unités");
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stockLabel.setForeground(new Color(100, 100, 100));
        stockLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(stockLabel);

        // 7. Description (truncated)
        String shortDescription = article.getDescription().length() > 100 ? 
            article.getDescription().substring(0, 100) + "..." : article.getDescription();
        JLabel descLabel = new JLabel("<html>" + shortDescription + "</html>");
        descLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        descLabel.setForeground(new Color(120, 120, 120));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(descLabel);

        p.add(Box.createRigidArea(new Dimension(0, 15)));

        // 8. Bouton Voir détails
        JButton voirButton = new JButton("Voir détails");
        voirButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        voirButton.setBackground(new Color(51, 122, 183));
        voirButton.setForeground(Color.WHITE);
        voirButton.setFont(new Font("Arial", Font.BOLD, 14));
        voirButton.setBorderPainted(false);
        voirButton.setFocusPainted(false);
        voirButton.setPreferredSize(new Dimension(120, 35));
        voirButton.addActionListener(e -> {
            VueArticle vueArticle = new VueArticle();
            vueArticle.afficherDetails(article);
        });
        p.add(voirButton);

        return p;
    }

    // Méthode pour obtenir le nom du fichier image correspondant
    private String getImageFileName(String nomArticle, String categorie) {
        // Conversion en minuscules et normalisation
        String nom = nomArticle.toLowerCase()
            .replace("é", "e")
            .replace("è", "e")
            .replace("à", "a")
            .replace("ù", "u")
            .replace("ç", "c");

        // Pour chaque catégorie, retourner le nom exact du fichier
        switch (categorie.toLowerCase()) {
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
            case "vêtements":
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
                if (nom.contains("cuisine") || nom.contains("vegetarienne") || nom.contains("végétarienne")) return "cuisine_vegetarienne.png";
                if (nom.contains("guide") && nom.contains("japon")) return "guide_japon.png";
                if (nom.contains("seigneur")) return "seigneur_anneaux.png";
                if (nom.contains("astrophysics")) return "astrophysics.png";
                if (nom.contains("pouvoir") && nom.contains("moment")) return "pouvoir_moment.png";
                break;

            case "decoration":
            case "décoration":
                if (nom.contains("joconde")) return "joconde.png";
                if (nom.contains("nuit") || nom.contains("etoilee") || nom.contains("étoilée")) return "nuit_etoilee.png";
                if (nom.contains("liberte") || nom.contains("liberté")) return "liberte_peuple.png";
                if (nom.contains("guernica")) return "guernica.png";
                if (nom.contains("napoleon") || nom.contains("napoléon")) return "napoleon.png";
                if (nom.contains("jeanne")) return "jeanne_arc.png";
                if (nom.contains("marie")) return "marie_antoinette.png";
                if (nom.contains("sphere") || nom.contains("sphère")) return "sphere_armillaire.png";
                break;
        }

        // Si aucune correspondance n'est trouvée, retourner null pour utiliser l'image par défaut
        //System.out.println("Aucune image trouvée pour l'article: " + nomArticle + " (catégorie: " + categorie + ")");
        return null;
    }

    private void creerRetour() {
        PannelRetour = new JPanel();
        PannelRetour.setBackground(new Color(240, 240, 240));
        PannelRetour.setPreferredSize(new Dimension(getWidth(), 100));
        PannelRetour.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        PannelRetour.setLayout(new GridBagLayout());
        addButton(PannelRetour, "Retour");
    }

    public void setNewArticle()
    {
        ajout_article = new JFrame();
        ajout_article.setSize(900, 700);
        ajout_article.setTitle("Ajout article");
        ajout_article.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ajout_article.setLayout(new BoxLayout(ajout_article.getContentPane(), BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Categorie");
        ajout_article.add(label1);
        String[] type = {"Electromenager", "Nourriture", "Necessite"};
        categorie = new JComboBox<>(type); /// Liste associée
        categorie.setSelectedIndex(0);
        categorie.setBounds(50, 50, 100, 20);
        ajout_article.add(categorie);
        JLabel label2 = new JLabel("Marque");
        ajout_article.add(label2);
        String[] type2 = {"Bosch", "Samsung", "Nestlé"};
        marque = new JComboBox<>(type2); /// Liste associée
        marque.setSelectedIndex(0);
        marque.setBounds(50, 50, 100, 20);
        ajout_article.add(marque);
        JPanel inscrire_nom = new JPanel();
        JLabel label3 = new JLabel("Nom");
        inscrire_nom.add(label3);
        JPanel ajout_article_button = new JPanel();
        nom_article = new TextField(10);
        prix = new TextField(10);
        stock = new TextField(10);
        description = new TextField(10);
        seuil_remise = new TextField(10);
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
        addButton(ajout_article_button, "Valider l'ajout");
        addButton(ajout_article_button, "Retour");
        ajout_article.add(inscrire_nom);
        ajout_article.add(inscrire_prix);
        ajout_article.add(inscrire_stock);
        ajout_article.add(inscrire_remise);
        ajout_article.add(decrire);
        ajout_article.add(ajout_article_button);
    }

    public void setPanier()
    {
        panier = new JFrame();
        panier.setTitle("Panier");
        panier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panier.setSize(1200, 800);
        panier.setLocationRelativeTo(null);
        panier.setLayout(new BorderLayout());

        Titre();
        Produits();
        creerRetour();

        panier.add(PTitre, BorderLayout.NORTH);
        panier.add(Scroll, BorderLayout.CENTER);
        panier.add(PannelRetour, BorderLayout.SOUTH);
    }
}
