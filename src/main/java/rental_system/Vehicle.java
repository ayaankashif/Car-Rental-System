package rental_system;

public abstract class Vehicle {
    protected String type;
    protected double Hourlyrate;
    protected String[] models;
    protected boolean[] availability;

    //{true,false,true}

    public Vehicle(String type, double Hourlyrate, String[] models) {
        this.type = type;
        this.Hourlyrate = Hourlyrate;
        this.models = models;
        this.availability = new boolean[models.length] ;
        
        
        for (int i = 0; i < availability.length; i++) {
            availability[i] = true;
        }
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
            System.out.println((i+1) + ": " + models[i] + (availability[i] ? " (Available)" : " (Rented)"));
        }
    }

    //method to check if a model is available

    public boolean isAvailable(int index) {
        return availability[index];
    }

    //method to update availability

    public void setAvailable(int index, boolean isAvailable){
        availability[index] = isAvailable;
    }

    public String getModel(int index){
        return models[index];
    }   
    
    

}