package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.ArchivoReglaSimpson;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.ModelViewReglaSimpson;

/**
 * Clase que se encarga de validar el archivo Simpson esperado.
 * @author WALONSO
 *
 */
public class ArchivoReglaSimpsonTest extends TestCase {
	
	/**
	 * Clase ArchivoEstimar testear
	 */
	private ArchivoReglaSimpson archivoProcesar;
	
	/**
	 * Constante donde estan los archivos a testear
	 */
	public static final String rutaArchivo = "./ArchivoProcesarTest/TestArchivoSimpson/";

	/**
     * Este metodo se encarga de verificar que se cargue bien cuando es clase
     * 
     */
    public void testCargarArchivo() {
    	try {
    		String archivo = rutaArchivo + "ArchivoCargaReglaSimpson.txt";
    		archivoProcesar = new ArchivoReglaSimpson(archivo);
    		archivoProcesar.procesarArchivo();
    		ArrayList<ModelViewReglaSimpson> datos = archivoProcesar.darDatos();
    		
    		assertEquals("Debe ser 0.20", 0.20, datos.get(0).darP());
    		assertEquals("Debe ser 6", 6, datos.get(0).darDof());
    		assertEquals("Debe ser 0.55338", 0.55338, datos.get(0).darValorEsperado());
    		
    		assertEquals("Debe ser 0.45", 0.45, datos.get(1).darP());
    		assertEquals("Debe ser 15", 15, datos.get(1).darDof());
    		assertEquals("Debe ser 1.75305", 1.75305, datos.get(1).darValorEsperado());
    		
    		assertEquals("Debe ser 0.495", 0.495, datos.get(2).darP());
    		assertEquals("Debe ser 4", 4, datos.get(2).darDof());
    		assertEquals("Debe ser 4.60409", 4.60409, datos.get(2).darValorEsperado());    		
    	}
    	catch(Exception ex) { 		
    		fail("Excepcion: " + ex.getMessage());
    	}
    }
    
    /**
     * Se valida que no tenga caracteres
     */
    public void testValidarSoloReales() {
    	try {
    		String archivo = rutaArchivo + "ArchivoConCaracteres.txt";
    		archivoProcesar = new ArchivoReglaSimpson(archivo);
    		archivoProcesar.procesarArchivo();
    		archivoProcesar.darDatos();
    		fail("Debio fallar: Hay un valor que no es numero real.");
    	}
    	catch(Exception ex) { 		
    		assertEquals( "El mensaje debe coincidir: Hay un valor que no es numero real.", "Hay un valor que no es numero real.", ex.getMessage());
    	}
    }
    
    /**
     * Se valida que no tenga caracteres
     */
    public void testValidarValoresMayoresACero() {
    	try {
    		String archivo = rutaArchivo + "ArchivoConDatoIgualCero.txt";
    		archivoProcesar = new ArchivoReglaSimpson(archivo);
    		archivoProcesar.procesarArchivo();
    		archivoProcesar.darDatos();
    		fail("Debio fallar: Hay valores menores o iguales a 0, los cuales deben ser mayores a 0 el dof.");
    	}
    	catch(Exception ex) { 		
    		assertEquals( "El mensaje debe coincidir: Hay valores menores o iguales a 0, los cuales deben ser mayores a 0 el dof.", "Hay valores menores o iguales a 0, los cuales deben ser mayores a 0 el dof.", ex.getMessage());
    	}
    }
    
    
}



