package test.controller;

import controller.ManagerDAO;
import controller.SectionDAO;
import model.Manager;
import model.Role;
import model.Section;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SectionDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static SectionDAO sectionDAO;
    private static ManagerDAO managerDAO;


    //SETUP
    @BeforeClass
    public static void setUpBeforeClass(){
        emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
        em = emf.createEntityManager();

        sectionDAO = new SectionDAO(em);
        managerDAO = new ManagerDAO(em);
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
        Section section = new Section("Chaussure");
        Manager manager = new Manager("Martin", "Jean", "jmartin", "azerty");

        managerDAO.create(manager);
        section.setManager(manager);
        sectionDAO.create(section);

        Section sectionFound = sectionDAO.findById(section.getId());

        Assert.assertEquals(section, sectionFound);
        Assert.assertEquals(section, managerDAO.findById(manager.getId()).getSection());
    }

    @Test
    public void testCreateDoNotWorkTwoTimesWithTheSameObject(){
        //Count how many sections are in the database before insert
        int nbSectionsBeforeInsert = sectionDAO.getAll().size();

        Section section = new Section("Piscine");
        sectionDAO.create(section);
        sectionDAO.create(section);

        Assert.assertEquals(nbSectionsBeforeInsert+1, sectionDAO.getAll().size());
    }

    @Test
    public void testGetAll(){
        //Count how many sections are in the database before insert
        int nbSectionsBeforeInsert = sectionDAO.getAll().size();

        Section section = new Section("Utilisateur révoqué");
        sectionDAO.create(section);

        //Get list of all roles in the database
        List<Section> sectionList = sectionDAO.getAll();

        //Check that there is one more section in the list than before insert
        Assert.assertEquals(nbSectionsBeforeInsert+1, sectionList.size());
        //Check that the last section is the one which we just added
        Assert.assertEquals(section, sectionList.get(sectionList.size()-1));
    }

    @Test
    public void testDelete(){
        Section section = new Section("Chaussure");
        Manager manager = new Manager("Monrency", "Clémence", "cmonrency", "kohF4Ii4");

        managerDAO.create(manager);
        section.setManager(manager);
        sectionDAO.create(section);

        //Count how many sections are in the database before delete
        int nbSectionsBeforeDelete = sectionDAO.getAll().size();

        sectionDAO.delete(section);

        Assert.assertEquals(nbSectionsBeforeDelete-1, sectionDAO.getAll().size());
        Assert.assertFalse(sectionDAO.getAll().contains(section));
        Assert.assertNull(managerDAO.findById(manager.getId()).getSection());

    }
}
