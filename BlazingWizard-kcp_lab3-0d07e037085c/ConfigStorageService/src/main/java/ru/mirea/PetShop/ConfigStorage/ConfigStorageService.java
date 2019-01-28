package ru.mirea.PetShop.ConfigStorage;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConfigStorageService {

    private Map<String, String> configs = new HashMap<>();

    @PostConstruct
    public void init() {
        configs.put("Balance", "http://localhost:8083");
        configs.put("Cart", "http://localhost:8082");
        configs.put("Item", "http://localhost:8081");
        configs.put("Identity", "http://localhost:8084");
    }

    public Map<String, String>  getAll() {
        return configs;
    }

}
