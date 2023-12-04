import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
    }

    public static void partTwo() {
        try {
            String[][] value = null;

            File file = new File("input2.txt");
            Scanner sizeScanner = new Scanner(file);
            String[] temp = sizeScanner.nextLine().split("");
            sizeScanner.close();
            int nMatrix = temp.length;

            Scanner scanner = new Scanner(file);
            value = new String[nMatrix][nMatrix];
            for (int i = 0; i < nMatrix; i++) {
                String[] numbers = scanner.nextLine().split("");
                for (int j = 0; j < nMatrix; j++) {
                    value[i][j] = numbers[j];
                }
            }
            scanner.close();

            Integer finalSum = 0;
            String possibleDetail = "";

            boolean adjacent = false;
            HashMap<Integer, ArrayList<Integer[]>> polohaMap = new HashMap<>();
            Integer[] poloha = new Integer[2];
            poloha[0] = -1;
            poloha[1] = -1;

            for (int i = 0; i < value.length; i++) {
                for (int k = 0; k < value[i].length; k++) {
                    if (isNumeric(value[i][k])) {
                        possibleDetail = possibleDetail + value[i][k];
                        // if (possibleDetail.equals("700")) {
                        // System.out.println("mah");
                        // }
                        if (possibleDetail.length() == 1) {
                            if (i >= 1) {
                                if (value[i - 1][k].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i - 1;
                                    poloha[1] = k;
                                }
                            }
                            if (k >= 1 && i < value.length - 1) {
                                if (value[i + 1][k - 1].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i + 1;
                                    poloha[1] = k - 1;
                                }
                            }
                            if (k >= 1 && i >= 1) {
                                if (value[i - 1][k - 1].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i - 1;
                                    poloha[1] = k - 1;
                                }
                            }
                            if (k > 1) {
                                if (value[i][k - 1].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i;
                                    poloha[1] = k - 1;
                                }
                            }
                            if (i < value.length - 1) {
                                if ((value[i + 1][k].equals("*"))) {
                                    adjacent = true;
                                    poloha[0] = i + 1;
                                    poloha[1] = k;
                                }
                            }
                        } else {
                            if (i >= 1) {
                                if (value[i - 1][k].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i - 1;
                                    poloha[1] = k;
                                }
                            }
                            if (i < value.length - 1) {
                                if (value[i + 1][k].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i + 1;
                                    poloha[1] = k;
                                }
                            }
                        }
                        if (k == value[i].length - 1) {
                            if (adjacent) {
                                boolean multiplied = false;
                                for (Integer x : polohaMap.keySet()) {
                                    for (Integer[] y : polohaMap.get(x)) {
                                        if (Arrays.equals(y, poloha)) {
                                            finalSum = finalSum + Integer.parseInt(possibleDetail) * x;
                                            System.out.println(possibleDetail + " x " + x);
                                            polohaMap.get(x).remove(y);
                                            multiplied = true;
                                            break;
                                        }
                                    }
                                }
                                if (!multiplied) {
                                    ArrayList<Integer[]> listPoloha = new ArrayList<>();
                                    String a = Integer.toString(poloha[0]);
                                    String b = Integer.toString(poloha[1]);
                                    Integer tempPoloha[] = { Integer.parseInt(a), Integer.parseInt(b) };
                                    listPoloha.add(tempPoloha);
                                    polohaMap.put(Integer.parseInt(possibleDetail), listPoloha);
                                }
                                // else {
                                // for (Integer x : polohaMap.keySet()) {
                                // for (Integer[] y : polohaMap.get(x)) {
                                // if (Arrays.equals(y, poloha)) {
                                // finalSum = finalSum + Integer.parseInt(possibleDetail) * x;
                                // System.out.println(possibleDetail + " x " + x);
                                // }
                                // }
                                // }
                                // }
                                adjacent = false;
                            }
                            possibleDetail = "";
                            poloha[0] = -1;
                            poloha[1] = -1;
                        }
                    } else {
                        if (possibleDetail.equals("")) {
                            continue;
                        } else {
                            if (value[i][k].equals("*")) {
                                adjacent = true;
                                poloha[0] = i;
                                poloha[1] = k;
                            }
                            if (k <= value.length && i >= 1) {
                                if (value[i - 1][k].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i - 1;
                                    poloha[1] = k;
                                }
                            }
                            if (k <= value.length && i < value.length - 1) {
                                if (value[i + 1][k].equals("*")) {
                                    adjacent = true;
                                    poloha[0] = i + 1;
                                    poloha[1] = k;
                                }
                            }
                            if (adjacent) {
                                boolean multiplied = false;
                                for (Integer x : polohaMap.keySet()) {
                                    for (Integer[] y : polohaMap.get(x)) {
                                        if (Arrays.equals(y, poloha)) {
                                            finalSum = finalSum + Integer.parseInt(possibleDetail) * x;
                                            System.out.println(possibleDetail + " x " + x);
                                            polohaMap.get(x).remove(poloha);
                                            multiplied = true;
                                            break;
                                        }
                                    }
                                }
                                // !polohaMap.containsKey(Integer.parseInt(possibleDetail)) &&
                                if (!multiplied) {
                                    if (!polohaMap.containsKey(Integer.parseInt(possibleDetail))) {
                                        ArrayList<Integer[]> listPoloha = new ArrayList<>();
                                        String a = Integer.toString(poloha[0]);
                                        String b = Integer.toString(poloha[1]);
                                        Integer tempPoloha[] = { Integer.parseInt(a), Integer.parseInt(b) };
                                        listPoloha.add(tempPoloha);
                                        polohaMap.put(Integer.parseInt(possibleDetail), listPoloha);
                                    } else {
                                        ArrayList<Integer[]> listPoloha = polohaMap
                                                .get(Integer.parseInt(possibleDetail));
                                        String a = Integer.toString(poloha[0]);
                                        String b = Integer.toString(poloha[1]);
                                        Integer tempPoloha[] = { Integer.parseInt(a), Integer.parseInt(b) };
                                        listPoloha.add(tempPoloha);
                                        polohaMap.put(Integer.parseInt(possibleDetail), listPoloha);
                                    }
                                }
                                // else {
                                // for (Integer x : polohaMap.keySet()) {
                                // for (Integer[] y : polohaMap.get(x)) {
                                // if (Arrays.equals(y, poloha)) {
                                // finalSum = finalSum + Integer.parseInt(possibleDetail) * x;
                                // System.out.println(possibleDetail + " x " + x);
                                // }
                                // }
                                // }
                                // }
                                adjacent = false;
                            }
                            possibleDetail = "";
                            poloha[0] = -1;
                            poloha[1] = -1;
                        }
                    }
                }
            }

            System.out.println(finalSum);

        } catch (Exception e) {
            System.out.println("Oops");
        }
    }

    public static void partOne() {
        try {
            String[][] value = null;

            File file = new File("input2.txt");
            Scanner sizeScanner = new Scanner(file);
            String[] temp = sizeScanner.nextLine().split("");
            sizeScanner.close();
            int nMatrix = temp.length;

            Scanner scanner = new Scanner(file);
            value = new String[nMatrix][nMatrix];
            for (int i = 0; i < nMatrix; i++) {
                String[] numbers = scanner.nextLine().split("");
                for (int j = 0; j < nMatrix; j++) {
                    value[i][j] = numbers[j];
                }
            }
            scanner.close();

            Integer finalSum = 0;
            String possibleDetail = "";
            boolean adjacent = false;

            for (int i = 0; i < value.length; i++) {
                for (int k = 0; k < value[i].length; k++) {
                    if (isNumeric(value[i][k])) {
                        possibleDetail = possibleDetail + value[i][k];
                        if (possibleDetail.length() == 1) {
                            if (i >= 1) {
                                if (!value[i - 1][k].equals(".")) {
                                    adjacent = true;
                                }
                            }
                            if (k >= 1 && i < value.length - 1) {
                                if (!value[i + 1][k - 1].equals(".")) {
                                    adjacent = true;
                                }
                            }
                            if (k >= 1 && i >= 1) {
                                if (!value[i - 1][k - 1].equals(".")) {
                                    adjacent = true;
                                }
                            }
                            if (k > 1) {
                                if (!value[i][k - 1].equals(".")) {
                                    adjacent = true;
                                }
                            }
                            if (i < value.length - 1) {
                                if ((!value[i + 1][k].equals("."))) {
                                    adjacent = true;
                                }
                            }
                        } else {
                            if (i >= 1) {
                                if (!value[i - 1][k].equals(".")) {
                                    adjacent = true;
                                }
                            }
                            if (i < value.length - 1) {
                                if (!value[i + 1][k].equals(".")) {
                                    adjacent = true;
                                }
                            }
                        }
                    } else {
                        if (possibleDetail.equals("")) {
                            continue;
                        } else {
                            if (!value[i][k].equals(".")) {
                                adjacent = true;
                            }
                            if (k <= value.length && i >= 1) {
                                if (!value[i - 1][k].equals(".")) {
                                    adjacent = true;
                                }
                            }
                            if (k <= value.length && i < value.length - 1) {
                                if (!value[i + 1][k].equals(".")) {
                                    adjacent = true;
                                }
                            }
                            if (adjacent) {
                                finalSum = finalSum + Integer.parseInt(possibleDetail);
                                adjacent = false;
                                System.out.println("I've put this number " + possibleDetail);
                            }
                            possibleDetail = "";
                        }
                    }
                }
            }

            if (!possibleDetail.equals("") && adjacent == true) {
                finalSum = finalSum + Integer.parseInt(possibleDetail);
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
