package com.example.demo.DAO;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
      return entityManager.createQuery("SELECT u From User u",User.class)
      .getResultList();
   }

   @Override
   public User getUserById(Long id){
      return (User) entityManager.createQuery("Select e FROM User e WHERE e.id = :id")
              .setParameter("id", id)
              .getSingleResult();
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
      return (User) entityManager.createQuery("Select e FROM User e WHERE e.email = :username")
              .setParameter("username", username)
              .getSingleResult();
   }
}
