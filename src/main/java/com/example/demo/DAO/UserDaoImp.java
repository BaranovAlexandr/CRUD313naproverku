package com.example.demo.DAO;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository

public class UserDaoImp implements UserDao {





   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.persist(user);
   }

   @Override
   public List<User> listUsers() {
      Query query = entityManager.createQuery("SELECT u From User u",User.class);
      return query.getResultList();
   }

   @Override
   public User getUserById(Long id){
      Query query = entityManager.createQuery("Select e FROM User e WHERE e.id = :id");
      query.setParameter("id", id);
      return (User) query.getSingleResult();
   }

   @Override
   public void update(User user) {
      entityManager.merge(user);
   }

   @Override
   public void delete(Long id) {
      entityManager.remove(getUserById(id));
   }

   @Override
   public User getUserByUsername(String username) {
      Query query = entityManager.createQuery("Select e FROM User e WHERE e.username = :username");
      query.setParameter("username", username);
      return (User) query.getSingleResult();
   }

}
