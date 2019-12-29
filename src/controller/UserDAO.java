package controller;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UserDAO extends BaseDAO<User> {
    public UserDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<User> getAll() {
        Query query = super.em.createQuery("FROM User");
        return query.getResultList();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public void create(User user) {
        super.create(user);
    }

    public void delete(User user){
        super.delete(user);
    }
}
