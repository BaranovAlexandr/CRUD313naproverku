package com.example.demo.service;

import com.example.demo.model.Role;

public interface RoleService {
    void addRole(Role role);
    Role getRoleById(Long id);
}
