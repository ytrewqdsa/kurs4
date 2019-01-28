package ru.mirea.petShop;

public class Pet {
    private int id;
    private double price;
    private double age;
    private String name;


    public Pet(){

    }

    public Pet(int id , double price, String name, double age){
        this.id = id;
        this.price = price;
        this.age = age;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
