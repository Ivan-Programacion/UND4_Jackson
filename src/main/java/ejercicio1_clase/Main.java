package ejercicio1_clase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		File fichero = new File("ejercicio1JSON.json");
		if (fichero.exists()) {
			System.out.println("Encontrado fichero");
			System.out.println(); // Salto línea
		}
		try {
			ProgramaJSON miPrograma = new ProgramaJSON();
			miPrograma = mapper.readValue(fichero, ProgramaJSON.class);
			for (Usuario usuario : miPrograma.getResults()) {
				System.out.println(usuario.datos());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
