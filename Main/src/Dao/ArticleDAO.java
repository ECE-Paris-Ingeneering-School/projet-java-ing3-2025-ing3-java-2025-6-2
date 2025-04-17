package Dao;

import Model.Article;
import Model.Utilisateurs;

public interface ArticleDAO
{
    public Article getArticle(Article article);

    /** Utilisé pour la création d'un compte
     * @Ajoute un nouvel utilisateur dans la base de données
     */
    public void ajouterArticle(Article article);

    /** Utilisé pour la connexion
     * @Permet à un utilisateur de se connecter
     */
    public void modifierArticle(Article article);
}
