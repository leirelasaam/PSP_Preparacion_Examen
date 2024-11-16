package procesos;

import java.io.IOException;

/**
 * Ejecutar una aplicación de Windows. (Bloc de notas, Word, …)
 */
public class Ejercicio1 {
	
	public static void main(String[] args) {
		Procesos procesos = new Procesos();
		String[] infoProceso = {"Notepad"};
		try {
			procesos.ejecutarProcesoPB(infoProceso, false, false, false);
		} catch (IOException e) {
			System.out.println("Error IO ejecutando procesos: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Error proceso interrumpudo: " + e.getMessage());
		}
	}

}
