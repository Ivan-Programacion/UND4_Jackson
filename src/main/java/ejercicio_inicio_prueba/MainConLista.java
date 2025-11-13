package ejercicio_inicio_prueba;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainConLista {
	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		// PROBANDO CON LISTA
		ArrayList<Disfraz> tienda = new ArrayList<>();
		tienda.add(new Disfraz(32, "pirata"));
		tienda.add(new Disfraz(38, "payaso"));
		// PROBANDO CON ARRAY
//		Disfraz[] tienda = new Disfraz[2];
//		tienda[0] = new Disfraz(32, "pirata");
//		tienda[1] = new Disfraz(38, "payaso");
		// Da lo mismo qué tipo de formato utilices para guardarlo que lo guardará como
		// un array en json
		try {
			// No añade los atributos indicados anteriormente en los parámetros de los
			// constructores porque ObjectMapper utiliza el constructor por defecto (vacío)
			System.out.println("Out3: " + mapper.writeValueAsString(tienda));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		// Generamos un string que simule un json de un array de dos objetos
		String json = "[{\"talla\":3,\"tema\":\"bailarina\"},{\"talla\":4,\"tema\":\"marinero\"}]";
		try {
			// Para leer un array de objetos en un json desde un array
			Disfraz[] paq1 = mapper.readValue(json, Disfraz[].class);
			for (Disfraz d : paq1)
				System.out.println("A-" + d);
			// Para leer un array de objetos en un json desde una lista
			List<Disfraz> paq2 = mapper.readValue(json, new TypeReference<List<Disfraz>>() {
			});
			for (Disfraz d : paq2)
				System.out.println("L-" + d);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// Para leer un Json con un Map
		try { // si no conozco la estructura del json
			String s = "{\"talla\":2,\"tema\":\"princesa\"}";
			Map<String, String> p2 = mapper.readValue(s, new TypeReference<Map<String, String>>() {
			});
			System.out.println("Leyendo Mapa:");
			for (Map.Entry<String, String> e : p2.entrySet())
				System.out.println(e.getKey() + "-:-" + e.getValue());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} // me muestra los atributos y sus valores
	}
}
