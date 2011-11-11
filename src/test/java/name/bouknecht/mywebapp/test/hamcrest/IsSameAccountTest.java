package name.bouknecht.mywebapp.test.hamcrest;

import static java.lang.Integer.MAX_VALUE;
import static name.bouknecht.mywebapp.test.hamcrest.IsSameAccount.sameAccountAs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import name.bouknecht.mywebapp.model.Account;

import org.junit.Before;
import org.junit.Test;

public class IsSameAccountTest {
    private Account expectedAccount;
    private Account actualAccount;

    @Before
    public void setUp() {
        expectedAccount = createAccount();
        actualAccount   = createAccount();
    }

    private Account createAccount() {
        return new Account("userid0", "firstname0", "lastname0");
    }

    @Test
    public void shouldMatchWhenBothAreNull() {
        assertThat(null, is(sameAccountAs(null)));
    }

    @Test
    public void shouldNotMatchWhenOnlyOneIsNull() {
        assertThat(null,          is(not(sameAccountAs(expectedAccount))));
        assertThat(actualAccount, is(not(sameAccountAs(null))));
    }

    @Test
    public void shouldMatchWhenSameObjects() {
        assertThat(expectedAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void shouldMatchWhenDifferentObjectsButSameFieldValues() {
        assertThat(actualAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void shouldMatchWhenOnlyIdFieldsAreNotTheSame() {
        setField(actualAccount, "id", MAX_VALUE);
        assertThat(actualAccount, is(sameAccountAs(expectedAccount)));
    }

    @Test
    public void shouldNotMatchWhenUserIdFieldsAreNotTheSame() {
        actualAccount.setUserId("userid1");
        assertThat(actualAccount, is(not(sameAccountAs(expectedAccount))));
    }

    @Test
    public void shouldNotMatchWhenFirstnameFieldsAreNotTheSame() {
        actualAccount.setFirstname("firstname1");
        assertThat(actualAccount, is(not(sameAccountAs(expectedAccount))));
    }

    @Test
    public void shouldNotMatchWhenLastnameFieldsAreNotTheSame() {
        actualAccount.setLastname("lastname1");
        assertThat(actualAccount, is(not(sameAccountAs(expectedAccount))));
    }
}
