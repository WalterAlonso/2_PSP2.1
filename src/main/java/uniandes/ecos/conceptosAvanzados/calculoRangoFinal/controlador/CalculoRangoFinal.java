/**
 * Propósito:     Calcular el rango final utilizando regla Simpson con distribución T de un conjúnto de datos dado.
 * Autor(s):   Walter Alonso
 * Fecha creación: 8 Abril 2017
 * Modificado por: Walter Alonso. 
 * Ultima modificación: 8 Abril 2017.
*/

package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.controlador;

import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.vista.VistaCalculoSimpson;

/**
 * Clase principal, al cual llega el usuario cuando inicia el programa.
 * @author WALONSO
 *
 */
public class CalculoRangoFinal {

	/**
	 * Recibe la ruta del archivo que se va a procesar.
	 * @param args : en la posición 0, llega el nombre del archivo a procesar.
	 */
	public static void main(String[] args) {
		VistaCalculoSimpson vista = new VistaCalculoSimpson();
		try
		{		
			if(args.length < 1) {
				throw new Exception("No tiene argumentos, debe ingresar la ruta del archivo");
			}
			
			Fachada fachada = new Fachada();			
			String rutaArchivo = "./ArchivoProcesar/" + args[0];
			fachada.procesarReglaSimpson(1.0,rutaArchivo, 0.00001, 10, 0.5);
			fachada.mostrarCalculos(vista);
		}
		catch(Exception ex) {				
			vista.mostrarError(ex.getMessage());
		}
	}
}
