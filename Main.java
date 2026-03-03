import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main{
    public static ArrayList<Car> cars = loadCars("Car_Data.csv");
    public static ArrayList<Car> working = new ArrayList<>(cars.subList(0, Math.min(2000, cars.size())));

    public static void main(String[] args){
    
        System.out.println(working.size());
    }
    public static ArrayList<Car> loadCars(String filename) {
        ArrayList<Car> loadedCars = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 7) {
                    continue;
                }

                try {
                    int carId = Integer.parseInt(parts[0].trim().replaceAll("[^0-9]", ""));
                    String brand = parts[1].trim();
                    String model = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    String fuelType = parts[4].trim();
                    String color = parts[5].trim();
                    double mileageKmpl = Double.parseDouble(parts[6].trim());

                    loadedCars.add(new Car(carId, brand, model, year, fuelType, color, mileageKmpl));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading cars: " + e.getMessage());
        }

        System.out.println("Total cars loaded: " + loadedCars.size());
        return loadedCars;
    }
}