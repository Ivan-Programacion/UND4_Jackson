package ejercicio_inicio_prueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainFichero {

	public static void main(String[] args) {
		Scanner entrada;
		try {
			ObjectMapper mapper = new ObjectMapper();
			File fichero = new File("pruebaJSON.json");
			Disfraz disfraz;
			if (fichero.exists()) {
				// PARA LEER
				entrada = new Scanner(new FileInputStream(fichero));
				System.out.println("JSON lecutra:");
				disfraz = mapper.readValue(entrada.nextLine(), Disfraz.class);
				System.out.println(disfraz.getTalla() + " - " + disfraz.getTema() + " - " + disfraz.getSomb().getTipo()
						+ " - " + disfraz.getSomb().isAdornado());
				entrada.close();
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
