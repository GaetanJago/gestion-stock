package controller;

import model.Article;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * DAO class for the entity Article
 */
public class ArticleDAO extends BaseDAO<Article> {

    public ArticleDAO(EntityManager em){
        super(em);
    }

    /**
     * get all articles inserted in the database
     * @return a list of all articles
     */
    @Override
    public List<Article> getAll() {
        Query query = super.em.createQuery("FROM Article");
        return query.getResultList();
    }

    /**
     * find an article in the database from an id
     * @param id of the article searched
     * @return the article found or an optional null if nothing has been found
     */
    @Override
    public Optional<Article> findById(int id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }


    /**
     * insert an article in the database and update the article list of its section
     * @param article to insert
     */
    public void create(Article article) {
        super.create(article);
        if(article.getSection() != null){
            article.getSection().addArticle(article);
        }

    }

    /**
     * delete an article in the database and update the article list of its section
     * @param article to delete
     */
    public void delete(Article article){
        if(article.getSection() != null) {
            article.getSection().removeArticle(article);
        }
        super.delete(article);
    }

}
