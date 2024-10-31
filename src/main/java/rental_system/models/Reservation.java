package rental_system.models;

public class Reservation {
    private int hours;
    private double cost;
    private Customer customer;
    private Employee assignedEmployee;
    private int id; 
    private Vehicle vehicle;
    

    public Reservation(Vehicle vehicle, int hours, Customer customer, Employee assignedEmployee) {
        this.vehicle = vehicle;
        this.hours = hours;
        this.cost = vehicle.CalculateRentalCost(hours);
        this.customer = customer;
        this.assignedEmployee = assignedEmployee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public int getHours() {
        return hours;
    }

    public double getCost() {
        return cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getAssignedEmployee() {
        return assignedEmployee;
    }

    public void updateReservation(int newHours) {
        this.hours = newHours;
        this.cost = vehicle.CalculateRentalCost(newHours);
    }
}
