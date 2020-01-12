package test.controller;

import controller.LeaderDAO;
import controller.RoleDAO;
import controller.StoreDAO;
import model.Leader;
import model.Section;
import model.Store;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class StoreDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static StoreDAO storeDAO;
    private static LeaderDAO leaderDAO;


    //SETUP
    @BeforeClass
    public static void setUpBeforeClass(){
        emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
        em = emf.createEntityManager();

        storeDAO = new StoreDAO(em);
        leaderDAO = new LeaderDAO(em);
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
    public void testCreate(){
        Store store = new Store("64 avenue Jean Portalis Tours");

        storeDAO.create(store);

        Store storeFound = storeDAO.findById(store.getId());

        Assert.assertEquals(store, storeFound);
    }

    @Test
    public void testCreateDoNotWorkTwoTimesWithTheSameObject(){
        //Count how many stores are in the database before insert
        int nbStoresBeforeInsert = storeDAO.getAll().size();

        Store store = new Store("1 rue Nationale Tours");
        storeDAO.create(store);
        storeDAO.create(store);

        Assert.assertEquals(nbStoresBeforeInsert+1, storeDAO.getAll().size());
    }

    @Test
    public void testGetAll(){
        //Count how many stores are in the database before insert
        int nbStoresBeforeInsert = storeDAO.getAll().size();

        Store store = new Store("1 avenue Grammont Tours");
        storeDAO.create(store);

        //Get list of all stores in the database
        List<Store> storeList = storeDAO.getAll();

        //Check that there is one more store in the list than before insert
        Assert.assertEquals(nbStoresBeforeInsert+1, storeList.size());
        //Check that the last store is the one which we just added
        Assert.assertEquals(store, storeList.get(storeList.size()-1));
    }

    @Test
    public void testDelete(){
        Store store = new Store("10 rue Nationale Tours");
        storeDAO.create(store);


        //Count how many stores are in the database before delete
        int nbStoresBeforeDelete = storeDAO.getAll().size();

        storeDAO.delete(store);

        Assert.assertEquals(nbStoresBeforeDelete-1, storeDAO.getAll().size());
        Assert.assertFalse(storeDAO.getAll().contains(store));

    }
}
