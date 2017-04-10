package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo;

import java.util.ArrayList;

/**
 * Representa el archivo de Regla Simpson.
 * @author WALONSO
 *
 */
public class ArchivoReglaSimpson extends Archivo {
	
	/**
	 * cantidad de columnas permitidas.
	 */
	private static int cantidadColumnas = 3;
	
	/**
	 * Ctor que inicializa el archivo de regla simpson con la ruta y nombre e indicando la cantidad de columnas permitidas.
	 * @param rutaArchivo: la ruta y nombre del archivo a cargar.
	 */
	public ArchivoReglaSimpson(String rutaArchivo) {
		super(rutaArchivo, cantidadColumnas);
	}
	
	/**
	 * Retorna los datos del archivo
	 * @return Retorna los datos del archivo
	 * @throws Exception : si no tiene el tipo de dato entero, decimal y si nos es mayor a 0 el dof y rango final. 
	 */
	public ArrayList<ModelViewReglaSimpson> darDatos() throws Exception {	
		ArrayList<ModelViewReglaSimpson> modelo = new ArrayList<ModelViewReglaSimpson>();		
		for (int indice = 0; indice < contenidoArchivo.size(); indice ++) {
			String linea = contenidoArchivo.get(indice);
			ModelViewReglaSimpson model = darModeloSimpson(linea);
			modelo.add(model);
		}
		return modelo;
	}
	
	/**
	 * devuelve el modelo Simpson de la caneda dada.
	 * @param linea : la linea que se está analizando.
	 * @return el modelo con lo datos obtenidos.
	 * @throws Exception si no cumple con los tipos de datos esperados.
	 */
	private ModelViewReglaSimpson darModeloSimpson(String linea) throws Exception {
		ModelViewReglaSimpson model = null;
		String[] divisionValores = linea.split(",");
		double p =  darValorReal(divisionValores[0].trim());
		int dof = darValorEntero(divisionValores[1].trim());
		double valorEsperado =  darValorReal(divisionValores[2].trim());
		
		if (this.esMayorCero(dof)){
			model = new ModelViewReglaSimpson();
			model.asignarP(p);
			model.asignarDof(dof);			
			model.asignarValorEsperado(valorEsperado);
		}
		return model;
	}
	
	/**
	 * Indica si el valor que tiene es numerico y entero.
	 * @param valor: el valor a ser validado.
	 * @return true si es valido, false si no lo es.
	 * @throws Exception si el valor no es numerico arroja excepción.
	 */
	public int darValorEntero(String valor) throws Exception {
		try {
			return Integer.parseInt(valor);
		}
		catch(Exception ex) {
			throw new Exception("Hay un valor que no es numero entero.");
		}
	}

	/**
	 * Indica si el valor que tiene es numerico y real.
	 * @param valor: el valor a ser validado.
	 * @return true si es valido, false si no lo es.
	 * @throws Exception si el valor no es numerico arroja excepción.
	 */
	public double darValorReal(String valor) throws Exception {
		try {
			return Double.parseDouble(valor);
		}
		catch(Exception ex) {
			throw new Exception("Hay un valor que no es numero real.");
		}
	}
	
	/**
	 * valida si el dato es mayor a cero.
	 * @param valor: el valor a ser validado.
	 * @return true si es maor a cero, false si no lo es.
	 * @throws Exception se genera si el valor no es mayor a cero.
	 */
	public boolean esMayorCero(double valor) throws Exception {
		if (valor <= 0) {
			throw new Exception("Hay valores menores o iguales a 0, los cuales deben ser mayores a 0 el dof.");
		}
		return true;
	}
	
	
}
