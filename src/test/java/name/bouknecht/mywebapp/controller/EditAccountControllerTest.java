package name.bouknecht.mywebapp.controller;

import static name.bouknecht.mywebapp.test.hamcrest.IsSameAccount.sameAccountAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class EditAccountControllerTest {
    private       EditAccountController editAccountController;
    private       TestData              data;
    private @Mock AccountDao            accountDao;

    @Before
    public void setUp() {
        initMocks(this);

        editAccountController = new EditAccountController();
        data                  = new TestData();

        setField(editAccountController, "accountDao", accountDao);
    }

    @Test
    public void shouldHaveAccountWithGiveAccountIdAndOutcomeNullAfterInitializationWhenAccountExists() {
        Account expectedAccount = data.createRandomAccount();
        setField(editAccountController, "accountId", 13);
        given(accountDao.findById(13)).willReturn(expectedAccount);

        String outcome = editAccountController.initialize();
        Account actualAccount = editAccountController.getAccount();

        assertThat(outcome, is(nullValue()));
        assertThat(actualAccount, sameAccountAs(expectedAccount));
    }

    @Test
    public void shouldHaveOutcomeNotFoundAfterInitializationWhenAccountDoesNotExists() {
        setField(editAccountController, "accountId", 13);
        given(accountDao.findById(13)).willReturn(null);

        String outcome = editAccountController.initialize();

        assertThat(outcome, is("notFound"));
    }

    @Test
    public void shouldSaveAccountAndHasOutcomeSavedWhenSuccessful() {
        Account account = data.createRandomAccount();
        setField(editAccountController, "account", account);

        String outcome = editAccountController.save();

        verify(accountDao).merge(account);
        assertThat(outcome, is("saved"));
    }
}
