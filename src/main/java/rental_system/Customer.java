package rental_system;

public class Customer {
    private String name;
    private String contact;
    private int id;
    private static int idCounter = 0;
    
    public Customer(String name, String contact){
        this.name = name;
        this.contact = contact;
        this.id = ++idCounter;
    }

    public int getId(){
        return id;  
    }   
    
    public String getname(){
        return name;
    }
    
    public String getcontact(){
        return contact;
    }
    
}
