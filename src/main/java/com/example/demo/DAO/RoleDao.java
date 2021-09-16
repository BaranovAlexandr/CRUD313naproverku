package com.example.demo.DAO;

import com.example.demo.model.Role;

import java.util.List;


public interface RoleDao {
    void addRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();
}
