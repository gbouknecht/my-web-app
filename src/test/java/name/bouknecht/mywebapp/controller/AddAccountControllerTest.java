package name.bouknecht.mywebapp.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;

public class AddAccountControllerTest {
    private TestData             data;
    private AddAccountController addAccountController;
    private AccountDao           accountDao;

    @Before
    public void setUp() {
        data                 = new TestData();
        addAccountController = new AddAccountController();
        accountDao           = mock(AccountDao.class);

        setField(addAccountController, "accountDao", accountDao);
    }

    @Test
    public void shouldHasPersistedAccountAndOutcomeAddedAfterSuccessfulAdd() {
        Account account = data.createRandomAccount();
        setField(addAccountController, "account", account);

        String outcome = addAccountController.add();

        verify(accountDao, only()).persist(account);
        assertThat(outcome, is("added"));
    }
}
