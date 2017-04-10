/************************************************************/
Codigo curso: CSOF5101 - 2017.
Nombre curso: CONCEPTOS AVANZADOS EN INGENIERIA DE SOFTWARE.
Nombre de la tarea: Assignment Kit for Program 6.
Fecha de envío de la tarea: 09 Abril 2017 (20:00), 
		GithHub: 09 Abril 2017 (21:00:00), Heroku: 09 Abril 2017 (21:25)
Autor: Walter Javier Alonso Roa
/************************************************************/


¨** Descripcion:
Primero se debe colocar el archivo a procesar en la carpeta "ArchivoProcesar". (deben ser txt),
el codigo fuente se encuentra en el repo: https://github.com/WalterAlonso/2_PSP2.1.git

** Ambiente de ejecucion:
Este programa fue realizado con JRE 1.8 en S.O 7.
Debe tener instalado Maven


**************** Instrucciones de ejecucion ************************
** clone repo desde github.
$git clone https://github.com/WalterAlonso/2_PSP2.1.git

** Ingresar al folder 2_PSP2.1
  	cd 2_PSP2.1

** Ejecucion maven

	 mvn clean install site

** Ejecute el programa:

	java -cp target/Ecos_CalculoRangoFinal-1.0-SNAPSHOT.jar uniandes.ecos.conceptosAvanzados.calculoRangoFinal.controlador.CalculoRangoFinal "ArchivoCargaReglaSimpson.txt"

** Documentacion del sitio

		- Cobertura de pruebas unitarias: target/site/cobertura/index.html
		- Analisis de codigo (PMD): 	  target/site/pmd.html
		- Documentacion de codigo test: target/site/testapidocs/index.html
		- Documentacion de codigo de la aplicacion: target/site/apidocs/index.html

** Heroku:

	https://app-calculorangofinal.herokuapp.com/
