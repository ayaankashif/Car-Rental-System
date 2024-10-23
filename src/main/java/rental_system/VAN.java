package rental_system;

public class VAN extends Vehicle{
    private int seats;

    public VAN(int seats){
        super("VAN", 0 , new String[]{"Crew VAN", "Toyota Hiace", "Camper VAN"} );
        this.seats = seats;
    }

    @Override
    public double CalculateRentalCost(int hours){
        double Hourlyrate;

        if (seats == 6) {
            Hourlyrate = 2000.0;
        }else if (seats == 10){
            Hourlyrate =  3000.0;
        }else {
            Hourlyrate = 4000.0;
        }
        return hours * Hourlyrate;
    }

    public int getSeats(){
        return seats;
    }

}