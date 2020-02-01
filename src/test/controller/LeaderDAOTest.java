package test.controller;

import controller.ArticleDAO;
import controller.LeaderDAO;
import controller.SectionDAO;
import controller.StoreDAO;
import model.Article;
import model.Leader;
import model.Store;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class LeaderDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static LeaderDAO leaderDAO;
    private static StoreDAO storeDAO;

    private static List<Leader> leaderList;
    private static List<Store> storeList;

    //SETUP
    @BeforeClass
    public static void setUpBeforeClass(){
        emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
        em = emf.createEntityManager();

        leaderDAO = new LeaderDAO(em);
        storeDAO = new StoreDAO(em);

        storeList = new ArrayList<>();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void setUpAfterClass(){

        for(Store store : storeList){
            storeList.remove(store);
        }


        em.close();
        emf.close();
    }

    @Test
    public void testCreate(){
        Leader leader = new Leader("Lambert", "Paul", "plambert", "password");

        Store store = new Store("64 avenue Jean Portalis Tours");
        storeList.add(store);
        storeDAO.create(store);

        leader.setStore(store);
        leaderDAO.create(leader);

        Leader leaderFound = leaderDAO.findById(leader.getId());

        Assert.assertEquals(leader, leaderFound);
        Assert.assertEquals(leader, storeDAO.findById(store.getId()).getLeader());
    }

    @Test
    public void testCreateDoNotWorkTwoTimesWithTheSameObject(){
        //Count how many leaders are in the database before insert
        int nbLeadersBeforeInsert = leaderDAO.getAll().size();

        Leader leader = new Leader("Martin", "Jerome", "jmartin", "password");
        leaderDAO.create(leader);
        leaderDAO.create(leader);

        Assert.assertEquals(nbLeadersBeforeInsert+1, leaderDAO.getAll().size());
    }

    @Test
    public void testGetAll(){
        //Count how many leaders are in the database before insert
        int nbLeadersBeforeInsert = leaderDAO.getAll().size();

        Leader leader = new Leader("Bousquet", "Joseph", "jbousquet", "avhrssqfg4");
        leaderDAO.create(leader);

        //Get list of all leaders in the database
        List<Leader> leaderList = leaderDAO.getAll();

        //Check that there is one more leader in the list than before insert
        Assert.assertEquals(nbLeadersBeforeInsert+1, leaderList.size());
        //Check that the last leader is the one which we just added
        Assert.assertEquals(leader, leaderList.get(leaderList.size()-1));
    }

    @Test
    public void testDelete(){
        Leader leader = new Leader( "Bourget" ,"Bernard", "bbourget", "sdfsdfqo545sd");
        Store store = new Store("1 rue Charles de Foucauld Tours");
        storeList.add(store);
        storeDAO.create(store);

        leader.setStore(store);
        leaderDAO.create(leader);

        //Count how many leaders are in the database before delete
        int nbLeadersBeforeDelete = leaderDAO.getAll().size();

        leaderDAO.delete(leader);

        Assert.assertEquals(nbLeadersBeforeDelete-1, leaderDAO.getAll().size());
        Assert.assertFalse(leaderDAO.getAll().contains(leader));
        Assert.assertNull(storeDAO.findById(store.getId()).getLeader());

    }

}
