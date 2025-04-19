package View;

import javax.swing.*;
import java.awt.*;

import Model.Article;
import java.io.File;

public class VueArticleDetail extends JFrame {
    private Article article;
    private JPanel mainPanel;
    private JPanel imagePanel;
    private JPanel infoPanel;
    private JPanel similarPanel;

    public VueArticleDetail(Article article) {
        this.article = article;
        setupFrame();
        createUI();
    }

    private void setupFrame() {
        setTitle("Détail du produit - " + article.getNom());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(mainPanel);
    }

    private void createUI() {
        createImageGallery();
        createProductInfo();
        createSimilarProducts();
    }

    private void createImageGallery() {
        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(400, 400));
        imagePanel.setBorder(BorderFactory.createTitledBorder("Image du produit"));

        String imagePath = getImagePath();
        ImageIcon imageIcon;
        
        if (imagePath != null) {
            imageIcon = new ImageIcon(imagePath);
            // Redimensionner l'image si nécessaire
            Image image = imageIcon.getImage();
            Image resizedImage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(resizedImage);
        } else {
            // Image par défaut si aucune image n'est trouvée
            imageIcon = new ImageIcon("Control.Main/src/View/image/default.png");
        }

        JLabel imageLabel = new JLabel(imageIcon);
        imagePanel.add(imageLabel);
        mainPanel.add(imagePanel, BorderLayout.WEST);
    }

    private void createProductInfo() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Informations produit"));

        // Nom du produit
        JLabel nameLabel = new JLabel("Nom: " + article.getNom());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        // Prix
        JLabel priceLabel = new JLabel(String.format("Prix: %.2f €", article.getPrixUnitaire()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        
        // Stock
        JLabel stockLabel = new JLabel("Stock disponible: " + article.getStock());
        
        // Description
        JTextArea descArea = new JTextArea(article.getDescription());
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setOpaque(false);
        descArea.setEditable(false);
        
        // Ajouter les composants
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(stockLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(new JLabel("Description:"));
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(descArea);
        
        mainPanel.add(infoPanel, BorderLayout.CENTER);
    }

    private void createSimilarProducts() {
        similarPanel = new JPanel();
        similarPanel.setBorder(BorderFactory.createTitledBorder("Produits similaires"));
        // À implémenter: affichage des produits similaires
        mainPanel.add(similarPanel, BorderLayout.SOUTH);
    }

    private String getImagePath() {
        String fileName = getImageFileName(article.getNom(), article.getCategorie());
        if (fileName == null) {
            return null;
        }
        String imagePath = "Control.Main/src/View/image/" + fileName;
        File imageFile = new File(imagePath);
        return imageFile.exists() ? imagePath : null;
    }

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
        System.out.println("Aucune image trouvée pour l'article: " + nomArticle + " (catégorie: " + categorie + ")");
        return null;
    }
} 