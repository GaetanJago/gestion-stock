package controller;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import model.Article;
import model.User;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity User
 */
public class UserDAO extends BaseDAO<User> {

    private final static Logger logger = Logger.getLogger(UserDAO.class);

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
    public User findById(int id) {

        Optional<User> userFound = Optional.ofNullable(em.find(User.class, id));
        if(userFound.isPresent()){
            return userFound.get();
        }
        return null;
    }

    /**
     * insert an user in the database
     * @param user to insert
     */
    public void create(User user) {
        logger.info("Creation de l'utilisateur " + user.getFullName());
        if(loginIsAvailable(user.getLogin())){
            if(user.getRole() != null){
                user.getRole().addUser(user);
            }

            super.create(user);
        } else {
            logger.info("Le login a deja ete attribue");
        }

    }

    /**
     * delete an user in the database
     * @param user to delete
     */
    public void delete(User user){
        logger.info("Suppression de l'utilisateur " + user.getFullName());
        if(user.getRole() != null){
            user.getRole().removeUser(user);
        }
        super.delete(user);
    }

    public boolean loginIsAvailable(String login){
        Query query = em.createQuery("SELECT login FROM User WHERE login = :login").setParameter("login", login);
        return query.getResultList().isEmpty();
    }
}
