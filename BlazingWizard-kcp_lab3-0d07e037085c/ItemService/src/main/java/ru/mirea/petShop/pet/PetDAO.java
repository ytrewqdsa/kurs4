package ru.mirea.petShop.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mirea.petShop.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PetDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pet> findAll() {
        return jdbcTemplate.query("select Id, Name, Price , Age from Items where isPet = 1", new PetRowMapper());
    }

    public List<Pet> findPetByID(int id) {
        return jdbcTemplate.query("select Id, Name, Price, Age from Items where isPet = 1 and Id =" + id , new PetRowMapper());
    }

    private class PetRowMapper implements RowMapper<Pet> {

        @Override
        public Pet mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("Id");
            String petName = resultSet.getString("Name");
            double price = resultSet.getDouble("price");
            double age = resultSet.getDouble("Age");

            return new Pet(id, price, petName, age);
        }
    }
}
