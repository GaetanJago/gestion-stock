package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/*public abstract class BaseDAO<T> {

    private EntityManager em;

    public BaseDAO(EntityManager em) {
        this.em = em;
    }

    protected void create(Object object){

    }

    public void save(){
        em.getTransaction().begin();

        em.getTransaction().commit();
    }

    public abstract delete()

}*/

public class DAO {
    private EntityManager em;

    public DAO(EntityManager em) {
        this.em = em;
    }

    public void create(Object object){
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    public void save(){
        em.getTransaction().begin();

        em.getTransaction().commit();
    }

    public void delete(Object object){
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
        em.detach(object);
    }
}