import java.util.ArrayList;
import java.io.*;

public class Main {

    // Global that allows the program to get the path to the current directory
    public static String currDir = System.getProperty("user.dir");

    /**************************************************************************
    * Method that searches through the given .txt file for entries that match
    * the city entered by the user.
    *
    * Returns an ArrayList of user names from the city.
    ***************************************************************************/
    static ArrayList<String> findNames(String city) {
        // List to hold names
        ArrayList<String> nameArr = new ArrayList<>();
        BufferedReader address_in = null;

        // Attempt to open the file
        try {
            address_in = new BufferedReader(new FileReader(currDir +
            "/personnel_addresses.txt"));
            String line;
            //Continue to read the file until there are no more entries
            while ((line = address_in.readLine()) != null) {
                // Split the line of the file using "|" as the separator
                String[] tokens = line.split("\\|", 2);
                // Check if the current line matches the city
                // (Contains allows substrings to match ex. ville -> Louisville)
                if (tokens[1].contains(city)) {
                    // If a match, add the name to the list
                    nameArr.add(tokens[0]);
                }
            }
            // Close the file after reading
            address_in.close();

        // Print an error if the file didn't open properly
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return the ArrayList of matching names
        return nameArr;
    }

    /**************************************************************************
    * Method that searches through the given .txt file for entries that match
    * the name from the given list of matching names.
    *
    * Prints the name and Salary of all employees that are found to match.
    * Format: "Name : Salary"
    ***************************************************************************/
    static void findSalaries(ArrayList<String> names) {
        // First check and notify the user if no names matched...
        if (names.isEmpty()) {
            System.out.println("No names found for the city entered...");

        // Else there are a list of names and you begin searching
        } else {
            // Keep count in case no names match in the file
            int count = 0;

            // Run the search for every name in the list
            for (String name: names) {
                BufferedReader salary_in = null;

                // Attempt to open the file
                try {
                    salary_in = new BufferedReader(new FileReader(currDir +
                    "/personnel_salaries.txt"));
                    String line;
                    //Continue to read the file until there are no more entries
                    while ((line = salary_in.readLine()) != null) {
                        // Split the line of the file using "|" as the separator
                        String[] tokens = line.split("\\|", 2);
                        // Check if the names match
                        // .equals() makes sure the names are exactly the same
                        if (tokens[0].equals(name)) {
                            // If they match, print the name and Salary
                            System.out.println(tokens[0] + " : " + tokens[1]);
                            count++;
                            // Exit out of the while loop
                            // No need to search the whole file for something
                            // that has been already found
                            break;
                        }
                    }
                    // break statement brings you here

                    // Close the file after reading
                    salary_in.close();

                // Print an error if the file didn't open properly
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (count == 0) {
                System.out.println("No matches found in the salary file...");
            }
        }
    }

    public static void main(String[] args) {
        // First make sure the user entered a city as an argument
        if(args.length == 0) {
            System.out.println("Please enter city as argument, like so:
            java Main CITYNAME");
            System.exit(0);
        }
        else {
            // Pull the city name from the command line entry
            String city = args[0];
            // Run the methods to find the names, salaries that match the city
            findSalaries(findNames(city));
        }
    }
}
