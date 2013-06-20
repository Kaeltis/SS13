package queue.playlist;

public class PlaylistElement {

	PlaylistElement nextElement;
	Musiktitel value;

	// Konstruktor mit 2 Parametern
	PlaylistElement(Musiktitel value, PlaylistElement nextElement) {
		this.value = value;
		this.nextElement = nextElement;
	}

}
