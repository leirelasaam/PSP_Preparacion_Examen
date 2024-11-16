package procesos;

import java.io.IOException;

/**
 * Haz un programa que obtenga la dirección MAC y la muestre por pantalla
 */
public class Ejercicio3 {

	public static void main(String[] args) {
		Procesos procesos = new Procesos();
		String[] infoProceso = { "cmd", "/c", "getmac" };
		try {
			procesos.ejecutarProcesoRuntime(infoProceso, true, true, true);
		} catch (IOException e) {
			System.out.println("Error IO ejecutando procesos: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Error proceso interrumpudo: " + e.getMessage());
		}
	}

}
