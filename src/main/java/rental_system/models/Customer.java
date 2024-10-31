package rental_system.models;

public class Customer {
    private String name;
    private String contact;
    private int id;
    private static int idCounter = 0;

    public Customer() {
        // This constructor is intentionally empty.
    }
    
    public Customer(String name, String contact){
        this.name = name;
        this.contact = contact;
        this.id = ++idCounter;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
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
