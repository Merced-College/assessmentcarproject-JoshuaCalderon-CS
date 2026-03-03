/**
 * Represents one car record loaded from the CSV dataset.
 */
public class Car{

    // Core fields from Car_Data.csv
    private int car_id;
    private String brand;
    private String model;
    private int year;
    private String fuel_type;
    private String color; 
    private double mileage_kmpl;

    // Default constructor for creating an empty car object
    public Car(){
        this.car_id = 0;
        this.brand = "";
        this.model = "";
        this.year = 0;
        this.fuel_type = "";
        this.color = "";
        this.mileage_kmpl = 0.0;
    }

    // Full constructor used when parsing a complete row from the CSV file
    public Car(int car_id, String brand, String model, int year, String fuel_type, String color, double mileage_kmpl){
        this.car_id = car_id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuel_type = fuel_type;
        this.color = color;
        this.mileage_kmpl = mileage_kmpl;
    }

    // Getter methods used by sorting, searching, and printing logic
    public int getCarId() {
        return car_id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getFuelType() {
        return fuel_type;
    }

    public String getColor() {
        return color;
    }

    public double getMileageKmpl() {
        return mileage_kmpl;
    }

    // Returns a readable string representation of a car object
    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + car_id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", fuel_type='" + fuel_type + '\'' +
                ", color='" + color + '\'' +
                ", mileage_kmpl=" + mileage_kmpl +
                '}';
    }
    
}