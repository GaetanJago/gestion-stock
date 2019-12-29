package controller;

import model.Leader;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class LeaderDAO extends BaseDAO<Leader> {
    public LeaderDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Leader> getAll() {
        Query query = super.em.createQuery("FROM Leader");
        return query.getResultList();
    }

    @Override
    public Optional<Leader> findById(int id) {
        return Optional.ofNullable(em.find(Leader.class, id));
    }

    public void create(Leader leader){
        if(leader.getStore() != null){
            leader.getStore().setLeader(leader);
        }
        super.create(leader);
    }

    public void delete(Leader leader){
        if(leader.getStore() != null){
            leader.getStore().setLeader(null);
        }
        super.delete(leader);
    }
}
