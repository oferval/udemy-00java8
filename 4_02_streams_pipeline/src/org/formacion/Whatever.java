package org.formacion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import org.hamcrest.core.StringEndsWith;

@FunctionalInterface
public interface Whatever<T>{
	
	int compare(T t1, T t2);
	
	
	default int myMethod_methodReferencesConst(String valueToTransform) {
				
		Function<String, Integer> conversor = Integer::new; 
		return conversor.apply(valueToTransform);

	}
	
	
	default int myMethod_methodReferencesValueOf (String valueToTransform) {
		
		Function<String, Integer> conversor = Integer::valueOf;
		return conversor.apply(valueToTransform);
	}
	
	
	default String getSaludo (Optional<Persona> person){	
				
		return person.map( p-> p.getNombre() )
					 .map( "Adios "::concat)//.map( name -> name.concat("Adios") )
					 .orElse( "Mr No Name" );
	}
	
	
	default String getSurname (Optional<Persona> person){	
			
		return person.flatMap( p-> p.getApellido2() )
					 .map( "Adios "::concat)//.map( name -> name.concat("Adios") )
					 .orElse( "Mr No Name" );
	}
		
}
