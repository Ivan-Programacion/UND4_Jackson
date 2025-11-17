package ejercicio_inicio_prueba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainFicheroLista {

	public static void main(String[] args) {
		Scanner entrada;
		List<Disfraz> lista;
		try {
			ObjectMapper mapper = new ObjectMapper();
			File fichero = new File("pruebaJSONLista.json");
			Disfraz disfraz;
			if (fichero.exists()) {
				// PARA LEER
				lista = new ArrayList<>();
				entrada = new Scanner(new FileInputStream(fichero));
				System.out.println("JSON lecutra:");
				lista = mapper.readValue(entrada.nextLine(), new TypeReference<List<Disfraz>>() {
				});
				for (Disfraz d : lista) {
					System.out.println(d.getTalla() + " - " + d.getTema() + " - " + d.getSomb().getTipo() + " - "
							+ d.getSomb().isAdornado());
				}
				entrada.close();
			}
			// PARA ESCRIBIR (se va a escribir [numDisfraces] objetos con los mismos
			// valores)
			lista = new ArrayList<>();
			int numDisfraces = 2;
			for (int i = 0; i < numDisfraces; i++) {
				disfraz = new Disfraz();
				disfraz.setTalla(100);
				disfraz.setTema("princesa");
				Sombrero sombrero = new Sombrero();
				sombrero.setTipo("Corona");
				sombrero.setAdornado(true);
				disfraz.setSomb(sombrero);
				lista.add(disfraz);
			}
			mapper.writeValue(fichero, lista);
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
