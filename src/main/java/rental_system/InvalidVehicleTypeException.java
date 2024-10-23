package rental_system;

public  class InvalidVehicleTypeException extends CarRentalException {
    public InvalidVehicleTypeException(String message) {
        super(message);
    }
}