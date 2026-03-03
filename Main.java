import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
  Main driver class for loading car data, sorting, and binary searching.
 */
public class Main{
    // Load all cars from the CSV once when the program starts
    public static ArrayList<Car> cars = loadCars("Car_Data.csv");

    // Keep only the first 2000 cars for assessment operations
    public static ArrayList<Car> working = new ArrayList<>(cars.subList(0, Math.min(2000, cars.size())));

    public static void main(String[] args){
        // Step 1: Sort by Car ID using custom insertion sort
        insertionSortByCarId(working);

        // Step 2: Print the first 10 sorted cars
        int printCount = Math.min(10, working.size());
        for (int i = 0; i < printCount; i++) {
            System.out.println(working.get(i));
        }

        // Step 3: Run binary search on the sorted list
        int targetCarId = 1500;
        Car foundCar = binarySearchByCarId(working, targetCarId);
        if (foundCar != null) {
            System.out.println("Found: " + foundCar);
        } else {
            System.out.println("Car with ID " + targetCarId + " not found.");
        }
    }

    /*
      Sorts the given car list in ascending order by car_id.
      Uses insertion sort (no built-in sorting utilities).
     */
    public static void insertionSortByCarId(ArrayList<Car> list) {
        for (int i = 1; i < list.size(); i++) {
            Car current = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getCarId() > current.getCarId()) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

    /* 
      Performs iterative binary search by car_id on a sorted list,
      Returns the matching Car, or null if no match exists.
     */
    public static Car binarySearchByCarId(ArrayList<Car> list, int targetCarId) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midCarId = list.get(mid).getCarId();

            if (midCarId == targetCarId) {
                return list.get(mid);
            }

            if (midCarId < targetCarId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }

    /*
      Loads car records from a CSV file,
      Skips header row,
      Parses valid rows into Car objects,
      Skips malformed rows safely,
      Prints total cars loaded
     */
    public static ArrayList<Car> loadCars(String filename) {
        ArrayList<Car> loadedCars = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // Read and ignore the header line
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 7) {
                    // Skip rows with missing/extra columns
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
                    // Skip rows with invalid numeric values
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