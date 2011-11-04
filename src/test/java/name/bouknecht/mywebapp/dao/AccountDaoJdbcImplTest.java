package name.bouknecht.mywebapp.dao;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestData;
import name.bouknecht.mywebapp.test.data.TestDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountDaoJdbcImplTest {
    private TestData     data;
    private TestDatabase database;
    private AccountDao   accountDao;

    @Before
    public void setUp() {
        data       = new TestData();
        database   = new TestDatabase(data);
        accountDao = new AccountDaoJdbcImpl(database.getDataSource());
    }

    @After
    public void tearDown() {
        database.shutdown();
    }

    @Test
    public void findAllAccountsShouldReturnAllAccounts() {
        List<Account> expectedAccounts = data.createAccounts();
        List<Account> actualAccounts   = accountDao.findAllAccounts();
        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }
}
