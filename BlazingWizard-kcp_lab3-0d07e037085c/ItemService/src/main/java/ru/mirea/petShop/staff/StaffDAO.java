package ru.mirea.petShop.staff;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ru.mirea.petShop.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StaffDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StaffDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Nullable
    public List<Staff> findAll() {
        return jdbcTemplate.query("select * from items  where isPet = 0", new StaffRowMapper());
    }

    @Nullable
    public List<Staff> findStaffByID(int id) {
        return jdbcTemplate.query("select Id, Name, Price from Items where isPet = 0 and Id =" + id , new StaffRowMapper());
    }

    class StaffRowMapper implements RowMapper<Staff>{
        @Override
        public Staff mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("Id");
            String staffName = resultSet.getString("Name");
            double price = resultSet.getDouble("price");

            return new Staff(id, price, staffName);
        }
    }


}
