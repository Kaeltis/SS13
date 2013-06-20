/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Durch Liste realisierte Playlist
 */

package queue.playlist;

import java.io.*;

public class Playlist {

	/**
	 * Erstelle Queue basierend auf einer Liste
	 * 
	 */
	String name = "";
	PlaylistElement root;

	public Playlist() {
		root = null;
	}

	/**
	 * Erstelle Queue basierend auf einer Liste
	 * 
	 * @param name
	 *            Der Name der Playlist
	 */
	public Playlist(String name) {
		root = null;
		this.name = name;
	}

	/**
	 * Emulation einer Play-Funktionalität: Erstes Element wird gelöscht
	 */
	public void play() {
		if (root != null) {
			root = root.nextElement;
		}
	}

	/**
	 * †bergebener Musiktitel wird am Ende der Playlist hinzugefügt
	 * 
	 * @param neuerMusiktitel
	 *            Der Musiktitel, der der Playlist hinzugefügt werden soll.
	 */
	public void hinzufuegenAmEnde(Musiktitel neuerMusiktitel) {
		if (root != null) {
			PlaylistElement temp = root;
			while (temp.nextElement != null) {
				temp = temp.nextElement;
			}
			temp.nextElement = new PlaylistElement(neuerMusiktitel, null);
		} else {
			root = new PlaylistElement(neuerMusiktitel, null);
		}

	}

	/**
	 * Übergebener Musiktitel wird aus Playlist entfernt.
	 * 
	 * @param zuEntfernen
	 *            Der Musiktitel, der aus der Playlist entfernt werden soll.
	 */
	public void entferneMusiktitel(Musiktitel zuEntfernen) {
		PlaylistElement temp = root;
		PlaylistElement prev = root;
		if (root.value == zuEntfernen) {
			root = null;
		} else {
			while (temp.value != zuEntfernen) {
				prev = temp;
				temp = temp.nextElement;
			}
			prev.nextElement = temp.nextElement;
		}
	}

	/**
	 * Alle in dieser Playlist enthaltenen Titel als Array zurückliefern
	 * 
	 * @return Ein Array, das so lang ist, wie die Playlist Titel enthält.
	 */
	public Musiktitel[] getMusiktitel() {
		Musiktitel[] musikListe;
		PlaylistElement temp = root;
		if (root == null) {
			return null;
		}
		int counter = 0;
		while (temp != null) {
			temp = temp.nextElement;
			counter++;
		}
		temp = root;
		musikListe = new Musiktitel[counter];
		musikListe[0] = temp.value;
		int musiktitel = 0;

		while (temp != null) {
			musikListe[musiktitel] = temp.value;
			temp = temp.nextElement;
			musiktitel++;
		}
		return musikListe;
	}

	/**
	 * Alle Inhalte der Playlist löschen.
	 */
	public void leerePlaylist() {
		root = null;
	}

	/**
	 * Übergebene Datei wird zeilenweise eingelesen. Die Datei muss
	 * folgendermaßen aufgebaut sein: Titel [Tab] Künstler [neue Zeile]
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void leseAusDatei(File file) throws IOException {

		int stelleTabulator = 0;
		String titel = "";
		String kuenstler = "";
		FileReader eingabestrom = new FileReader(file);
		BufferedReader eingabe = new BufferedReader(eingabestrom);
		String eingabeZeile = eingabe.readLine();
		// Verarbeitungsschleife, solange nicht EOF
		while (eingabeZeile != null) {
			// Verarbeitung von eingabeZeile genau wie bisher über
			// die Tastatur (ggf. StringTokenizer, ...parse..() etc.)
			stelleTabulator = eingabeZeile.lastIndexOf("\t".toString());
			for (int buchstabeTitel = 0; buchstabeTitel < stelleTabulator; buchstabeTitel++) {
				titel += String.valueOf(eingabeZeile.charAt(buchstabeTitel));
			}
			for (int buchstabeKuenstler = stelleTabulator + 1; buchstabeKuenstler < eingabeZeile
					.length(); buchstabeKuenstler++) {
				kuenstler += String.valueOf(eingabeZeile
						.charAt(buchstabeKuenstler));
			}
			hinzufuegenAmEnde(new Musiktitel(kuenstler, titel));
			kuenstler = "";
			titel = "";
			eingabeZeile = eingabe.readLine();
		}
		eingabe.close(); // Datei schließen

	}

	/**
	 * Der übergebene Musiktitel wird in der Playlist eine Position weiter
	 * bewegt.
	 * 
	 * @param musiktitel
	 */
	public void bewegeTitelEinePositionWeiter(Musiktitel musiktitel) {
		PlaylistElement temp = root;
		PlaylistElement prev = root;
		PlaylistElement middle = root;
		PlaylistElement next = root;
		if (root != null) {
			if (middle.value != musiktitel) {
				while (middle.value != musiktitel) {
					prev = middle;
					middle = prev.nextElement;
					if (middle.nextElement != null) {
						next = middle.nextElement;
					} else {
						next = null;
					}
				}
				temp = middle;
				prev.nextElement = next;
				temp.nextElement = next.nextElement;
				next.nextElement = temp;
			}
			else{
				temp = root;
				root = root.nextElement;
				temp.nextElement = root.nextElement;
				root.nextElement = temp;
			}
		}
	}

	/**
	 * Der übergebene Musiktitel wird in der Playlist eine Position zurück
	 * bewegt.
	 * 
	 * @param musiktitel
	 */
	public void bewegeTitelEinePositionZurueck(Musiktitel musiktitel) {

		PlaylistElement temp = root;
		PlaylistElement prev = root;
		PlaylistElement middle = root;
		PlaylistElement next = root;
		if (root != null) {
			if (middle.nextElement.value != musiktitel) {
				while (middle.nextElement.value != musiktitel) {
					prev = middle;
					middle = prev.nextElement;
					if (middle.nextElement != null) {
						next = middle.nextElement;
					} else {
						next = null;
					}
				}
				System.out.println(prev.value);
				System.out.println(middle.value);
				System.out.println(next.value);
				temp = middle;
				prev.nextElement = next;
				temp.nextElement = next.nextElement;
				next.nextElement = temp;
			} else {
				temp = root;
				root = root.nextElement;
				temp.nextElement = root.nextElement;
				root.nextElement = temp;
			}
		}
	}

	/**
	 * Liefert den Namen dieser Playlist
	 * 
	 * @return Der Name dieser Playlist
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Verändert den Namen dieser Playlist
	 * 
	 * @param name
	 *            Der neue Name dieser Playlist
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Hängt eine andere Playlist an diese Playlist an.
	 */
	public void haengePlaylistAn(Playlist anderePlaylist) {

		PlaylistElement temp = root;

		if (temp == null) {
			root = anderePlaylist.root;
		} else {
			while (temp.nextElement != null) {
				temp = temp.nextElement;
			}
			temp.nextElement = anderePlaylist.root;
			System.out.println(anderePlaylist.root.value);
		}
	}
}
