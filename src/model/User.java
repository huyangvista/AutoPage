package model;
public class User {  
    private int id;  
    private String name;  
    private String password;  
      
    public User(){}  
    public User(int id, String name) {  
        this.id = id;  
        this.name = name;  
    }  
      
    public int getId() {  
        return this.id;  
    }  
      
    public void setId(int id) {  
        this.id = id;  
    }  
      
    public String getName() {  
        return this.name;  
    }  
      
    public void setName(String name) {  
        this.name = name;  
    }  
      
    public String getPassword() {  
        return this.password;  
    }  
      
    public void setPassword(String password) {  
        this.password = password;  
    }  
      
    @Override  
    public String toString() {  
        return "User [id=" + this.id + ", name=" + this.name + ", password=" + this.password + "]";  
    }  
      
}  