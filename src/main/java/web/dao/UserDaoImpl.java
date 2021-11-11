package web.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@Repository
@EnableTransactionManagement
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    private List<User> users;

    public List<User> index() {
        Query query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }


    public User show(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u WHERE u.id=:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

//    @Transactional
//    public void update(Long id, User updateUser){
//        Query query = entityManager.createQuery("select u from User u WHERE u.id=:id");
//        query.setParameter("id",id);
//        User userToUpdate = (User) query.getSingleResult();
//        userToUpdate.setName(updateUser.getName());
//        userToUpdate.setAge(updateUser.getAge());
//        userToUpdate.setEmail(updateUser.getEmail());
//        userToUpdate.setUsername(updateUser.getUsername());
//        userToUpdate.setPassword(updateUser.getPassword());
//        userToUpdate.setRoles(updateUser.getRoles());
//    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }


    @Transactional
    public void delete(Long id) {
        Query query = entityManager.createQuery("delete from User u where u.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public User getUserByName(String name) {
        Query query = entityManager.createQuery("select user from User user where user.username =:user_login", User.class)
                .setParameter("user_login", name);
        return (User) query.getSingleResult();
    }
}
