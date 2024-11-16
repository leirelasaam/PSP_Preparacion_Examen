package hilos.ejercicio3;

/**
 * Crea un programa que contenga dos clases, Principal y Escritora. La clase
 * Principal contiene el main, que instanciará dos hilos de tipo Escritora y
 * los ejecutará. Cada hilo hará lo siguiente (indefinidamente):
 * 
 * - Si se inicializa con un True, escribirá números del 1 al 30.
 * 
 * - Si se inicializa con un False, escribirá letras de la ‘a’ a la ‘z’
 * 
 * Comprueba que las salidas de ambos hilos salen mezcladas por la consola.
 */
public class Ejercicio3 {

	public static void main(String[] args) {
		Escritora esc1 = new Escritora("Números", true);
		Escritora esc2 = new Escritora("Letras", false);
		
		esc1.start();
		esc2.start();
	}

}
