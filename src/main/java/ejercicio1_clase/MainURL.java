package ejercicio1_clase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainURL {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		// Creamos cliente
		HttpClient cliente = HttpClient.newHttpClient();
		// Creamos petición a la api
		HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create("https://randomuser.me/api/?results=3")).build();
		try {
			// Recogemos la respuesta en modo string
			HttpResponse<String> respuesta = cliente.send(peticion, BodyHandlers.ofString());
			// El string lo guardamos para utilizarlo
			String json = respuesta.body();
			ProgramaJSON miPrograma = new ProgramaJSON();
			miPrograma = mapper.readValue(json, ProgramaJSON.class);
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
