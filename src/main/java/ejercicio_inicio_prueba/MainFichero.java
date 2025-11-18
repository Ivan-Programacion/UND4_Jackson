package ejercicio_inicio_prueba;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainFichero {

	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			File fichero = new File("pruebaJSON.json");
			Disfraz disfraz;
			if (fichero.exists()) {
				// PARA LEER
				System.out.println("JSON lecutra:");
				disfraz = mapper.readValue(fichero, Disfraz.class);
				System.out.println(disfraz.getTalla() + " - " + disfraz.getTema() + " - " + disfraz.getSomb().getTipo()
						+ " - " + disfraz.getSomb().isAdornado());
			}
			// PARA ESCRIBIR
			disfraz = new Disfraz();
			disfraz.setTalla(100);
			disfraz.setTema("princesa");
			Sombrero sombrero = new Sombrero();
			sombrero.setTipo("Corona");
			sombrero.setAdornado(true);
			disfraz.setSomb(sombrero);
			mapper.writeValue(fichero, disfraz);
			System.out.println("JSON creado o modificado");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
