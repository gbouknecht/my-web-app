package name.bouknecht.mywebapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import name.bouknecht.mywebapp.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoJdbcImpl implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Account> findAllAccounts() {
        return jdbcTemplate.query("select id, userid, firstname, lastname from account", new AccountMapper());
    }

    private static class AccountMapper implements RowMapper<Account> {
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Account(Integer.valueOf(rs.getInt("id")),
                               rs.getString("userid"),
                               rs.getString("firstname"),
                               rs.getString("lastname"));
        }
    }
}
