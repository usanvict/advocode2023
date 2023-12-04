import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class App {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
    }

    public static void partTwo() throws Exception {
        try {
            BufferedReader readerCount = new BufferedReader(new FileReader("input2.txt"));
            int lines = 0;
            while (readerCount.readLine() != null)
                lines++;
            readerCount.close();

            HashMap<Integer, Integer> numbers = new HashMap<>();
            for (int z = 1; z < lines+1; z++){
                numbers.put(z, 1);
            }

            BufferedReader reader = new BufferedReader(new FileReader("input2.txt"));

            String line;
            Integer finalSum = 0;

            while ((line = reader.readLine()) != null) {
                String[] cards = line.split("\\|");
                String[] current = cards[0].split(":");

                String[] parseForNumber = current[0].split("\\s+");

                Integer cardNumber = 0;
                for (int x = 0; x < parseForNumber.length; x++) {
                    if (isNumeric(parseForNumber[x])) {
                        cardNumber = Integer.parseInt(parseForNumber[x]);
                    }
                }

                String[] currentCards = current[1].split("\\s+");
                String[] winningCards = cards[1].split("\\s+");

                Integer interSum = 0;

                for (int i = 1; i < currentCards.length; i++) {
                    for (int k = 1; k < winningCards.length; k++) {
                        if (currentCards[i].equals(winningCards[k])) {
                            interSum = interSum + 1;
                        }
                    }
                }

                for (int l = 1; l <= numbers.get(cardNumber); l++){
                    for (int c = cardNumber+1; c < cardNumber+interSum+1; c++){
                        numbers.put(c, numbers.get(c) + 1);
                    }
                }

            }

            for (Integer key : numbers.keySet()){
                finalSum = finalSum + numbers.get(key);
            }

            System.out.println(finalSum);

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void partOne() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input2.txt"));

            String line;
            Integer finalSum = 0;

            while ((line = reader.readLine()) != null) {
                String[] cards = line.split("\\|");
                String[] current = cards[0].split(":");

                String[] currentCards = current[1].split("\\s+");
                String[] winningCards = cards[1].split("\\s+");

                Integer interSum = 0;

                for (int i = 1; i < currentCards.length; i++) {
                    for (int k = 1; k < winningCards.length; k++) {
                        if (currentCards[i].equals(winningCards[k])) {
                            if (interSum == 0) {
                                interSum = 1;
                            } else {
                                interSum = interSum * 2;
                            }
                        }
                    }
                }
                finalSum = finalSum + interSum;
            }

            System.out.println(finalSum);

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void main(String[] args) throws Exception {
        partTwo();
    }
}
