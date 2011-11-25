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
public class EditAccountController {
    private static final Logger logger = LoggerFactory.getLogger(EditAccountController.class);

    @Autowired
    private AccountService accountService;

    private Integer accountId;
    private Account account;

    public String initialize() {
        account = accountService.findById(accountId);
        if (account == null) {
            logger.warn("Account with ID {} not found", accountId);
            return "pretty:accountNotFound";
        }
        return null;
    }

    public String save() {
        logger.info("Saving account: " + account);
        account = accountService.merge(account);
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
