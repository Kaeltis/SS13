/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Durch Liste realisierte Playlist
 */

package queue.playlist;

import java.io.*;

public class Playlist {

    /**
     * Erstelle Queue basierend auf einer Liste
     */
    String name = "";
    PlaylistElement root;

    public Playlist() {
        root = null;
    }

    /**
     * Erstelle Queue basierend auf einer Liste
     *
     * @param name Der Name der Playlist
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
     * Übergebener Musiktitel wird am Ende der Playlist hinzugefügt
     *
     * @param neuerMusiktitel Der Musiktitel, der der Playlist hinzugefügt werden soll.
     */
    public void hinzufuegenAmEnde(Musiktitel neuerMusiktitel) {
        if (root != null) {
            PlaylistElement temp = root;
            //Schleife durchläuft die Listen-Elemente(Musikttitel), solgange bis das Ende erreicht ist
            while (temp.nextElement != null) {
                temp = temp.nextElement;
            }
            temp.nextElement = new PlaylistElement(neuerMusiktitel, null);
        } else {
            root = new PlaylistElement(neuerMusiktitel, null);
        }

    }

    /**
     * �bergebener Musiktitel wird aus Playlist entfernt.
     *
     * @param zuEntfernen Der Musiktitel, der aus der Playlist entfernt werden soll.
     */
    public void entferneMusiktitel(Musiktitel zuEntfernen) {
        PlaylistElement temp = root;
        PlaylistElement prev = root;
        if (root.value == zuEntfernen) {
            root = null;
        } else {
            //Schleife durchläuft die Listen-Elemente(Musikttitel), solganege bis das zu löschende Element gefunden wurde
            while (temp.value != zuEntfernen) {
                prev = temp;
                temp = temp.nextElement;
            }
            prev.nextElement = temp.nextElement;
        }
    }

    /**
     * Alle in dieser Playlist enthaltenen Titel als Array zur�ckliefern
     *
     * @return Ein Array, das so lang ist, wie die Playlist Titel enth�lt.
     */
    public Musiktitel[] getMusiktitel() {
        Musiktitel[] musikListe;
        PlaylistElement temp = root;
        if (root == null) {
            return null;
        }
        int counter = 0;
        //Schleife zählt die Einträge in der Liste
        while (temp != null) {
            temp = temp.nextElement;
            counter++;
        }
        temp = root;
        musikListe = new Musiktitel[counter];
        musikListe[0] = temp.value;
        int musiktitel = 0;

        //Schleife durchläuft die Liste und füllt ein Array
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
            //Lese Tabulatorstelle ein
            stelleTabulator = eingabeZeile.lastIndexOf("\t".toString());
            //Lese bis zum Tabulator ein --> Titel
            for (int buchstabeTitel = 0; buchstabeTitel < stelleTabulator; buchstabeTitel++) {
                //Buchstabe für Buchstabe wird der String gebaut
                titel += String.valueOf(eingabeZeile.charAt(buchstabeTitel));
            }
            //Lese alles nach dem Tabulator ein --> Künstler
            for (int buchstabeKuenstler = stelleTabulator + 1; buchstabeKuenstler < eingabeZeile
                    .length(); buchstabeKuenstler++) {
                //Buchstabe für Buchstabe wird der String gebaut
                kuenstler += String.valueOf(eingabeZeile
                        .charAt(buchstabeKuenstler));
            }
            //Erstelle einen Descriptor auf ein neues Musiktitel-Objekt, welches dann hinten an die Queue(Liste) angefügt wird
            hinzufuegenAmEnde(new Musiktitel(kuenstler, titel));
            kuenstler = "";
            titel = "";
            eingabeZeile = eingabe.readLine();
        }
        eingabe.close(); // Datei schlie�en

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

        // Im Prinzip muss man nur Referenzen vertauschen. Darauf achten , das beim kopieren Objektreferenzen ihre
        // Descriptoren nicht verlieren, da sonst der Garbage-Collector aktiv wird. Dafür kann man eine Temporäre Variable
        // des gleichen Typs benutzen um den Descriptor zwischen zu speichern
        if (root != null) {
            if (middle.value != musiktitel) {
                //Schleife läuft solange durch bis der Musiktitel welcher verschoben soll erreicht wird.
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
            } else {
                //Else behandelt wenn die oberen beiden Elemente der Liste(Queu) verschoben werden.
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
        // Im Prinzip muss man nur Referenzen vertauschen. Darauf achten , das beim kopieren Objektreferenzen ihre
        // Descriptoren nicht verlieren, da sonst der Garbage-Collector aktiv wird. Dafür kann man eine Temporäre Variable
        // des gleichen Typs benutzen um den Descriptor zwischen zu speichern
        if (root != null) {
            if (middle.nextElement.value != musiktitel) {
                //Schleife läuft solange durch bis der Musiktitel welcher verschoben soll erreicht wird.
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
                //Else behandelt wenn die oberen beiden Elemente der Liste(Queu) verschoben werden.
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
     * @param name Der neue Name dieser Playlist
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
            //Schleife durchläuft solange bis das Ende erreicht ist.
            // Notwendig, da wir an das Ende die neuen Elemente anfügen möchten
            while (temp.nextElement != null) {
                temp = temp.nextElement;
            }
            temp.nextElement = anderePlaylist.root;
        }
    }
}
