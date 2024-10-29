package rental_system; 

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();
    private static CustomerDAO customerDAO = new CustomerDAO();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Sedan sedan = new Sedan();
        SUV suv = new SUV();
        VAN van6seater = new VAN(6);
        VAN van10seater  = new VAN(10);

        employees.add(new Employee("Mananger", "Rental Manager"));
        employees.add(new Employee("Worker1", "Maintenance Worker"));
        employees.add(new Employee("Worker2", "Maintenance Worker"));

        boolean exit = false;
    try{
        while (!exit) {
        System.out.println("\n---------------------");
        System.out.println("Vehicle Rental System");
        System.out.println("---------------------");
        System.out.println("1: Vehicle Management");
        System.out.println("2: RentACar");
        System.out.println("3: Add a Customer");
        System.out.println("4: Employees");
        System.out.println("5: Display Status");
        System.out.println("6: Exit");
        
        int VehicleChoice = scanner.nextInt();
        
        switch (VehicleChoice) {
            case 1:
                vehicleManagement(scanner, sedan, suv, van6seater, van10seater);       
                break;
                
            case 2:
                rentAcar(scanner);
                break;
            
            case 3:
                registerCustomer(scanner);
                break;
                
            case 4:
                displayEmployees(); 
                break;
            
            case 5:
                displayStatus();
                break;
                
            case 6:
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

    private static void registerCustomer(Scanner scanner) {
        System.out.print("Your name: ");    
        String name = scanner.nextLine(); 
        
        System.out.print("Your Contact: "); 
        String contact = scanner.nextLine(); 
        
        Customer customer = new Customer(name, contact); 
        
        if (customerDAO.saveCustomer(customer)) {  
            System.out.println("New customer registered successfully.");
        } else {
            System.out.println("Failed to register new customer.");
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
        scanner.nextLine();

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
        
        System.out.print("Enter your Contact: ");
        String contact = scanner.nextLine();
        
        Customer existingCustomer = customerDAO.findCustomerByContact(contact);

        Customer customer;
        if (existingCustomer != null) {
            System.out.println("Welcome back, " + existingCustomer.getname() + "!");
            customer = existingCustomer; 
        } else {

            System.out.print("Enter your name: ");
            String customerName = scanner.nextLine();
            customer = new Customer(customerName, contact);
        
            if (customerDAO.saveCustomer(customer)) {
            System.out.println("New customer registered successfully.");
            } else {
            System.out.println("Failed to register new customer.");
            }
        }

        Employee manager = new Employee("Manager", "Rental Manager");

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

        
        reservations.add (new Reservation (vehicle, selectedModel, hours, customer, manager));

        VehicleDAO vehicleDAO = new VehicleDAO();
        ReservationDAO reservationDAO = new ReservationDAO();

        Reservation reservation = new Reservation(vehicle, selectedModel, hours, customer, manager);
            if (reservationDAO.saveReservation(reservation)) {
                System.out.println("Reservation saved successfully!");
            } else {
                System.out.println("Failed to save reservation.");
            }

        System.out.println("\nIs there anyone who wants to rent a vehicle (yes|no)");
        
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
            System.out.println("4: Return Vehicle");
            System.out.println("5: Go Back");
            
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
                    returnVehicle(scanner);
                    break;
                case 5:
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

    private static void returnVehicle(Scanner scanner) throws InvalidReservationException {
        System.out.println("Enter reservation number to cancel (1 to " + reservations.size() + "):");
        int reservationNumber = scanner.nextInt() - 1;
        
        if (reservationNumber < 0 || reservationNumber >= reservations.size()) {
            throw new InvalidReservationException("Invalid reservation number.");
        }
        
        Reservation reservation = reservations.remove(reservationNumber);
        Vehicle vehicle = reservation.getVehicle();

        vehicle.setAvailable(reservation.getModel().indexOf(reservation.getModel()), true); 

        VehicleDAO vehicleDAO = new VehicleDAO();
        if (vehicleDAO.returnVehicle(vehicle.getId())) {
            System.out.println("Vehicle availability updated in the database.");
        } else {
            System.out.println("Failed to update vehicle availability in the database.");
        }
        
        Employee worker = new Employee("Worker", "Maintenance Worker");
        System.out.println("The Vehicle " + reservation.getModel() + " has been returned and assigned to " + worker.getrole() + " for maintenance.");
    }
    
    
    private static void displayStatus() {
        System.out.println("\nCurrent Reservations Status:");
        if (reservations.isEmpty()) {
            System.out.println("No active reservations.");
        } else {
            for (int i = 0; i < reservations.size(); i++) {
                Reservation res = reservations.get(i);
                System.out.println("Reservation #" + (i + 1) + ":");
                System.out.println("Customer Name: " + res.getCustomer().getname());
                System.out.println("Contact: " + res.getCustomer().getcontact());
                System.out.println("Vehicle: " + res.getVehicle().getType() + " - " + res.getModel());
                System.out.println("Hours Rented: " + res.getHours());
                System.out.println("Assigned Employee: " + res.getAssignedEmployee().getname() + " (" + res.getAssignedEmployee().getrole() + ")");
                System.out.println("Total Cost: $" + res.getCost());
                System.out.println("---------------------");
            }
        }
    }

    private static void displayEmployees() {
        System.out.println("\nEmployee List:");
        for (Employee emp : employees) {
            System.out.println("Name: " + emp.getname() + " | Role: " + emp.getrole());
        }
    }
}
