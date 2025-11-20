package ejercicio2_clase;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) {
		String api = "https://swapi.dev/api/people/";
		ArrayList<Personaje> listaPersonajes = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(api)).build();
		HttpResponse<String> respuesta;
		try {
			respuesta = cliente.send(peticion, BodyHandlers.ofString());
			String json = respuesta.body();
			StarWars starWars = new StarWars();
			starWars = mapper.readValue(json, StarWars.class);
			System.out.println("PERSONAJES");
			// AÑADIRLO A LA LISTA
			for (int i = 0; i < starWars.getResults().size(); i++) {
				System.out.println((i + 1) + ". " + starWars.getResults().get(i).getName());
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
