package name.bouknecht.mywebapp.service;

import static name.bouknecht.mywebapp.test.hamcrest.IsSameAccount.sameAccountAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import java.util.List;

import name.bouknecht.mywebapp.dao.AccountDao;
import name.bouknecht.mywebapp.model.Account;
import name.bouknecht.mywebapp.test.data.TestData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AccountServiceImplTest {
    private       AccountService accountService;
    private       TestData       data;
    private @Mock AccountDao     accountDao;

    @Before
    public void setUp() {
        initMocks(this);

        accountService = new AccountServiceImpl();
        data           = new TestData();

        setField(accountService, "accountDao", accountDao);
    }

    @Test
    public void findShouldFindAccounts() {
        String text = "abc";
        List<Account> expectedAccounts = data.createAccounts();
        given(accountDao.find(text)).willReturn(expectedAccounts);

        List<Account> actualAccounts = accountService.find(text);

        assertEquals(expectedAccounts, actualAccounts);
    }

    @Test
    public void findByIdSouldFindAccountById() {
        Integer id = 13;
        Account expectedAccount = data.createRandomAccount();
        given(accountDao.findById(id)).willReturn(expectedAccount);

        Account actualAccount = accountService.findById(id);

        assertThat(actualAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void userIdExistsShouldReturnTrueIfUserIdExists() {
        given(accountDao.findByUserId("abc")).willReturn(data.createRandomAccount());

        boolean result = accountService.userIdExists("abc");

        assertThat(result, is(true));
    }

    @Test
    public void userIdExistsShouldReturnFalseIfUserIdDoesNotExists() {
        given(accountDao.findByUserId("abc")).willReturn(null);

        boolean result = accountService.userIdExists("abc");

        assertThat(result, is(false));
    }

    @Test
    public void persistShouldPersistAccount() {
        Account account = data.createRandomAccount();

        accountService.persist(account);

        verify(accountDao).persist(account);
    }

    @Test
    public void mergeShouldMergeAccount() {
        Account account = data.createRandomAccount();
        Account expectedAccount = data.createRandomAccount();
        given(accountDao.merge(account)).willReturn(expectedAccount);

        Account actualAccount = accountService.merge(account);

        assertThat(actualAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void removeByIdShouldRemoveAccountAndReturnRemovedAccountWhenAccountExists() {
        Account expectedAccount = data.createRandomAccount();
        given(accountDao.findById(13)).willReturn(expectedAccount);

        Account actualAccount = accountService.removeById(13);

        verify(accountDao).remove(expectedAccount);
        assertThat(actualAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void removeByIdShouldNotRemoveAccountAndReturnNullWhenAccountDoesNotExists() {
        given(accountDao.findById(13)).willReturn(null);

        Account actualAccount = accountService.removeById(13);

        verify(accountDao, never()).remove(any(Account.class));
        assertThat(actualAccount, is(nullValue()));
    }
}
