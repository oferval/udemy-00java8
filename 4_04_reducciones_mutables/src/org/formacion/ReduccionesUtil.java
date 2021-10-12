package org.formacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Para las soluciones de estos ejercicios utiliza streams, las 
 * operaciones intermedias que necesites y una operación terminal
 * con una estructura mutante.
 */
public class ReduccionesUtil {

	/**
	 * Devuelve una coleccion con los Strings de la lista original con:
	 *  - los nulls eliminados del resultado
	 *  - el resto pasado a mayúsculas.
	 *  
	 *  Es decir, si pasamos
	 *  {"a",null,"b",null} debe devolver {"A","B"}
	 *  
	 */
	public Collection<String> obtenSinNulosYMayusculas(List<String> original) {
		
//ALT1:		return original.stream()
//							.filter( item -> Objects.nonNull(item) )
//							.map(String::toUpperCase)
//							.collect( Collectors.toCollection(ArrayList<String>::new) );
		
//ALT2: Mutable:
		Collection<String> result = new ArrayList<>();
		
		 original.stream()
				  .filter( item -> Objects.nonNull(item) )
				  .map( String::toUpperCase )
				  .forEach( item -> result.add(item) );
		 
		 return result;
							
	}

	/**
	 * Devolver un array de dos enteros. En la posicion 0 con 
	 * el numero de pares encontrado en la lista de numeros y en 
	 * la posicion 1 el numero de impares
	 * 
	 * Por ejemplo, 
	 * si numeros es { 0, 1, 2, 4 } debe devolver [3,1]
	 */
	public int[] paresImpares (List<Integer> numeros) {
		
		/* Ir acumulando en la posicion 0 el recuento de pares
		 * y en la posicion 1 el recuento de impares
		 */
		int[] acumular = {0,0};
		
		
//ALT1:
		//numeros.stream().forEach( num -> { if (num%2==0)  acumular[0]++; else  acumular[1]++;});
		
//ALT2: better:
		numeros.stream().peek( num -> System.out.println(num%2) )
                        .forEach( num -> acumular[num%2]++ );
		
		return acumular;	
	}
	
}
