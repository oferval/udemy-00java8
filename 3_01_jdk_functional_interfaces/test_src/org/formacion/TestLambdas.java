package org.formacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;

import org.junit.Test;

public class TestLambdas {

	
	/**
	 * Modificad el test asignando a la variable cuadrado una expresion que produzca un
	 * IntUnaryOperator que, dado un entero, devuelva su cuadrado.
	 */
	@Test
	public void test_function() {
		
		IntUnaryOperator cuadrado = s -> s*s;
		
		assertEquals(0, cuadrado.applyAsInt(0));
		assertEquals(1, cuadrado.applyAsInt(1));
		assertEquals(4, cuadrado.applyAsInt(2));
		assertEquals(9, cuadrado.applyAsInt(3));
	}
	
	
	/**
	 * Igual que el anterior, sustituid el null asignado a menor por una expresion que 
	 * produzca un operador que devuelva el menor de dos numeros
	 */
	@Test
	public void test_funcion_2() {
		
		LongBinaryOperator menor = (n1, n2) -> (0+n1) < (0+n2) ? n1 : n2 ; 
		
		assertEquals(-2, menor.applyAsLong(-2, 3));
		assertEquals(5, menor.applyAsLong(10, 5));
		assertEquals(2, menor.applyAsLong(2, 3));
		assertEquals(2, menor.applyAsLong(3, 2));
		assertEquals(-2, menor.applyAsLong(-2, 3));
		assertEquals(-2, menor.applyAsLong(3, -2));
		assertEquals(-3, menor.applyAsLong(-2, -3));
		assertEquals(-3, menor.applyAsLong(-3, -2));
			
	}
	
	
	/**
	 * En los siguientes ejercicios debereis implementar tanto la declaracion de la 
	 * interface funcional como la lambda expression a assignar.
	 * Se utiliza el termino generico funcion, pero la opcion a usar puede ser cualquier tipo de 
	 * interface funcional del JDK
	 * Este ejercicio no tiene pruebas unitarias, ya que parte del ejercicio es determinar que tipo
	 * es el mas adecuado en cada caso y, sin la variable declarada con su tipo, es dificil hacer un test!
	 * Por este motivo, debereis probar vosotros mismos vuestros resultados. Para facilitaros las pruebas
	 * se crean tres personas con distintos apellidos.
	 */
	@Test
	public void test_extras() {
		
		//GIVEN
		// personas creadas para las pruebas
		Persona personaSinSegundoApellido = new Persona ("nombre","apellido1",null);
		Persona personaConSegundoApellido = new Persona ("nombre", "apellido1", "apellido2");
		Persona personaNoPariente = new Persona ("nombre","otro","otro");
		
		//WHEN
		// Cread una funcion que indique si el segundo apellido de una persona es null
		Predicate<Persona> secondNullLastNamePredicate = person -> Objects.isNull(person.getApellido2());
		//THEN
		assertTrue( secondNullLastNamePredicate.test(personaSinSegundoApellido) );
		assertFalse( secondNullLastNamePredicate.test(personaConSegundoApellido) );
		
		//WHEN
		// Una funcion que nos diga si dos personas son parientes: para nosotros parientes
		// son personas con el mismo primer apellido		
		BiPredicate<Persona, Persona> relatives = (person1, person2) -> person1.getApellido1().equals( person2.getApellido1() );
		//THEN
		assertTrue( relatives.test(personaSinSegundoApellido,personaConSegundoApellido) );
		assertFalse( relatives.test(personaConSegundoApellido,personaNoPariente) );
		
		// Una funcion que "enmascare" los datos de una persona: debe permutar los valores de sus
		// y nombre
		
		Consumer<Persona> personMask = person -> {
			String apellido1 = person.getApellido1();
			person.setApellido1( person.getApellido2() );
			person.setApellido2( person.getNombre() );
			person.setNombre( apellido1 );
		};
		
		personMask.accept(personaConSegundoApellido);
		
		assertEquals("apellido1", personaConSegundoApellido.getNombre());
		assertEquals("apellido2", personaConSegundoApellido.getApellido1());
		assertEquals("nombre", personaConSegundoApellido.getApellido2());
	}
	
	
	@Test
	public void test_validadores() {
		
		/**
		 * Modificad la clase validador conforme las instrucciones que encontrareis ahi
		 *      
		 * Hecho esto, descomentad el codigo que sigue (que, con vuestros cambios en Validador deberia 
		 * compilar excepto por el contenido en la invocacion al metodo add.
		 * Como parametro al metodo add debereis pasar una expresion que produzca el tipo de funcion que 
		 * hayais decidido que usa la clase Validador
		 */
		Validador<Persona> validador = new Validador<Persona>();

		/* pasar un predicado que mire si el primer apellido es null */
		validador.add( p -> PersonUtil.isNullSecondLastName(p));
		
		assertTrue(validador.valida(new Persona("nombre","ape1","ape2")));
		assertFalse(validador.valida(new Persona("nombre",null,"ape2")));
	}
	
	
}
