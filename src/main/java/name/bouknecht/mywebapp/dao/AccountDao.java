package name.bouknecht.mywebapp.dao;

import java.util.List;

import name.bouknecht.mywebapp.model.Account;

public interface AccountDao {
    List<Account> findAllAccounts();
}
