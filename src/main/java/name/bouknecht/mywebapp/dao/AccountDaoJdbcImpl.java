package name.bouknecht.mywebapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import name.bouknecht.mywebapp.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoJdbcImpl implements AccountDao {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDaoJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Account> findAllAccounts() {
        return jdbcTemplate.query("select * from account", (SqlParameterSource) null, new AccountMapper());
    }

    public List<Account> find(String text) {
        if (text == null) return findAllAccounts();
        return jdbcTemplate.query("select * from account where lower(userid) like :text or lower(firstname) like :text or lower(lastname) like :text",
                                  new MapSqlParameterSource("text", "%" + text.toLowerCase() + "%"),
                                  new AccountMapper());
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
