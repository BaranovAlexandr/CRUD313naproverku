package com.example.demo.DAO;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRoleById(Long id){
        Query query = entityManager.createQuery("Select e FROM Role e WHERE e.id = :id");
        query.setParameter("id", id);
        return (Role) query.getSingleResult();
    }
}
