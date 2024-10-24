package rental_system; 

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    private static List<Reservation> reservations = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Sedan sedan = new Sedan();
        SUV suv = new SUV();
        VAN van = new VAN(0);
        VAN van6seater = new VAN(6);
        VAN van10seater  = new VAN(10);
        
        boolean exit = false;
    try{
        while (!exit) {
        System.out.println("\n---------------------");
        System.out.println("Vehicle Rental System");
        System.out.println("---------------------");
        System.out.println("1: Vehicle Management");
        System.out.println("2: RentAcar");
        System.out.println("3: Exit");
        
        int VehicleChoice = scanner.nextInt();
        
        switch (VehicleChoice) {
            case 1:
                vehicleManagement(scanner, sedan, suv, van6seater, van10seater);       
                break;
                
            case 2:
                rentAcar(scanner);
                break;
                
                case 3:
                exit = true;
                break;
                
                default:
                throw new InvalidVehicleTypeException("Invalid selection for vehicle type");
            }
        }
    } catch (CarRentalException e){
        System.out.println("Error" + e.getMessage());
    } finally{ 
        scanner.close();
    }
}
    
    private static void vehicleManagement(Scanner scanner, Sedan sedan, SUV suv, VAN van6seater, VAN van10seater) throws InvalidVehicleTypeException, VehicleAlreadyRentedException {
        boolean goBack = false;

        while (!goBack) {
            
            System.out.println("---------------------");
            System.out.println("Vehicle Management");
            System.out.println("---------------------");
            System.out.println("1: Register Vehicle");
            System.out.println("2: Update Vehicle");
            System.out.println("3: Remove Vehicle");
            System.out.println("4: List Vehicles");
            System.out.println("5: Go Back");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerVehicle(scanner, sedan, suv, van6seater, van10seater);
                    break;
                
                case 2:
                    updateVehicle(scanner);
                    break;
                
                case 3:
                    removeVehicle(scanner);
                    break;
                
                case 4:
                    listVehicles(sedan, suv, van6seater, van10seater);
                    break;
                
                case 5:
                    goBack = true;
                    break;
                default:
                throw new InvalidVehicleTypeException("Invalid selection in vehicle management.");
            }
        }
    }

    private static void registerVehicle(Scanner scanner, Sedan sedan, SUV suv, VAN van6seater, VAN van10seater) throws InvalidVehicleTypeException, VehicleAlreadyRentedException {
        System.out.println("Select vehicle type to rent:");
        System.out.println("1: Sedan");
        System.out.println("2: SUV");
        System.out.println("3: VAN (6-seater)");
        System.out.println("4: VAN (10-seater)");
    
        int vehicleType = scanner.nextInt();

        Vehicle vehicle = null;

        switch (vehicleType) {
            case 1:
                vehicle = sedan;
                break;
            case 2:
                vehicle = suv;
                break;
            case 3:
                vehicle = van6seater;
                break;
            case 4:
                vehicle = van10seater;
                break;
            default:
            throw new InvalidVehicleTypeException("Invalid vehicle type selected");
        }

        System.out.println("You selected: " + vehicle.getType());
        System.out.println();
        vehicle.showModels();        
        System.out.println("\nSelect a Model: ");
        int modelchoice = scanner.nextInt();
        
        String selectedModel = vehicle.getModel(modelchoice - 1);

            
        if (!vehicle.isAvailable(modelchoice - 1)) {
            throw new VehicleAlreadyRentedException("The selected model is already rented out.");
        } else {
            vehicle.setAvailable(modelchoice - 1, false);
            System.out.println("You have rented: " + selectedModel);
        }


        System.out.print("\nEnter the number of Hours you need the Vehicle: ");
        int hours = scanner.nextInt();
        double TotalCost = vehicle.CalculateRentalCost(hours);

        System.out.println("Total rental cost for " + hours + " hours is: $ " + TotalCost);
        System.out.println("\nIs there anyone who wants to rent a vehicle (yes|no)");
        reservations.add (new Reservation (vehicle, selectedModel, hours));
        
        String anothervehicle = scanner.next();
        if (!anothervehicle.equals("yes")) {
            return;
        }
    }
        
    private static void updateVehicle(Scanner scanner) {
            System.out.println("Feature not implemented yet. Please check back later.");
    }
    
    private static void removeVehicle(Scanner scanner) {
        System.out.println("Feature not implemented yet. Please check back later.");
    }
    
    private static void listVehicles(Sedan sedan, SUV suv, VAN van6seater, VAN van10seater) {
            System.out.println("\nAvailable Vehicles:");
            sedan.showModels();
            System.out.println("\n");
            suv.showModels();
            System.out.println("\n");
            van6seater.showModels();
            System.out.println("\n");
            van10seater.showModels();
    }
        

    private static void rentAcar(Scanner scanner) throws VehicleAlreadyRentedException, InvalidVehicleTypeException, InvalidReservationException {
        boolean renting = true;
    
        while (renting) {
            System.out.println("-------------");
            System.out.println("Rent a Car");
            System.out.println("-------------");
            System.out.println("1: Reserve Vehicle");
            System.out.println("2: Update Reservation");
            System.out.println("3: Cancel Reservation");
            System.out.println("4: Go Back");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    registerVehicle(scanner, new Sedan(), new SUV(), new VAN(6), new VAN(10));
                    break;
                case 2:
                    updateReservation(scanner);
                    break;
                case 3:
                    cancelReservation(scanner);
                    break;
                case 4:
                    renting = false;
                    break;
                default:
                throw new InvalidVehicleTypeException("Invalid selection in rent a car.");
            }
        }
    }
    
    private static void updateReservation(Scanner scanner) throws InvalidReservationException {
        System.out.println("Enter reservation number to update (1 to " + reservations.size() + "):");
        int reservationNumber = scanner.nextInt() - 1;
        
            if (reservationNumber < 0 || reservationNumber >= reservations.size()) {
                throw new InvalidReservationException("Invalid reservation number.");
            }

            System.out.print("Enter new number of hours: ");
            int newHours = scanner.nextInt();
            reservations.get(reservationNumber).updateReservation(newHours);
            System.out.println("Reservation updated. New cost: $" + reservations.get(reservationNumber).getCost());
        }
    
    private static void cancelReservation(Scanner scanner) throws InvalidReservationException {
        System.out.println("Enter reservation number to cancel (1 to " + reservations.size() + "):");
        int reservationNumber = scanner.nextInt() - 1;
        
        if (reservationNumber < 0 || reservationNumber >= reservations.size()) {
            throw new InvalidReservationException("Invalid reservation number.");
        }
        
        Reservation reservation = reservations.remove(reservationNumber);
        reservation.getVehicle().setAvailable(reservation.getModel().indexOf(reservation.getModel()), true); 
        System.out.println("Reservation for " + reservation.getModel() + " has been canceled.");
    }
                
}
