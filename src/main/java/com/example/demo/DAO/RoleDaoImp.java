package com.example.demo.DAO;

import com.example.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return (Role) entityManager.createQuery("Select e FROM Role e WHERE e.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    @Override
    public Role getRoleByName(String role) {
        return (Role) entityManager.createQuery("Select e FROM Role e WHERE e.role = :role")
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r From Role r", Role.class).getResultList();
    }
}
