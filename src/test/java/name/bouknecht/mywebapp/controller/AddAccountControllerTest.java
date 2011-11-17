package name.bouknecht.mywebapp.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;

public class AddAccountControllerTest {
    private TestData             data;
    private AddAccountController addAccountController;
    private AccountDao           accountDao;
    private FacesContext         facesContext;

    @Before
    public void setUp() {
        data                 = new TestData();
        addAccountController = new AddAccountController();
        accountDao           = mock(AccountDao.class);
        facesContext         = mock(FacesContext.class);

        setField(addAccountController, "accountDao",   accountDao);
        setField(addAccountController, "facesContext", facesContext);
    }

    @Test
    public void shouldPersistAccountAndHasOutcomeAddedWhenSuccessful() {
        Account account = data.createRandomAccount();
        setField(addAccountController, "account", account);

        String outcome = addAccountController.add();

        verify(accountDao, times(1)).persist(account);
        assertThat(outcome, is("added"));
    }

    @Test
    public void shouldNotPersistAccountButNotifyUserWhenUserIdAlreadyExists() {
        Account account = data.createRandomAccount();
        setField(addAccountController, "account", account);
        given(accountDao.findByUserId(account.getUserId())).willReturn(data.createRandomAccount());

        String outcome = addAccountController.add();

        verify(accountDao, never()).persist(account);
        verify(facesContext, times(1)).addMessage(eq("user-id-input-text"), any(FacesMessage.class));
        assertThat(outcome, is(nullValue()));
    }
}
