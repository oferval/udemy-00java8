package org.formacion;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class TestUtil {

    private PersonaUtil util;
    
    
   @Before
   public void init() {
	   
	   util = new PersonaUtil();
   }

    
   @Test
   public void test_apellido2_null() {
        
	   List<Persona> resultado = util.listaSinApellido2().collect(Collectors.toList());
       assertEquals(1, resultado.size());
       assertEquals("John", resultado.get(0).getNombre());
    }

   
    @Test
   public void test_obtener_nombres() {
        
    	List<String> resultado = util.listaNombres().collect(Collectors.toList());

        assertEquals(3, resultado.size());
        assertEquals("Antonia", resultado.get(0));
        assertEquals("John",resultado.get(1));
        assertEquals("Pedro", resultado.get(2));
    }


    @Test
    public void test_obtener_apellido1() {
        
    	List<String> resultado = util.listaApellido1Ordenada().collect(Collectors.toList());

        assertEquals(3,resultado.size());
        assertEquals("Garcia", resultado.get(0));
        assertEquals("Llull", resultado.get(1));
        assertEquals("smith", resultado.get(2));
    }
    
    
    @Test
    public void test_constructor_methodReferencesConst() {
    	
    	//Given
    	Whatever<String> we = (o1,o2) -> Integer.valueOf(o1) - Integer.valueOf(o2); 
    	//When
    	int valueTransformed = we.myMethod_methodReferencesConst("3");
    	
    	//Then
    	Assert.assertSame(3 , valueTransformed);
    }
    
    
    @SuppressWarnings("unused")
	@Test
    @Ignore
    public void NOtest_justToExecuteMyCode() {

    	Whatever<String> we = (o1,o2) -> Integer.valueOf(o1) - Integer.valueOf(o2); 

    	int valueTransformed = we.myMethod_methodReferencesValueOf("4");

    	Assert.assertSame(4 , valueTransformed);
    	
    	String greet= we.getSaludo(Optional.of(new Persona("Jaro", "", Optional.of(""))));
    	greet.getBytes();
    	
    	StringBuilder sbBuilder = new StringBuilder();
    	sbBuilder.append("");
    	
    	Stream.of( "pepe", "anton" )
    		   .map( String::toUpperCase  )
    		   .forEach( sbBuilder::append ); //String::toUpperCase
    	
    	String result = sbBuilder.toString()  ;
    	result.charAt(1);
    	
    	List<Integer> listInt = Arrays.asList(1,2,3,4,5,6);
    	
    	
    	Integer sumaInt = listInt.parallelStream()
    							 .peek(System.out::println)
    			                 .reduce(0, (int1, int2) -> int1 + int2);
    	//or:
    	sumaInt = listInt.parallelStream()
				 		 .peek(System.out::println)
				 		 .reduce(0, Integer::sum);
    	
    	
    	String sumaStr1 = listInt.parallelStream()
				                 .map(String::valueOf)
                                 .reduce("", String::concat);
    	
    	String sumaStr2 = listInt.parallelStream()
                                 .map(String::valueOf)
                                 .collect(Collectors.joining(","));
                                 
    	List<String> words = Arrays.asList("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
        Map<String, Integer> wordsCounter = new HashMap<>();
        
        //With merge
        words.forEach( word -> wordsCounter.merge(word, 1, (a,b) ->  a+b));
        
        //Without merge - with compute
        words.forEach( word -> {
        	wordsCounter.putIfAbsent(word, 1);
        	wordsCounter.computeIfPresent(word, (w,prev) -> prev+1);
        });
    	
      //Without merge (1)
        words.forEach( word -> {
        	wordsCounter.compute(word, (w ,prev) -> prev == null ? 1 : prev+1);
        }); 
        
    }
   
}
