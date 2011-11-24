package name.bouknecht.mywebapp.controller;

import name.bouknecht.mywebapp.annotation.RequestScoped;
import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
@RequestScoped
public class RemoveAccountController {
    private static final Logger logger = LoggerFactory.getLogger(RemoveAccountController.class);

    @Autowired
    private AccountDao accountDao;

    private Integer accountId;

    @Transactional    // TODO: Introduce service layer and remove this @Transactional.
    public String remove() {
        Account account = accountDao.findById(accountId);
        if (account != null) {
            return removeAccount(account);
        } else {
            return notifyUserThatAccountIsNotFound(accountId);
        }
    }

    private String removeAccount(Account account) {
        logger.info("Removing account: " + account);
        accountDao.remove(account);
        logger.info("Removed account: " + account);
        return "pretty:removedAccount";
    }

    private String notifyUserThatAccountIsNotFound(Integer id) {
        logger.warn("Account with ID {} not found", id);
        return "pretty:accountNotFound";
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
