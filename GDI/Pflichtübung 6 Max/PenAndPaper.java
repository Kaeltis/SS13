/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Hauptprogramm des Spiels PenAndPaper. Klasse ruft nacheinander verschiedene Methoden auf das Spielerlebnis
 * zu ermöglichen.
 */

import java.util.Scanner;

class PenAndPaper {

    //Globale Objektreferenzen deklarieren
    public static Scanner in = new Scanner(System.in);
    public static Held Spieler1;

    public static void main(String[] args) {
        klassenwahl();
        ausgabeGeschichte();
        fuehreKaempfeAus(würfeln(1, 4));
        storyEnde(Spieler1);
    }

    /**
     * Methode, welche das Endergebnis des Spiels ausgibt
     */
    private static void storyEnde(Held spieler1) {
        if (Spieler1.getLebenspunkte() > 0) {
            System.out.println("Du bist sicher in Bree im Gasthaus zum tänzelnden Ponny angekommen!");
            System.out.println("Doch wo ist Gandalf??");
            System.out.println("Du hast gewonnen");
        } else {
            System.out.println("Leider waren die Scharen des dunklen Herrschers Sauron stärker!");
            System.out.println("Sauron wird über alles Leben auf dieser Welt herrschen! Auch über das Ende der Welt!");
            System.out.println("Du hast verloren");
        }
    }

    /**
     * Methode, welche Kämpfe in Abhängikeit der Begegnungen ausführt
     */
    private static void fuehreKaempfeAus(int anzahlBegegnungen) {
        int anzahlMonster = 0;
        int monsterTotZähler = 0;

        //Schleife durchläuft die Anzahl der Begegnungen
        while (Spieler1.getLebenspunkte() > 0 && anzahlBegegnungen > 0) {
            anzahlMonster = würfeln(1, 4);

            //Deklariere ein Array von Monster. Welches Monster generiert wie stark
            // es ist und wie viele Monster es enthält wird in generiereMonster bzw. berechnePunkte berechnet
            Monster[] monsterListe = generiereMonster(berechneArt(würfeln(1, 6)), berechneMonsterPunkte(anzahlMonster), anzahlMonster);
            int monsterZaehler = 0;
            monsterTotZähler = 0;
            System.out.println("---------------------");
            System.out.println("Gebt acht, aus dem grauen Zwiellicht, greift dich eine Monstergruppe " + monsterListe[monsterZaehler].getArt() + " an");
            System.out.println("Haltet eure Stellung! Kääämpft!");

            //Schleife durchläuft die Monster
            while (monsterTotZähler != monsterListe.length && Spieler1.getLebenspunkte() > 0) {
                if (monsterZaehler == monsterListe.length) {
                    monsterZaehler = 0;
                }
                if (monsterListe[monsterZaehler].getLebenspunkte() > 0) {
                    System.out.println(Spieler1.angriffMonster(monsterListe[monsterZaehler]));
                    if (monsterListe[monsterZaehler].getLebenspunkte() <= 0) {
                        monsterTotZähler++;
                    }
                }
                monsterZaehler++;
            }
            System.out.println("Die Monstergruppe wurde besiegt!");
            System.out.println("---------------------");
            System.out.println();
            anzahlBegegnungen--;
        }
    }

    /**
     * Methode, welche ein Array von Monster generiert. Abhängig
     * von der Art,
     * der Anzahl der Monster
     * den Monsterpunkten(pro Monster 3/4 der Heldenpunkte/Anzahl der Monster pro Durchlauf)
     */
    private static Monster[] generiereMonster(Monster.Monsterarten monsterarten, int monsterPunkte, int anzahlMonster) {
        Monster[] monsterListe = new Monster[anzahlMonster];
        //Schleife durchläuft die Monster legt pro Durchlauf ein neues Monster in Abhängigkeit der Vorgaben an
        for (int monsterZaehler = 0; monsterZaehler < monsterListe.length; monsterZaehler++) {
            monsterListe[monsterZaehler] = new Monster(monsterarten, monsterPunkte);
        }
        return monsterListe;
    }

    /**
     *  Methode, welche die Punkte pro Monster berechnet.
     *  Formel: 3/4 der Heldenpunkte/Anzahl der Monster
     */
    private static int berechneMonsterPunkte(int anzahlMonster) {
        //1. Lebenspunkte des Helden * 0,75
        //2. Geteilt durch die Anzahl der Monster
        //3. Runde das Ergebnis
        //4. Caste es in einen int
        return (int) (Math.round((Spieler1.getLebenspunkte() * 0.75F) / anzahlMonster));
    }


    /**
     * Methode, welche in Abhängigkeit der Zufallszahl würfeln, im Switch
     * Case eine Monsterart auswählt
     */
    private static Monster.Monsterarten berechneArt(int würfeln) {
        Monster.Monsterarten art;
        switch (würfeln) {
            case 1:
                art = Monster.Monsterarten.Goblin;
                break;
            case 2:
                art = Monster.Monsterarten.Zombie;
                break;
            case 3:
                art = Monster.Monsterarten.Skelett;
                break;
            case 4:
                art = Monster.Monsterarten.Harpyie;
                break;
            case 5:
                art = Monster.Monsterarten.Ork;
                break;
            case 6:
                art = Monster.Monsterarten.Troll;
                break;
            default:
                art = Monster.Monsterarten.Goblin;
        }
        return art;
    }

    /**
     * Methode, welche eine Zufallszahl zwischen 0 und 1 * augen + 1 zurückliefert.
     * In Abängigkeit von augen wird noch bestimmt wie oft eine Zufallszahl erzeugt werden soll.
     * "augen"-Größen größer 1 werden addiert
     */
    private static int würfeln(int anzahlWuerfel, int augen) {
        int ergebnis = 0;
        for (int wuerfel = 1; wuerfel <= anzahlWuerfel; wuerfel++) {
            ergebnis += (int) (Math.random() * augen + 1);
        }
        return ergebnis;
    }

    /**
     * Methode, welche eine Geschichte aus gibt. Dynamisch platziert wird der Spieler1 Name.
     */
    private static void ausgabeGeschichte() {
        System.out.println("\n\n\n");
        System.out.println(Spieler1.getName() + ", dir wurde eine sehr wichtige Aufgabe übertragen.");
        System.out.println("Du sollst den einen Ring nach Bree zum Gasthaus zum tänzelnden Pony bringen.");
        System.out.println("Doch vorsicht der Weg ist lang und es lauern viele Gefahren");
        System.out.println("Nun mach dich auf den Weg!");
    }


    /**
     * Methode, welche die Klassenwahl realisiert.
     */
    public static void klassenwahl() {
        String eingabeString = "";
        int eingabe = 0;
        boolean check = false;
        String name;
        System.out.println("Willkommen zum Der Herr der Ringe-Rollenspiel");
        System.out.println("Bitte geben Sie als erstes einen Namen für Ihren Helden ein:");
        name = in.nextLine();
        System.out.println("Seid gegrüßt " + name + "! Bitte wählen als nächstes eine Klasse aus");
        System.out.println("Durch Eingabe einer Zahl können Sie eine Klasse wählen");
        System.out.println("1. Zauberer");
        System.out.println("2. Krieger");
        System.out.println("3. Schuetze");
        //Schleife durchläuft die Eingabe des Benuters, solange wie die Eingabe korrekt ist.
        //Korrekte Eingaben sind 1 und 2 und 4
        do {
            eingabeString = in.nextLine();
            if (eingabeString.matches("1|2|3")) { //Regular-Expression
                check = true;
            } else {
                System.out.println("Keine gültige Eingabe. Bitte wiederholen Sie Ihre Eingabe");
            }
        } while (!check);
        eingabe = Integer.parseInt(eingabeString);

        // Das Switch Case setzt je nach Eingabe des Benutzers eine Klasse.
        switch (eingabe) {
            case 1:
                Spieler1 = new Held(name, Held.Klassen.Zauberer);
                break;
            case 2:
                Spieler1 = new Held(name, Held.Klassen.Krieger);
                break;
            case 3:
                Spieler1 = new Held(name, Held.Klassen.Schuetze);
                break;
        }
    }

}