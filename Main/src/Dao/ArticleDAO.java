package Dao;

import Model.Article;

import java.util.List;

public interface ArticleDAO
{
    public Article getArticle(Article article);

    /** Utilisé pour la création d'un article
     * @Ajoute un nouvel article dans la base de données (réservé aux admins)
     */
    public void ajouterArticle(Article article);

    /** Utilisé pour la modification d'un article
     * @Modifie un article dans la base de données (réservé aux admins)
     */
    public void modifierArticle(Article article);

    /** Utilisé pour lister tous les articles
     * @Retourne la liste de tous les articles
     */
    public List<Article> listerArticles();

    /** Utilisé pour récupérer les nouveaux produits
     * @param limit Le nombre maximum de produits à retourner
     * @return La liste des derniers produits ajoutés
     */
    public List<Article> getNouveauxProduits(int limit);

    /** Utilisé pour récupérer les articles en promotion
     * @return La liste des articles en promotion
     */
    public List<Article> getArticlesEnPromotion();
}
