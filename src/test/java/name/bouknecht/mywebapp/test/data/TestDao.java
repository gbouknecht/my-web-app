package name.bouknecht.mywebapp.test.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import name.bouknecht.mywebapp.model.Account;

import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    @PersistenceContext
    private EntityManager entityManager;

    public TestDao insertTestData(TestData testData) {
        insertAccounts(testData.createAccounts());
        return this;
    }

    private void insertAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            entityManager.persist(account);
        }
    }
}
