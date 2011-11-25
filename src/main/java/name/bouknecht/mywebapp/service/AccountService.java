package name.bouknecht.mywebapp.service;

import java.util.List;

import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;

public interface AccountService {

    /**
     * @see AccountDao#find(String)
     */
    List<Account> find(String text);

    /**
     * @see AccountDao#findById(Integer)
     */
    Account findById(Integer id);

    boolean userIdExists(String userId);

    /**
     * @see AccountDao#persist(Account)
     */
    void persist(Account account);

    /**
     * @see AccountDao#merge(Account)
     */
    Account merge(Account account);

    /**
     * @return removed account; <code>null</code> if account did not exists
     */
    Account removeById(Integer id);
}
