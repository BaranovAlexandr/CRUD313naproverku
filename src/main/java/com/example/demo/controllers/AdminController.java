package com.example.demo.controllers;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(ModelMap model){
        model.addAttribute("users", userService.listUsers());
        return "admin/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", userService.getStringRolesByUsername(userService.getUserById(id).getUsername()));
        return "admin/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "checkbox_roles", required = false) Long[] rolesId){

        Set<Role> roles = new HashSet<>();
        for (Long role : rolesId) {
            roles.add(roleService.getRoleById(role));
        }
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, ModelMap model){
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") User user,
                         @RequestParam(value = "checkbox_roles", required = false) Long[] rolesId,
                         Model model){
        model.addAttribute("roles", roleService.getAllRoles());
        Set<Role> roles = new HashSet<>();
        for (Long role : rolesId) {
            roles.add(roleService.getRoleById(role));
        }
        user.setRoles(roles);
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }
}
