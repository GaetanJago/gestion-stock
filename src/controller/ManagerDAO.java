package controller;

import model.Manager;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class ManagerDAO extends BaseDAO<Manager> {
    public ManagerDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Manager> getAll() {
        Query query = super.em.createQuery("FROM Manager ");
        return query.getResultList();
    }

    @Override
    public Optional<Manager> findById(int id) {
        return Optional.ofNullable(em.find(Manager.class, id));
    }

    public void create(Manager manager){
        if(manager.getSection() != null){
            manager.getSection().setManager(manager);
        }
        super.create(manager);
    }

    public void delete(Manager manager){
        if(manager.getSection() != null){
            manager.getSection().setManager(null);
        }
        super.delete(manager);
    }
}
