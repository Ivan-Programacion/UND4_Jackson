package ejercicio2_clase;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) {
		// Añadimos la api
		String api = "https://swapi.dev/api/people/";
		// En esta lista guardaremos todos los personajes
		ArrayList<Personaje> listaPersonajes = new ArrayList<>();
		ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
		ArrayList<Personaje> personajesPorPeliculas = new ArrayList<>();
		ArrayList<Vehiculos> listaVehiculos = new ArrayList<>();
		// Se crea una caché para que no tenga que llamar a la API todo el rato
		HashMap<String, Object> cache = new HashMap<>();
		// mapper y conexiones
		ObjectMapper mapper = new ObjectMapper();
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest peticion;
		HttpResponse<String> respuesta;
		Scanner sc = new Scanner(System.in);
		String json;
		StarWars starWars;
		try {
			int opcion = 0;
			while (opcion != -1) {
				if (!cache.containsKey(api)) {
					peticion = HttpRequest.newBuilder().uri(URI.create(api)).build();
					respuesta = cliente.send(peticion, BodyHandlers.ofString());
					// Guardamos el JSON que nos ha dado la api
					json = respuesta.body();
					// Creamos objeto principal y trabajamos con él
					starWars = new StarWars();
					starWars = mapper.readValue(json, StarWars.class);
					cache.put(api, starWars);
				} else {
					starWars = (StarWars) cache.get(api);
				}
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
						if (!cache.containsKey(starWars.getNext())) {
							String url = starWars.getNext();
							peticion = HttpRequest.newBuilder().uri(URI.create(url)).build();
							respuesta = cliente.send(peticion, BodyHandlers.ofString());
							json = respuesta.body();
							starWars = mapper.readValue(json, StarWars.class);
							cache.put(url, starWars);
						} else {
							String key = starWars.getNext();
							starWars = (StarWars) cache.get(key);
						}
					}
				}
				// Lista de personajes
				for (int i = 0; i < listaPersonajes.size(); i++) {
					System.out.println((i + 1) + ". " + listaPersonajes.get(i).getName());
				}
				System.out.print("Escribe el número identificativo del personaje que quieres ver: ");
				opcion = sc.nextInt(); // REALIZAR EXCEPCION
				sc.nextLine(); // salto de línea
				Personaje miPersonaje = listaPersonajes.get(opcion - 1);
				peticion = HttpRequest.newBuilder().uri(URI.create(miPersonaje.getHomeworld())).build();
				respuesta = cliente.send(peticion, BodyHandlers.ofString());
				json = respuesta.body();
				System.out.println(); // salto de línea
				Planeta miPlaneta = mapper.readValue(json, Planeta.class);
				System.out.println("-- Personaje " + miPersonaje.getName() + " seleccionado --");
				System.out.println("INFORMACIÓN DEL PERSONAJE:");
				System.out.println(miPersonaje.datos(miPlaneta));
				System.out.println(); // salto de línea
				System.out.println("PELÍCULAS:");
				for (String url : miPersonaje.getFilms()) {
					if (!cache.containsKey(url)) {
						peticion = HttpRequest.newBuilder().uri(URI.create(url)).build();
						respuesta = cliente.send(peticion, BodyHandlers.ofString());
						json = respuesta.body();
						Pelicula objeto = mapper.readValue(json, Pelicula.class);
						cache.put(url, objeto);
						listaPeliculas.add(objeto);
					} else
						listaPeliculas.add((Pelicula) cache.get(url));
				}
				for (Pelicula pelicula : listaPeliculas) {
					System.out.println("Película " + pelicula.getTitle());
					for (String url : pelicula.getCharacters()) {
						if (!cache.containsKey(url)) {
							peticion = HttpRequest.newBuilder().uri(URI.create(url)).build();
							respuesta = cliente.send(peticion, BodyHandlers.ofString());
							json = respuesta.body();
							Personaje objeto = mapper.readValue(json, Personaje.class);
							cache.put(url, objeto);
							personajesPorPeliculas.add(objeto);
						} else
							personajesPorPeliculas.add((Personaje) cache.get(url));
					}
					System.out.println("-- Personajes de " + pelicula.getTitle() + " --");
					for (Personaje personaje : personajesPorPeliculas) {
						System.out.println("- " + personaje.getName());
					}
					System.out.println(); // salto de línea
				}
				System.out.println("VEHÍCULOS");
				if (miPersonaje.getVehicles().isEmpty())
					System.out.println("(vacío)");
				else {
					for (String url : miPersonaje.getVehicles()) {
						if (!cache.containsKey(url)) {
							peticion = HttpRequest.newBuilder().uri(URI.create(url)).build();
							respuesta = cliente.send(peticion, BodyHandlers.ofString());
							json = respuesta.body();
							Vehiculos objeto = mapper.readValue(json, Vehiculos.class);
							cache.put(url, objeto);
							listaVehiculos.add(objeto);
						} else
							listaVehiculos.add((Vehiculos) cache.get(url));
					}

					for (Vehiculos vehiculo : listaVehiculos) {
						System.out.println("- " + vehiculo.getName());
					}
				}
				System.out.println("Seguir con el programa? (-1: SALIR | 0: SEGUIR)");
				opcion = sc.nextInt();
			}
			// FIN PROGRAMA
			sc.close();
			cliente.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
