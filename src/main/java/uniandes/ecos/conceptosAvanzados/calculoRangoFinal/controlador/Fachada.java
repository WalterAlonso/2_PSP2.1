package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.ArchivoReglaSimpson;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.DistribucionT;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.ModelViewReglaSimpson;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo.ReglaSimpson;
import uniandes.ecos.conceptosAvanzados.calculoRangoFinal.vista.VistaCalculoSimpson;

/**
 * Utilizando el patrón fachada, se encargara de orquestar las clases desde la carga del archivo,
 * calcular la regla Simpson y mostrar los resultados, sean web o en consola. 
 * @author WALONSO
 *
 */
public class Fachada {
	
	private  ArrayList<ModelViewReglaSimpson> datosReglaSimpson;
	
	/**
	 * Procesa la regla Simpson
	 * @param rangoFinal : el rango final con el cual se inicia el calculo.
	 * @param rutaArchivo : ruta y nombre del archivo a procesar
	 * @param errorAceptable : el error aceptable
	 * @param numeroSegmentos : el número de segmentos a partir
	 * @param d : Da el número d inicial (para calcular el rango final)
	 * @throws Exception al cargar archivo se valida que no este vacio, cantidad columnas adecuadas.
	 */
	public void procesarReglaSimpson(double rangoFinal, String rutaArchivo, double errorAceptable,int numeroSegmentos, double d) throws Exception {		
		this.cargarDatosInicialesReglaSimpson(rutaArchivo);
		for (int indice = 0; indice < this.datosReglaSimpson.size(); indice++) {
			ModelViewReglaSimpson modelReglaSimpson = this.datosReglaSimpson.get(indice);
			modelReglaSimpson.asignarRangoFinal(rangoFinal);
			this.asignarValoresConfiguracionSimpson(errorAceptable, numeroSegmentos, d);
			this.asignarValoresConfiguracionFuncionSimetrica(modelReglaSimpson.darDof());			
			double rangFinal = this.darCalculoRangoFinal(modelReglaSimpson);
			modelReglaSimpson.asignarRangoFinal(rangFinal);			
		}
	}
	
	/**
	 * Se encarga de cargar y procesar el archivo segun regla Simpson.
	 * @param rutaArchivo : la ruta de ubicación del archivo.
	 * @throws Exception : Se válidan las reglas de datos del archivo.
	 */
	private void cargarDatosInicialesReglaSimpson(String rutaArchivo) throws Exception {
		ArchivoReglaSimpson archivoReglaSimpson = new ArchivoReglaSimpson(rutaArchivo);
		archivoReglaSimpson.procesarArchivo();
		datosReglaSimpson = archivoReglaSimpson.darDatos();		
	}
	
	/**
	 * Se asignan valores de iniciación para calcular Simpson.
	 * @param errorAceptable : indica el valor de error aceptable.
	 * @param numeroSegmentos : el número de segmentos a partir el rango y calcular su área.
	 * @param d : el valor d incremental o decreciente segun cálculo para el rango final.
	 */
	private void asignarValoresConfiguracionSimpson(double errorAceptable, int numeroSegmentos, double d) {
		ReglaSimpson reglaSimpson = ReglaSimpson.getInstance();
		reglaSimpson.asignarErrorAceptable(errorAceptable);
		reglaSimpson.asignarNumeroSegmentos(numeroSegmentos);
		reglaSimpson.asignarD(d);
	}

	/**
	 * Asigna los valores de configuración de la distribución t
	 * @param dof : asigna los degree of fredoom a la distribución t. 
	 */
	private void asignarValoresConfiguracionFuncionSimetrica(int dof) {
		DistribucionT distribucion = DistribucionT.getInstance();
		distribucion.asignarDof(dof);
	}
	
	/**
	 * Cálcula el rango final que aplica para la regla simpson. 
	 * @param datosCalcular : el modélo con los dato a calcular
	 * @return el calculo del rango final.
	 * @throws Exception : con el vaor 0 de una integral se puede generar error.
	 */
	private double darCalculoRangoFinal(ModelViewReglaSimpson datosCalcular) throws Exception {
		ReglaSimpson reglaSimpson = ReglaSimpson.getInstance();
		return reglaSimpson.darCalculo(datosCalcular.darRangoFinal(), datosCalcular.darP(), DistribucionT.getInstance());
	}
	
	/**
	 * Muestra los cálculos de la regla
	 * @param vista : la clase que se encarga de renderizar la informacion.
	 */
	public void mostrarCalculos(VistaCalculoSimpson vista) {
		vista.asignarModeloSimpson(this.datosReglaSimpson);
		vista.mostrarCalculosReglaSimpson();
	}

	/**
	 * Muestra los cálculos web
	 * @return los datos y la vista de los datos que se van a desplegar.
	 */
	public ModelAndView mostrarCalculosWeb() {
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("datos", datosReglaSimpson);		
		return new ModelAndView(attributes, "reglaSimpson.ftl");
	}
}
