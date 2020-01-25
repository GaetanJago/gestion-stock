package controller;

import model.Manager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity Manager
 */
public class ManagerDAO extends BaseDAO<Manager> {

    private final static Logger logger = Logger.getLogger(ManagerDAO.class);

    public ManagerDAO(EntityManager em) {
        super(em);
    }

    /**
     * get all managers inserted in the database
     * @return a list of all managers
     */
    @Override
    public List<Manager> getAll() {
        Query query = super.em.createQuery("FROM Manager ");
        return query.getResultList();
    }

    /**
     * find a manager in the database from an id
     * @param id of the manager searched
     * @return the manager found or an optional null if nothing has been found
     */
    @Override
    public Manager findById(int id) {
        Optional<Manager> managerFound = Optional.ofNullable(em.find(Manager.class, id));

        if(managerFound.isPresent()){
            return managerFound.get();
        }
        return null;
    }

    /**
     * insert a manager in the database and update its section manager
     * @param manager to insert
     */
    public void create(Manager manager){
        logger.info("Creation du manager " + manager.getFullName());
        if(loginIsAvailable(manager.getLogin())){
            if(manager.getSection() != null){
                manager.getSection().setManager(manager);
            }
            super.create(manager);
        } else {
            logger.info("Le login a deja ete attribue");
        }

    }

    /**
     * delete a manager in the database and update its section manager
     * @param manager to delete
     */
    public void delete(Manager manager){
        logger.info("Suppression du manager " + manager.getFullName());
        if(manager.getSection() != null){
            manager.getSection().removeManager();
        }
        super.delete(manager);
    }

    public boolean loginIsAvailable(String login){
        Query query = em.createQuery("SELECT login FROM User WHERE login = :login").setParameter("login", login);
        return query.getResultList().isEmpty();
    }
}
