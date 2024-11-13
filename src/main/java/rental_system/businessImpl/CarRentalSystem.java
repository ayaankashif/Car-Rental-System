package rental_system.businessImpl; 

import java.util.Scanner;

import rental_system.dao.CustomerDAO;
import rental_system.dao.EmployeeDAO;
import rental_system.dao.ReservationDAO;
import rental_system.dao.VehicleDAO;
import rental_system.exception.CarRentalException;
import rental_system.exception.InvalidReservationException;
import rental_system.exception.InvalidVehicleTypeException;
import rental_system.exception.VehicleAlreadyRentedException;
import rental_system.models.Customer;
import rental_system.models.Employee;
import rental_system.models.Reservation;
import rental_system.models.SUV;
import rental_system.models.Sedan;
import rental_system.models.VAN;
import rental_system.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CarRentalSystem {
    private static List<Reservation> reservations = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();

    private static CustomerDAO customerDAO = new CustomerDAO();
    private static VehicleDAO vehicleDAO = new VehicleDAO();
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static ReservationDAO reservationDAO = new ReservationDAO();
    
    //private CustomerBusinessImpl customerBusinessImpl = new CustomerBusinessImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Sedan sedan = new Sedan("YUR-922");
        SUV suv = new SUV("AFI-928");
        VAN van6seater = new VAN(6, "MLK-287");
        VAN van10seater  = new VAN(10, "SRF-716");

        //getvehicles 
        // Sedan sedanCorolla = new Sedan("Corolla","M-007");
        // Sedan sedanCivic= new Sedan("Civic","ETF-088");
        // Sedan sedanCity = new Sedan("City","MHE-932");
        // SUV suv = new SUV("","KFH-836");
        // SUV suv = new SUV("","LWE-836");
        // VAN van6seater = new VAN(6,"","");
        // VAN van6seater = new VAN(6,"","");
        // VAN van10seater  = new VAN(10),"","");
        // VAN van10seater  = new VAN(10,"","");

        // employees.add(new Employee("Mananger", "Rental Manager"));
        // employees.add(new Employee("Worker1", "Maintenance Worker"));
        // employees.add(new Employee("Worker2", "Maintenance Worker"));

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
        System.out.println("5: Add a Employee");
        System.out.println("6: Register Vehicle");
        System.out.println("7: Display Status");
        System.out.println("8: Exit");
        
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
                registerEmployee(scanner);
                break;
            case 6:
                //regvehicle(scanner);
                break;
            case 7:
                displayStatus();
                break;
            case 8:
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


    // private static void regvehicle (Scanner scanner){
    //     System.out.print("Enter Vehicle Type (Sedan/SUV/VAN): ");
    //     String type = scanner.nextLine();
        
    //     System.out.print("Enter Vehicle Model: ");
    //     String model = scanner.nextLine();

    //     System.out.print("Enter Vehicle Licence Plate: ");
    //     String licenseno = scanner.nextLine();

    //     System.out.print("Enter Hourly rate: ");
    //     double hourlyrate = scanner.nextDouble();

    //     //Vehicle existingVehicle = vehicleDAO.findVehicleByLicensePlate(licenseno);
         
    //     Vehicle newVehicle = new Vehicle(type, hourlyrate, model, licenseno, "available");
        
    //     if (vehicleDAO.saveVehicle(newVehicle)) {  
    //         System.out.println("Vehicle registered successfully.");

    //     } else {
    //         System.out.println("Failed to register vehicle. ");
    //     }
    // }

    
    private static Customer registerCustomer(Scanner scanner) {    
        Scanner sc1 = new Scanner(System.in);
        
        Customer customer;
        System.out.print("Your name: ");    
        String name = sc1.nextLine(); 
    
        System.out.print("Your Contact: "); 
        String contact = sc1.nextLine(); 
        
        Customer existingCustomer = customerDAO.findCustomerByContact(contact);
    
        if (existingCustomer != null) {
            System.out.println("Welcome back " + existingCustomer.getname() + "!");
           return existingCustomer; 
        }
    
        customer = new Customer(name, contact); 
        
        if (customerDAO.saveCustomer(customer)) {  
            System.out.println("New customer registered successfully.");
            return customer;
        } else {
            System.out.println("Failed to register new customer.");
            return null;
        }
    }


    private static Employee registerEmployee (Scanner scanner){
        Scanner sc = new Scanner(System.in);
        Employee employee;

        System.out.print("Enter Employee name: ");
        String emname = sc.nextLine();
        
        Employee existingEmployee = employeeDAO.findEmployeeByName(emname);
        
        if (existingEmployee != null) {
            System.out.println("Hey " + existingEmployee.getname() + "!");
            return existingEmployee; 
        }
        else {
        System.out.print("Enter the role: ");
        String role = sc.nextLine();
        
        employee = new Employee(emname, role);

        if (employeeDAO.saveEmployee(employee)) {  
            System.out.println("New employee registered successfully.");
            return employee;
        } else {
            System.out.println("Failed to register new employee.");
            return null;
        }
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
        //Scanner sc1 = new Scanner (System.in);
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
        
        // System.out.print("Enter your Contact: ");
        // String contact = scanner.nextLine();
        
        // Customer existingCustomer = customerDAO.findCustomerByContact(contact);

        // Customer customer;
        // if (existingCustomer != null) {
        //     System.out.println("Welcome back, " + existingCustomer.getname() + "!");
        //     customer = existingCustomer; 
        // } else {
        //     System.out.print("Enter your name: ");
        //     String customerName = scanner.nextLine();
        //     customer = new Customer(customerName, contact);
        
        //     if (customerDAO.saveCustomer(customer)) {
        //     System.out.println("New customer registered successfully.");
        //     } else {
        //     System.out.println("Failed to register new customer.");
        //     }
        // }

        Customer customer = registerCustomer(scanner);

        Employee employee = registerEmployee(scanner);
        
        System.out.println("You selected: " + vehicle.getType());
        System.out.println();
        vehicle.showModels();        
        System.out.println("\nSelect a Model: ");
        int modelchoice = scanner.nextInt();
        
        // for (int i = 0; i < vehicle.getModel().length; i++) {
        //     System.out.println((i + 1) + ". " + vehicle.getModel()[i]);
        // }

        String selectedModel = vehicle.getModel(modelchoice - 1);
    
        if (!vehicle.isAvailable(modelchoice - 1)) {
            throw new VehicleAlreadyRentedException("The selected model is already rented out.");
        } else {
            vehicle.setAvailable(modelchoice - 1, false);
            vehicleDAO.updateVehicleAvailability(vehicle.getId(), false);
            System.out.println("You have rented: " + selectedModel);
        }

        // System.out.print("Enter License Plate: ");
        // String licensePlate = scanner.nextLine();

        // vehicle.setLicensePlate(licensePlate);
        // vehicleDAO.saveVehicle(vehicle); 
    
        String licensePlate;
        boolean licensePlateExists;
    
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter license plate of the vehicle: ");
            licensePlate = sc.nextLine();
    
            // Check if the license plate already exists
            licensePlateExists = vehicleDAO.isLicensePlateExists(licensePlate);
            if (licensePlateExists) {
                System.out.println("This license plate already exists. Please enter a different license plate.");
            }
        } while (licensePlateExists);

        vehicle.setLicensePlate(licensePlate);
        
        System.out.print("\nEnter the number of Hours you need the Vehicle: ");
        int hours = scanner.nextInt();
        double TotalCost = vehicle.CalculateRentalCost(hours);
        
        System.out.println("Total rental cost for " + hours + " hours is: $ " + TotalCost);

        // After setting the vehicle as rented
        if (vehicleDAO.saveVehicle(vehicle)) {
            System.out.println("Vehicle registered successfully in the database.");
        } else {
            System.out.println("Failed to register vehicle in the database.");
        }

        reservations.add (new Reservation (vehicle, hours, customer, employee));

        Reservation reservation = new Reservation(vehicle, hours, customer, employee);
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
                    //registerVehicle(scanner, new Sedan(), new SUV(), new VAN(6), new VAN(10));
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
        reservation.getVehicle().setAvailable(reservation.getVehicle().getModel(0).indexOf(reservation.getVehicle().getModel(0)), true); 
        
        System.out.println("Reservation for " + reservation.getVehicle().getModel(0) + " has been canceled.");
    }

    private static void returnVehicle(Scanner scanner) throws InvalidReservationException {
        System.out.println("Enter reservation number to cancel (1 to " + reservations.size() + "):");
        int reservationNumber = scanner.nextInt() - 1;
        
        if (reservationNumber < 0 || reservationNumber >= reservations.size()) {
            throw new InvalidReservationException("Invalid reservation number.");
        }
        
        Reservation reservation = reservations.remove(reservationNumber);
        Vehicle vehicle = reservation.getVehicle();

        vehicle.setAvailable(reservation.getVehicle().getModel(0).indexOf(reservation.getVehicle().getModel(0)), true); 

        VehicleDAO vehicleDAO = new VehicleDAO();
        if (vehicleDAO.returnVehicle(vehicle.getId())) {
            System.out.println("Vehicle availability updated in the database.");
        } else {
            System.out.println("Failed to update vehicle availability in the database.");
        }
        
        Employee worker = new Employee("Worker", "Maintenance Worker");
        System.out.println("The Vehicle " + reservation.getVehicle().getModel(0) + " has been returned and assigned to " + worker.getrole() + " for maintenance.");
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
                System.out.println("Vehicle: " + res.getVehicle().getType() + " - " + res.getVehicle().getModel(0));
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
