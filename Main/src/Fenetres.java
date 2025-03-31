import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetres implements ActionListener {
    JPanel connecter_text, inscrire_text, connecter_button, inscrire_button;
    JFrame inscrire, connecter; /// View.Fenetre principale
    TextField affichage, nom, prenom, mail, mdp;
    private double valeur;
    private boolean virgule = false;
    private String op;
    private double nb1, nb2, res;

    public Fenetres()
    {
        setIdentification();
        setInscrire();
    }

    public void setInscrire()
    {
        inscrire = new JFrame();
        inscrire.setSize(900, 700);
        inscrire.setTitle("Inscription");
        inscrire.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inscrire.setLayout(new BoxLayout(inscrire.getContentPane(), BoxLayout.Y_AXIS));
        inscrire_text = new JPanel();
        JLabel label1 = new JLabel("Nom");
        inscrire_text.add(label1);
        inscrire_button = new JPanel();
        nom = new TextField(10);
        prenom = new TextField(10);
        mail = new TextField(10);
        mdp = new TextField(10);
        inscrire_text.add(nom);
        JLabel label2 = new JLabel("Prenom");
        inscrire_text.add(label2);
        inscrire_text.add(prenom);
        JLabel label3 = new JLabel("Mail");
        inscrire_text.add(label3);
        inscrire_text.add(mail);
        JLabel label4 = new JLabel("Mdp");
        inscrire_text.add(label4);
        inscrire_text.add(mdp);
        addButton(inscrire_button, "Valider");
        inscrire.add(inscrire_text, BorderLayout.CENTER);
        inscrire.add(inscrire_button, BorderLayout.SOUTH);
        inscrire.pack();
    }

    public void setIdentification()
    {
        connecter = new JFrame();
        connecter.setSize(900, 700);
        connecter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connecter.setTitle("Connexion");
        connecter.setLayout(new BoxLayout(connecter.getContentPane(), BoxLayout.Y_AXIS));
        connecter_text = new JPanel();
        JLabel label1 = new JLabel("Mail");
        connecter_text.add(label1);
        connecter_button = new JPanel();
        mail = new TextField(10);
        mdp = new TextField(10);
        connecter_text.add(mail);
        JLabel label2 = new JLabel("Mdp");
        connecter_text.add(label2);
        connecter_text.add(mdp);
        addButton(connecter_button, "Valider");
        addButton(connecter_button, "Inscrire");
        connecter.add(connecter_text, BorderLayout.CENTER);
        connecter.add(connecter_button, BorderLayout.SOUTH);
        connecter.pack();
    }

    public void affichage()
    {
        connecter.setVisible(true);
    }

    private void addButton(JPanel panel, String label)
    {
        JButton button = new JButton(label);
        button.addActionListener(this);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton) e.getSource();
        switch(button.getText())
        {
            case "Valider":
                break;
            case "Inscrire":
                inscrire.setVisible(true);
                connecter.setVisible(false);
                break;
        }
    }
}
