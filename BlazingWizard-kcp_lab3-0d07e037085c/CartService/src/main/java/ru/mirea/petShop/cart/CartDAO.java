package ru.mirea.petShop.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.mirea.petShop.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CartDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CartDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Item> findAll(int userId) {
        return jdbcTemplate.query("select * from Carts where userid = " + userId, new RowMapper<Item>(){

            @Override
            public Item mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                int itemId = resultSet.getInt("itemId");
                return new Item(id,userId,itemId);
            }
        });
    }

    public void addItem(int itemId, double price, int userId) {
        jdbcTemplate.execute("insert into Carts(UserID, ItemId) values(" + userId + "," + itemId + ")");

        jdbcTemplate.execute("update totalprices set cartprice = cartprice + " + price + " where userid = " + userId);
    }

    public void deleteItem(int id, int userId) {
        jdbcTemplate.execute("delete from Carts where Id =" + id + " and userID = " + userId);
    }

    public void deleteAll(int userId) {
        jdbcTemplate.execute("delete from Carts where userid = " + userId);
        jdbcTemplate.execute("update totalprices set cartprice = 0 where userid = " + userId);
    }

    public double getTotalPrice(int userId) {
        String query = "SELECT * FROM TOTALPRICES where userid = " + userId;

        List<Price> totalPrice = jdbcTemplate.query(query, new RowMapper<Price>() {

            @Override
            public Price mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Price(resultSet.getInt("cartPrice"));
            }
          });

        return totalPrice.get(0).money;
    }

    class Price{
        double money;

        Price(double money){
            this.money = money;
        }
    }

}
