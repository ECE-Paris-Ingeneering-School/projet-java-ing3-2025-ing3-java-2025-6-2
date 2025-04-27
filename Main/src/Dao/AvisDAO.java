package Dao;

import Model.Article;
import Model.Avis;
import Model.Client;

import java.util.List;

/** Gestion des avis
 * @Par Alara
 * */
public interface AvisDAO
{
    /**Ajoute un avis
     * @param avis Le nouvel avis à ajouter
     * */
    public void ajouterAvis(Avis avis, Client client, Article article);

    /**
     * */
    List<Avis> getAvisParArticle(int idArticle);
}
