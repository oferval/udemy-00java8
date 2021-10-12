package org.formacion;

import java.util.Objects;

public class PersonUtil {
	
	
	public static boolean isNullSecondLastName(Persona person) {
			
		return Objects.nonNull(person.getApellido1());
	}

}
