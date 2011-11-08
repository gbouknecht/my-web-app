package name.bouknecht.mywebapp.dao;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
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

        List<Account> actualAccounts = accountDao.findAllAccounts();

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void findWithNullShouldReturnAllAccounts() {
        List<Account> expectedAccounts = data.createAccounts();

        List<Account> actualAccounts = accountDao.find(null);

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void findWithMatchingUserIdShouldReturnMatchedAccounts() {
        verifyThatFindWithGivenTextReturnsMatchingAccounts("sErId-1");
    }

    @Test
    public void findWithMatchingFirstnameShouldReturnMatchedAccounts() {
        verifyThatFindWithGivenTextReturnsMatchingAccounts("iRsTnAme-1");
    }

    @Test
    public void findWithMatchingLastnameShouldReturnMatchedAccounts() {
        verifyThatFindWithGivenTextReturnsMatchingAccounts("aStNaMe-1");
    }

    private void verifyThatFindWithGivenTextReturnsMatchingAccounts(String text) {
        List<Account> expectedAccounts = filter(data.createAccounts(), text);

        List<Account> actualAccounts = accountDao.find(text);

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    private List<Account> filter(List<Account> accounts, String text) {
        List<Account> result = new ArrayList<Account>();
        for (Account account : accounts) {
            if (   containsIgnoreCase(account.getUserId(),    text)
                || containsIgnoreCase(account.getFirstname(), text)
                || containsIgnoreCase(account.getLastname(),  text)) {
                result.add(account);
            }
        }
        return result;
    }
}
