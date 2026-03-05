/*
Joshua Calderon
3/5/2026
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Main driver class for loading car data, sorting, and binary searching.
public class Main{
    // Load all cars from the CSV once when the program starts
    public static ArrayList<Car> cars = loadCars("Car_Data.csv");

    // Keep only the first 2000 cars for assessment operations
    public static ArrayList<Car> working = new ArrayList<>(cars.subList(0, Math.min(2000, cars.size())));
    public static ArrayList<Car> foundCars = new ArrayList<>();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                insertionSortByBrand(working);
                printFirstTenCars(working);
                System.out.println("Sorted by brand (case-insensitive). Showing first 10 cars.");
            } else if (choice.equals("2")) {
                insertionSortByBrand(working);
                System.out.print("Enter brand to search (exact match, case-insensitive): ");
                String brandInput = scanner.nextLine().trim();
                foundCars = searchByBrand(working, brandInput);

                if (foundCars.isEmpty()) {
                    System.out.println("No cars found for brand: " + brandInput);
                } else {
                    System.out.println("Matches found: " + foundCars.size());
                }
            } else if (choice.equals("3")) {
                printFoundCars(foundCars);
            } else if (choice.equals("4")) {
                System.out.println("Exiting program.");
                break;
            } else {
                System.out.println("Invalid choice. Select 1, 2, 3, or 4.");
            }
        }

        scanner.close();
    }
    // Prints the main menu options to the user.
    public static void printMenu() {
        System.out.println("\n=== Project A: Brand + Efficiency Finder ===");
        System.out.println("1. Sort by Brand");
        System.out.println("2. Search by Brand");
        System.out.println("3. Show found car object(s)");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }
    // Prints the first 10 cars from the provided list, or all if fewer than 10.
    public static void printFirstTenCars(ArrayList<Car> list) {
        int printCount = Math.min(10, list.size());
        for (int i = 0; i < printCount; i++) {
            System.out.println(list.get(i));
        }
    }
    // Prints all cars in the provided list, or a message if the list is empty.
    public static void printFoundCars(ArrayList<Car> list) {
        if (list.isEmpty()) {
            System.out.println("No found cars to display. Run a search first.");
            return;
        }

        for (Car car : list) {
            System.out.println(car);
        }
    }

    /*
      Sorts the given car list in ascending order by brand.
      Uses insertion sort (no built-in sorting utilities).
     */
    public static void insertionSortByBrand(ArrayList<Car> list) {
        for (int i = 1; i < list.size(); i++) {
            Car current = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).getBrand().compareToIgnoreCase(current.getBrand()) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
    }

    /*
      Performs iterative binary search by brand on a sorted list.
      Returns one match index, or -1 if no match exists.
     */
    public static int binarySearchByBrand(ArrayList<Car> list, String targetBrand) {
        int low = 0;
        int high = list.size() - 1;
        String normalizedTarget = targetBrand.trim().toLowerCase();

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midBrand = list.get(mid).getBrand().toLowerCase();
            int compare = midBrand.compareTo(normalizedTarget);

            if (compare == 0) {
                return mid;
            }

            if (compare < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
    // Finds all cars matching the target brand (case-insensitive) using binary search to find one match, then expands left and right to find all matches.
    public static ArrayList<Car> searchByBrand(ArrayList<Car> list, String targetBrand) {
        ArrayList<Car> matches = new ArrayList<>();
        int matchIndex = binarySearchByBrand(list, targetBrand);

        if (matchIndex == -1) {
            return matches;
        }

        int left = matchIndex;
        int right = matchIndex;

        while (left >= 0 && list.get(left).getBrand().equalsIgnoreCase(targetBrand)) {
            left--;
        }
        while (right < list.size() && list.get(right).getBrand().equalsIgnoreCase(targetBrand)) {
            right++;
        }

        for (int i = left + 1; i < right; i++) {
            matches.add(list.get(i));
        }

        return matches;
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