package controller;

import model.Store;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity Store
 */
public class StoreDAO extends BaseDAO<Store> {
    public StoreDAO(EntityManager em) {
        super(em);
    }

    /**
     * get all stores inserted in the database
     * @return a list of all stores
     */
    @Override
    public List<Store> getAll() {
        Query query = super.em.createQuery("FROM Store");
        return query.getResultList();
    }

    /**
     * find a store in the database from an id
     * @param id of the store searched
     * @return the store found or an optional null if nothing has been found
     */
    @Override
    public Optional<Store> findById(int id) {
        return Optional.ofNullable(em.find(Store.class, id));
    }

    /**
     * insert a store in the database
     * @param store to insert
     */
    public void create(Store store) {
        super.create(store);
    }

    /**
     * delete a store in the database
     * @param store to delete
     */
    public void delete(Store store){
        super.delete(store);
    }
}
