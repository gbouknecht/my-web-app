package name.bouknecht.mywebapp.controller;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.List;

import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;

public class ShowAccountsControllerTest {
    private TestData               data;
    private ShowAccountsController showAccountsController;
    private AccountDao             accountDao;

    @Before
    public void setUp() {
        data                   = new TestData();
        showAccountsController = new ShowAccountsController();
        accountDao             = mock(AccountDao.class);

        setField(showAccountsController, "accountDao", accountDao);
    }

    @Test
    public void shouldContainAllAccountsAfterInitialization() {
        List<Account> expectedAccounts = data.createAccounts();
        given(accountDao.findAllAccounts()).willReturn(expectedAccounts);

        showAccountsController.initialize();
        List<Account> actualAccounts = showAccountsController.getAccounts();

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void shouldContainFoundAccountsAfterFind() {
        List<Account> expectedAccounts = data.createAccounts();
        expectedAccounts.remove(0);
        given(accountDao.findAllAccounts()).willReturn(data.createAccounts());
        given(accountDao.find("abc")).willReturn(expectedAccounts);

        showAccountsController.initialize();
        showAccountsController.setFindText("abc");
        showAccountsController.find();
        List<Account> actualAccounts = showAccountsController.getAccounts();

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }
}
