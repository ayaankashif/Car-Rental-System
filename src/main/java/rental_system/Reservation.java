
import java.util.ArrayList;
import java.util.List;


class Reservation {
    private Vehicle vehicle;
    private String model;
    private int hours;
    private double cost;

    public Reservation(Vehicle vehicle, String model, int hours) {
        this.vehicle = vehicle;
        this.model = model;
        this.hours = hours;
        this.cost = vehicle.CalculateRentalCost(hours);
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

    public void updateReservation(int newHours) {
        this.hours = newHours;
        this.cost = vehicle.CalculateRentalCost(newHours);
    }
}
