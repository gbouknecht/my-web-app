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
public class EditAccountController {
    private static final Logger logger = LoggerFactory.getLogger(EditAccountController.class);

    @Autowired
    private AccountDao accountDao;

    private Integer accountId;
    private Account account;

    public String initialize() {
        account = accountDao.findById(accountId);
        if (account == null) {
            logger.warn("Account with ID {} not found", accountId);
            return "pretty:accountNotFound";
        }
        return null;
    }

    public String save() {
        logger.info("Saving account: " + account);
        account = accountDao.merge(account);
        logger.info("Saved account: " + account);
        return "pretty:savedAccount";
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }
}
