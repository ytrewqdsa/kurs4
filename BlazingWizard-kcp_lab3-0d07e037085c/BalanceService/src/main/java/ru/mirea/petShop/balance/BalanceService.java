package ru.mirea.petShop.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.petShop.Balance;

@Service
public class BalanceService {

    BalanceDAO balanceDataAccessObject;

    @Autowired
    public BalanceService(BalanceDAO balanceDataAccessObject){
        this.balanceDataAccessObject = balanceDataAccessObject;
    }

    public Balance balance(int userID) {
        return balanceDataAccessObject.getBalanceByUserID(userID).get(0);
    }

    public void updateUserBalance(double newBalance, int userID) {
        balanceDataAccessObject.updateUserBalance(newBalance, userID);
    }
}
