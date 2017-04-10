package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo;

/**
 * Representa cada segmento partido en la regla Simpson, el cual realiza operaciones
 * pertinentes a cada segmento.
 * @author WALONSO
 *
 */
public class SegmentoSimpson {
	
	/**
	 * El valor del segmento.
	 */
	private double valorSegmento;
	
	/**
	 * El ancho del segmento.
	 */
	private double anchoSegmento;
	
	/**
	 * El factor de multiplicación.
	 */
	private int multiplicador;
	
	/**
	 * La función de distribución asociada, para calular el valor del segmento.
	 */
	private IMetodoDistribucionSimetrica metodoDistribucionSimetrica;
	
	/**
	 * el valor de la función, segun la función de distibución.
	 */
	private double valorFuncion; 
	
	/**
	 * Ctor del segmento en regla Simpson
	 * @param valorSegmento : el valor del segmento.
	 * @param anchoSegmento : el ancho del segmento
	 * @param multiplicador : el factor de multiplicacion.
	 * @param metodoDistribucionSimetrica : el metodo de distribución para el calculo.
	 */
	public SegmentoSimpson(double valorSegmento, double anchoSegmento, 
		int multiplicador, IMetodoDistribucionSimetrica metodoDistribucionSimetrica) {	
		this.valorSegmento = valorSegmento;
		this.anchoSegmento = anchoSegmento;
		this.multiplicador = multiplicador;
		this.metodoDistribucionSimetrica = metodoDistribucionSimetrica;
	}
	
	/**
	 * Da el cálculo de la distribucion.
	 */
	public void calcular() {
		double valorDistribucion = metodoDistribucionSimetrica.darCalculo(this.valorSegmento);
		this.valorFuncion = (this.anchoSegmento / 3.0) * this.multiplicador * valorDistribucion; 
	}
	
	/**
	 * Da el valor de la distribución.
	 * @return el valor de la distribución.
	 */
	public double darValorFuncion() {
		return this.valorFuncion;
	}
}
