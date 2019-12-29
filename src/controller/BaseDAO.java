package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * Abstract class used as a base for each entity DAO
 * @param <T> Entity type for which the DAO is used
 */
public abstract class BaseDAO<T> {

    /**
     * Entity manager which persist all data
     */
    protected EntityManager em;

    public BaseDAO(EntityManager em) {
        this.em = em;
    }

    /**
     * Return all the entries of a table
     * @return list of all entity objects
     */
    abstract public List<T> getAll();

    /**
     * Return an entity object from an id
     * @param id of the entity in the database
     * @return if found return the entity object
     */
    abstract public Optional<T> findById(int id);


    /**
     * Insert an entry in the database from an entity object
     * @param object entity object
     */
    protected void create(T object){
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    /**
     * save all persisted objects
     */
    public void save(){
        em.getTransaction().begin();

        em.getTransaction().commit();
    }

    /**
     * delete a specific entry in the database
     * @param object entity object which determine the entry to delete
     */
    protected void delete(T object){
        if(em.contains(object)){
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
            em.detach(object);
        }
    }
}