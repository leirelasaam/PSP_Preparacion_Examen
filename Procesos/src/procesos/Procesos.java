package procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Procesos {

	protected Procesos() {

	}

	/**
	 * Ejecuta un proceso mediante la clase Runtime.
	 * 
	 * @param infoProceso   String[] que contiene la información del proceso a
	 *                      ejecutar.
	 * @param leerResultado True si se quiere leer el resultado.
	 * @param buffered      True si se leer por línea. False si se quiere leer por
	 *                      caracter.
	 * @param esperar       True si se quiere esperar a que el proceso termine antes
	 *                      de leer el resultado.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected void ejecutarProcesoRuntime(String[] infoProceso, boolean leerResultado, boolean buffered,
			boolean esperar) throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process proceso = r.exec(infoProceso);
		System.out.println("Ejecutando proceso: " + proceso.toString());
		if (leerResultado) {
			// Esperar hasta que se termine de ejecutar para leer el resultado
			if (esperar)
				proceso.waitFor();
			if (buffered)
				leerResultadoBuffered(proceso);
			else
				leerResultado(proceso);
		}
	}

	/**
	 * Ejecuta un proceso mediante la clase ProcessBuilder.
	 * 
	 * @param infoProceso   String[] que contiene la información del proceso a
	 *                      ejecutar.
	 * @param leerResultado True si se quiere leer el resultado.
	 * @param buffered      True si se leer por línea. False si se quiere leer por
	 *                      caracter.
	 * @param esperar       True si se quiere esperar a que el proceso termine antes
	 *                      de leer el resultado.
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected void ejecutarProcesoPB(String[] infoProceso, boolean leerResultado, boolean buffered, boolean esperar)
			throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(infoProceso);
		Process proceso = pb.start();
		System.out.println("Ejecutando proceso: " + proceso.toString());
		if (leerResultado) {
			// Esperar hasta que se termine de ejecutar para leer el resultado
			if (esperar)
				proceso.waitFor();
			if (buffered)
				leerResultadoBuffered(proceso);
			else
				leerResultado(proceso);
		}
	}

	/**
	 * Lee el resultado de un proceso mediante InputStream. Se lee caracter a
	 * caracter.
	 * 
	 * @param p Proceso del cual se quiere leer el resultado.
	 * @throws IOException
	 */
	private void leerResultado(Process p) throws IOException {
		// Leer el contenido de la consola
		InputStream is = p.getInputStream();
		int c;
		// El valor -1 indica fin del input, no hay nada más que leer
		while ((c = is.read()) != -1) {
			System.out.print((char) c);
		}

		is.close();
	}

	/**
	 * Lee el resultado de un proceso mediante BufferedReader. Se lee línea a línea.
	 * 
	 * @param p Proceso del cual se quiere leer el resultado.
	 * @throws IOException
	 */
	private void leerResultadoBuffered(Process p) throws IOException {
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String linea;
		// Se llega al final de la lectura si la línea es un valor nulo
		while ((linea = br.readLine()) != null) {
			System.out.println(linea);
		}

		br.close();
	}
}
