package controller;

import model.Section;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class SectionDAO extends BaseDAO<Section> {
    public SectionDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Section> getAll() {
        Query query = super.em.createQuery("FROM Section");
        return query.getResultList();
    }

    @Override
    public Optional<Section> findById(int id) {
        return Optional.ofNullable(em.find(Section.class, id));
    }

    public void create(Section section) {
        if(section.getStore() != null){
            section.getStore().addSection(section);
        }

        super.create(section);
    }

    public void delete(Section section){
        if(section.getStore() != null){
            section.getStore().removeSection(section);
        }
        if(section.getManager() != null){
            section.getManager().setSection(null);
        }
        super.delete(section);
    }
}
