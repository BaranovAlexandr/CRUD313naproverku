package com.example.demo.service;

import com.example.demo.DAO.UserDao;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {


   private final PasswordEncoder passwordEncoder;
   private final UserDao userDao;

   @Autowired
   public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
      this.userDao = userDao;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public void add(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userDao.add(user);
   }

   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Override
   public User getUserById(Long id) {
      return userDao.getUserById(id);
   }

   @Override
   public void update(User user) {
      User lastUser = userDao.getUserByUsername(user.getUsername());
      if (lastUser.getPassword().equals(user.getPassword()) ) {
         userDao.update(user);
      } else {
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         userDao.update(user);
      }
   }

   @Override
   public void delete(Long id) {
      userDao.delete(id);
   }

   @Override
   public User getUserByUsername(String username) {
      return userDao.getUserByUsername(username);
   }

   @Override
   public String[] getStringRolesByUsername(String username){
      User user = userDao.getUserByUsername(username);
      Set<Role> roles = user.getRoles();
      String[] stringRoles = new String[roles.size()];
      int i = 0;
      for (Role role : roles) {
         stringRoles[i++] = role.getRole();
      }
      return stringRoles;
   }
}
