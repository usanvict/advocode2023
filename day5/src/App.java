import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class App {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
    }

    public static void partOne() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input2.txt"));

            String[] seeds = reader.readLine().split("\\s+");

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.length() != 0) {
                    if (line.charAt(line.length() - 1) == ':') {
                        // reader.readLine();
                        String rule;

                        boolean[] changes = new boolean[seeds.length];

                        while ((rule = reader.readLine()) != null) {
                            if (rule.length() == 0) {
                                break;
                            }
                            String[] ranges = rule.split("\\s+");

                            for (int i = 1; i < seeds.length; i++) {
                                BigInteger result = new BigInteger(seeds[i]);
                                BigInteger start = new BigInteger(ranges[1]);
                                BigInteger end = new BigInteger(ranges[2]);
                                if (result.compareTo(start) == 1
                                        && result.compareTo(start.add(end)) == -1) {
                                    if (changes[i - 1] == false) {
                                        BigInteger temp = new BigInteger(ranges[0]);
                                        result = result.add(temp).subtract(start);
                                        seeds[i] = String.valueOf(result);
                                        changes[i - 1] = true;
                                    }
                                }
                            }
                        }
                    }
                }

            }

            BigInteger location = null;

            for (int l = 1; l < seeds.length; l++) {
                if (location == null) {
                    location = new BigInteger(seeds[l]);
                } else {
                    BigInteger locResult = new BigInteger(seeds[l]);
                    location = location.min(locResult);
                }
            }

            System.out.println(location);

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void main(String[] args) throws Exception {
        partOne();
    }
}
