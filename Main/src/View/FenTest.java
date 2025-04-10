package View;


//test pour push, dont pay attention//


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FenTest extends JFrame {
    private JPanel PTitre;
    private JPanel Liste;
    private JPanel PannelRetour;
    private JButton Retour;
    private JLabel titrre;
    private JScrollPane Scroll;
    private Random random = new Random();

    public FenTest() {
        setTitle("Ma Fenêtre avec Panels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Titre();
        Produits();
        creerRetour();

        add(PTitre, BorderLayout.NORTH);
        add(Scroll, BorderLayout.CENTER);
        add(PannelRetour, BorderLayout.SOUTH);
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

        Voir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    "Détails du produit:\n" +
                            "Nom: " + nom + "\n" +
                            "Propriétaire: " + proprietaire + "\n" +
                            "Prix: " + prix + " €\n" +
                            "Stock: " + stock + " unités");
        });

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

        Retour.addActionListener(e -> {
            JOptionPane.showMessageDialog(FenTest.this,
                    "Retour");
        });

        PannelRetour.setLayout(new GridBagLayout());
        PannelRetour.add(Retour);
    }

}

