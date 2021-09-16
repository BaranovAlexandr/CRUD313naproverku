package com.example.demo.DAO;

import com.example.demo.model.User;

import java.util.List;


public interface UserDao {
   void add(User user);

   List<User> listUsers();

   User getUserById(Long id);

   void update(User user);

   void delete(Long id);

   User getUserByUsername(String username);
}
