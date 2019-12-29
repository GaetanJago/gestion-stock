package controller;

import model.Role;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class RoleDAO extends BaseDAO<Role> {

    public RoleDAO(EntityManager em) {
        super(em);
    }

    @Override
    public List<Role> getAll() {
        Query query = super.em.createQuery("FROM Role");
        return query.getResultList();
    }

    @Override
    public Optional<Role> findById(int id) {
        return Optional.ofNullable(em.find(Role.class, id));
    }

    public void create(Role role) {
        super.create(role);
    }

    public void delete(Role role){
        super.delete(role);
    }
}
