package name.bouknecht.mywebapp.dao;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

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

    @Autowired
    private TestDao testDao;

    private final TestData testData = new TestData();

    @Before
    public void setUp() {
        testDao.insertTestData(testData);
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
}
