package test.controller;

import controller.RoleDAO;
import controller.UserDAO;
import model.Leader;
import model.Role;
import model.User;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static UserDAO userDAO;
    private static RoleDAO roleDAO;


    //SETUP
    @BeforeClass
    public static void setUpBeforeClass(){
        emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
        em = emf.createEntityManager();

        userDAO = new UserDAO(em);
        roleDAO = new RoleDAO(em);
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
        User user = new User("Lambert", "Paul", "plambert", "password");

        Role role = new Role("Admin");
        roleDAO.create(role);

        user.setRole(role);
        userDAO.create(user);

        User userFound = userDAO.findById(user.getId());

        Assert.assertEquals(user, userFound);
        Assert.assertTrue(roleDAO.findById(role.getId()).getUsers().contains(user));
    }

    @Test
    public void testCreateDoNotWorkTwoTimesWithTheSameObject(){
        //Count how many users are in the database before insert
        int nbUsersBeforeInsert = userDAO.getAll().size();

        User user = new Leader("Pascal", "Patrick", "jmartin", "password");
        userDAO.create(user);
        userDAO.create(user);

        Assert.assertEquals(nbUsersBeforeInsert+1, userDAO.getAll().size());
    }

    @Test
    public void testGetAll(){
        //Count how many user are in the database before insert
        int nbUsersBeforeInsert = userDAO.getAll().size();

        //Creation of a user with a role
        User user = new User("Lesage", "Patricia", "plesage", "sdfsdhfjk");
        Role role = new Role("Admin");
        roleDAO.create(role);

        user.setRole(role);

        userDAO.create(user);

        //Get list of all users in the database
        List<User> userList = userDAO.getAll();

        //Check that there is one more user in the list than before insert
        Assert.assertEquals(nbUsersBeforeInsert+1, userList.size());
        //Check that the last user is the one which we just added
        Assert.assertEquals(user, userList.get(userList.size()-1));
    }

    @Test
    public void testDelete(){
        User user = new User("Lambert", "Paul", "plambert", "password");

        Role role = new Role("Admin");
        roleDAO.create(role);

        user.setRole(role);
        userDAO.create(user);

        //Count how many stores are in the database before insert
        int nbUsersBeforeDelete = userDAO.getAll().size();

        userDAO.delete(user);

        Assert.assertEquals(nbUsersBeforeDelete-1, userDAO.getAll().size());
        Assert.assertFalse(userDAO.getAll().contains(user));
        Assert.assertFalse(roleDAO.findById(role.getId()).getUsers().contains(user));
    }
}
