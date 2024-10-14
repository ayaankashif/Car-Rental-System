
public class VAN extends Vehicle{
    private int seats;

    public VAN(int seats){
        super("VAN", calculateHourlyrate(seats) , new String[]{"Crew VAN", "Toyota Hiace", "Camper VAN"} );
        this.seats = seats;
    }

    private static double calculateHourlyrate(int seats) {
        if (seats == 6) {
            return 2000.0;
        }else if (seats == 10){
            return 3000.0;
        }else {
            return 4000.0;
        }
    }

    @Override
    public double CalculateRentalCost(int hours){
        return hours * Hourlyrate;
    }

    public int getSeats(){
        return seats;
    }

}