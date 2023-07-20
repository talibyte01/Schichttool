package org.example;

import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        Scanner userinp = new Scanner(System.in);

        System.out.println("Bitte gib die Namen der Personen ein, die die Schichten übernehmen sollen, bitte mit Kommas getrennt");
        String personinput = userinp.nextLine();
        String[] namen = personinput.split(",");

        Person[] persons = new Person[namen.length];

        for (int i = 0; i < persons.length; i++) {
            String name = namen[i].trim(); // Entferne führende und nachfolgende Leerzeichen

            // Erstelle eine neue Person und speichere sie im Array
            persons[i] = new Person(name);
        }

        for (Person person : persons) {
            System.out.println(person.getName());
        }


        System.out.println("Bitte gib die Stände der Veranstaltung ein mit Komma getrennt:");
        String[] categories = userinp.nextLine().split(","); //Columns

        System.out.println("Bitte gib die Schichtslots der Veranstaltung ein mit Komma getrennt:");
        String[] timeslots = userinp.nextLine().split(","); // Rows

        String[][] schichtArray = new String[timeslots.length][categories.length];


        for (int i = 0; i < timeslots.length; i++) {
            for (int j = 0; j < categories.length; j++) {

                Optional<Person> nextPerson = Arrays.stream(persons).min(Comparator.comparing(Person::getSchichtAnzahl));
                int finalI = i;
                int finalJ = j;
                nextPerson.ifPresent(person -> {
                            person.setSchichtAnzahl(person.getSchichtAnzahl() + 1);
                            if(Arrays.stream(schichtArray[finalI]).noneMatch(Predicate.isEqual(person.getName()))) {
                                schichtArray[finalI][finalJ] = person.getName();
                            }else {
                                schichtArray[finalI][finalJ] = "no person found";
                            }
                        }
                );

            }
        }


        System.out.println("\t\t");
        for (String category : categories) {
            System.out.print("\t" +  category);
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
}

class Person {
    private String name;

    private int schichtAnzahl;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSchichtAnzahl() {
        return schichtAnzahl;
    }

    public void setSchichtAnzahl(int schichtAnzahl) {
        this.schichtAnzahl = schichtAnzahl;
    }
}