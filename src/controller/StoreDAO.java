package controller;

import model.Store;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class StoreDAO extends BaseDAO<Store> {
    public StoreDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Store> getAll() {
        Query query = super.em.createQuery("FROM Store");
        return query.getResultList();
    }

    @Override
    public Optional<Store> findById(int id) {
        return Optional.ofNullable(em.find(Store.class, id));
    }

    public void create(Store store) {
        super.create(store);
    }

    public void delete(Store store){
        super.delete(store);
    }
}
