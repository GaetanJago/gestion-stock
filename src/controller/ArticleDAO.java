package controller;

import model.Article;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

//import static main.Main.logger;

/**
 * DAO class for the entity Article
 */
public class ArticleDAO extends BaseDAO<Article> {

    private final static Logger logger = Logger.getLogger(ArticleDAO.class);

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
        logger.info("Creation de l'article " + article.getName());
        super.create(article);
        if(article.getSection() != null){
            //logger.info("Ajout dans la section " + article.getSection().getName());
            article.getSection().addArticle(article);
        }

    }

    /**
     * delete an article in the database and update the article list of its section
     * @param article to delete
     */
    public void delete(Article article){
        logger.info("Suppression de l'article " + article.getName());
        if(article.getSection() != null) {
            article.getSection().removeArticle(article);
        }
        super.delete(article);
    }

}
