import java.util.*;
import java.io.*;

public class prog0 {
    /**************************************************************************
    *                               openFile()
    * Method that opens the file passed in and populates a new list with the
    * entries.
    *
    * Returns a List with each element as a line from the file.
    ***************************************************************************/
    static List<String> openFile(String filename) {
        List<String> tmp = new ArrayList<>();
        try (Scanner s = new Scanner(new FileReader(filename))) {
            while (s.hasNext()) {
                tmp.add(s.nextLine());
            }
            // Close the file after reading
            s.close();
        // Print an error if the file didn't open properly
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }


    /**************************************************************************
    *                               findNames()
    * Method that searches through the given .txt file for entries that match
    * the city entered by the user.
    *
    * Returns a List of user names from the city.
    ***************************************************************************/
    static void findNames(String city, List<String> address, List<String> salary) {
        // Loop through every name and city in the list
        for(String addr: address) {
            // Split the line of the file using "|" as the separator
            String[] tokens = addr.split("\\|", 2);
            // Check if the current line matches the city
            // (Contains allows substrings to match ex. ville -> Louisville)
            if (tokens[1].contains(city)) {
                // Save the name
                String name = tokens[0];
                // Loop through every name and salary in the list
                for (String sal: salary) {
                    //Split the line of the file using "|" as the separator
                    String[] tok = sal.split("\\|", 2);
                    // Check if the names match
                    // .equals() makes sure the names are exactly the same
                    if (tok[0].equals(name)) {
                        // If they match, print the name and Salary
                        System.out.println(tok[0] + ":" + tok[1]);
                        // Remove all unnecessary entires to make searching
                        // faster.
                        salary.subList(0, salary.indexOf(sal)).clear();
                        // Exit out of the for loop
                        break;
                    }
                }
            }
        }
    }

    /**************************************************************************
    *                               MAIN                                      *
    **************************************************************************/
    public static void main(String[] args) {
        // First make sure the user entered a city as an argument
        if(args.length == 0) {
            System.out.println("Please enter city as argument, like so: " +
            "java Main CITYNAME");
            System.exit(0);
        } else {
            // Helps get the path to the current directory
            String currDir = System.getProperty("user.dir");
            // String vals for the path to the txt files
            String address_file = (currDir + "/personnel_addresses.txt");
            String salary_file = (currDir + "/personnel_salaries.txt");

            // Create lists for the addresses and salaries
            // use the openFile function to populate the lists
            List<String> address = openFile(address_file);
            List<String> salary = openFile(salary_file);

            // Sort the lists by alphabetical order for faster searching.
            Collections.sort(address);
            Collections.sort(salary);

            // Grab the city name from the command line entry
            String city = args[0];

            // Run the method to find the names/salaries that match the city
            findNames(city, address, salary);
        }
    }
}
