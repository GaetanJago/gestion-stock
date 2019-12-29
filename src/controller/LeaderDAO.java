package controller;

import model.Leader;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity Leader
 */
public class LeaderDAO extends BaseDAO<Leader> {
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
    public Optional<Leader> findById(int id) {
        return Optional.ofNullable(em.find(Leader.class, id));
    }


    /**
     * insert a store leader in the database and update the leader of its store
     * @param leader to insert
     */
    public void create(Leader leader){
        if(leader.getStore() != null){
            leader.getStore().setLeader(leader);
        }
        super.create(leader);
    }

    /**
     * delete a store leader in the database and update the leader of its store
     * @param leader to delete
     */
    public void delete(Leader leader){
        if(leader.getStore() != null){
            leader.getStore().setLeader(null);
        }
        super.delete(leader);
    }
}
