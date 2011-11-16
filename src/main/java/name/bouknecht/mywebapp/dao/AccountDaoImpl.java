package name.bouknecht.mywebapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
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

    @Transactional
    public void persist(Account account) {
        assert account != null;
        entityManager.persist(account);
    }
}
