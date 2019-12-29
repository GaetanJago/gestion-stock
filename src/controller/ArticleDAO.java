package controller;

import model.Article;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ArticleDAO extends BaseDAO<Article> {

    public ArticleDAO(EntityManager em){
        super(em);
    }

    @Override
    public List<Article> getAll() {
        Query query = super.em.createQuery("FROM Article");
        return query.getResultList();
    }

    @Override
    public Optional<Article> findById(int id) {
        return Optional.ofNullable(em.find(Article.class, id));
    }


    public void create(Article article) {
        super.create(article);
        if(article.getSection() != null){
            article.getSection().addArticle(article);
        }

    }

    public void delete(Article article){
        if(article.getSection() != null) {
            article.getSection().removeArticle(article);
        }
        super.delete(article);
    }

}
