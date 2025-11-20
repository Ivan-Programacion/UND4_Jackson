package ejercicio2_clase;

import java.util.ArrayList;

public class StarWars {
	private Integer count;
	private String next; // "https://swapi.dev/api/people/?page=X" --> X = num pagina siguiente (de la 1
							// a la 9)
	private String previous;
	private ArrayList<Personaje> results;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public ArrayList<Personaje> getResults() {
		return results;
	}

	public void setResults(ArrayList<Personaje> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "StarWars [count=" + count + ", next=" + next + ", previous=" + previous + ", results=" + results + "]";
	}
}
