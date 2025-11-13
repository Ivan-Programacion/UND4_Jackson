package ejercicio_inicio_prueba;

public class Disfraz {
	private int talla;
	private String tema;
	private Sombrero somb;

	public Disfraz() {

	}

	public Disfraz(int i, String string) {
	}

	public int getTalla() {
		return talla;
	}

	public void setTalla(int talla) {
		this.talla = talla;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Sombrero getSomb() {
		return somb;
	}

	public void setSomb(Sombrero somb) {
		this.somb = somb;
	}

	@Override
	public String toString() {
		return "Disfraz [talla=" + talla + ", tema=" + tema + ", sombrero:"
				+ (somb == null ? "-" : somb.getTipo() + "-" + somb.isAdornado()) + "]";
	}

}
