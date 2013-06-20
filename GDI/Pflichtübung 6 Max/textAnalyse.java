/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Programm zum bearbeiten von Texten
 * Moegliche Operationen sind Vorkommen der Buchstaben als Ganzzahl und Prozent,
 * sowie das Verschlüsseln des Textes mit Caesar und das transformieren des Textes
 * in Groß - bzw. Kleinbuhstaben
 */
public class textAnalyse {

    String text;

    /**
     * Konstruktor
     */
    public textAnalyse(String text) {
        this.text = text;
        this.text = text.replace("ä", "ae");
        this.text = text.replace("ö", "oe");
        this.text = text.replace("ü", "ue");
        this.text = text.replace("Ä", "AE");
        this.text = text.replace("Ö", "OE");
        this.text = text.replace("Ü", "UE");
		this.text = text.replace("ß", "ss");
    }


    /**
     * Methode, welche das Vorkommen der einzelnen Buchstaben zählt
     */
    public int[] getAnalyseAmount() {

        int[] alphabet = new int[26];

        char[] buchstabenListe = text.toLowerCase().toCharArray();

        //Es werden alle Buchstaben des eingegebenen Wortes durchlaufen
        for (int buchstaben = 0; buchstaben < buchstabenListe.length; buchstaben++) {
            if ((buchstabenListe[buchstaben] >= 'a' && buchstabenListe[buchstaben] <= 'z')) {
                // Derzeit durchlaufender Wert wird vom Character a abgezogen, die Zahl >= 0 representiert
                //den Index des Arrays wo gezählt werden soll
                alphabet[buchstabenListe[buchstaben] - 'a']++;
            }
        }
        return alphabet;
    }


    /**
     * Methode, welche den prozentualen Anteil eines Buchstabens
     * in einem Text errechnet.
     */
    public float[] getAnaylsePercents() {

        int laenge = text.length();
        int countSpaces = 0; //Leertastenfilter

        //Schleife durchläuft den Text um eventuelle Leertasten zu ermitteln
        for (int buchstabeStelle = 0; buchstabeStelle < text.length(); buchstabeStelle++) {
            if ((text.charAt(buchstabeStelle)) == ' ') {
                countSpaces++;
            }
        }
        //Berechnng wie viel Prozent ein Buchstabe in Abhängikeit der Wortlänge hat(Ohne Leertasten).
        float percentageFactor = 100F / (laenge - countSpaces);

        //Hole von getAnalyseAmount die Anzahl jedes Buchatabens und speichere diese in einem int[] Array
        int[] amount = getAnalyseAmount();

        float[] percentage = new float[26];

        //Schleife durchläuft das Alphabet(a-z --> 0-25)
        for (int alphabet = 0; alphabet < percentage.length; alphabet++) {
            // An jeder Stelle wird der Eintrag reingeschrieben, welcher sich aus folgender Rechnung ergibt:
            // Buchstabenanzahl * prozentualer Faktor eines Buchstabens
            percentage[alphabet] = amount[alphabet] * percentageFactor;
        }
        return percentage;
    }

    /**
     * Methode, welche einen Text mit Casaer verschlüsselt.
     * Dabei gibt setting die Stellen der Verschiebung an
     */
    public String getCaesarChiffre(int setting) {


        //Wir müssen das Alphabet nur einmal durchlaufen. Teilen durch 26 --> Rest !
        setting %= 26;

        char[] textArray = text.toCharArray();

        //Schleife durchläuft dden Text und verschiebt an jeder Stelle um setting(Verschiebelänge)
        // Im Falle von z oder Z wird bei a bzw A weitergemacht.
        for (int buchstaben = 0; buchstaben < textArray.length; buchstaben++) {
            if ((textArray[buchstaben] >= 'A' && textArray[buchstaben] <= 'Z')
                    || (textArray[buchstaben] >= 'a' && textArray[buchstaben] <= 'z')) {
                // Handelt das Ende des Alphabets ab. Der Default-Wert ist das kleine
                // und große Alphabet ohne das z/Z(a-y,A-Y)
                switch (textArray[buchstaben]) {
                    case 'Z':
                        textArray[buchstaben] = (char) ('A' + setting);
                        break;
                    case 'z':
                        textArray[buchstaben] = (char) ('a' + setting);
                        break;
                    default:
                        textArray[buchstaben] += setting;
                        break;
                }
            }
        }
        assert new String(textArray).equals("bla");
        return new String(textArray);
    }

    /**
     * Methode, welche den String text in Abhängikeit von setting entweder als
     * Großbuchstaben zurückliefert oder Kleinbuchstaben
     */
    public static String getUpperLower(String text, boolean setting) {
       return (setting? text.toUpperCase():text.toLowerCase());
    }
}
