package rental_system;

public class Employee {
    private String name;
    private String role;

    public Employee(String name, String role){
        this.name = name;
        this.role = role;
    }

    public String getname(){
        return name;
    }

    public String getrole(){
        return role;
    }

}
