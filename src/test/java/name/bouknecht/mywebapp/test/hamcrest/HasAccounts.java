package name.bouknecht.mywebapp.test.hamcrest;

import java.util.ArrayList;
import java.util.List;

import name.bouknecht.mywebapp.model.Account;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsCollectionContaining;

public class HasAccounts {

    @Factory
    public static Matcher<Iterable<? super Account>> hasAccount(Account expectedAccount) {
        return new IsCollectionContaining<Account>(IsSameAccount.sameAccountAs(expectedAccount));
    }

    @Factory
    public static Matcher<Iterable<Account>> hasAccounts(List<Account> expectedAccounts) {
        List<Matcher<? super Iterable<Account>>> all = new ArrayList<Matcher<? super Iterable<Account>>>();
        if (expectedAccounts != null) {
            for (Account expectedAccount : expectedAccounts) {
                all.add(hasAccount(expectedAccount));
            }
        }
        return Matchers.allOf(all);
    }
}
