package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo;

/**
 * Modelo de lo que posee ReglaSimpson y se comparte entre capas de presentación y modelo.
 * @author WALONSO
 *
 */
public class ModelViewReglaSimpson {
	/**
	 * Rango final
	 */
	private double rangoFinal;
	
	/**
	 * Dof (degre of fredoom)
	 */
	private int dof;
	
	/**
	 * valor esperado
	 */
	private double valorEsperado;
	
	/**
	 * p, el valor de la regla Simpson.
	 */
	private double p;
	
	/**
	 * Tiene el rango final para calcular los segmentos.
	 * @return el rango final
	 */
	public double darRangoFinal() {
		return rangoFinal;
	}
	
	/**
	 * Asigna el rango final
	 * @param rangoFinal: el rango final calculado segun segmentos.
	 */
	public void asignarRangoFinal(double rangoFinal) {
		this.rangoFinal = rangoFinal;
	}
	
	/**
	 * Da el dof de la distribución T
	 * @return el dof de la distribucion T 
	 */
	public int darDof() {
		return dof;
	}
	
	/**
	 * Asigna el dof de la distribución T
	 * @param dof : segun la distribución T retorna el dof
	 */
	public void asignarDof(int dof) {
		this.dof = dof;
	}
	
	/**
	 * El valor esperado, segun el archivo.
	 * @return el valor esperado del archivo.
	 */
	public double darValorEsperado() {
		return this.valorEsperado;
	}
	
	/**
	 * Asigna el valor esperado.
	 * @param valorEsperado: el valor esperado del proceso.
	 */
	public void asignarValorEsperado(double valorEsperado) {
		this.valorEsperado = valorEsperado;
	}
	
	/**
	 * da el valor calculado del programa de la regla simpson
	 * @return el valor calculado del programa de la regla simpson
	 */
	public double darP() {
		return this.p;
	}
	
	/**
	 * Asigna el valor calculado de regla Simpson
	 * @param p : contiene el valor calculado del programa.
	 */
	public void asignarP(double p) {
		this.p = p;
	}
	
}
