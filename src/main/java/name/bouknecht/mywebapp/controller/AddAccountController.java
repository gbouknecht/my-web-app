package name.bouknecht.mywebapp.controller;

import javax.faces.bean.RequestScoped;

import name.bouknecht.mywebapp.model.Account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
@RequestScoped
public class AddAccountController {
    private static final Logger logger = LoggerFactory.getLogger(AddAccountController.class);

    private Account account = new Account();

    public void add() {
        logger.info("Add account: " + account);
        // TODO: Implement this action.
    }

    public Account getAccount() {
        return account;
    }
}
