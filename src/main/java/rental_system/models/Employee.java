package rental_system.models;

public class Employee {
    private String name;
    private String role;
    private int id;
    private static int idCounter = 0;

    public Employee(){

    }

    public Employee(String name, String role){
        this.name = name;
        this.role = role;
        this.id = ++idCounter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getId(){
        return id;
    }

    public String getname(){
        return name;
    }

    public String getrole(){
        return role;
    }

}
