package controller;

import model.Manager;

import javax.persistence.EntityManager;

public class ManagerDAO extends BaseDAO {
    public ManagerDAO(EntityManager em) {
        super(em);
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
