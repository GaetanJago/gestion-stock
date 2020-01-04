package controller;

import model.Role;
import model.User;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity Role
 */
public class RoleDAO extends BaseDAO<Role> {

    private final static Logger logger = Logger.getLogger(RoleDAO.class);

    public RoleDAO(EntityManager em) {
        super(em);
    }

    /**
     * get all roles inserted in the database
     * @return a list of all roles
     */
    @Override
    public List<Role> getAll() {
        Query query = super.em.createQuery("FROM Role");
        return query.getResultList();
    }

    /**
     * find a role in the database from an id
     * @param id of the role searched
     * @return the role found or an optional null if nothing has been found
     */
    @Override
    public Optional<Role> findById(int id) {
        return Optional.ofNullable(em.find(Role.class, id));
    }

    /**
     * insert a role in the database
     * @param role to insert
     */
    public void create(Role role) {
        logger.info("Creation du role " + role.getName());
        super.create(role);
    }

    /**
     * delete a role in the database
     * @param role to delete
     */
    public void delete(Role role){
        logger.info("Suppression du role " + role.getName());
        super.delete(role);
    }
}
