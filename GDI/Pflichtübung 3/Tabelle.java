import java.util.Scanner;

/*
 * Max Jando - 1312825 / Patrick Fruh - 1314827 
 * Klasse, welche eine Tabelle in Java implementiert.
 * Es k�nnen beliebig viele Zeilen und Spalten angelegt und Spalten�berschriften
 * definiert werden. Der Zelleninhalt wird mit "Zeilennummer x Spaltennummer" gef�llt.
 */
public class Tabelle {

	public static void main(String[] args) {
		// Variablen-Deklaration
		int spalten;
		int zeilen;

		// Tastatureingabe initialisieren
		Scanner in = new Scanner(System.in);

		System.out.println("Programm zum Generieren einer Tabelle\n");
		System.out.print("Bitte geben Sie die Anzahl der Spalten ein: ");
		spalten = in.nextInt();
		System.out.print("\nBitte geben Sie die Anzahl der Zeilen ein: ");
		zeilen = in.nextInt();

		// Entfernen von \n aus dem Input
		in.nextLine();

		String[][] zweiDimArray = new String[spalten][zeilen + 1];

		// Einlesen einer �berschrift f�r jede Spalte
		for (int i = 0; i < spalten; i++) {
			System.out.print("\nBitte geben Sie die �berschrift der " + (i + 1)
					+ ". Spalte ein: ");
			zweiDimArray[i][0] = in.nextLine() + "\t";
		}

		in.close();

		// F�llen der Zellen mit "Zeilennummer x Spaltennummer"
		// bzw. Trennern f�r bessere Ausgabeformatierung
		for (int i = 0; i < spalten; i++) {
			for (int j = 1; j <= zeilen; j++) {
				if (i == 0) {
					zweiDimArray[i][j] = (i + 1) + " x " + j;
				} else {
					zweiDimArray[i][j] = " | " + (i + 1) + " x " + j;
				}
			}
		}

		// Ausgabe der Tabelle
		for (int i = 0; i <= zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				System.out.print(zweiDimArray[j][i]);
			}
			System.out.println();
		}

	}

}