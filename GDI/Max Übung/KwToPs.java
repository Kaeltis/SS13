/**
 * @author Max Jando
 * Programm rechnet von einem eingegebenen Start - und Endwert, jeweils in 1er Schritten PS in KW um.
 */

import java.util.Scanner;

public class KwToPs {

    //KONSTANTE, welche den Umrechnungsfaktor von KW in PS angibt
    private static final double KW_TO_PS_FACTOR = 1.3596216;

    public static void main(String[] args) {
        System.out.println("Programm, welches in einem gegebenen Bereich KW in PS umrechnet");
        System.out.println("Bitte geben Sie einen Startwert ein :");
        Double startWert = pruefeEingabe();
        System.out.println("Bitte geben Sie einen Endwert ein :");
        Double endWert = pruefeEingabe();
        fuehreBerechnungAus(startWert, endWert);
    }

    /**
     * Methode, welche die Berechnung anhand des Start -und Endwerts ausführt
     */
    private static void fuehreBerechnungAus(double startWert, double endWert) {
        //Schleife durchläuft in 1er Schritten die KW-Angaben, bis der eingegebene Endwert erreicht ist.
        for (double kwSchritt = startWert; kwSchritt <= endWert; kwSchritt++) {
            System.out.println(kwSchritt + " | " + kwSchritt * KW_TO_PS_FACTOR);
        }
    }

    /**
     * Methode, welche die Eingabe auf Richtigkeit überprüft
     */
    private static double pruefeEingabe() {
        Scanner in = new Scanner(System.in);
        String eingabe;
        boolean erfolgreich = false;
        do {
            eingabe = in.next();
            if (eingabe.matches("\\d+(,\\d*)??")) { //Regular-Expression. Prüfung ob es eine KommaZahl ist
                erfolgreich = true;
            } else {
                System.out.println("Keine gültige Eingabe. Bitte wiederholen Sie Ihre Eingabe");
            }
        } while (!erfolgreich);
        eingabe = eingabe.replaceAll(",", ".");

        return Double.parseDouble(eingabe);
    }

}
