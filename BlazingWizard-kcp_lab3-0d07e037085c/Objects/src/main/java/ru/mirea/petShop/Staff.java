package ru.mirea.petShop;

public class Staff{
    private int id;
    private double price;
    private String name;

    public Staff(){

    }

    public  Staff(int id, double price, String name){
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }
}
