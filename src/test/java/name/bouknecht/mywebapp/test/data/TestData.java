package name.bouknecht.mywebapp.test.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import name.bouknecht.mywebapp.model.Account;

public class TestData {
    public List<Account> createAccounts() {
        return new ArrayList<Account>(Arrays.asList(new Account(0, "ppieterse", "Piet", "Pieterse"),
                                                    new Account(1, "jjansse", "Jan", "Jansse"),
                                                    new Account(2, "ggerritse", "Gerrit", "Gerritse")));
    }
}
