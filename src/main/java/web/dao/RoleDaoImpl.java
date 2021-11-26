package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;
    private String nameRoles;


    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query=entityManager.createQuery("select r from Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Set<Role> getByIdRoles(List<Long> idR) {
        TypedQuery<Role> query = entityManager.createQuery("select r FROM Role r WHERE r.id in :idR", Role.class);
        return new HashSet<>(query.setParameter("idR", idR).getResultList());
    }
    @Override
    public Set<Role> getByNameRoles(String nameRoles){
        this.nameRoles = nameRoles;
        TypedQuery<Role> query = entityManager.createQuery("select r FROM Role r WHERE r.name in :nameRoles",Role.class);
    return new HashSet<>(query.setParameter("nameRoles", nameRoles).getResultList());
    }
}
