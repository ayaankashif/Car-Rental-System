package rental_system.exception;

public class VehicleAlreadyRentedException extends CarRentalException {
    public VehicleAlreadyRentedException(String message) {
        super(message);
    }
}
