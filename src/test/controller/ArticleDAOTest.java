package test.controller;

import controller.ArticleDAO;
import controller.SectionDAO;
import model.Article;
import model.Section;
import org.junit.*;

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
        Article articleFound = articleDAO.findById(article.getId()).get();

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
        Article articleFound = articleDAO.findById(article.getId()).get();

        Assert.assertEquals(article, articleFound);
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

        //Check that there is one more article in the list than before
        Assert.assertEquals(nbArticlesBeforeInsert+1, articleList.size());
        //Check that the last article is the one which we just added
        Assert.assertEquals(article, articleList.get(articleList.size()-1));
    }

    @Test
    public void testFindById(){
        Article article = new Article("Balle de tennis", "Artengo", 2, 40);
        articleDAO.create(article);

        Assert.assertEquals(article, articleDAO.findById(article.getId()).get());
    }

    @Test
    public void testDelete(){
        Article article = new Article("Short", "Adidas", 30, 3);
        articleDAO.create(article);

        //Count how many articles are in the database before delete
        int nbArticlesBeforeDelete = articleDAO.getAll().size();

        articleDAO.delete(article);

        Assert.assertEquals(nbArticlesBeforeDelete-1, articleDAO.getAll().size());
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

}
