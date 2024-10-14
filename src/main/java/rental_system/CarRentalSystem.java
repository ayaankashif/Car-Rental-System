import java.util.Scanner;
import java.util.InputMismatchException;

public class CarRentalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vehicle vehicle = null;
        
    try{
        System.out.println("\nWelcome to Hyderabad Motors");
        System.out.println("Select the vehicle type: ");
        System.out.println("1: Sedan");
        System.out.println("2: SUV");
        System.out.println("3: VAN");

        int VehicleChoice = scanner.nextInt();
        
        switch (VehicleChoice) {
            case 1:
                vehicle = new Sedan();
                break;
                
            case 2:
                vehicle = new SUV();
                break;

            case 3:
                System.out.println("Enter number of seats (6 or 10)");
                int seats = scanner.nextInt();
                vehicle = new VAN(seats);
                break;

            default:
                System.out.println("Invalid Selection, Try again");
                return;
        }

        System.out.println("You selected: " + vehicle.getType());
        System.out.println();
        
        vehicle.showModels();
        System.out.println("\nSelect a Model: ");
        int modelchoice = scanner.nextInt();
        String selectedModel = vehicle.getmodel(modelchoice - 1);
        
        if(VehicleChoice == 3){
            int seats  = ((VAN)vehicle).getSeats();
            System.out.println("You selected " + seats + "-seater VAN. Model: " +selectedModel);
        } else {
            System.out.print("Model: " + selectedModel);
        }

        System.out.print("\nEnter the number of Hours you need the Vehicle: ");
        int hours = scanner.nextInt();

        double TotalCost = vehicle.CalculateRentalCost(hours);

        System.out.println("Total rental cost for " + hours + " hours is: $ " + TotalCost);
    
    } catch(InputMismatchException e ){
        System.out.println("\nInvalid input, Please enter the correct number.");
        scanner.nextLine();
    } catch (Exception e){
        System.out.println("Error Occured, " + e.getMessage());
    } finally{
        scanner.close();
    }
}
}
