/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Programm zum Verwalten eines Helden.
 */
class Held {
    // Eigenschaften des Helden:
    private String Name;
    private Klassen Klasse;
    private int Erfahrungspunkte = 0;
    private int Lebenspunkte = 100;
    private int Stufe = 1;

    //Enum für die Klassen
    public enum Klassen {
        Zauberer, Krieger, Schuetze
    }

    // Konstruktor der Klasse
    Held(String name, Klassen klasse) {
        Name = name;
        Klasse = klasse;
    }

    //Getter für Lebenspunkte
    public int getLebenspunkte() {
        // gibt die Lebenspunkte des Helden wieder
        return Lebenspunkte;
    }

    //Getter für Erfahrung
    public int getErfahrung() {
        // gibt die Erfahrungspunkte des Helden wieder
        return Erfahrungspunkte;
    }

    //Getter für Lebenspunkte
    public Klassen getKlasse() {
        // gibt die Klasse des Helden zurück
        return Klasse;
    }

    //Getter für den Spielnamen
    public String getName() {
        // gibt den Namen des Spielers zurück
        return Name;
    }

    /**
     * Methode, welche den Angriff auf ein Monster  simuliert.
     */
    public String angriffMonster(Monster gegner) {
        int lebensPunkteBeginn = gegner.getLebenspunkte();
        int monsterSchaden=0;
        int erfahrungspunkte = gegner.createSchaden(berechneHeldSchaden(gegner));

        if (gegner.getLebenspunkte() > 0) {
            monsterSchaden = gegner.getKonter(this.Stufe);
            Lebenspunkte -= monsterSchaden;
        }

        if (Lebenspunkte > 0) {
            if (gegner.getLebenspunkte() > 0) {
                return "Du hast " + (lebensPunkteBeginn - gegner.getLebenspunkte()) + " Schaden dem Monster zugefügt und selbst " + monsterSchaden + " Schaden erlitten.";
            } else {
                Erfahrungspunkte += erfahrungspunkte;
                if (Erfahrungspunkte > Stufe * 20) {
                    this.Stufe++;
                    this.Lebenspunkte = 100;
                    assert this.Lebenspunkte == 100;
                    System.out.println("Harte Kämpfe zahlen sich aus! Du bist eine Stufe aufgestiegen! Du bist nun Stufe " + this.Stufe);
                }
                return "Du hast das Monster mit " + (lebensPunkteBeginn - gegner.getLebenspunkte()) + " Schaden niedergestreckt und erhälst " + erfahrungspunkte + " Erfahrungspunkte.";
            }
        } else {
            return "Du bist tot.";
        }

    }

    /**
     * Methode, welche den Schaden des Helden an einem Monster berechnet
     */
    private int berechneHeldSchaden(Monster gegner) {
        int heldenLevel = 1;
        int wuerfelAnzahl = 1;
        int wuerfel = 6;
        int verechnungsWert = 0;
        int obergrenze = 0;
        int schaden = 0;
        boolean ende = false;

        //Schleife durchläuft solange, bis ein passendes Heldenlevel gefunden wurde.
        // De Algorithmus berechnet in Abhängigkeit des derzeitigen Heldenlevels den Schaden
        while (!ende && heldenLevel <= 1000) {
            obergrenze = heldenLevel + 2;
            verechnungsWert = 0;

            //Innere Schleife durchläuft einen Stufenbereich, da Schadensklassen z.B. wie folgt aussehen:
            //Stufe 1-3,4-6,7-9 usw..
            while (heldenLevel <= obergrenze && !ende) {
                if (Stufe == heldenLevel) {
                    schaden = würfeln(wuerfelAnzahl, wuerfel);

                    // Runde das Ergebnis von pruefeSchwaechen und Caste es in einen int
                    schaden *= (int) (Math.round(pruefeSchwaechenUndStaerken(gegner.getArt())));
                    ende = true;
                }
                verechnungsWert += 2; // Verechnungswert für einen Würfel
                wuerfel += verechnungsWert; // Würfeln also immer 6,8,12 Augen hoch
                heldenLevel++; //in der Heldenklasse eins höher zählen
            }
            // Wurde das Heldenlevel in einer Heldenklasse nicht gefunden springe
            // in die nächste Würfelstufe um den Schaden zu erhöhen
            wuerfelAnzahl++;

        }
        return schaden;
    }

    /**
     * Methode, welche prüft ob der Schaden reduziert wird, oder seine volle Stärke beibehält
     */
    private double pruefeSchwaechenUndStaerken(Monster.Monsterarten art) {
        double schadensMultiplikator = 1.00; //Standard-Schadensmultiplikator

        // Switch durchläuft die Monster-Enums, je nach Treffer wird noch auf die Klasse geprüft
        switch (art) {
            case Goblin:
                if (this.getKlasse().equals(Klassen.Zauberer)) {
                    schadensMultiplikator = 0.50;
                }
                break;
            case Zombie:
                if (this.getKlasse().equals(Klassen.Krieger)) {
                    schadensMultiplikator = 0.50;
                } else if (this.getKlasse().equals(Klassen.Zauberer)) {
                    schadensMultiplikator = 0.75;
                }
                break;
            case Skelett:
                if (this.getKlasse().equals(Klassen.Krieger)) {
                    schadensMultiplikator = 0.75;
                }
                if (this.getKlasse().equals(Klassen.Schuetze)) {
                    schadensMultiplikator = 0.50;
                }
                break;
            case Harpyie:
                if (this.getKlasse().equals(Klassen.Krieger)) {
                    schadensMultiplikator = 0.50;
                } else if (this.getKlasse().equals(Klassen.Zauberer)) {
                    schadensMultiplikator = 0.75;
                }
                break;
            case Ork:
                if (this.getKlasse().equals(Klassen.Zauberer)) {
                    schadensMultiplikator = 0.50;
                } else if (this.getKlasse().equals(Klassen.Schuetze)) {
                    schadensMultiplikator = 0.75;
                }
                break;
            case Troll:
                if (this.getKlasse().equals(Klassen.Krieger) || this.getKlasse().equals(Klassen.Zauberer)) {
                    schadensMultiplikator = 0.75;
                } else if (this.getKlasse().equals(Klassen.Schuetze)) {
                    schadensMultiplikator = 0.50;
                }
                break;
        }
        return schadensMultiplikator;
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