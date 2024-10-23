package rental_system;

public class InvalidReservationException extends CarRentalException {
    public InvalidReservationException(String message) {
        super(message);
    }
}

