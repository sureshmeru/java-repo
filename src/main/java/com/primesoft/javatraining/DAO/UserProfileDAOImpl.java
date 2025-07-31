package com.primesoft.javatraining.DAO;

import com.primesoft.javatraining.Model.UserProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserProfileDAOImpl implements UserProfileDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(UserProfile userProfile) {
        entityManager.persist(userProfile);
    }

    @Override
    public UserProfile findById(Long id) {
        return entityManager.find(UserProfile.class, id);
    }

    @Override
    public List<UserProfile> findAll() {
        return entityManager.createQuery("from UserProfile", UserProfile.class).getResultList();
    }

    @Override
    public void deleteById(Long id) {
        UserProfile user = entityManager.find(UserProfile.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public UserProfile findByEmail(String email) {
        List<UserProfile> results = entityManager
                .createQuery("from UserProfile where email = :email", UserProfile.class)
                .setParameter("email", email)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
