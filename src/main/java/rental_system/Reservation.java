package rental_system;

import java.util.ArrayList;
import java.util.List;

class Reservation {
    private Vehicle vehicle;
    private String model;
    private int hours;
    private double cost;
    private Customer customer;
    private Employee assignedEmployee;

    public Reservation(Vehicle vehicle, String model, int hours, Customer customer, Employee employee) {
        this.vehicle = vehicle;
        this.model = model;
        this.hours = hours;
        this.cost = vehicle.CalculateRentalCost(hours);
        this.customer = customer;
        this.assignedEmployee = employee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getModel() {
        return model;
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
