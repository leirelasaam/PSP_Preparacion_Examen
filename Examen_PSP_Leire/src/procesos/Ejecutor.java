package procesos;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * Clase que ejecuta SumadorRestador.
 */
public class Ejecutor {
	private static String RUTA_FICHERO = "src/procesos/salida.txt";
	public static void main(String[] args) {
		// Se indica el tipo de archivo y el paquete.clase
		ProcessBuilder builder = new ProcessBuilder("java", "procesos.SumadorRestador");
		
		// Directorio de trabajo
		builder.directory(new File("bin"));
		
		// Redirigir la salida
		File ficheroOutput = new File(RUTA_FICHERO);
		
		// Crear el fichero si no existe
		if (!ficheroOutput.exists())
			try {
				ficheroOutput.createNewFile();
			} catch (IOException e) {
				System.out.println("-- NO SE ENCUENTRA LA RUTA DEL FICHERO");
			}
		builder.redirectOutput(new File(RUTA_FICHERO));
		
		Process p = null;
		
		try {
			p = builder.start();
			System.out.println("> PID proceso actual: " + p.pid());
			
			ProcessHandle parentHandle = p.toHandle().parent().get();
			System.out.println("> PID proceso padre: " + parentHandle.pid());
			
			OutputStream os = p.getOutputStream();
			
			// Leer las opciones por consola para después pasarlas al proceso con OutputStream
			Scanner sc = new Scanner(System.in);
			System.out.println("> ¿Qué quieres hacer?");
			System.out.println("\t1. Sumar");
			System.out.println("\t2. Restar");
			
			int opcion = sc.nextInt();
			
			byte[] textoBytes = ( opcion + System.lineSeparator()).getBytes();
			os.write(textoBytes);
			
			System.out.println("> Operador 1:");
			int operador1 = sc.nextInt();
			textoBytes = ( operador1 + System.lineSeparator()).getBytes();
			os.write(textoBytes);
			
			System.out.println("> Operador 2:");
			int operador2 = sc.nextInt();
			textoBytes = ( operador2 + System.lineSeparator()).getBytes();
			os.write(textoBytes);
			sc.close();
			
			os.flush();
			os.close();
			
		} catch (IOException e) {
			System.out.println("-- ERROR EN LA EJECUCIÓN DEL PROCESO");
		}
	}

}
