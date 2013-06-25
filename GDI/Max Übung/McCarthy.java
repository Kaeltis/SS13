/**
 * @author Max Jando
 *
 * Programm zur Implementierung der McCarthy-Funktion.
 * Zahlen von -NAN bis 100 ergeben 91. Zahlen größer 100 (Zahl - 10)
 */
import java.util.Scanner;

public class McCarthy {

    public static void main(String[] args) {
        System.out.println("Programm, welches die McCarthy-Funktion realisiert");
        System.out.println("Bitte geben Sie eine ganze Zahl ein :");
        int eingabe = pruefeEingabe();
        System.out.println(mcCarthyRek(eingabe));
    }

    /**
     * Rekursiver Aufruf der McCarthy - Funktion
     */
    private static int mcCarthyRek(int n) {
        if (n > 100) {
           return n - 10;
        }
        else{
            return mcCarthyRek(mcCarthyRek(n+11));
        }
    }

    /**
     * Methode, welche die Eingabe auf Richtigkeit überprüft
     *
     */
    private static int pruefeEingabe() {
        Scanner in = new Scanner(System.in);
        String eingabe;
        boolean erfolgreich = false;
        do {
            eingabe = in.next();
            if (eingabe.matches("-?[0-9]+")) { //Regular-Expression. Prüfung ob es eine ganze Zahl ist
                erfolgreich = true;
            } else {
                System.out.println("Keine gültige Eingabe. Bitte wiederholen Sie Ihre Eingabe");
            }
        } while (!erfolgreich);

        return (Integer.parseInt(eingabe));
    }


}
