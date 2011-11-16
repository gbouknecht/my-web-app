package name.bouknecht.mywebapp.controller;

import name.bouknecht.mywebapp.annotation.RequestScoped;
import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestScoped
public class AddAccountController {
    private static final Logger logger = LoggerFactory.getLogger(AddAccountController.class);

    @Autowired
    private AccountDao accountDao;

    private Account account = new Account();

    public String add() {
        logger.info("Adding account: " + account);
        accountDao.persist(account);
        logger.info("Added account: " + account);
        // TODO: Handle error case.
        return "added";
    }

    public Account getAccount() {
        return account;
    }
}
