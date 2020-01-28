import java.util.ArrayList;
import java.io.*;

public class Main {

    public static int count = 0;
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
        for (String name: names) {
            BufferedReader salary_in = null;
            try {
                salary_in = new BufferedReader(new FileReader(currDir + "/personnel_salaries2.txt"));
                String line;
                while ((line = salary_in.readLine()) != null) {
                    String[] tokens = line.split("\\|", 2);
                    if (tokens[0].equals(name)) {
                        count++;
                        break;
                    }
                }
                salary_in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String city = args[0];
        findSalaries(findNames(city));
        System.out.println(count);
    }
}
