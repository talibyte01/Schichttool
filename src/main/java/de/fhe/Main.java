package de.fhe;

import java.util.*;

public class Main {
    private static final HashMap<String, Integer> slots = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bitte gib die Namen der Personen ein, die die Schichten übernehmen sollen, bitte mit Kommas getrennt");
        String[] names = scanner.nextLine().split(",");

        for (int i = 0; i < names.length; i++) {
            slots.put(names[i], 0);
        }

        System.out.println("Bitte gib die Stände der Veranstaltung ein mit Komma getrennt:");
        String[] categories = scanner.nextLine().split(","); //Columns

        System.out.println("Bitte gib die Schichtslots der Veranstaltung ein mit Komma getrennt:");
        String[] timeslots = scanner.nextLine().split(","); // Rows

        String[][] schichtArray = new String[timeslots.length][categories.length];

        for (int i = 0; i < timeslots.length; i++) {
            for (int j = 0; j < categories.length; j++) {
                schichtArray[i][j] = countLowestMember();
            }
        }

        System.out.println("\t\t");
        for (String category : categories) {
            System.out.print("\t" + category);
        }

        System.out.println();
        for (int i = 0; i < timeslots.length; i++) {
            System.out.print(timeslots[i]);
            for (int j = 0; j < categories.length; j++) {
                System.out.printf("\t" + schichtArray[i][j]);
            }
            System.out.println();
        }


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
}