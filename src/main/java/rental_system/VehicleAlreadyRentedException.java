package rental_system;

public class VehicleAlreadyRentedException extends CarRentalException {
    public VehicleAlreadyRentedException(String message) {
        super(message);
    }
}
