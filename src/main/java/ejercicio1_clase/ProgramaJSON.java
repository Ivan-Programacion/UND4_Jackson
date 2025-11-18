package ejercicio1_clase;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProgramaJSON implements Serializable {
	private List<Usuario> results;
	@JsonIgnore
	private Info info; // por definir

	public List<Usuario> getResults() {
		return results;
	}

	public void setResults(List<Usuario> results) {
		this.results = results;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
}
