package name.bouknecht.mywebapp.test.data;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import name.bouknecht.mywebapp.model.Account;

/**
 * This class is used by the unit tests to create test data.
 *
 * <p><i>Note: unit tests may change the data that is returned by this class
 * (even using reflection when needed). Therefore, the data must not be cached
 * (by static fields for example). Fresh copies should be returned instead.</i></p>
 */
public class TestData {
    private final Random random = new Random();

    public List<Account> createAccounts() {
        List<Account> result = new ArrayList<Account>();
        for (int i = 0; i < 20; i++) {
            result.add(new Account(format(   "userId-%02d", i),
                                   format("firstname-%02d", i),
                                   format( "lastname-%02d", i)));
        }
        return result;
    }

    public Account createRandomAccount() {
        return new Account(createRandomString(Account.USER_ID_MAX_LENGTH),
                           createRandomString(Account.FIRSTNAME_MAX_LENGTH),
                           createRandomString(Account.LASTNAME_MAX_LENGTH));
    }

    private String createRandomString(int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append((char) (65 + random.nextInt(26)));
        }
        return result.toString();
    }
}
