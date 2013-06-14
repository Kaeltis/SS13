public class textAnalyse {
	String Text;

	textAnalyse(String text) {
		Text = text;

		Text = Text.replace("�", "ae");
		Text = Text.replace("�", "oe");
		Text = Text.replace("�", "ue");
		Text = Text.replace("�", "AE");
		Text = Text.replace("�", "OE");
		Text = Text.replace("�", "UE");
	}

	public int[] getAnalyseAmount() {
		// gibt die Anzahl der einzelnen Buchstaben als Array zur�ck
		// die Form soll dabei a den Index 0, b den Index 1 usw haben.
		// Beispiel: Banane wird zu 0 => 2, 1 => 0, 2 => 0, 3 => 0, 4 => 1
		int[] zeichen = new int[26];

		char[] textArray = Text.toLowerCase().toCharArray();

		for (int i = 0; i < textArray.length; i++) {
			if ((textArray[i] >= 'a' && textArray[i] <= 'z')) {
				zeichen[textArray[i] - 'a']++;
			}
		}

		return zeichen;
	}

	public float[] getAnaylsePercents() {
		// gibt den Prozentsatz des Vorkommens der einzelnen Buchstaben zur�ck
		// in gleicher Form wie getAnalyseAmount()

		// TODO ausf�llen

		float[] zeichen = new float[26];

		char[] textArray = Text.toLowerCase().toCharArray();

		return zeichen;
	}

	public String getCaesarChiffre(int setting) {
		// gibt den String als eine Verschiebechiffre zur�ck, dabei gibt setting
		// die Anzahl der Stellen an, um die verschoben wird.

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

	public String getUpperLower(String text, boolean setting) {
		// gibt dein text in Gro�buchstaben bei setting = true und
		// Kleinbuchstaben
		// bei setting = false zur�ck. Leerzeichen werden ignoriert.
		if (setting)
			return Text.toUpperCase();
		else
			return Text.toLowerCase();
	}

}