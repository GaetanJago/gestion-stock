package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

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

public abstract class BaseDAO<T> {
    protected EntityManager em;

    public BaseDAO(EntityManager em) {
        this.em = em;
    }

    abstract public List<T> getAll();

    abstract public Optional<T> findById(int id);

    protected void create(T object){
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    public void save(){
        em.getTransaction().begin();

        em.getTransaction().commit();
    }

    protected void delete(T object){
        if(em.contains(object)){
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction().commit();
            em.detach(object);
        }
    }
}