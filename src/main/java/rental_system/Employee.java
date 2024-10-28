package rental_system;

public class Employee {
    private String name;
    private String role;
    private int id;
    private static int idCounter = 0;

    public Employee(String name, String role){
        this.name = name;
        this.role = role;
        this.id = ++idCounter;
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
