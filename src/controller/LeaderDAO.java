package controller;

import model.Leader;

import javax.persistence.EntityManager;

public class LeaderDAO extends BaseDAO {
    public LeaderDAO(EntityManager em) {
        super(em);
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
