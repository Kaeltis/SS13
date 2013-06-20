/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Durch Array realisierte Playlist
 */

package queue.playlist;

import java.io.*;
import java.util.Arrays;

/**
 * @author Herdt/Kaiser
 * 
 */
public class Playlist {
	final private static int DEFAULTLEN = 20;
	private String playlistName;
	private Musiktitel[] lieder;
	private int freeIndex = 0;

	/**
	 * Erstelle Queue basierend auf einem Array mit Standardl�nge
	 * 
	 */
	public Playlist() {
		lieder = new Musiktitel[DEFAULTLEN];
		playlistName = "Unbenannt";
	}

	/**
	 * Erstelle Queue basierend auf einem Array mit Standardl�nge
	 * 
	 * @param name
	 *            Der Name der Playlist
	 */
	public Playlist(String name) {
		lieder = new Musiktitel[DEFAULTLEN];
		playlistName = name;
	}

	/**
	 * Erstelle Queue basierend auf einem Array mit festgelegter L�nge
	 * 
	 * @param name
	 *            Der Name der Playlist
	 */
	public Playlist(String name, int groesse) {
		lieder = new Musiktitel[groesse];
		playlistName = name;
	}

	/**
	 * Emulation einer Play-Funktionalit�t: Erstes Element wird gel�scht
	 * 
	 */
	public void play() {

		// Entferne erstes Element falls vorhanden und sortiere Playlist neu
		if (freeIndex != 0) {
			lieder[0] = null;
			reAlignPlaylist();
			freeIndex--;
		}

	}

	/**
	 * �bergebener Musiktitel wird am Ende der Playlist hinzugef�gt
	 * 
	 * @param neuerMusiktitel
	 *            Der Musiktitel, der der Playlist hinzugef�gt werden soll.
	 */
	public void hinzufuegenAmEnde(Musiktitel neuerMusiktitel) {
		// Wenn die Playliste nicht voll ist, Lied am Ende anf�gen und Ende
		// weiter nach hinten verschieben.
		if (freeIndex < lieder.length) {
			lieder[freeIndex] = neuerMusiktitel;
			freeIndex++;
		}
	}

	/**
	 * �bergebener Musiktitel wird aus Playlist entfernt.
	 * 
	 * @param zuEntfernen
	 *            Der Musiktitel, der aus der Playlist entfernt werden soll.
	 */
	public void entferneMusiktitel(Musiktitel zuEntfernen) {
		int count = 0;
		boolean found = false;

		// Durchlaufe ganze Playlist, bis Titel gefunden wurde
		while (!found && count < freeIndex) {
			// L�sche Titel und sortiere Playliste neu
			if (lieder[count].equals(zuEntfernen)) {
				lieder[count] = null;
				reAlignPlaylist();
				freeIndex--;
				found = true;
			}
			count++;
		}
	}

	/**
	 * Alle in dieser Playlist enthaltenen Titel als Array zur�ckliefern
	 * 
	 * @return Ein Array, das so lang ist, wie die Playlist Titel enth�lt.
	 */
	public Musiktitel[] getMusiktitel() {
		return lieder;
	}

	/**
	 * Alle Inhalte der Playlist l�schen.
	 */
	public void leerePlaylist() {
		Arrays.fill(lieder, null);
	}

	/**
	 * �bergebene Datei wird zeilenweise eingelesen. Die Datei muss
	 * folgenderma�en aufgebaut sein: Titel [Tab] K�nstler [neue Zeile]
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void leseAusDatei(File file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String zeile;
		String[] split;
		Musiktitel lied;

		// Datei Zeile f�r Zeile durchlaufen und neue Musiktitelobjekte anlegen
		while ((zeile = in.readLine()) != null) {
			split = zeile.split("\t");
			lied = new Musiktitel(split[0], split[1]);
			hinzufuegenAmEnde(lied);
		}

		in.close();
	}

	/**
	 * Der �bergebene Musiktitel wird in der Playlist eine Position weiter
	 * bewegt.
	 * 
	 * @param musiktitel
	 */
	public void bewegeTitelEinePositionWeiter(Musiktitel musiktitel) {
		Musiktitel temp;
		boolean found = false;
		int count = 0;

		// Durchlaufe Playlist von oben nach unten, bis Titel gefunden wurde
		while (!found && count < freeIndex) {
			if (lieder[count + 1] != null) {
				if (lieder[count].equals(musiktitel)) {
					// Titel mit dem darunter vertauschen
					temp = lieder[count + 1];
					lieder[count + 1] = lieder[count];
					lieder[count] = temp;
					found = true;
				}
			}
			count++;
		}
	}

	/**
	 * Der �bergebene Musiktitel wird in der Playlist eine Position zur�ck
	 * bewegt.
	 * 
	 * @param musiktitel
	 */
	public void bewegeTitelEinePositionZurueck(Musiktitel musiktitel) {
		Musiktitel temp;
		boolean found = false;
		int count = freeIndex - 1;

		// Durchlaufe Playlist von unten nach oben, bis Titel gefunden wurde
		while (!found && count > 0) {
			if (lieder[count - 1] != null) {
				if (lieder[count].equals(musiktitel)) {
					// Titel mit dem dar�ber vertauschen
					temp = lieder[count - 1];
					lieder[count - 1] = lieder[count];
					lieder[count] = temp;
					found = true;
				}
			}
			count--;
		}

	}

	/**
	 * Liefert den Namen dieser Playlist
	 * 
	 * @return Der Name dieser Playlist
	 */
	public String getName() {
		return playlistName;
	}

	/**
	 * Ver�ndert den Namen dieser Playlist
	 * 
	 * @param name
	 *            Der neue Name dieser Playlist
	 */
	public void setName(String name) {
		playlistName = name;
	}

	/**
	 * H�ngt eine neue Playlist an die Aktuelle, die L�nge der neuen Playlist
	 * ist die Kombinierte der beiden alten
	 * 
	 * @param anderePlaylist
	 */
	public void haengePlaylistAn(Playlist anderePlaylist) {
		// Lieder aus Playlist 1 und 2 zwischenspeichern.
		Musiktitel[] altelieder = getMusiktitel();
		Musiktitel[] neuelieder = anderePlaylist.getMusiktitel();
		int count = 0, arraycount = 0;

		lieder = new Musiktitel[altelieder.length + neuelieder.length];

		// Playlist 1 in neue Playlist kopieren
		while (arraycount < altelieder.length) {
			if (altelieder[arraycount] != null) {
				lieder[count] = altelieder[arraycount];
				count++;
			}
			arraycount++;
		}

		arraycount = 0;

		// Playlist 2 in neue Playlist kopieren
		while (arraycount < neuelieder.length) {
			if (neuelieder[arraycount] != null) {
				lieder[count] = neuelieder[arraycount];
				count++;
			}
			arraycount++;
		}

		// Freier Index nun bei count
		freeIndex = count;
	}

	/**
	 * Playlist neu aufbauen, schiebt alle Lieder zusammen, entfernt also leere
	 * Eintr�ge vom Anfang und aus der Mitte
	 */
	private void reAlignPlaylist() {
		int count = 0;
		int swapcount;

		// Playlist komplett durchlaufen
		while (count < lieder.length) {
			if (lieder[count] == null) {
				swapcount = count;
				// Jedes Element, welches auf ein Leeres folgt, eins nach vorne
				// schieben
				while (swapcount < lieder.length - 1) {
					lieder[swapcount] = lieder[swapcount + 1];
					swapcount++;
				}
			}
			count++;
		}
	}
}
