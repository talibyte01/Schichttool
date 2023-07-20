package de.fhe;

import java.util.*;

public class Main {
    private static final HashMap<String, Integer> slots = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        InitData result = getInitData();

        String[][] schichtArray = new String[result.timeslots().length][result.categories().length];

        for (int i = 0; i < result.timeslots().length; i++) {
            for (int j = 0; j < result.categories().length; j++) {
                schichtArray[i][j] = countLowestMember();
            }
        }

        printData(result, schichtArray);
    }

    private static void printData(InitData result, String[][] schichtArray) {
        System.out.println("\t\t");
        for (String category : result.categories()) {
            System.out.print("\t" + category);
        }

        System.out.println();
        for (int i = 0; i < result.timeslots().length; i++) {
            System.out.print(result.timeslots()[i]);
            for (int j = 0; j < result.categories().length; j++) {
                System.out.printf("\t" + schichtArray[i][j]);
            }
            System.out.println();
        }
    }

    private static InitData getInitData() {
        System.out.println("Bitte gib die Namen der Personen ein, die die Schichten übernehmen sollen, bitte mit Kommas getrennt");
        String[] names = scanner.nextLine().split(",");

        for (int i = 0; i < names.length; i++) {
            slots.put(names[i], 0);
        }

        System.out.println("Bitte gib die Stände der Veranstaltung ein mit Komma getrennt:");
        String[] categories = scanner.nextLine().split(","); //Columns

        System.out.println("Bitte gib die Schichtslots der Veranstaltung ein mit Komma getrennt:");
        String[] timeslots = scanner.nextLine().split(","); // Rows
        InitData result = new InitData(categories, timeslots);
        return result;
    }

    public static String countLowestMember() {
        int min = Integer.MAX_VALUE;
        for (Integer value : slots.values()) {
            if (min > value) {
                min = value;
            }
        }

        String key = "";
        for (String s : slots.keySet()) {
            if (slots.get(s) == min) {
                key = s;
                break;
            }
        }

        slots.put(key, slots.get(key) + 1);
        return key;
    }

    private record InitData(String[] categories, String[] timeslots) {
    }
}