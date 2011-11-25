package name.bouknecht.mywebapp.controller;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.List;

import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.service.AccountService;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ShowAccountsControllerTest {
    private       ShowAccountsController showAccountsController;
    private       TestData               data;
    private @Mock AccountService         accountService;

    @Before
    public void setUp() {
        initMocks(this);

        showAccountsController = new ShowAccountsController();
        data                   = new TestData();

        setField(showAccountsController, "accountService", accountService);
    }

    @Test
    public void shouldContainAllAccountsAfterInitialization() {
        List<Account> expectedAccounts = data.createAccounts();
        given(accountService.find(null)).willReturn(expectedAccounts);

        showAccountsController.initialize();
        List<Account> actualAccounts = showAccountsController.getAccounts();

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void shouldContainFoundAccountsAfterFind() {
        List<Account> expectedAccounts = data.createAccounts();
        expectedAccounts.remove(0);
        given(accountService.find(null)).willReturn(data.createAccounts());
        given(accountService.find("abc")).willReturn(expectedAccounts);

        showAccountsController.initialize();
        showAccountsController.setFindText("abc");
        showAccountsController.find();
        List<Account> actualAccounts = showAccountsController.getAccounts();

        assertThat(actualAccounts, hasSize(expectedAccounts.size()));
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }
}
