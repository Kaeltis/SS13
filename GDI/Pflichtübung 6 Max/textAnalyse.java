public class textAnalyse {

    String text;


    public textAnalyse(String text) {
        this.text = text;
        this.text = text.replace("ä", "ae");
        this.text = text.replace("ö", "oe");
        this.text = text.replace("ü", "ue");
        this.text = text.replace("ä", "AE");
        this.text = text.replace("ö", "OE");
        this.text = text.replace("ü", "UE");
    }

    public int[] getAnalyseAmount() {
        int[] alphabet = new int[26];

        char[] buchstabenListe = text.toLowerCase().toCharArray();

        for (int i = 0; i < buchstabenListe.length; i++) {
            if ((buchstabenListe[i] >= 'a' && buchstabenListe[i] <= 'z')) {
                alphabet[buchstabenListe[i] - 'a']++;
            }
        }
        return alphabet;
    }

    public float[] getAnaylsePercents() {
        int laenge = text.length();
        int countSpaces = 0;
        for (int wortlänge = 0; wortlänge < text.length();wortlänge++){
            if((text.charAt(wortlänge))== ' '){
                  countSpaces++;
            }
        }
        float percentageFactor = 100F / (laenge-countSpaces);
        int[] amount = getAnalyseAmount();
        float[] percentage = new float[26];
        for (int alphabet = 0; alphabet < percentage.length; alphabet++) {
            percentage[alphabet] = amount[alphabet] * percentageFactor;
        }
        return percentage;
    }

    public String getCaesarChiffre(int setting) {
		// gibt den String als eine Verschiebechiffre zurück, dabei gibt setting
		// die Anzahl der Stellen an, um die verschoben wird.

		while (setting >= 26) {
			setting -= 26;
		}

		char[] textArray = Text.toCharArray();

		for (int i = 0; i < textArray.length; i++) {
			if ((textArray[i] >= 'A' && textArray[i] <= 'Z')
					|| (textArray[i] >= 'a' && textArray[i] <= 'z')) {
				switch (textArray[i]) {
				case 'Z':
					textArray[i] = (char) ('A' + setting);
					break;
				case 'z':
					textArray[i] = (char) ('a' + setting);
					break;
				default:
					textArray[i] += setting;
					break;
				}
			}
		}

		return new String(textArray);
	}

    public static String getUpperLower(String text, boolean setting) {
        String ergebnis = "";
        if (!setting) {
            return text.toLowerCase();
        } else {
            return text.toUpperCase();
        }
    }
}
