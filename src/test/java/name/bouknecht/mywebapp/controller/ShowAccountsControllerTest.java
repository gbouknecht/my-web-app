package name.bouknecht.mywebapp.controller;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.Arrays;
import java.util.List;

import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;

import org.junit.Before;
import org.junit.Test;

public class ShowAccountsControllerTest {
    private ShowAccountsController showAccountsController;
    private AccountDao             accountDao;
    private List<Account>          expectedAccounts;

    @Before
    public void setUp() {
        showAccountsController = new ShowAccountsController();
        accountDao = mock(AccountDao.class);
        setField(showAccountsController, "accountDao", accountDao);
        expectedAccounts = createAccounts();
    }

    private List<Account> createAccounts() {
        return Arrays.asList(new Account(0, "user1", "first1", "last1"),
                             new Account(1, "user2", "first2", "last2"),
                             new Account(2, "user3", "first3", "last3"));
    }

    @Test
    public void shouldContainAllAccountsAfterInitialization() {
        given(accountDao.findAllAccounts()).willReturn(expectedAccounts);
        showAccountsController.initialize();
        List<Account> actualAccounts = showAccountsController.getAccounts();
        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }
}
