package hilos.ejercicio2;

/**
 * Ejercicios:
 * 
 * 1. Crear una clase de nombre HiloRunnable. El programa principal lanzará dos
 * hilos (empleando la interface Runnable) y después ejecutará un bucle. Emplea
 * isAlive para chequear cuándo acaban los hilos
 * 
 * 2. Crear una clase de nombre HiloThread. El programa principal lanzará dos
 * hilos (heredando de la clase Thread) y después ejecutará un bucle. Emplea
 * isAlive para chequear cuándo acaban los hilos.
 * 
 * 3. Crea una clase ej3.Tenemos una aplicación a la que se conectan de manera
 * concurrente 2 personas (2 hilos). Cada una de estas personas debe realizar 3
 * operaciones. Suponemos que cada una de estas operaciones tardará 10
 * milisegundos en realizarse. Representaremos las 3 tareas que realizará cada
 * una de estas personas con el siguiente código:
 */
public class Ejercicio2 {

	public static void main(String[] args) {
		HiloRunnable runnable1 = new HiloRunnable();
		HiloRunnable runnable2 = new HiloRunnable();
		
		// A los hilos se les puede pasar un Runnable
		Thread hilo1 = new Thread(runnable1);
		Thread hilo2 = new Thread(runnable2);
		
		hilo1.setName("Persona 1");
		hilo2.setName("Persona 2");
		
		hilo1.start();
		hilo2.start();
		
		while(hilo1.isAlive() || hilo2.isAlive()) {
			System.out.println("> Los hilos no han terminado.");
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) {
				
			}
		}
		
		System.out.println("> Los hilos han terminado");
	}

}
