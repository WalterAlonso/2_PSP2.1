package uniandes.ecos.conceptosAvanzados.calculoRangoFinal.modelo;

/**
 * Interface que representa los metodos de distribuci�n simetrica.
 * @author WALONSO
 *
 */
public interface IMetodoDistribucionSimetrica {
	
	/**
	 * Realiza el calculo propio del metodo de distibuci�n simetrica.
	 * @param segmento : el segmento que se esta validando
	 * @return el c�lculo del segmento que se esta procesando.
	 */
	public double darCalculo(double segmento);
	
	/**
	 * Realiza el reinicio de los valores los cuales eben cambiar por cada operacion de busqueda.	 
	 */
	public void iniciarValores();
}
