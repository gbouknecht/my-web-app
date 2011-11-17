package name.bouknecht.mywebapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import name.bouknecht.mywebapp.model.Account;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AccountDaoImpl implements AccountDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Account> findAll() {
        return entityManager.createNamedQuery("findAllAccounts", Account.class)
                            .getResultList();
    }

    public List<Account> find(String text) {
        if (text == null) return findAll();
        return entityManager.createNamedQuery("findAccounts", Account.class)
                            .setParameter("text", "%" + text.toLowerCase() + "%")
                            .getResultList();
    }

    public Account findByUserId(String userId) {
        try {
            return entityManager.createNamedQuery("findAccountByUserId", Account.class)
                                .setParameter("userId", userId)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void persist(Account account) {
        assert account != null;
        entityManager.persist(account);
    }
}
