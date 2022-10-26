import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class MostActiveCookie {
    public static final String ERROR_MSG = "Please use the format: ./most_active_cookie [filename] -d [date]";
    public static String FILENAME;
    public static String FLAG;
    public static String DATE;

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            System.out.println(ERROR_MSG);
            return;
        }

        FILENAME = args[0];
        FLAG = args[1];
        DATE = args[2];

        if (!FLAG.equals("-d")) {
            System.out.println(ERROR_MSG);
            return;
        }

        HashMap<String, Integer> cookies;

        try {
            cookies = csvToMap(FILENAME, DATE);
        } catch (Exception e) {
            return;
        }

        printMostActive(cookies);
    }

    /**
     * Returns a HashMap representation of all cookies on a given date and the
     * number of times they appear.
     */
    public static HashMap<String, Integer> csvToMap(String inputFile, String inputDate) {
        HashMap<String, Integer> cookieMap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            // skip header line
            String header = reader.readLine();

            // parse rest of CSV file
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                String cookie = tokens[0];
                String date = getDate(tokens);
                // String date = tokens[1].substring(0, tokens[1].indexOf('T'));
                if (date.equals(inputDate)) {
                    if (!cookieMap.containsKey(cookie)) {
                        cookieMap.put(cookie, 1);
                    } else {
                        cookieMap.put(cookie, cookieMap.get(cookie) + 1);
                    }
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException f) {
            System.out.println("File " + inputFile + " not found.");
        } catch (Exception e) {
            System.out.println("Invalid file format.");
        }
        return cookieMap;
    }

    /**
     * Returns a string representation of the date a cookie occurred on.
     */
    public static String getDate(String[] tokens) {
        String date = tokens[1].substring(0, tokens[1].indexOf('T'));
        return date;
    }

    /**
     * Prints the cookie(s) that appears most often on a given date.
     */
    public static HashSet<String> printMostActive(HashMap<String, Integer> cookies) {
        HashSet<String> mostActive = new HashSet<>();
        Integer max = 0;

        for (String key : cookies.keySet()) {
            if (cookies.get(key) == max) {
                mostActive.add(key);
            } else if (cookies.get(key) > max) {
                max = cookies.get(key);
                mostActive.clear();
                mostActive.add(key);
            }
        }

        // print most common cookies
        for (String cookie : mostActive) {
            System.out.println(cookie);
        }
        return mostActive;
    }
}