package ru.mirea.petShop.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mirea.petShop.Balance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class BalanceDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BalanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Balance> getBalanceByUserID(int userID) {
        return jdbcTemplate.query("select * from Balance where USERID =" + userID, new RowMapper<Balance>() {
            @Override
            public Balance mapRow(ResultSet resultSet, int i) throws SQLException {
                double money = resultSet.getDouble("Money");
                return new Balance(money);
            }
        });
    }

    public void updateUserBalance(double newBalance, int userID) {
        jdbcTemplate.execute("UPDATE BALANCE set MONEY = " + newBalance + " where USERID = " + userID);
    }
}
