package procesos;

import java.io.IOException;

/**
 * Haz un programa que muestre los procesos en ejecución.
 */
public class Ejercicio4 {
	
	public static void main(String[] args) {
		Procesos procesos = new Procesos();
		String[] infoProceso = { "cmd", "/c", "tasklist" };
		try {
			// Si se espera a que termine el proceso no funciona. Por ello, el último parámetro es false.
			procesos.ejecutarProcesoRuntime(infoProceso, true, true, false);
		} catch (IOException e) {
			System.out.println("Error IO ejecutando procesos: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Error proceso interrumpudo: " + e.getMessage());
		}
	}

}
