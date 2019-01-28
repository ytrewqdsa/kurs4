package ru.mirea.petShop;

public class Item {
    public int internalID;
    public int userId;
    public int itemId;

    public Item(int internalID, int userId, int itemId){
        this.internalID = internalID;
        this.userId = userId;
        this.itemId = itemId;
    }
}
