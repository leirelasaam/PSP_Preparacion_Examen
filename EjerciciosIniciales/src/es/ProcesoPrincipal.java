package es;

import java.io.File;

public class ProcesoPrincipal {

	/*
	 * Este proceso va a arrancar el proceso secundario.
	 */
	public static void main(String[] args) {
		System.out.println("Ejecutamos el proceso primario...");

		try {
			ProcessBuilder builder;

			// Se indica el tipo de archivo y el paquete.clase
			builder = new ProcessBuilder("java", "es.ProcesoSecundario");
			// Directorio de trabajo
			builder.directory(new File("bin"));
			
			System.out.println("Ejecutamos el proceso secundario...");
			
			// Crea un proceso para ejecutar el main del proceso secundario
			Process process = builder.start();

			// Los procesos se van a comunicar mediante ficheros donde van a escribir y leer

			// Esperar a que termine el proceso secundario y cuando termine almacena el
			// valor en el int
			int valorRetorno = process.waitFor();

			if (valorRetorno == 0) {
				System.out.println("Proceso secundario finalizado con exito");
			} else {
				System.out.println("El proceso secundario ha fallado");
				System.out.println("Codigo de error: " + valorRetorno);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
