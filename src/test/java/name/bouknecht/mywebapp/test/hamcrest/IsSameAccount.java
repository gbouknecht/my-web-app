package name.bouknecht.mywebapp.test.hamcrest;

import name.bouknecht.mywebapp.model.Account;

import org.apache.commons.lang3.ObjectUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class IsSameAccount extends BaseMatcher<Account> {
    private final Account expectedAccount;

    public IsSameAccount(Account expectedAccount) {
        this.expectedAccount = expectedAccount;
    }

    @Override
    public boolean matches(Object actualObject) {
        if (expectedAccount == actualObject) return true;
        if ((expectedAccount == null) || (actualObject == null)) return false;
        return areEqual(expectedAccount, (Account) actualObject);
    }

    private boolean areEqual(Account expectedAccount, Account actualAccount) {
        return    ObjectUtils.equals(expectedAccount.getUserId(),    actualAccount.getUserId())
               && ObjectUtils.equals(expectedAccount.getFirstname(), actualAccount.getFirstname())
               && ObjectUtils.equals(expectedAccount.getLastname(),  actualAccount.getLastname());
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expectedAccount);
    }

    public static Matcher<? super Account> sameAccountAs(Account expectedAccount) {
        return new IsSameAccount(expectedAccount);
    }
}
