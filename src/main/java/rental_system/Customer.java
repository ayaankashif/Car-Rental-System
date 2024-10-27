package rental_system;

public class Customer {
    private String name;
    private String contact;
    
    public Customer(String name, String contact){
        this.name = name;
        this.contact = contact;
    }
    
    public String getname(){
        return name;
    }
    
    public String getcontact(){
        return contact;
    }
    
}
