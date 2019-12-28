package controller;

import model.Store;

import javax.persistence.EntityManager;

public class StoreDAO extends BaseDAO {
    public StoreDAO(EntityManager em) {
        super(em);
    }

    public void create(Store store) {
        super.create(store);
    }

    public void delete(Store store){
        super.delete(store);
    }
}
