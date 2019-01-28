package ru.mirea.petShop.IdentityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mirea.petShop.Token;
import ru.mirea.petShop.User;

@Controller
public class IdentityController {

    @Autowired
    private IdentityService identityService;

    @RequestMapping(value ="identity", method = RequestMethod.POST)
    @ResponseBody
    public Token getPets(@RequestBody User auth){
        return identityService.getToken(auth);
    }
}