package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.test;

import junit.framework.TestCase;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.DistribucionT;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.ReglaSimpson;

/**
 * Esta se encarga de validar el correcto calculo de la regla simpson.
 * @author WALONSO
 *
 */
public class ReglaSimpsonTest extends TestCase {

	/**
	 * Valída el calculo de encontrar el valor de rango final
	 */
	public void testValidarCalculoRegla() {
		try {		
			DistribucionT distribucion = DistribucionT.getInstance();
			distribucion.iniciarValores();
			distribucion.asignarDof(15);			
			ReglaSimpson reglaSimpson = ReglaSimpson.getInstance();
			reglaSimpson.asignarErrorAceptable(0.00001);
			reglaSimpson.asignarNumeroSegmentos(20);
			reglaSimpson.asignarD(0.5);
						
			assertEquals( "El valor del rango final debe ser 1.75305", 1.75305, reglaSimpson.darCalculo(1.0, 0.45, distribucion), 0.00001);			
		}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
	}
	
	/**
	 * Valída el calculo de encontrar el valor de rango final
	 */
	public void testValidarCalculoReglaDos() {
		try {		
			DistribucionT distribucion = DistribucionT.getInstance();
			distribucion.iniciarValores();
			distribucion.asignarDof(6);
			ReglaSimpson reglaSimpson = ReglaSimpson.getInstance();
			reglaSimpson.asignarErrorAceptable(0.00001);
			reglaSimpson.asignarNumeroSegmentos(10);
			reglaSimpson.asignarD(0.5);
			
			assertEquals( "El valor del rango final debe ser 0.55338", 0.55338, reglaSimpson.darCalculo(1.0, 0.20, distribucion), 0.00001);			
		}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
	}
	
	/**
	 * Valída el calculo de encontrar el valor de rango final
	 */
	public void testValidarCalculoReglaTres() {
		try {		
			DistribucionT distribucion = DistribucionT.getInstance();
			distribucion.iniciarValores();
			distribucion.asignarDof(4);
			ReglaSimpson reglaSimpson = ReglaSimpson.getInstance();
			reglaSimpson.asignarErrorAceptable(0.00001);
			reglaSimpson.asignarNumeroSegmentos(10);
			reglaSimpson.asignarD(0.5);
						
			assertEquals( "El valor del rango final debe ser 4.60409", 4.60409, reglaSimpson.darCalculo(1.0, 0.495, distribucion), 0.00001);			
		}
    	catch(Exception ex) { 		
    		fail("Se genero excepcion "+ ex.getMessage());
    	}
	}
}

