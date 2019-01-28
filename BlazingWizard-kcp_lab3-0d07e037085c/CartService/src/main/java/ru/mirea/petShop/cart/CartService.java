package ru.mirea.petShop.cart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mirea.petShop.*;

import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private Map<String, String> config;
    private CartDAO cartDataAccessObject;
    private HttpHeaders headers;

    @Autowired
    public CartService(CartDAO cartDataAccessObject) throws JsonProcessingException {
        this.cartDataAccessObject = cartDataAccessObject;

        RestTemplate restTemplate = new RestTemplate();
        config = restTemplate.getForObject("http://localhost:8085/config", Map.class);

        Token token = new Token(-1, "admin");
        String signature = DigestUtils.sha256Hex(token.toString() + "LOL");
        token.setSignature(signature);
        ObjectMapper objectMapper = new ObjectMapper();
        String tokenHeader = objectMapper.writer().writeValueAsString(token);

        headers = new HttpHeaders();
        headers.add("Token" , tokenHeader);

    }


    public void putPet(int id, int userId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Pet[]> pet = restTemplate.exchange(config.get("Item") + "/pet/" + id, HttpMethod.GET, httpEntity, Pet[].class);

        if (pet.getBody().length != 0) {
            cartDataAccessObject.addItem(id, pet.getBody()[0].getPrice(), userId);
        }
    }

    public void putStaff(int id, int userId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Staff[]> staff = restTemplate.exchange(config.get("Item") + "/staff/" + id, HttpMethod.GET, httpEntity, Staff[].class);

        if (staff.getBody().length != 0) {
            cartDataAccessObject.addItem(id, staff.getBody()[0].getPrice(), userId);
        }
    }

    public void deleteItem(int id, int userId) {
        cartDataAccessObject.deleteItem(id , userId);
    }

    public List<Item> items(int userId) {
        return cartDataAccessObject.findAll(userId);
    }

    public String buy(int userId) {
        double totalPrice = cartDataAccessObject.getTotalPrice(userId);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Balance> balanceResponseEntity = restTemplate.exchange(config.get("Balance") + "/balance/" + userId, HttpMethod.GET, httpEntity, Balance.class);
        Balance balance = balanceResponseEntity.getBody();

        if (balance.getMoney() < totalPrice) {
            return "Fail! Small balance!";
        }

        Balance newBalance = new Balance(balance.getMoney() - totalPrice);
        HttpEntity httpEntityWithBody = new HttpEntity(newBalance, headers);
        restTemplate.exchange(config.get("Balance") + "/balance/" + userId, HttpMethod.POST, httpEntityWithBody, Balance.class);

        cartDataAccessObject.deleteAll(userId);
        return "OK";
    }


}