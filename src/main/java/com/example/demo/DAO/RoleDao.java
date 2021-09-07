package com.example.demo.DAO;


import com.example.demo.model.Role;



public interface RoleDao {

    void addRole(Role role);
    Role getRoleById(Long id);
}
