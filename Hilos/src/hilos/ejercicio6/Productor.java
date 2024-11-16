package hilos.ejercicio6;

import java.util.Random;

/**
 * ∙ Productor
 * 
 * La clase que describe el productor denominada Productor deriva de la clase
 * Thread y redefine la función run.
 * 
 * Tiene como atributo un objeto buffer de la clase Buffer
 * 
 * La función run ejecuta un bucle for, cuando se completa el bucle se alcanza
 * el final de run y el subproceso entra en el estado Death (muerto), y detiene
 * su ejecución En el bucle for:
 * 
 * 1- se genera una letra al azar
 * 
 * 2- se pone en el objeto buffer, llamando a la función poner de la clase
 * Buffer 3- se imprime
 * 
 * 4- se hace una pausa por un número determinado de milisegundos llamando a la
 * función sleep y pasándole el tiempo de pausa, durante este tiempo el
 * subproceso está en el estado Not Runnable
 */
public class Productor extends Thread {

	private Buffer buffer;
	
	public Productor(Buffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = (char) ('A' + random.nextInt(26));
			System.out.println("PRODUCTOR> Caracter añadido: " + c);
			buffer.poner(c);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
