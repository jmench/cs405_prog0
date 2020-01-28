import java.util.ArrayList;
import java.io.*;

public class Main {

    // Global that allows the program to get the path to the current directory
    public static String currDir = System.getProperty("user.dir");

    static ArrayList<String> findNames(String city) {
        ArrayList<String> nameArr = new ArrayList<>();
        BufferedReader address_in = null;
        try {
            address_in = new BufferedReader(new FileReader(currDir + "/personnel_addresses2.txt"));
            String line;
            while ((line = address_in.readLine()) != null) {
                String[] tokens = line.split("\\|", 2);
                if (tokens[1].contains(city)) {
                    nameArr.add(tokens[0]);
                }
            }
            address_in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameArr;
    }

    static void findSalaries(ArrayList<String> names) {
        if (names.isEmpty()) {
            System.out.println("No names found for the city entered...");
        } else {
            for (String name: names) {
                BufferedReader salary_in = null;
                try {
                    salary_in = new BufferedReader(new FileReader(currDir + "/personnel_salaries2.txt"));
                    String line;
                    while ((line = salary_in.readLine()) != null) {
                        String[] tokens = line.split("\\|", 2);
                        if (tokens[0].equals(name)) {
                            System.out.println(tokens[0] + " : " + tokens[1]);
                            break;
                        }
                    }
                    salary_in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // Pull the city name from the command line entry
        String city = args[0];
        findSalaries(findNames(city));
    }
}
