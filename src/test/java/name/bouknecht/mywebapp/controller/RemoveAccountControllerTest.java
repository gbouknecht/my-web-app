package name.bouknecht.mywebapp.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.service.AccountService;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class RemoveAccountControllerTest {
    private       RemoveAccountController removeAccountController;
    private       TestData                data;
    private @Mock AccountService          accountService;

    @Before
    public void setUp() {
        initMocks(this);

        removeAccountController = new RemoveAccountController();
        data                    = new TestData();

        setField(removeAccountController, "accountService", accountService);
    }

    @Test
    public void shouldRemoveAccountAndNavigateToRemovedAccount() {
        verifyRemoveByIdCallAndOutcome(data.createRandomAccount(), "pretty:removedAccount");
    }

    @Test
    public void shouldRemoveAccountAndNavigateToAccountNotFoundWhenAccountDoesNotExists() {
        verifyRemoveByIdCallAndOutcome(null, "pretty:accountNotFound");
    }

    private void verifyRemoveByIdCallAndOutcome(Account removedAccount, String expectedOutcome) {
        setField(removeAccountController, "accountId", 13);
        given(accountService.removeById(13)).willReturn(removedAccount);

        String outcome = removeAccountController.remove();

        verify(accountService).removeById(13);
        assertThat(outcome, is(expectedOutcome));
    }
}
