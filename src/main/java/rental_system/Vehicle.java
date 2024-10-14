
public abstract class Vehicle {
    protected String type;
    protected double Hourlyrate;
    protected String[] models;

    public Vehicle(String type, double Hourlyrate, String[] models) {
        this.type = type;
        this.Hourlyrate = Hourlyrate;
        this.models = models;
    }  

    public double CalculateRentalCost(int hours){
        return hours * Hourlyrate;
    }

    public String getType(){
        return type;    
    }
    
    public void showModels(){
        System.out.println("Available models for " + type + ":");
        for (int i = 0; i < models.length; i++) {
            System.out.println((i+1) + ": " + models[i]);
        }
    }

    public String getmodel(int index){
        return models[index];
    }   
}