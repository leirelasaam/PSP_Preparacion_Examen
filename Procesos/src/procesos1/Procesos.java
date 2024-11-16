package procesos1;

import java.io.*;

public class Procesos {

	public Procesos() {

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
	public void ejecutarProcesoRuntime(String[] infoProceso, boolean leerResultado, boolean buffered,
			boolean esperar) throws IOException, InterruptedException {
		Runtime r = Runtime.getRuntime();
		Process proceso = r.exec(infoProceso);
		System.out.println("Ejecutando proceso: " + proceso.toString());
		if (leerResultado) {
			// Esperar hasta que se termine de ejecutar para leer el resultado
			if (esperar)
				proceso.waitFor();
			if (buffered)
				System.out.println(leerResultadoBuffered(proceso));
			else
				System.out.println(leerResultado(proceso));
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
	public void ejecutarProcesoPB(String[] infoProceso, boolean leerResultado, boolean buffered, boolean esperar)
			throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(infoProceso);
		Process proceso = pb.start();
		System.out.println("Ejecutando proceso: " + proceso.toString());
		if (leerResultado) {
			// Esperar hasta que se termine de ejecutar para leer el resultado
			if (esperar)
				proceso.waitFor();
			if (buffered)
				System.out.println(leerResultadoBuffered(proceso));
			else
				System.out.println(leerResultado(proceso));
		}
	}

	/**
	 * Lee el resultado de un proceso mediante InputStream. Se lee caracter a
	 * caracter.
	 * 
	 * @param p Proceso del cual se quiere leer el resultado.
	 * @return Salida del proceso como String.
	 * @throws IOException
	 */
	private String leerResultado(Process p) throws IOException {
		// Leer el contenido de la consola
		InputStream is = p.getInputStream();
		int c;
		StringBuilder sb = new StringBuilder();
		// El valor -1 indica fin del input, no hay nada más que leer
		while ((c = is.read()) != -1) {
			sb.append((char) c);
		}

		is.close();

		return sb.toString();
	}

	/**
	 * Lee el resultado de un proceso mediante BufferedReader. Se lee línea a línea.
	 * 
	 * @param p Proceso del cual se quiere leer el resultado.
	 * @return Salida del proceso como String.
	 * @throws IOException
	 */
	private String leerResultadoBuffered(Process p) throws IOException {
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String linea;
		StringBuilder sb = new StringBuilder();
		// Se llega al final de la lectura si la línea es un valor nulo
		while ((linea = br.readLine()) != null) {
			sb.append(linea);
		}

		br.close();

		return sb.toString();
	}

	/**
	 * Ejecuta el comando tasklist para determinar si el proceso está en ejecución y
	 * en ese caso, lo mata.
	 * 
	 * @param nombreProceso Nombre del proceso a matar.
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void matarSiEstaVivo(String nombreProceso) throws IOException {
		String[] infoProceso = { "cmd.exe", "/c", "tasklist" };
		ProcessBuilder pb = new ProcessBuilder(infoProceso);

		System.out.println("Buscando el proceso " + nombreProceso);
		Process proceso = pb.start();

		String resultado = leerResultadoBuffered(proceso);

		if (resultado.contains(nombreProceso)) {
			Runtime.getRuntime().exec("taskkill /F /IM " + nombreProceso);
			System.out.println("Se ha cerrado el proceso " + nombreProceso);
		} else {
			System.out.println("El proceso " + nombreProceso + " no está en ejecución");
		}
	}
	
	public void ejecutarBat(String rutaBat, String rutaError, String rutaOutput) throws IOException {
		ProcessBuilder pb = new ProcessBuilder(rutaBat);
		
		File ficheroError = new File(rutaError);
		if (!ficheroError.exists())
			ficheroError.createNewFile();
		pb.redirectError(ficheroError);
		
		File ficheroOutput = new File(rutaOutput);
		if (!ficheroOutput.exists())
			ficheroOutput.createNewFile();
		pb.redirectOutput(ficheroOutput);
		
		System.out.println("Ejecutando bat");
		pb.start();
	}
}
