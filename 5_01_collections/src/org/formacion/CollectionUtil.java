package org.formacion;

import java.util.List;
import java.util.Map;

public class CollectionUtil {

	/**
	 * Modifica la lista orignial de personas eliminando las personas que 
	 * tengan un nombre con un espacio en blanco 
	 */
	public void eliminaNombresConEspacios(List<Persona> original) {
		
		original.removeIf( person -> person.getNombre().contains(" "));
	}
	
	
	/**
	 * Modifica la lista con pasando todos los nombres a mayúscula (nombre, primer y segundo apellido)
	 * Id con cuidado con el segundo apellido, que es optional!
	 */
	public void pasarAMayusculas (List<Persona> original) {
	
		original.replaceAll( person -> new Persona( person.getNombre().toUpperCase(), 
				 								    person.getApellido1().toUpperCase(), 
				                                    person.getApellido2().map( ape2 -> ape2.toUpperCase() ) ));
	}
	
	
	/**
	 * Devuelve una cadena con el contenido del map
	 * si map es
	 * [ "prod1", 4
	 *   "prod2", 5 ]
	 * El metodo debe devolver "prod1:4,prod2:5," 
	 */
	public String aTexto (Map<String, Integer> factura) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		factura.forEach( (key, value) -> stringBuilder.append(key).append(":").append(value).append(",") );
		
		return stringBuilder.toString();
	}
}
