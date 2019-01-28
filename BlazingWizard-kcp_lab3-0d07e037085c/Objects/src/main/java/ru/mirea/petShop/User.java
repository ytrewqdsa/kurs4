package ru.mirea.petShop;

public class User {
    private int id;
    private String login;
    private String role;
    private String password;

    public User(){

    }

    public User(int id, String login, String password, String role){
        this.id = id;
        this.login = login;
        this.role = role;
        this.password = password;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
