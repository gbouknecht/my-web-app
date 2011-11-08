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
    private String        findText;

    public void initialize() {
        find();
    }

    public void find() {
        accounts = accountDao.find(findText);
    }

    public List<Account> getAccounts() {
        assert accounts != null;
        return accounts;
    }

    public String getFindText() {
        return findText;
    }

    public void setFindText(String findText) {
        this.findText = findText;
    }
}
