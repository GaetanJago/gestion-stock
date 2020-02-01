package test.controller;

import controller.ManagerDAO;
import controller.RoleDAO;
import controller.UserDAO;
import model.Manager;
import model.Role;
import model.Section;
import model.User;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static RoleDAO roleDAO;

    private static List<Role> roleList;


    //SETUP
    @BeforeClass
    public static void setUpBeforeClass(){
        emf = Persistence.createEntityManagerFactory("stock"); //name of persistence unit
        em = emf.createEntityManager();

        roleDAO = new RoleDAO(em);

        roleList = new ArrayList<>();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @AfterClass
    public static void setUpAfterClass(){
        for(Role role : roleList){
            roleDAO.delete(role);
        }

        em.close();
        emf.close();
    }

    @Test
    public void testCreate(){
        Role role = new Role("admin");
        roleList.add(role);

        roleDAO.create(role);

        Role roleFound = roleDAO.findById(role.getId());

        Assert.assertEquals(role, roleFound);
    }

    @Test
    public void testCreateDoNotWorkTwoTimesWithTheSameObject(){
        //Count how many roles are in the database before insert
        int nbRolesBeforeInsert = roleDAO.getAll().size();

        Role role = new Role("Chef de rayon");
        roleList.add(role);
        roleDAO.create(role);
        roleDAO.create(role);

        Assert.assertEquals(nbRolesBeforeInsert+1, roleDAO.getAll().size());
    }

    @Test
    public void testGetAll(){
        //Count how many roles are in the database before insert
        int nbRolesBeforeInsert = roleDAO.getAll().size();

        Role role = new Role("Utilisateur révoqué");
        roleList.add(role);
        roleDAO.create(role);

        //Get list of all roles in the database
        List<Role> roleList = roleDAO.getAll();

        //Check that there is one more role in the list than before insert
        Assert.assertEquals(nbRolesBeforeInsert+1, roleList.size());
        //Check that the last role is the one which we just added
        Assert.assertEquals(role, roleList.get(roleList.size()-1));
    }

    @Test
    public void testDelete(){
        Role role = new Role("Super admin");
        roleList.add(role);
        roleDAO.create(role);

        /*User user = new User("Leblanc", "Victoire", "vleblanc", "TohGhe8ae");
        user.setRole(role);
        userDAO.create(user);*/

        //Count how many roles are in the database before delete
        int nbRolesBeforeDelete = roleDAO.getAll().size();

        roleDAO.delete(role);

        Assert.assertEquals(nbRolesBeforeDelete-1, roleDAO.getAll().size());
        Assert.assertFalse(roleDAO.getAll().contains(role));
        //Assert.assertNull(userDAO.findById(user.getId()).get().getRole());
    }


}
