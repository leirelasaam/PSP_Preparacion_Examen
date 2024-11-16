package procesos;

import java.io.IOException;

/*
 * Ejecutar un comando de windows. (Dir, Taskmgr, …) y mostrar su resultado por pantalla.
 */
public class Ejercicio2 {

	public static void main(String[] args) {
		Procesos procesos = new Procesos();
		String[] infoProceso = { "cmd", "/c", "dir" };
		try {
			procesos.ejecutarProcesoRuntime(infoProceso, true, true, true);
		} catch (IOException e) {
			System.out.println("Error IO ejecutando procesos: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Error proceso interrumpudo: " + e.getMessage());
		}
	}

}
