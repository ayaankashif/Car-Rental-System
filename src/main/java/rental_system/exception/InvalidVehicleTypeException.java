package rental_system.exception;

public  class InvalidVehicleTypeException extends CarRentalException {
    public InvalidVehicleTypeException(String message) {
        super(message);
    }
}