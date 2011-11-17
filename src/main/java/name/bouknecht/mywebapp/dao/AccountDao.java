package name.bouknecht.mywebapp.dao;

import java.util.List;

import name.bouknecht.mywebapp.model.Account;

public interface AccountDao {
    List<Account> findAll();

    /**
     * Finds all the accounts where at least one of the fields contain the
     * given <code>text</code> (case-insensitive). The same as
     * {@link #findAll()} if <code>text</code> is <code>null</code>.
     */
    List<Account> find(String text);

    /**
     * @return <code>null</code> when not found
     */
    Account findByUserId(String userId);

    /**
     * @param account not <code>null</code>
     */
    void persist(Account account);
}
