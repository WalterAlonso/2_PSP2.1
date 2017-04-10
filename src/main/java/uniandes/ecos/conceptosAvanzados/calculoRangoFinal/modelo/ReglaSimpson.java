package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo;

import java.util.ArrayList;

/**
 * Clase que se encarga de realizar las operaciones referentes a la regla Simpson.
 * @author WALONSO
 *
 */
public class ReglaSimpson {
	
	/**
	 * Instancia única
	 */
	private static ReglaSimpson INSTANCE = null;
	
	/**
	 * Número de segmentos con el cual calcular la regla Simpson. 
	 */
	private int numeroSegmentos;
	
	/**
	 * El error aceptable configurado.
	 */
	private double errorAceptable;
	
	/**
	 * el valor de d, con el cual se incrementa o decrementa el rango final.
	 */
	private double d;
		
	/**
	 * Valor del signo actual y con el cual se compara para saber si se debe modificar el valor de d actual.
	 */
	private int valorSignoActual; 	
	
	/**
	 * Valída la diferencia entre los valores de valor calculado y el p actual, en busqueda del rango final. 
	 */
	private double toleranciaErrorIgualdad = 0.0000000001;
		 
	/**
	 * Ctor de la regla Simpson.
	 */
	private ReglaSimpson(){		
		this.valorSignoActual = 1;
	}

	/**
	 * créa la instancia.
	 */
	private synchronized static void createInstance() {
	    if (INSTANCE == null) { 
	        INSTANCE = new ReglaSimpson();
	    }
	}
	
	/**
	 * Devuelve instancia única de Regla Simpson.
	 * @return la instáncia de Regla Simpson.
	 */
	public static ReglaSimpson getInstance() {
	    if (INSTANCE == null) {
	    	createInstance();
	    }
	    return INSTANCE;
	}
	
	/**
	 * Asigna el vaor del error aceptable.
	 * @param errorAceptable : valor del error aceptable.
	 */
	public void asignarErrorAceptable(double errorAceptable) {
		this.errorAceptable = errorAceptable;
	}
	
	/**
	 * Asigna el valor de numeros de segmentos
	 * @param numeroSegmentos : número de segmentos el cual partir 
	 */
	public void asignarNumeroSegmentos(int numeroSegmentos) {
		this.numeroSegmentos = numeroSegmentos;
	}
	
	/**
	 * Se asigna el valor de d, con el cual se realiza el cálculo para econtrar aproximación de P con el valor caculado.
	 * @param d : valor de inicio de d
	 */
	public void asignarD(double d) {
		this.d = d;
	}
	
	/**
	 * devuelve el ancho de los segmentos 
	 * @param rangoFinal : el rango final de los segmentos a analizar.
	 * @return el ancho de los segmentos.
	 */
	private double  darAnchoSegmento(double rangoFinal) {
		return rangoFinal / this.numeroSegmentos;
	}
	
	/**
	 * Realiza el cálculo de obtener el calculo del rango final.
	 * @param rangoFinal : valor del rango final para el metodo Simpson.
	 * @param valorP: el valor de la regla esperado, con el cual se va a comparar.
	 * @param metodoDistribucionSimetrica: metodo de la funcion simetrica.
	 * @return el calculo del rango final.
	 * @throws Exception para dar manejo al valor 0 en rango final.
	 */
	public double darCalculo(double rangoFinal, double valorP, IMetodoDistribucionSimetrica metodoDistribucionSimetrica) throws Exception {	
		
		double valorSimpson = 0;
		int signoCalculado = 0;
		boolean encontradoValor = false;
		int iteracionesGlobal=0;
		int numeroSegmentosPivot = this.numeroSegmentos;
		double rangoFinalEncontrado = 0;
		while(!encontradoValor) {
			
			iteracionesGlobal +=1;			
			this.numeroSegmentos = numeroSegmentosPivot;
						
			valorSimpson = darValorCalculoReglaSimpson(rangoFinal, metodoDistribucionSimetrica);
			
			if (iteracionesGlobal > 1) {				
				encontradoValor = esErrorAceptable(valorSimpson, valorP);
				if (encontradoValor) {				
					rangoFinalEncontrado = rangoFinal;
				}
			}		
			
			double diferencia = valorSimpson - valorP;
			if(iteracionesGlobal == 1) {				
				if (diferencia < 0) {
					this.valorSignoActual = -1;
				}
				else {
					this.valorSignoActual = 1;
				}
			} 
			else {
				if (diferencia < 0) {
					signoCalculado = -1;
				}
				else {
					signoCalculado = 1;
				}				
				this.cambiarD(signoCalculado);	
			}
			
			rangoFinal = this.calcularRangoFinal(valorSimpson, valorP, rangoFinal);	
		}
		return rangoFinalEncontrado;		
	}
	
	/**
	 * Da el valor del cálculo de regla simpson.
	 * @param rangoFinal : el rango final inicial.
	 * @param metodoDistribucionSimetrica : la función de distribución asociada, en este caso Distribución t.
	 * @return el valor calculado de regla simpson. 
	 */
	private double darValorCalculoReglaSimpson(double rangoFinal, IMetodoDistribucionSimetrica metodoDistribucionSimetrica) {
		boolean esAceptableElError = false;
		int iteraciones = 0;
		double valorCalculoAnterior = 0;
		double valorSimpson = 0;
		while (!esAceptableElError) {
			iteraciones += 1;
			double valorCalculoActual = 0;			
			ArrayList<SegmentoSimpson> segmentos = darSegmentosSimpson(rangoFinal, metodoDistribucionSimetrica);
			for (int indice = 0; indice < segmentos.size(); indice ++) {
				valorCalculoActual += segmentos.get(indice).darValorFuncion();
			}			
			
			if (iteraciones > 1) {
				if (Math.abs(valorCalculoAnterior - valorCalculoActual) < this.errorAceptable) {
					valorSimpson = valorCalculoActual;
					esAceptableElError = true;
				}
			}
			this.numeroSegmentos *=2;	
			valorCalculoAnterior = valorCalculoActual;
		}
		return valorSimpson;
	}
	
	/**
	 * da los segmentos de la regla Simpson.
	 * @param rangoFinal : el rango final con el cual realiza la cantidad de segmentos (rangoFinal / numero segmentos)
	 * @param metodoDistribucionSimetrica : la funcion de distribución con la cual se realizara el cálculo.
	 * @return los segmentos calculados.
	 */
	private ArrayList<SegmentoSimpson> darSegmentosSimpson(double rangoFinal, IMetodoDistribucionSimetrica metodoDistribucionSimetrica) {
		double anchoSegmento = darAnchoSegmento(rangoFinal);
		double valorSegmento = 0;	
		metodoDistribucionSimetrica.iniciarValores();
		ArrayList<SegmentoSimpson> segmentos = new ArrayList<SegmentoSimpson>();
		for (int indice = 0; indice <= this.numeroSegmentos; indice ++) {
			int multiplicador = 4;
			if (indice == 0 || indice == this.numeroSegmentos) {
				multiplicador = 1;
			}
			else if (indice % 2 == 0) {
				multiplicador = 2;
			}
			
			SegmentoSimpson segmento = new SegmentoSimpson(valorSegmento, anchoSegmento, multiplicador, metodoDistribucionSimetrica);
			segmento.calcular();
			segmentos.add(segmento);			
			valorSegmento += anchoSegmento;
		}
		return segmentos;
	}	
	
	/**
	 * Valida si el error es aceptable entre la diferencia del valor calculado y el valor de P.
	 * @param valorPCalculado : el valor de la regla simpson calculado (para el actual rango final)
	 * @param valorPAnterior : el valor anterior que se calculo.
	 * @return true si el error es acepatble, false si no lo és.
	 */
	private boolean esErrorAceptable(double valorPCalculado, double valorPAnterior) {
		double valorDiferencia = valorPCalculado - valorPAnterior;
		if (Math.abs(valorDiferencia) <= this.toleranciaErrorIgualdad){
			return true;
		}
		return false;
	}	
	
	/**
	 * Realiza el cambio del valor de d (si cambia el signo realiza el cambio)
	 * @param signoCalculado : el signo calculado y con el cual compara para saber si cambio el signo de la
	 * diferencia en el valor calculado y el p buscado.
	 */
	private void cambiarD(int signoCalculado) {
		if (this.valorSignoActual != signoCalculado) {
			this.d = this.d/2.0;
			this.valorSignoActual = signoCalculado;
		}
	}
	
	/**
	 * Cálcula el rango final, teniendo en cuenta el cambio o no de d y que tan cerca esta del valor de p buscado.
	 * @param valorCalculoActual : el valor del calculo realizado.
	 * @param valorP : el valor buscado.
	 * @param rangoFinal : el rango final actual y el cual cambiará.
	 * @return el valor del rango final (x) cambiado.
	 */
	private double calcularRangoFinal(double valorCalculoActual, double valorP, double rangoFinal) {
		if (valorCalculoActual > valorP) {
			rangoFinal = rangoFinal - this.d;
		}
		else {
			rangoFinal = rangoFinal + this.d;
		}
		return rangoFinal;
		
	}
}