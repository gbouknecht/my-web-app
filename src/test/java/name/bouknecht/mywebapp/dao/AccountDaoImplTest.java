package name.bouknecht.mywebapp.dao;

import static java.lang.Integer.MAX_VALUE;
import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static name.bouknecht.mywebapp.test.hamcrest.IsSameAccount.sameAccountAs;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestDao;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testContext.xml")
@Transactional
public class AccountDaoImplTest {

    @Autowired
    private AccountDao accountDao;

    private TestData testData;

    @Autowired
    private TestDao testDao;

    @Before
    public void setUp() {
        testData = new TestData();
        testDao.insertTestData(testData).flush();
    }

    @Test
    public void findAllShouldReturnAllAccounts() {
        List<Account> expectedAccounts = testData.createAccounts();

        List<Account> actualAccounts = accountDao.findAll();

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void findWithNullShouldReturnAllAccounts() {
        List<Account> expectedAccounts = testData.createAccounts();

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
        List<Account> expectedAccounts = filter(testData.createAccounts(), text);

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

    @Test
    public void findByIdShouldFindExistingAccount() {
        Account expectedAccount = accountDao.findByUserId(testData.createAccounts().get(2).getUserId());

        Account actualAccount = accountDao.findById(expectedAccount.getId());

        assertThat(actualAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void findByIdShouldReturnNullWhenAccountDoesNotExists() {
        Account actualAccount = accountDao.findById(MAX_VALUE);

        assertThat(actualAccount, is(nullValue()));
    }

    @Test
    public void findByIdShouldReturnNullWhenGivenIdIsNull() {
        Account actualAccount = accountDao.findById(null);

        assertThat(actualAccount, is(nullValue()));
    }

    @Test
    public void findByUserIdShouldFindExistingAccount() {
        Account expectedAccount = testData.createAccounts().get(0);

        Account actualAccount = accountDao.findByUserId(expectedAccount.getUserId());

        assertThat(actualAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void findByUserIdShouldReturnNullWhenAccountDoesNotExists() {
        Account actualAccount = accountDao.findByUserId(testData.createRandomAccount().getUserId());

        assertThat(actualAccount, is(nullValue()));
    }

    @Test
    public void persistShouldMakeAccountPersistent() {
        Account account = testData.createRandomAccount();
        List<Account> expectedAccounts = testData.createAccounts();
        expectedAccounts.add(account);

        accountDao.persist(account);
        testDao.flush();
        List<Account> actualAccounts = accountDao.findAll();

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void mergeShouldUpdateAccountInDatabase() {
        Account expectedAccount = accountDao.findByUserId(testData.createAccounts().get(1).getUserId());
        testDao.clear();
        expectedAccount.setFirstname(testData.createRandomAccount().getFirstname());

        accountDao.merge(expectedAccount);
        testDao.flush();
        Account actualAccount = accountDao.findById(expectedAccount.getId());

        assertThat(actualAccount, sameAccountAs(expectedAccount));
    }

    @Test
    public void mergeShouldReturnManagedInstance() {
        Account expectedAccount = accountDao.findByUserId(testData.createAccounts().get(1).getUserId());
        testDao.clear();

        Account actualAccount = accountDao.merge(expectedAccount);

        assertTrue(testDao.isManaged(actualAccount));
        assertThat(actualAccount, sameAccountAs(expectedAccount));
    }

    @Test
    public void removeShouldRemoveAccountFromDatabase() {
        Account account = accountDao.findByUserId(testData.createAccounts().get(1).getUserId());

        accountDao.remove(account);
        testDao.flush();
        Account actualAccount = accountDao.findById(account.getId());

        assertThat(actualAccount, is(nullValue()));
    }
}
