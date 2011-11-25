package name.bouknecht.mywebapp.service;

import java.util.List;

import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public List<Account> find(String text) {
        return accountDao.find(text);
    }

    public Account findById(Integer id) {
        return accountDao.findById(id);
    }

    public boolean userIdExists(String userId) {
        return accountDao.findByUserId(userId) != null;
    }

    public void persist(Account account) {
        accountDao.persist(account);
    }

    public Account merge(Account account) {
        return accountDao.merge(account);
    }

    public Account removeById(Integer id) {
        Account account = accountDao.findById(id);
        if (account != null) accountDao.remove(account);
        return account;
    }
}
