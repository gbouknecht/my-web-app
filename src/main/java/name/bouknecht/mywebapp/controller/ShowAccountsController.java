package name.bouknecht.mywebapp.controller;

import java.util.ArrayList;
import java.util.List;

import name.bouknecht.mywebapp.annotation.RequestScoped;
import name.bouknecht.mywebapp.model.Account;

import org.springframework.stereotype.Controller;

@Controller
@RequestScoped
public class ShowAccountsController {
    private List<Account> accounts;

    public void loadAccounts() {
        accounts = new ArrayList<Account>();
        accounts.add(new Account("ppieterse", "Piet", "Pieterse"));
        accounts.add(new Account("jjansse", "Jan", "Jansse"));
        accounts.add(new Account("ggerritse", "Gerrit", "Gerritse"));
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
