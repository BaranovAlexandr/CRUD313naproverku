package com.example.demo.service;

import com.example.demo.model.Role;

import java.util.List;

public interface RoleService {
    void addRole(Role role);

    Role getRoleById(Long id);

    List<Role> getAllRoles();
}
