package Model;

import java.util.HashMap;
import java.util.Map;

import Dao.ArticleDAOImpl;
import Dao.DaoFactory;


/// Administrateur de l'application
public class Administrateur extends Utilisateurs
{

    public Administrateur(int identifiant, String nom, String prenom, String email, String motDePasse, String type_utilisateur)
    {
        super(identifiant, nom, prenom, email, motDePasse, type_utilisateur);
    }

    public void ajouterArticle(Article article)
    {
        DaoFactory dao = DaoFactory.getInstance("ecommerce_db", "root", "");
        ArticleDAOImpl artdao = new ArticleDAOImpl(dao);
        artdao.ajouterArticle(article);
    }

    public void modifierArticle(Article article) {
        // Implémentation de la modification d'article
    }

    public void supprimerArticle(Article article) {
        // Implémentation de la suppression d'article
    }

    public void ajouterReduction(Réduction reduction) {
        // Implémentation de l'ajout de réduction
    }

    public Map<String, Double> consulterStatistiquesVentes() {
        // Implémentation de la consultation des statistiques
        return new HashMap<>();
    }
}