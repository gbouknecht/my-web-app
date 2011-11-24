package name.bouknecht.mywebapp.controller;

import javax.faces.context.FacesContext;

import name.bouknecht.mywebapp.annotation.RequestScoped;
import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.util.MessageUtils;

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

    @Autowired
    private FacesContext facesContext;

    private Account account = new Account();

    public String add() {
        if (userIdNotAlreadyExists()) {
            return addAccount();
        } else {
            return notifyUserThatUserIdAlreadyExists();
        }
    }

    private boolean userIdNotAlreadyExists() {
        return accountDao.findByUserId(account.getUserId()) == null;
    }

    private String addAccount() {
        logger.info("Adding account: " + account);
        accountDao.persist(account);
        logger.info("Added account: " + account);
        return "pretty:addedAccount";
    }

    private String notifyUserThatUserIdAlreadyExists() {
        facesContext.addMessage("user-id-input-text", MessageUtils.createFacesMessage("validationMessageAlreadyExists"));
        return null;
    }

    public Account getAccount() {
        return account;
    }
}
