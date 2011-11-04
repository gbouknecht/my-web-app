package name.bouknecht.mywebapp.test.data;

import java.util.List;

import javax.sql.DataSource;

import name.bouknecht.mywebapp.model.Account;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

public class TestDatabase {
    private final EmbeddedDatabase embeddedDatabase;

    public TestDatabase(TestData testData) {
        embeddedDatabase = new EmbeddedDatabaseBuilder().addScript("schema.sql").build();
        insertTestData(testData);
    }

    public DataSource getDataSource() {
        return embeddedDatabase;
    }

    public void shutdown() {
        embeddedDatabase.shutdown();
    }

    private final void insertTestData(TestData testData) {
        insertAccounts(testData.createAccounts());
    }

    private final void insertAccounts(List<Account> accounts) {
        SimpleJdbcInsert insertAccount = new SimpleJdbcInsert(getDataSource()).withTableName(Account.class.getSimpleName());
        for (Account account : accounts) {
            insertAccount.execute(new BeanPropertySqlParameterSource(account));
        }
    }
}
