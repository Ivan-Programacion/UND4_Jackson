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
		// Añadimos la api
		String api = "https://swapi.dev/api/people/";
		// En esta lista guardaremos todos los personajes
		ArrayList<Personaje> listaPersonajes = new ArrayList<>();
		// mapper y conexiones
		ObjectMapper mapper = new ObjectMapper();
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest peticion = HttpRequest.newBuilder().uri(URI.create(api)).build();
		HttpResponse<String> respuesta;
		try {
			respuesta = cliente.send(peticion, BodyHandlers.ofString());
			// Guardamos el JSON que nos ha dado la api
			String json = respuesta.body();
			// Creamos objeto principal y trabajamos con él
			StarWars starWars = new StarWars();
			starWars = mapper.readValue(json, StarWars.class);
			System.out.println("PERSONAJES");
			// Un contador que se comparará con el count de starWars
			// count --> número de personajes totales
			// Cada instancia de starWars guarda 10 personajes
			int contador = 0;
			while (contador < starWars.getCount()) {
				for (Personaje personaje : starWars.getResults()) {
					listaPersonajes.add(personaje);
					contador++;
				}
				// Cuando ha incluido en la lista los 10 personajes, llamamos a la api "next"
				// para pasar a la siguiente pagina
				if (contador % 10 == 0) {
					peticion = HttpRequest.newBuilder().uri(URI.create(starWars.getNext())).build();
					respuesta = cliente.send(peticion, BodyHandlers.ofString());
					json = respuesta.body();
					starWars = mapper.readValue(json, StarWars.class);
				}
			}
			// Lista de personajes
			for (int i = 0; i < listaPersonajes.size(); i++) {
				System.out.println((i + 1) + ". " + listaPersonajes.get(i).getName());
			}
			// FIN PROGRAMA
			cliente.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
