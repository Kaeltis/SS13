/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Programm zum Verwalten eines Monsters.
 */

class Monster {
    private Monsterarten Art;
    private int Lebenspunkte;
    private int Erfahrungspunkte;

    //Enum für die Monster-Arten
    public enum Monsterarten {
        Goblin, Zombie, Skelett, Harpyie, Ork, Troll;
    }

    // Konstruktor für die Monster
    Monster(Monsterarten art, int lebenspunkte) {
        Art = art;
        Lebenspunkte = lebenspunkte;
        Erfahrungspunkte = 10;
    }

    //Getter für Lebenspunkte
    public int getLebenspunkte() {
        // gibt die Lebenspunkte des Helden wieder
        return Lebenspunkte;
    }

    //Setter für Lebenspunkte
    public void setLebenspunkte(int wert) {
        if (this.getLebenspunkte() - wert > 0) {
            this.Lebenspunkte = getLebenspunkte() - wert;
        } else {
            this.Lebenspunkte = 0;
        }
    }

    /**
     * Methode, welche den Schaden den das Monster bekommt übergeben bekommt.
     * Aufruf innerhalb ist dann ein Setter
     * Zurückgeliefert werden die Erfahrungspunkte
     */
    public int createSchaden(int schaden) {
        this.setLebenspunkte(schaden);
        return this.Erfahrungspunkte;
    }

    //Getter für Monster-Art
    public Monsterarten getArt() {
        // gibt die Art des Monsters zurück
        return Art;
    }

    /**
     * Methode, welche einen Konter von einem Monster ausführt.
     * Aufruf innerhalb ist eine neue Methode berechneMonsterSchaden,
     * welche die Stufe des Helden übergeben bekommt.
     */
    public int getKonter(int stufe) {
        return berechneMonsterSchaden(stufe);
    }

    /**
     * Methode, welche den Schaden des Monsters berechnet
     * Verarbeitung innerhalb ist ein Alogrithmus, welcher dynamisch,
     * je nach Heldenlevel 1-1000 den Schaden berechnet.
     */
    private int berechneMonsterSchaden(int stufe) {
        int heldenStufe = 1;
        int wuerfel = 6;
        int wuerfelAnzahl = 1;
        int rechner = 0;
        int verechnungsWert = 0;
        int obergrenze = 0;
        int schaden=0;
        boolean ende = false;

        //Schleife durchläuft solange, bis ein passendes Heldenlevel gefunden wurde.
        // Der Algorithmus berechnet in Abhängigkeit des derzeitigen Heldenlevels den Schaden
        while (!ende && heldenStufe < 1000) {
            obergrenze = heldenStufe + 2;
            verechnungsWert = 0;
            //Innere Schleife durchläuft einen Stufenbereich, da Schadensklassen z.B. wie folgt aussehen:
            //Stufe 1-3,4-6,7-9 usw..
            //Zusatz in einem Durchlauf einer Schleife die Summe von würfel * würfelanzahl kleiner wie rechner, wird
            // die Würfelanzahl erhöht
            while (heldenStufe <= obergrenze && !ende) {
                while ((wuerfel * wuerfelAnzahl) < rechner) {
                   wuerfelAnzahl++;
                }
                if (stufe == heldenStufe) {
                    schaden = würfeln(wuerfelAnzahl, wuerfel);
                    ende = true;
                }
                verechnungsWert += 2;
                wuerfel += verechnungsWert;
                heldenStufe++;
            }
        }
        return schaden;
    }

    /**
     * Methode, welche eine Zufallszahl zwischen dem Übergabewert augen und 1 zurückliefert.
     * In Abängigkeit von augen wird noch bestimmt wie oft eine Zufallszahl erzeugt werden soll.
     * "augen"-Größen größer 1 werden addiert
     */
    private int würfeln(int anzahlWuerfel, int augen) {
        int ergebnis = 0;
        //Schleife durchläuft die Würfelanzahl
        for (int wuerfel = 1; wuerfel <= anzahlWuerfel; wuerfel++) {
            //Generiere eine Zufallszahl zwischen 0 und 1, multipliziere es mit augen und rechne 1 drauf
            ergebnis += (int) (Math.random() * augen + 1);
        }
        return ergebnis;
    }
}