package test.controller;

import controller.LeaderDAO;
import controller.ManagerDAO;
import controller.SectionDAO;
import controller.StoreDAO;
import model.*;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static ManagerDAO managerDAO;
    private static SectionDAO sectionDAO;

    private static List<Manager> managerList;
    private static List<Section> sectionList;


    //SETUP
    @BeforeClass
    public static void setUpBeforeClass(){
        emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
        em = emf.createEntityManager();

        managerDAO = new ManagerDAO(em);
        sectionDAO = new SectionDAO(em);

        managerList = new ArrayList<>();
        sectionList = new ArrayList<>();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void setUpAfterClass(){
        for(Manager manager : managerList){
            managerDAO.delete(manager);
        }

        for(Section section : sectionList){
            sectionDAO.delete(section);
        }

        em.close();
        emf.close();
    }

    @Test
    public void testCreate(){
        Manager manager = new Manager("Lambert", "Paul", "plambert", "password");
        managerList.add(manager);

        Section section = new Section("Sports d'hiver");
        sectionList.add(section);
        sectionDAO.create(section);

        manager.setSection(section);
        managerDAO.create(manager);

        Manager managerFound = managerDAO.findById(manager.getId());

        Assert.assertEquals(manager, managerFound);
        Assert.assertTrue(managerDAO.getAll().contains(manager));
    }

    @Test
    public void testCreateDoNotWorkTwoTimesWithTheSameObject(){
        //Count how many managers are in the database before insert
        int nbManagersBeforeInsert = managerDAO.getAll().size();

        Manager manager = new Manager("Martin", "Jerome", "jmartin", "password");
        managerList.add(manager);
        managerDAO.create(manager);
        managerDAO.create(manager);

        Assert.assertEquals(nbManagersBeforeInsert+1, managerDAO.getAll().size());

    }

    @Test
    public void testGetAll(){
        //Count how many managers are in the database before insert
        int nbManagersBeforeInsert = managerDAO.getAll().size();

        Manager manager = new Manager("Guibord", "Joanna", "jguibord", "12dfjnvre");
        managerList.add(manager);
        managerDAO.create(manager);

        //Get list of all managers in the database
        List<Manager> managerList = managerDAO.getAll();

        //Check that there is one more manager in the list than before insert
        Assert.assertEquals(nbManagersBeforeInsert+1, managerList.size());
        //Check that the last manager is the one which we just added
        Assert.assertEquals(manager, managerList.get(managerList.size()-1));
    }

    @Test
    public void testDelete(){
        Manager manager = new Manager("Authier", "Ernest", "eauthier", "ChohR9dio");
        managerList.add(manager);

        Section section = new Section("Plongée");
        sectionList.add(section);
        sectionDAO.create(section);
        manager.setSection(section);
        managerDAO.create(manager);

        //Count how many managers are in the database before delete
        int nbManagersBeforeDelete = managerDAO.getAll().size();

        managerDAO.delete(manager);

        Assert.assertEquals(nbManagersBeforeDelete-1, managerDAO.getAll().size());
        Assert.assertFalse(managerDAO.getAll().contains(manager));
        Assert.assertNull(sectionDAO.findById(section.getId()).getManager());

    }

}
