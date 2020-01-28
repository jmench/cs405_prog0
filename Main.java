import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main {
public static void main(String[] args) {

    String currDir = System.getProperty("user.dir");
    int count = 0;

    // Start by asking for the city to comapre to
    Scanner in = new Scanner(System.in);
    System.out.println("Please enter the city to search for: ");
    String city = in.nextLine();
    System.out.println("You are searching for the city: " + city);

    ArrayList<String> nameArr = new ArrayList<>();
    ArrayList<String> salaryArr = new ArrayList<>();

    BufferedReader address_in;
    try {
        //address_in = new BufferedReader(new FileReader(currDir + "/test_addresses.txt"));
        //address_in = new BufferedReader(new FileReader(currDir + "/personnel_addresses.txt"));
        address_in = new BufferedReader(new FileReader(currDir + "/personnel_addresses2.txt"));
        String line = address_in.readLine();
        while (line != null) {
            String[] tokens = line.split("\\|", 2);
            if (tokens[1].contains(city)) {
                nameArr.add(tokens[0]);
            }

            line = address_in.readLine();
        }
        address_in.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

    BufferedReader salary_in;
    try {
        for (String name: nameArr) {
            //salary_in = new BufferedReader(new FileReader(currDir + "/test_salaries.txt"));
            //salary_in = new BufferedReader(new FileReader(currDir + "/personnel_salaries.txt"));
            salary_in = new BufferedReader(new FileReader(currDir + "/personnel_salaries2.txt"));
            String line = salary_in.readLine();
            while (line != null) {

                String[] tokens = line.split("\\|", 2);
                if (tokens[0].contains(name)) {
                    System.out.println("The name of the person is " + tokens[0] +
                    " and their salary is: " + tokens[1]);
                    salaryArr.add(tokens[1]);
                    count++;
                }

                line = salary_in.readLine();
            }
            salary_in.close();
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    System.out.println(count);

}

}
