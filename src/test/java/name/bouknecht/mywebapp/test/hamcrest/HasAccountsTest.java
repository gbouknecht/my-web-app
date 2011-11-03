package name.bouknecht.mywebapp.test.hamcrest;

import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccount;
import static name.bouknecht.mywebapp.test.hamcrest.HasAccounts.hasAccounts;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import name.bouknecht.mywebapp.model.Account;

import org.junit.Before;
import org.junit.Test;

public class HasAccountsTest {
    List<Account> expectedAccounts;
    List<Account> actualAccounts;

    @Before
    public void setUp() {
        expectedAccounts = createAccounts();
        actualAccounts   = createAccounts();

    }

    private List<Account> createAccounts() {
        return new ArrayList<Account>(Arrays.asList(new Account(0, "userid0", "firstname0", "lastname0"),
                                                    new Account(1, "userid1", "firstname1", "lastname1"),
                                                    new Account(2, "userid2", "firstname2", "lastname2")));
    }

    @Test
    public void hasAccountShouldMatchWhenExpectingNullAndActualAccountsContainsNull() {
        actualAccounts.add(null);
        assertThat(actualAccounts, hasAccount(null));
    }

    @Test
    public void hasAccountShouldNotMatchWhenExpectingNullAndActualAccountsNotContainsNull() {
        assertThat(actualAccounts, not(hasAccount(null)));
    }

    @Test
    public void hasAccountShouldNeverMatchWhenActualAccountsIsNull() {
        assertThat(null, not(hasAccount(null)));
        assertThat(null, not(hasAccount(expectedAccounts.get(0))));
    }

    @Test
    public void hasAccountShouldNeverMatchWhenActualAccountsIsEmpty() {
        assertThat(new ArrayList<Account>(), not(hasAccount(null)));
        assertThat(new ArrayList<Account>(), not(hasAccount(expectedAccounts.get(0))));
    }

    @Test
    public void hasAccountShouldMatchWhenActualAccountsContainsExpectedAccount() {
        for (Account expectedAccount : expectedAccounts) {
            assertThat(actualAccounts, hasAccount(expectedAccount));
        }
    }

    @Test
    public void hasAccountShouldNotMatchWhenActualAccountsNotContainsExpectedAccount() {
        Account removedAccount = actualAccounts.remove(1);
        assertThat(actualAccounts, not(hasAccount(removedAccount)));
    }

    @Test
    public void hasAccountsShouldAlwaysMatchWhenExpectingNull() {
        assertThat(null,                     hasAccounts(null));
        assertThat(new ArrayList<Account>(), hasAccounts(null));
        assertThat(actualAccounts,           hasAccounts(null));
    }

    @Test
    public void hasAccountsShouldAlwaysMatchWhenExpectingEmptyCollection() {
        assertThat(null,                     hasAccounts(new ArrayList<Account>()));
        assertThat(new ArrayList<Account>(), hasAccounts(new ArrayList<Account>()));
        assertThat(actualAccounts,           hasAccounts(new ArrayList<Account>()));
    }

    @Test
    public void hasAccountsShouldMatchWhenActualAccountsContainsAllExpectedAccounts() {
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void hasAccountsShouldMatchWhenActualAccountsContainsAllExpectedAccountsAndMore() {
        expectedAccounts.remove(1);
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }

    @Test
    public void hasAccountsShouldNotMatchWhenActualAccountsIsNullAndExpectedAccountsIsNotEmpty() {
        assertThat(null, not(hasAccounts(expectedAccounts)));
    }

    @Test
    public void hasAccountsShouldNotMatchWhenActualAccountsNotContainsAllExpectedAccounts() {
        actualAccounts.remove(1);
        assertThat(actualAccounts, not(hasAccounts(expectedAccounts)));
    }

    @Test
    public void hasAccountsShouldMatchRegardlessOfOrder() {
        Collections.shuffle(actualAccounts);
        assertThat(actualAccounts, hasAccounts(expectedAccounts));
    }
}
