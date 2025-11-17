package ejercicio_inicio_prueba;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		Disfraz disfraz = new Disfraz(32, "pirata");
		disfraz.setSomb(new Sombrero("calavera", true));
		System.out.println(disfraz);
		String json;

		try {
			// om.writeValue(System.out, d); // cierra System.out
			System.out.println(mapper.writeValueAsString(disfraz));
		} catch (IOException e) {
			e.printStackTrace();
		}

		disfraz = new Disfraz(38, "payaso");
		json = "{\"talla\":1,\"tema\":\"marinero\",\"somb\":{\"tipo\":\"gorra\",\"adornado\":false}}";
//		json = "{\"talla\":3,\"tema\":\"bailarina\"}";
		System.out.println(disfraz + " -- " + json);

		try {
			// Le dices que el objeto lea el Json y lo construya en diisfraz
			disfraz = mapper.readValue(json, Disfraz.class); // disfraz.getClass());
			System.out.println(disfraz);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
