package test.controller;

import controller.ArticleDAO;
import controller.SectionDAO;
import main.Main;
import model.Article;
import model.Section;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class ArticleDAOTest {


    private static ArticleDAO articleDAO;
    private static SectionDAO sectionDAO;
    private static EntityManagerFactory emf;
    private static EntityManager em;



    //SETUP
    @BeforeClass
    public static void setUpBeforeClass(){
        emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
        em = emf.createEntityManager();

        articleDAO = new ArticleDAO(em);
        sectionDAO = new SectionDAO(em);
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void setUpAfterClass(){
        em.close();
        emf.close();
    }

    @Test
    public void testBasicCreate() {
        //Add a basic article
        Article article = new Article("Raquette de tennis", "Babolat", 40, 5);
        articleDAO.create(article);

        //get the new article in the database
        Article articleFound = articleDAO.findById(article.getId());

        Assert.assertEquals(article, articleFound);

    }

    @Test
    public void testCreateWithSection(){
        Article article = new Article("Chaussures de sport", "Nike", 70, 10);
        Section section = new Section("Chaussure");
        article.setSection(section);

        sectionDAO.create(section);
        articleDAO.create(article);

        //get the new article in the database
        Article articleFound = articleDAO.findById(article.getId());

        Assert.assertEquals(article, articleFound);
        //Check if the article is in the section article list
        Assert.assertTrue(sectionDAO.findById(section.getId()).getArticles().contains(article));
    }

    @Test
    public void testCreateDoNotWorkTwoTimesWithTheSameObject(){
        //Count how many articles are in the database before insert
        int nbArticlesBeforeInsert = articleDAO.getAll().size();

        Article article = new Article("Tee shirt", "Adidas", 25, 7);
        articleDAO.create(article);
        articleDAO.create(article);

        Assert.assertEquals(nbArticlesBeforeInsert+1, articleDAO.getAll().size());
    }

    @Test
    public void testGetAll(){
        //Count how many articles are in the database before insert
        int nbArticlesBeforeInsert = articleDAO.getAll().size();

        Article article = new Article("Ballon", "Puma", 30, 10);
        articleDAO.create(article);

        //Get list of all articles in the database
        List<Article> articleList = articleDAO.getAll();

        //Check that there is one more article in the list than before insert
        Assert.assertEquals(nbArticlesBeforeInsert+1, articleList.size());
        //Check that the last article is the one which we just added
        Assert.assertEquals(article, articleList.get(articleList.size()-1));
    }

    /*@Test
    public void testFindById(){
        Article article = new Article("Balle de tennis", "Artengo", 2, 40);

        Section section = new Section("Balle");
        article.setSection(section);
        sectionDAO.create(section);

        articleDAO.create(article);

        Assert.assertEquals(article, articleDAO.findById(article.getId()).get());
    }*/

    @Test
    public void testDelete(){
        Article article = new Article("Short", "Adidas", 30, 3);

        Section section = new Section("Short");
        sectionDAO.create(section);

        article.setSection(section);
        articleDAO.create(article);

        //Count how many articles are in the database before delete
        int nbArticlesBeforeDelete = articleDAO.getAll().size();

        articleDAO.delete(article);

        Assert.assertEquals(nbArticlesBeforeDelete-1, articleDAO.getAll().size());
        Assert.assertFalse(articleDAO.getAll().contains(article));
        Assert.assertFalse(sectionDAO.findById(section.getId()).getArticles().contains(article));
    }

    @Test
    public void testDeleteDoNotWorkTwoTimesWithTheSameObject(){
        Article article = new Article("Maillot de bain", "Adidas", 25, 5);
        articleDAO.create(article);

        //Count how many articles are in the database before delete
        int nbArticlesBeforeDelete = articleDAO.getAll().size();

        articleDAO.delete(article);
        articleDAO.delete(article);

        Assert.assertEquals(nbArticlesBeforeDelete-1, articleDAO.getAll().size());
    }

    @Test
    public void testFindBySection(){
        Article article = new Article("Chaussure de foot", "Nike", 50, 1);
        Article article2 = new Article("Raquette de tennis", "Babolat", 40, 5);
        Article article3 = new Article("Chaussure de marche", "Asics", 60, 2);
        Article article4 = new Article("Maillot de bain", "Adidas", 25, 5);

        Section section = new Section("Chaussure");
        article.setSection(section);
        article3.setSection(section);

        Section section2 = new Section("Piscine");
        article4.setSection(section2);

        sectionDAO.create(section);
        sectionDAO.create(section2);

        articleDAO.create(article);
        articleDAO.create(article2);
        articleDAO.create(article3);
        articleDAO.create(article4);

        List<Article> articlesInSection = articleDAO.findBySection(section);

        Assert.assertEquals(2, articlesInSection.size());

        Assert.assertTrue(articlesInSection.contains(article));
        Assert.assertTrue(articlesInSection.contains(article3));


    }

}
