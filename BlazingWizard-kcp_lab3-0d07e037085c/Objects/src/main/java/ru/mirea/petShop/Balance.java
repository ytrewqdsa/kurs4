package ru.mirea.petShop;

public class Balance {

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private double money;

    public  Balance(){

    }

    public Balance(double money){
        this.money = money;
    }

}
