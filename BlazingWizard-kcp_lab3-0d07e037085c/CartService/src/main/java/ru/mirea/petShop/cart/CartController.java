package ru.mirea.petShop.cart;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.petShop.Item;
import ru.mirea.petShop.Token;

import java.io.IOException;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "cart", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getItems(@RequestHeader HttpHeaders hh) throws IOException {
        int userId = getUserID(hh);
        return cartService.items(userId);
    }

    @RequestMapping(value = "cart/pet/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String putPet(@PathVariable int id, @RequestHeader HttpHeaders hh) throws IOException {
        int userId = getUserID(hh);
        cartService.putPet(id, userId);
        return "ok";
    }


    @RequestMapping(value = "cart/staff/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String putStaff(@PathVariable int id, @RequestHeader HttpHeaders hh) throws IOException {
        int userId = getUserID(hh);
        cartService.putStaff(id, userId);
        return "ok";
    }

    @RequestMapping(value = "cart/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteItem(@PathVariable int id, @RequestHeader HttpHeaders hh) throws IOException {
        int userId = getUserID(hh);
        cartService.deleteItem(id, userId);
        return "ok";
    }

    @RequestMapping(value = "cart", method = RequestMethod.POST)
    @ResponseBody
    public String buyItems(@RequestHeader HttpHeaders hh) throws IOException {
        int userId = getUserID(hh);
        return cartService.buy(userId);
    }

    private int getUserID(HttpHeaders hh) throws IOException {
        String tokenStr = hh.get("token").get(0);
        ObjectMapper objectMapper = new ObjectMapper();
        Token token = objectMapper.readValue(tokenStr, Token.class);
        return token.getUserID();
    }



}
