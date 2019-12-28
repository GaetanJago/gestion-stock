package controller;

import model.Article;

import javax.persistence.EntityManager;

public class ArticleDAO extends BaseDAO {

    public ArticleDAO(EntityManager em){
        super(em);
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
