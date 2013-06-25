import java.util.Scanner;
/**
 * @author Max Jando
 * Programm prüft, wie der Gleiche Buchstabe aneinander gereit auftaucht.
 */
public class Zeichenkette {

    private static int countMostCaracter = 0;

    public static void main(String[] args) {

        System.out.println("Bitte geben Sie eine Zeichenkette, in welcher nach gleichen Vorkommen gesucht werden soll.");
        String eingabe = pruefeEingabe();
        System.out.println("Buchstabe der am Meisten vorkommt: " + checkMostCharacter(eingabe));
        System.out.println("Anzahl: " + countMostCaracter);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private static String pruefeEingabe() {
        Scanner in = new Scanner(System.in);
        String eingabe = in.nextLine();
        char removeToken;

        for (int buchstabe = 0; buchstabe < eingabe.length(); buchstabe++) {
            if (((eingabe.charAt(buchstabe) <= 'a' || eingabe.charAt(buchstabe) >= 'z')) &&
                    ((eingabe.charAt(buchstabe) <= 'A' || eingabe.charAt(buchstabe) >= 'Z'))) {
                        //Kein Buchstabe --> Entfernen
                        removeToken = eingabe.charAt(buchstabe);
                        eingabe = eingabe.replaceAll(String.valueOf(removeToken), "");
                    } else {
                //Letter OK!
            }
        }

        return eingabe.toLowerCase();
    }

    private static char checkMostCharacter(String eingabe) {
        int counter = 0;
        System.out.println(eingabe);
        char mostCharacter = 0;
        char tmp = eingabe.charAt(0);
        for (int zeichenkette = 0; zeichenkette < eingabe.length(); zeichenkette++) {
            if (eingabe.charAt(zeichenkette) == tmp) {
                counter++;
            } else {
                if (counter > countMostCaracter) {
                    countMostCaracter = counter;
                    mostCharacter = eingabe.charAt(zeichenkette - 1);
                }
                tmp = eingabe.charAt(zeichenkette);
                counter = 1;
            }
        }
        return mostCharacter;
    }


}
