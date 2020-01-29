import java.util.*;
import java.io.*;

public class Main {

    // Global that allows the program to get the path to the current directory
    public static String currDir = System.getProperty("user.dir");
    public static String address_file = (currDir + "/personnel_addresses.txt");
    public static String salary_file = (currDir + "/personnel_salaries.txt");

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
    * Method that searches through the given .txt file for entries that match
    * the city entered by the user.
    *
    * Returns a List of user names from the city.
    ***************************************************************************/
    static void findNames(String city, List<String> address, List<String> salary) {
        int count = 0;

        // Loop through every name and city in the list
        for(String addr: address) {
            // Split the line of the file using "|" as the separator
            String[] tokens = addr.split("\\|", 2);
            // Check if the current line matches the city
            // (Contains allows substrings to match ex. ville -> Louisville)
            if (tokens[1].contains(city)) {
                String name = tokens[0];
                for (String sal: salary) {
                    //Split the line of the file using "|" as the separator
                    String[] tok = sal.split("\\|", 2);
                    // Check if the names match
                    // .equals() makes sure the names are exactly the same
                    if (tok[0].equals(name)) {
                        count++;
                        // If they match, print the name and Salary
                        System.out.println(tok[0] + ":" + tok[1]);
                        //Remove item from list to make it faster
                        //salary.remove(sal);
                        salary.subList(0, salary.indexOf(sal)).clear();
                        // Exit out of the for loop
                        // No need to search the whole file for something
                        // that has been already found
                        break;
                    }
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        // First make sure the user entered a city as an argument
        if(args.length == 0) {
            System.out.println("Please enter city as argument, like so: " +
            "java Main CITYNAME");
            System.exit(0);
        } else {
            List<String> address = new ArrayList<>();
            List<String> salary = new ArrayList<>();
            address = openFile(address_file);
            salary = openFile(salary_file);

            java.util.Collections.sort(address);
            java.util.Collections.sort(salary);

            // Pull the city name from the command line entry
            String city = args[0];

            // Run the methods to find the names, salaries that match the city
            findNames(city, address, salary);
        }
    }
}
