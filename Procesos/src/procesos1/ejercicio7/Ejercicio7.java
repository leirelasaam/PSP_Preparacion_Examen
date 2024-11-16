package procesos1.ejercicio7;

import java.io.IOException;
import java.util.Scanner;

import procesos1.Procesos;

/**
 * Haz un programa que llame a otro que envíe una cadena de caracteres y muestre
 * el texto
 * 
 * Para ello, primero se creará un programa que llama a “EjemploLectura” que
 * recoge una cadena de caracteres y escribe el resultado. Una vez que el
 * programa funcione, probaremos que se ejecuta en el cmd con la instrucción
 * “java nombredelpaquete.EjemploLectura.
 * 
 * Después, crearemos otro programa que llame Ejer7. Este programa pedirá un
 * texto al usuario y lo enviará al programa EjemploLectura. Se recogerá la
 * salida y se mostrará por pantalla.
 */
public class Ejercicio7 {
	public static void main(String[] args) {
		// Pedir texto al usuario
		System.out.print("Introduce un texto: ");
		Scanner sc = new Scanner(System.in);
		String texto = sc.nextLine();
		sc.close();

		Procesos procesos = new Procesos();
		// -cp es classpath
		String[] infoProceso = { "java", "-cp", "src", "procesos1.ejercicio7.EjemploLectura", texto };
		try {
			procesos.ejecutarProcesoRuntime(infoProceso, true, true, true);
		} catch (IOException e) {
			System.out.println("Error IO ejecutando procesos: " + e.getMessage());
		} catch (InterruptedException e) {
			System.out.println("Error proceso interrumpudo: " + e.getMessage());
		}
	}
}
