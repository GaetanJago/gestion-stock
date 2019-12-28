package controller;

import model.Section;

import javax.persistence.EntityManager;

public class SectionDAO extends BaseDAO {
    public SectionDAO(EntityManager em) {
        super(em);
    }

    public void create(Section section) {
        super.create(section);
        if(section.getStore() != null){
            section.getStore().addSection(section);
        }

    }

    public void delete(Section section){
        if(section.getStore() != null){
            section.getStore().removeSection(section);
        }

        super.delete(section);
    }
}
