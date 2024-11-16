package procesos2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Procesos2 {

	public Procesos2() {

	}

	public ArrayList<String> ejecutarProcesoPB(String[] infoProceso, boolean leerResultado)
			throws IOException, InterruptedException {
		ArrayList<String> resultado = new ArrayList<String>();

		ProcessBuilder pb = new ProcessBuilder(infoProceso);
		Process proceso = pb.start();
		resultado.add(proceso.pid() + "");

		ProcessHandle parentHandle = proceso.toHandle().parent().get();
		resultado.add(parentHandle.pid() + "");

		System.out.println("Ejecutando proceso: " + proceso.toString());

		String salida = "";
		if (leerResultado) {
			salida = leerResultadoBuffered(proceso);
		}

		resultado.add(salida);

		return resultado;
	}

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

}
