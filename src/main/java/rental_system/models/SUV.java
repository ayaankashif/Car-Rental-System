package rental_system.models;

public class SUV extends Vehicle {
    public SUV(String LicencePlate){
        super("SUV", 2500.0, new String[]{"Fortuner", "KIA Sportage", "Land Cruiser"}, LicencePlate);
    }
}

