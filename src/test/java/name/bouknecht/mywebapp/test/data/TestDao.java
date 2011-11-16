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

    /**
     * Flush ORM framework's underlying session.
     *
     * <p>According to the Spring Framework Reference (version 3.0.x): <b>Avoid
     * false positives when testing ORM code.</b> When you test code involving
     * an ORM framework such as JPA or Hibernate, flush the underlying session
     * within test methods which update the state of the session. Failing to
     * flush the ORM framework's underlying session can produce false positives:
     * your test may pass, but the same code throws an exception in a live,
     * production environment.</p>
     */
    public void flush() {
        entityManager.flush();
    }
}
