
public class Car{

    // data variables
    private int car_id;
    private String brand;
    private String model;
    private int year;
    private String fuel_type;
    private String color; 
    private double mileage_kmpl;


    // default constructor
    public Car(){
        this.car_id = 0;
        this.brand = "";
        this.model = "";
        this.year = 0;
        this.fuel_type = "";
        this.color = "";
        this.mileage_kmpl = 0.0;
    }

    public Car(int car_id, String brand, String model, int year, String fuel_type, String color, double mileage_kmpl){
        this.car_id = car_id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuel_type = fuel_type;
        this.color = color;
        this.mileage_kmpl = mileage_kmpl;
    }

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