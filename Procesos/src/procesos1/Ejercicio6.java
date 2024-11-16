package procesos1;

import java.io.IOException;

/**
 * Programa que ejecuta un “.bat” previamente preparado y recoge la salida en un
 * archivo y los errores en otro.
 */
public class Ejercicio6 {
	
	private static final String RUTA_CARPETA = "src/procesos1/archivos/";
	private static final String RUTA_ERROR = RUTA_CARPETA + "ejercicio6_error.txt";
	private static final String RUTA_OUTPUT = RUTA_CARPETA + "ejercicio6_output.txt";
	private static final String RUTA_BAT = RUTA_CARPETA + "ejercicio6.bat";

	public static void main(String[] args) {
		Procesos procesos = new Procesos();
		try {
			procesos.ejecutarBat(RUTA_BAT, RUTA_ERROR, RUTA_OUTPUT);
		} catch (IOException e) {
			System.out.println("Error IO ejecutando procesos: " + e.getMessage());
		}
	}

}
