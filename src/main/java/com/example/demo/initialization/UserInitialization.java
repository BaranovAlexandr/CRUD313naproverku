package com.example.demo.initialization;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserInitialization {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserInitialization(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        roleService.addRole(new Role("ROLE_ADMIN"));
        roleService.addRole(new Role("ROLE_USER"));

        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        Set<Role> adminUserRoles = new HashSet<>();

        adminRoles.add(roleService.getRoleById(1L));
        userRoles.add(roleService.getRoleById(2L));
        adminUserRoles.add(roleService.getRoleById(1L));
        adminUserRoles.add(roleService.getRoleById(2L));

        userService.add(new User("alex","alex","Alexandr","Baranov", 23, adminUserRoles));
        userService.add(new User("admin", "admin", "Admin", "Admin", 30, adminRoles));
        userService.add(new User("user", "user", "user", "user", 20, userRoles));
        userService.add(new User("SU","SU","SimpleUser","SimpleUser", 22, userRoles));
    }
}


