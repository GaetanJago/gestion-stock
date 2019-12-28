package controller;

import model.User;

import javax.persistence.EntityManager;

public class UserDAO extends BaseDAO {
    public UserDAO(EntityManager em) {
        super(em);
    }

    public void create(User user) {
        super.create(user);
    }

    public void delete(User user){
        super.delete(user);
    }
}
