package procesos1;

import java.io.IOException;

/**
 * Haz un programa que detecte si el bloc de notas se está ejecutando y en caso
 * afirmativo cree un proceso que lo elimine de la ejecución (matar el proceso).
 */
public class Ejercicio5 {
	public static void main(String[] args) {
		Procesos procesos = new Procesos();
		String nombreProceso = "Notepad.exe";
		try {
			procesos.matarSiEstaVivo(nombreProceso);
		} catch (IOException e) {
			System.out.println("Error IO ejecutando procesos: " + e.getMessage());
		}
	}
}
