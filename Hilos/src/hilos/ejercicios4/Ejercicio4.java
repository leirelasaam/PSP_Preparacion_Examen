package hilos.ejercicios4;

/**
 * Crea un programa que contenga dos clases, Principal y DetonadorConRetardo. La
 * clase Principal contiene el main, que instanciará cuatro hilos de tipo
 * DetonadorConRetardo y los ejecutará. Cada hilo hará lo siguiente:
 * 
 * - Se le inicializa con un nombre y un valor numérico (contador).
 * 
 * - Cuando el hilo se ejecute, escribe su nombre y el contador. A continuación,
 * reduce el valor del contador en 1. Repite estas acciones hasta que el valor
 * de contador sea 0 - El completar su tarea, informa de que ha finalizado.
 * 
 * El hilo principal del programa (main) NO debe de finalizar antes que los
 * demás hilos.
 */
public class Ejercicio4 {
	public static void main(String[] args) {
		DetonadorConRetardo d1 = new DetonadorConRetardo("Detonador 1", 20);
		DetonadorConRetardo d2 = new DetonadorConRetardo("Detonador 2", 10);
		DetonadorConRetardo d3 = new DetonadorConRetardo("Detonador 3", 5);
		DetonadorConRetardo d4 = new DetonadorConRetardo("Detonador 4", 2);
		
		d1.start();
		d2.start();
		d3.start();
		d4.start();
		
		// Para que el mensaje final del main se ejecute una vez finalizados los hilos, se debe usar join()
		try {
			d1.join();
			d2.join();
			d3.join();
			d4.join();
		} catch (InterruptedException e) {
			
		}
		
		System.out.println("> Main ha terminado");
	}
}
