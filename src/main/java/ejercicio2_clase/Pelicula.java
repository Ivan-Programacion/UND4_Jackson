package ejercicio2_clase;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pelicula {
	private String title;
	private ArrayList<String> characters;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getCharacters() {
		return characters;
	}

	public void setCharacters(ArrayList<String> characters) {
		this.characters = characters;
	}

	@Override
	public String toString() {
		return "Pelicula [title=" + title + ", characters=" + characters + "]";
	}

}
