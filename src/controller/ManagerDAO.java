package controller;

import model.Manager;

import javax.persistence.EntityManager;

public class ManagerDAO extends BaseDAO {
    public ManagerDAO(EntityManager em) {
        super(em);
    }

    public void create(Manager manager){
        super.create(manager);
    }

    public void delete(Manager manager){
        super.delete(manager);
    }
}
