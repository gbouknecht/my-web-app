package name.bouknecht.mywebapp.test.data;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import name.bouknecht.mywebapp.model.Account;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    @Resource
    private DataSource testDataSource;

    public TestDao insertTestData(TestData testData) {
        insertAccounts(testData.createAccounts());
        return this;
    }

    private void insertAccounts(List<Account> accounts) {
        SimpleJdbcInsert insertAccount = new SimpleJdbcInsert(testDataSource).withTableName(Account.class.getSimpleName());
        for (Account account : accounts) {
            insertAccount.execute(new BeanPropertySqlParameterSource(account));
        }
    }
}
