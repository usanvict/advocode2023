import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import javax.crypto.AEADBadTagException;

public class App {

    public static void partOne() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input1.txt"));
            String line;
            Integer finalSum = 0;

            Integer red = 12;
            String redColor = "red";
            Integer blue = 14;
            String blueColor = "blue";
            Integer green = 13;
            String greenColor = "green";

            while ((line = reader.readLine()) != null) {
                String[] firstLine = line.split(";");
                String[] before = firstLine[0].split(":");

                Integer gameID = Integer.parseInt(String.valueOf(before[0].split(" ")[before.length - 1]));
                ArrayList<String[]> game = new ArrayList<>();

                for (int k = 1; k < firstLine.length; k++) {
                    game.add(firstLine[k].split(","));
                }
                game.add(before[1].split(","));

                // going through the first set
                boolean isValid = true;
                for (String[] x : game) {
                    if (isValid == false) {
                        break;
                    }
                    for (int k = 0; k < x.length; k++) {
                        if (x[k].contains(greenColor)) {
                            Integer currNumber = Integer.parseInt(String.valueOf(x[k].split(" ")[1]));
                            if (currNumber > green) {
                                isValid = false;
                                break;
                            }
                        }
                        if (x[k].contains(redColor)) {
                            Integer currNumber = Integer.parseInt(String.valueOf(x[k].split(" ")[1]));
                            if (currNumber > red) {
                                isValid = false;
                                break;
                            }
                        }
                        if (x[k].contains(blueColor)) {
                            Integer currNumber = Integer.parseInt(String.valueOf(x[k].split(" ")[1]));
                            if (currNumber > blue) {
                                isValid = false;
                                break;
                            }
                        }
                    }
                }
                if (isValid) {
                    finalSum = finalSum + gameID;
                }
            }

            System.out.println(finalSum);

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void partTwo() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input2.txt"));
            String line;

            Integer power = 0;

            Integer maxRed = 0;
            String redColor = "red";
            Integer maxBlue = 0;
            String blueColor = "blue";
            Integer maxGreen = 0;
            String greenColor = "green";

            while ((line = reader.readLine()) != null) {
                String[] firstLine = line.split(";");
                String[] before = firstLine[0].split(":");

                Integer gameID = Integer.parseInt(String.valueOf(before[0].split(" ")[before.length - 1]));

                ArrayList<String[]> game = new ArrayList<>();

                for (int k = 1; k < firstLine.length; k++) {
                    game.add(firstLine[k].split(","));
                }
                game.add(before[1].split(","));

                for (String[] x : game) {
                    for (int k = 0; k < x.length; k++) {
                        if (x[k].contains(greenColor)) {
                            Integer currNumber = Integer.parseInt(String.valueOf(x[k].split(" ")[1]));
                            if (currNumber > maxGreen) {
                                maxGreen = currNumber;
                            }
                        }
                        if (x[k].contains(redColor)) {
                            Integer currNumber = Integer.parseInt(String.valueOf(x[k].split(" ")[1]));
                            if (currNumber > maxRed) {
                                maxRed = currNumber;
                            }
                        }
                        if (x[k].contains(blueColor)) {
                            Integer currNumber = Integer.parseInt(String.valueOf(x[k].split(" ")[1]));
                            if (currNumber > maxBlue) {
                                maxBlue = currNumber;
                            }
                        }
                    }
                }
                power = power + maxBlue * maxRed * maxGreen;
                maxRed = 0;
                maxBlue = 0;
                maxGreen = 0;
            }

            System.out.println(power);

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void main(String[] args) throws Exception {
        partOne();
        partTwo();
    }
}
