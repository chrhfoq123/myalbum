package csm.myalbum.repository;

import csm.myalbum.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public User findOne(Long id){
        return em.find(User.class, id);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    public Optional<User> findByUserId(String userId){
        return findAll().stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
    }

    public List<User> findByUserEmail(String userEmail){
        return em.createQuery("select u from User u where u.userEmail = :userEmail", User.class)
                .setParameter("userEmail", userEmail)
                .getResultList();
    }
}
