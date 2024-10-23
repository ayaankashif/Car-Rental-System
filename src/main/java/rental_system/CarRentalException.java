package rental_system;

public class CarRentalException extends Exception {
    public CarRentalException(String message) {
        super(message);
    }
}

class InvalidVehicleTypeException extends CarRentalException {
    public InvalidVehicleTypeException(String message) {
        super(message);
    }
}

class VehicleAlreadyRentedException extends CarRentalException {
    public VehicleAlreadyRentedException(String message) {
        super(message);
    }
}

class InvalidReservationException extends CarRentalException {
    public InvalidReservationException(String message) {
        super(message);
    }
}
