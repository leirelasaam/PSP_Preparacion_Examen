package hilos.ejercicio6;

/**
 * ∙ Ejecutable
 * 
 * Definimos una clase que describe una aplicación. En la función main, creamos
 * tres objetos un objeto b de la clase Buffer, un objeto p de la clase
 * Productor y otro objeto c de la clase Consumidor.
 * 
 * Al constructor de las clases Productor y Consumidor le pasamos el objeto b
 * compartido de la clase Buffer.
 * 
 * Ponemos en marcha los subprocesos descritos por las clases Productor y
 * Consumidor, mediante la llamada a la función start de modo que el estado de
 * los subprocesos pasa de New Thread a Runnable. Los subprocesos una vez
 * puestos en marcha mueren de muerte natural, pasando al estado Death después
 * de ejecutar un bucle for de 10 iteraciones en la función run.
 * 
 * 
 */
public class Ejercicio6 {

	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		// Espera 1s para poner
		Productor productor = new Productor(buffer);
		// Espera 2s para recoger
		Consumidor consumidor = new Consumidor(buffer);
		
		// Inicializar antes consumidor, el buffer va a estar vacío
		consumidor.start();
		productor.start();
	}

}
