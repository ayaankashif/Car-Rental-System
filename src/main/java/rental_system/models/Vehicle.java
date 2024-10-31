package rental_system.models;

public abstract class Vehicle {
    protected String type;
    protected double Hourlyrate;
    protected String[] models;
    protected boolean[] availability;
    private int id;
    private static int idCounter = 0;
    private String LicensePlate;


    //{true,false,true}
    //generate license number.

    public Vehicle(String type, double Hourlyrate, String[] models, String LicensePlate) {
        this.type = type;
        this.Hourlyrate = Hourlyrate;
        this.models = models;
        this.availability = new boolean[models.length] ;
        this.id = ++idCounter;
        this.LicensePlate = LicensePlate;
        
        for (int i = 0; i < availability.length; i++) {
            availability[i] = true;
        }
    }  

    // public void setModel(String model) {
    //     this.model = model;
    // }
    
    public void setLicensePlate(String LicensePlate){
        this.LicensePlate = LicensePlate;
    }

    public String getLicensePlate(){
        return LicensePlate;
    }

    public double getHourlyRate(){
        return Hourlyrate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
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
    
    public String getModels(int index) {
        if (index >= 0 && index < models.length) { 
            return models[index];
        }
        return null;
    }

}