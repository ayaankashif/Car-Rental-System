package rental_system.models;

public class Sedan extends Vehicle{
    public Sedan(String LicensePlate) {
        super("Sedan", 1500.0, new String[]{"Corrolla", "Civic", "City"}, LicensePlate);
        
    }
}
