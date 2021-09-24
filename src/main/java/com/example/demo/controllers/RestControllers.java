package com.example.demo.controllers;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:63342/")
public class RestControllers {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestControllers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.listUsers();
    }

//    @GetMapping("/roles")
//    public List<Role> getAllRoles() {
//        return roleService.getAllRoles();
//    }

    @GetMapping("/user")
    public User getPrincipal(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user
//            ,@RequestParam(value = "checkbox_roles", required = false) Long[] rolesId) {
//        Set<Role> roles = new HashSet<>();
//        for (Long role : rolesId) {
//            roles.add(roleService.getRoleById(role));
//        }
//        user.setRoles(roles);
    ) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(1L));
        roles.add(roleService.getRoleById(2L));
        user.setRoles(roles);
        userService.add(user);
        return user;
    }

    @PatchMapping("/users")
    public User updateUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(1L));
        roles.add(roleService.getRoleById(2L));
        user.setRoles(roles);
        userService.update(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.delete(id);
        return " " + id;
    }


}
