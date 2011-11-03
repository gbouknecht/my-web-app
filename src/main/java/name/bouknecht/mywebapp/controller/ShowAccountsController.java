package name.bouknecht.mywebapp.controller;

import java.util.List;

import name.bouknecht.mywebapp.annotation.RequestScoped;
import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestScoped
public class ShowAccountsController {

    @Autowired
    private AccountDao accountDao;

    private List<Account> accounts;

    public void initialize() {
        accounts = accountDao.findAllAccounts();
    }

    public List<Account> getAccounts() {
        assert accounts != null;
        return accounts;
    }
}
