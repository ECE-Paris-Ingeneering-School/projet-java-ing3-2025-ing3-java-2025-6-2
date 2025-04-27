package Dao;

import Model.Article;

import java.util.List;

/** Gestion des articles et de la table associée
 * @Par Andy et Alara
 * */
public interface ArticleDAO
{
    /** Utilisé pour récupérer un article spécifique
     * @param id L'ID d'un article
     * @return L'article avec l'ID associé
     * */
    public Article getArticle(int id);

    /** Utilisé pour la création d'un article
     * @param article Un Article
     * @Ajoute un nouvel article dans la base de données (réservé aux admins)
     */
    public void ajouterArticle(Article article);

    /** Utilisé pour la modification d'un article
     * @param article Un article
     * @Modifie un article dans la base de données (réservé aux admins)
     */
    public void modifierArticle(Article article);

    /** Utilisé pour lister tous les articles
     * @return la liste de tous les articles
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

    /** Utilisé pour les statistiques
     * @return Le nombre d'articles
     * */
    public int compteArticle();

    /** Utilisé pour les statistiques
     * @return Le prix maximum parmi tous les articles
     * */
    public int Articlemax();

    /** Utilisé pour les statistiques
     * @return Le prix minimum parmi tous les articles
     * */
    public int Articlemin();
}
