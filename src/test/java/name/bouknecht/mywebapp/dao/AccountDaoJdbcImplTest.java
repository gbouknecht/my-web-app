package name.bouknecht.mywebapp.dao;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.Arrays;
import java.util.List;

import name.bouknecht.mywebapp.model.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class AccountDaoJdbcImplTest {
    private AccountDao       accountDao;
    private EmbeddedDatabase database;

    @Before
    public void setUp() {
        database   = new EmbeddedDatabaseBuilder().addDefaultScripts().build();
        accountDao = new AccountDaoJdbcImpl(database);
    }

    @After
    public void tearDown() {
        database.shutdown();
    }

    @Test
    public void findAllAccountsShouldReturnAllAccounts() {
        List<Account> expectedAccounts = createAccounts();
        List<Account> actualAccounts   = accountDao.findAllAccounts();
        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    private List<Account> createAccounts() {
        return Arrays.asList(new Account(0, "ppieterse", "Piet", "Pieterse"),
                             new Account(1, "jjansse", "Jan", "Jansse"),
                             new Account(2, "ggerritse", "Gerrit", "Gerritse"));
    }
}
