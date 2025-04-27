package Dao;

import Model.Avis;
import Model.Article;
import Model.Client;
import Model.Utilisateurs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvisDAOImpl implements AvisDAO
{
    private DaoFactory daoFactory;

    public AvisDAOImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouterAvis(Avis avis, Client client, Article article)
    {
        try (Connection connexion = daoFactory.getConnection()) {
            PreparedStatement ps = connexion.prepareStatement(
                    "INSERT INTO avis(id_avis, id_utilisateur, id_article, commentaire, note, date) VALUES (?, ?, ?, ?, ?, ?)"
            );
            ps.setInt(1, avis.getId());
            ps.setInt(2, client.getIdentifiant());
            ps.setInt(3, article.getId());
            ps.setString(4, avis.getCommentaire());
            ps.setInt(5, avis.getNote());
            ps.setString(6, avis.getDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Avis> getAvisParArticle(int id_article) {
        List<Avis> avisList = new ArrayList<>();
        ArticleDAOImpl articleDAO = new ArticleDAOImpl(daoFactory);
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT * FROM avis WHERE id_article = ?"
            );
            ps.setInt(1, id_article);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // À adapter selon la structure de vos objets
                int id = rs.getInt("id_avis");
                int idClient = rs.getInt("id_utilisateur");
                int idArticle = rs.getInt("id_article");
                String commentaire = rs.getString("commentaire");
                int note = rs.getInt("note");
                String date = rs.getString("date");
                // Il faut récupérer le client et l'article à partir de leur id
                Client client = new Client(idClient, "", "", "", "", "client"); // À remplacer par une vraie récupération
                Article article = articleDAO.getArticle(idArticle);
                avisList.add(new Avis(id, client, article, commentaire, note, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avisList;
    }
}