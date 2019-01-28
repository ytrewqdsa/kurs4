package ru.mirea.petShop.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.mirea.petShop.Balance;

@Controller
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @RequestMapping(value = "balance/{userID}", method = RequestMethod.GET)
    @ResponseBody
    public Balance getBalance(@PathVariable int userID)
    {
        return balanceService.balance(userID);
    }

    @RequestMapping(value = "balance/{userID}", method = RequestMethod.POST)
    @ResponseBody
    public void getBalance (@PathVariable int userID, @RequestBody Balance balance)
    {
        balanceService.updateUserBalance(balance.getMoney(),userID);
    }
}
