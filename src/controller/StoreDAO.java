package controller;

import model.Store;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity Store
 */
public class StoreDAO extends BaseDAO<Store> {

    private final static Logger logger = Logger.getLogger(StoreDAO.class);


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
    public Store findById(int id) {
        Optional<Store> storeFound = Optional.ofNullable(em.find(Store.class, id));

        if(storeFound.isPresent()){
            return storeFound.get();
        }
        return null;
    }

    /**
     * insert a store in the database
     * @param store to insert
     */
    public void create(Store store) {
        logger.info("Creation du magasin à l'adresse " + store.getAddress());
        super.create(store);
    }

    /**
     * delete a store in the database
     * @param store to delete
     */
    public void delete(Store store){
        logger.info("Suppression du magasin à l'adresse " + store.getAddress());
        super.delete(store);
    }
}
