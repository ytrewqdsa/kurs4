package ru.mirea.PetShop.ConfigStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ConfigStorageController {

    @Autowired
    private ConfigStorageService configStorageService;

    @RequestMapping(value ="config", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getPets(){
        return configStorageService.getAll();
    }
}
