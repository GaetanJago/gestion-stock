package controller;

import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity User
 */
public class UserDAO extends BaseDAO<User> {
    public UserDAO(EntityManager em) {
        super(em);
    }

    /**
     * get all users inserted in the database
     * @return a list of all users
     */
    @Override
    public List<User> getAll() {
        Query query = super.em.createQuery("FROM User");
        return query.getResultList();
    }

    /**
     * find an user in the database from an id
     * @param id of the user searched
     * @return the user found or an optional null if nothing has been found
     */
    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    /**
     * insert an user in the database
     * @param user to insert
     */
    public void create(User user) {
        super.create(user);
    }

    /**
     * delete an user in the database
     * @param user to delete
     */
    public void delete(User user){
        super.delete(user);
    }
}
