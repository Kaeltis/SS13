import java.util.Random;

class Held {
    // Eigenschaften des Helden:
    private String Name;
    private Klassen Klasse;
    private int Erfahrungspunkte = 0;
    private int Lebenspunkte = 100;
    private int Stufe = 1;

    public enum Klassen {
        Zauberer, Krieger, Schuetze
    }

    Held(String name, Klassen klasse) {
        // Konstruktor der Klasse
        Name = name;
        Klasse = klasse;
    }

    public int getLebenspunkte() {
        // gibt die Lebenspunkte des Helden wieder
        return Lebenspunkte;
    }

    public int getErfahrung() {
        // gibt die Erfahrungspunkte des Helden wieder
        return Erfahrungspunkte;
    }

    public Klassen getKlasse() {
        // gibt die Klasse des Helden zurück
        return Klasse;
    }

    public String getName() {
        // gibt den Namen des Spielers zurück
        return Name;
    }

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

    private int berechneHeldSchaden(Monster gegner) {
        int heldenLevel = 1;
        int wuerfelAnzahl = 1;
        int wuerfel = 6;
        int verechnungsWert = 0;
        int obergrenze = 0;
        int schaden = 0;
        boolean ende = false;

        while (!ende && heldenLevel <= 1000) {
            obergrenze = heldenLevel + 2;
            verechnungsWert = 0;
            while (heldenLevel <= obergrenze && !ende) {
                if (Stufe == heldenLevel) {
                    schaden = würfeln(wuerfelAnzahl, wuerfel);
                    schaden *= (int) (Math.round(pruefeSchwaechenUndStaerken(gegner.getArt())));
                    ende = true;
                }
                verechnungsWert += 2;
                wuerfel += verechnungsWert;
                heldenLevel++;
            }
            wuerfelAnzahl++;
        }
        return schaden;
    }

    private double pruefeSchwaechenUndStaerken(Monster.Monsterarten art) {
        double schadensMultiplikator = 1.00;
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


    private int würfeln(int anzahlWuerfel, int augen) {
        int ergebnis = 0;
        for (int wuerfel = 1; wuerfel <= anzahlWuerfel; wuerfel++) {
            ergebnis += (int) (Math.random() * augen + 1);
        }
        return ergebnis;
    }
}