package name.bouknecht.mywebapp.test.data;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import name.bouknecht.mywebapp.model.Account;

public class TestData {
    public List<Account> createAccounts() {
        List<Account> result = new ArrayList<Account>();
        for (int i = 0; i < 20; i++) {
            result.add(new Account(format(   "userId-%02d", i),
                                   format("firstname-%02d", i),
                                   format( "lastname-%02d", i)));
        }
        return result;
    }
}
