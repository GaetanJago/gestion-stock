package controller;

import model.Role;

import javax.persistence.EntityManager;

public class RoleDAO extends BaseDAO {

    public RoleDAO(EntityManager em) {
        super(em);
    }

    public void create(Role role) {
        super.create(role);
    }

    public void delete(Role role){
        super.delete(role);
    }
}
