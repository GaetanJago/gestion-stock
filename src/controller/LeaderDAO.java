package controller;

import model.Leader;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity Leader
 */
public class LeaderDAO extends BaseDAO<Leader> {

    private final static Logger logger = Logger.getLogger(LeaderDAO.class);


    public LeaderDAO(EntityManager em) {
        super(em);
    }

    /**
     * get all leaders inserted in the database
     * @return a list of all leaders
     */
    @Override
    public List<Leader> getAll() {
        Query query = super.em.createQuery("FROM Leader");
        return query.getResultList();
    }

    /**
     * find a leader in the database from an id
     * @param id of the leader searched
     * @return the leader found or an optional null if nothing has been found
     */
    @Override
    public Leader findById(int id) {
        Optional<Leader> leaderFound = Optional.ofNullable(em.find(Leader.class, id));

        if(leaderFound.isPresent()){
            return leaderFound.get();
        }
        return null;
    }


    /**
     * insert a store leader in the database and update the leader of its store
     * @param leader to insert
     */
    public void create(Leader leader){
        logger.info("Creation du leader " + leader.getFullName());
        if(loginIsAvailable(leader.getLogin())){
            if(leader.getStore() != null){
                leader.getStore().setLeader(leader);
            }
            super.create(leader);
        } else {
            logger.info("Le login a deja ete attribue");
        }

    }

    /**
     * delete a store leader in the database and update the leader of its store
     * @param leader to delete
     */
    public void delete(Leader leader){
        logger.info("Suppression du leader " + leader.getFullName());
        if(leader.getStore() != null){
            leader.getStore().setLeader(null);
        }
        super.delete(leader);
    }

    public boolean loginIsAvailable(String login){
        Query query = em.createQuery("SELECT login FROM User WHERE login = :login").setParameter("login", login);
        return query.getResultList().isEmpty();
    }
}
