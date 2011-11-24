package name.bouknecht.mywebapp.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class RemoveAccountControllerTest {
    private       RemoveAccountController removeAccountController;
    private       TestData                data;
    private @Mock AccountDao              accountDao;

    @Before
    public void setUp() {
        initMocks(this);

        removeAccountController = new RemoveAccountController();
        data                    = new TestData();

        setField(removeAccountController, "accountDao", accountDao);
    }

    @Test
    public void shouldRemoveAccountAndNavigateToRemovedAccountWhenSuccessful() {
        Account expectedAccount = data.createRandomAccount();
        setField(removeAccountController, "accountId", 13);
        given(accountDao.findById(13)).willReturn(expectedAccount);

        String outcome = removeAccountController.remove();

        verify(accountDao).remove(expectedAccount);
        assertThat(outcome, is("pretty:removedAccount"));
    }

    @Test
    public void shouldNotRemoveAccountAndNavigateToAccountNotFoundWhenAccountDoesNotExists() {
        setField(removeAccountController, "accountId", 13);
        given(accountDao.findById(13)).willReturn(null);

        String outcome = removeAccountController.remove();

        verify(accountDao, never()).remove(any(Account.class));
        assertThat(outcome, is("pretty:accountNotFound"));
    }
}
