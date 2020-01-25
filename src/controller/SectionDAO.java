package controller;

import model.Section;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


/**
 * DAO class for the entity Section
 */
public class SectionDAO extends BaseDAO<Section> {

    private final static Logger logger = Logger.getLogger(SectionDAO.class);

    public SectionDAO(EntityManager em) {
        super(em);
    }

    /**
     * get all sections inserted in the database
     * @return a list of all sections
     */
    @Override
    public List<Section> getAll() {
        Query query = super.em.createQuery("FROM Section");
        return query.getResultList();
    }

    /**
     * find a section in the database from an id
     * @param id of the section searched
     * @return the section found or an optional null if nothing has been found
     */
    @Override
    public Section findById(int id) {
        Optional<Section> sectionFound = Optional.ofNullable(em.find(Section.class, id));

        if(sectionFound.isPresent()){
            return sectionFound.get();
        }
        return null;
    }

    /**
     * insert a section in the database and update the section list of its store
     * @param section to insert
     */
    public void create(Section section) {
        logger.info("Creation de la section " + section.getName());
        if(section.getStore() != null){
            section.getStore().addSection(section);
        }
        if(section.getManager() != null){
            section.getManager().setSection(section);
        }

        super.create(section);
    }

    /**
     * delete a section in the database, update the sectionlist of its store
     * and set the section of its manager to null
     * @param section to delete
     */
    public void delete(Section section){
        logger.info("Suppression de la section " + section.getName());
        if(section.getStore() != null){
            section.getStore().removeSection(section);
        }
        if(section.getManager() != null){
            section.getManager().removeSection();
        }
        super.delete(section);
    }
}
