/**
 * @author Max Jando - 1312825
 * Programm zum Ausgeben eines Dreiecks
 */

import java.util.Scanner;

public class Dreieck {

    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {

        System.out.println("Bitte geben Sie eine ganze Zahl für das Dreieck ein:");
        String eingabe;
        boolean erfolgreich = true;
        Scanner in = new Scanner(System.in);

        do {
            eingabe = in.nextLine();
            if (eingabe.matches("[0-9]+")) { //Regular-Expression
                erfolgreich = true;
            } else {
                System.out.println("Keine gültige Eingabe. Bitte wiederholen Sie Ihre Eingabe");
            }
        } while (!erfolgreich);
        int eingabeInt = Integer.parseInt(eingabe);

        //Schleife durchläuft die Zeilen des Dreiecks
        for (int zeilen = 1; zeilen <= eingabeInt; zeilen++) {
            //Schleife durchläuft die Stellen des Dreiecks
            for (int stellen = 1; stellen <= zeilen; stellen++) {
                System.out.print("*");
            }
            System.out.println();
        }


    }


}
