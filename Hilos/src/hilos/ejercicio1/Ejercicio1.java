package hilos.ejercicio1;

/**
 * Ejecutar dos hilos. Tienen que contar hasta 1000 y una vez lleguen deben
 * finalizar.
 */
public class Ejercicio1 {

	public static void main(String[] args) {
		Contador contador1 = new Contador("Contador 1");
		Contador contador2 = new Contador("Contador 2");

		contador1.start();
		contador2.start();
	}

}
