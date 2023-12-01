import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class App {
    public static void partOne() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input1.txt"));
            String line;
            Integer finalSum = 0;
            while ((line = reader.readLine()) != null) {
                char[] parsedLine = line.toCharArray();
                ArrayList<Character> numbers = new ArrayList<>();
                for (int i = 0; i < parsedLine.length; i++) {
                    if (Character.isDigit(parsedLine[i])) {
                        numbers.add(parsedLine[i]);
                    }
                }

                String word = "";

                word = word + numbers.get(0) + numbers.get(numbers.size() - 1);

                finalSum = finalSum + Integer.parseInt(word);
            }

            System.out.println(finalSum);

            reader.close();

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void partTwo() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input1.txt"));
            String line;
            Integer finalSum = 0;
            List<String> numbers = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight",
                    "nine");

            while ((line = reader.readLine()) != null) {
                HashMap<Integer, String> numberMap = new HashMap<>();
                for (String s : numbers) {
                    int index = line.indexOf(s);
                    while (index >= 0) {
                        numberMap.put(index, s);
                        index = line.indexOf(s, index + 1);
                    }
                }

                char[] parsedLine = line.toCharArray();
                for (int i = 0; i < parsedLine.length; i++) {
                    if (Character.isDigit(parsedLine[i])) {
                        numberMap.put(i, String.valueOf(parsedLine[i]));
                    }
                }

                String word = "";

                List<Integer> employeeByKey = new ArrayList<>(numberMap.keySet());
                Collections.sort(employeeByKey);

                Integer number1 = 0;
                String firstValue = numberMap.get(employeeByKey.get(0));

                if (Character.isDigit(firstValue.charAt(0))) {
                    number1 = Integer.parseInt(firstValue);
                } else {
                    number1 = numbers.indexOf(firstValue) + 1;
                }

                Integer number2 = 0;
                String lastValue = numberMap.get(employeeByKey.get(employeeByKey.size() - 1));
                if (Character.isDigit(lastValue.charAt(0))) {
                    number2 = Integer.parseInt(lastValue);
                } else {
                    number2 = numbers.indexOf(lastValue) + 1;
                }

                word = word + number1 + number2;

                finalSum = finalSum + Integer.parseInt(word);

            }

            System.out.println(finalSum);

            reader.close();

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void main(String[] args) throws Exception {
        partTwo();
    }
}
