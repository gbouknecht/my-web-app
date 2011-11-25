package name.bouknecht.mywebapp.controller;

import name.bouknecht.mywebapp.annotation.RequestScoped;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestScoped
public class RemoveAccountController {
    private static final Logger logger = LoggerFactory.getLogger(RemoveAccountController.class);

    @Autowired
    private AccountService accountService;

    private Integer accountId;

    public String remove() {
        logger.info("Removing account with ID {}", accountId);
        Account account = accountService.removeById(accountId);
        if (account != null) {
            logger.info("Removed account: " + account);
            return "pretty:removedAccount";
        } else {
            logger.warn("Account with ID {} not found", accountId);
            return "pretty:accountNotFound";
        }
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
