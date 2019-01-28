package ru.mirea.petShop.IdentityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mirea.petShop.User;

@Component
public class IdentityDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public IdentityDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public User getUserByLogin(String userLogin){
        return jdbcTemplate.queryForObject("select * from Users where Login = '" + userLogin + "'", (resultSet, i) -> {
            int id = resultSet.getInt("USERID");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            String role = resultSet.getString("ROLE");

            return new User(id, login, password, role);
        });
    }
}
