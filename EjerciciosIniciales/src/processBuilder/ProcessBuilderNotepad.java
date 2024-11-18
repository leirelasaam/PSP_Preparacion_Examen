package processBuilder;

import java.util.Map;

public class ProcessBuilderNotepad {

	public static void main(String[] args) {
		System.out.println("Vamos a lanzar el Notepad...");
		
		// No es necesario indicar la ruta entera porque es un proceso del sistema
		String infoProceso = "Notepad.exe";
		
		try {
			ProcessBuilder pb = new ProcessBuilder(infoProceso);
			
			// Recoge variables de entorno mapeando con el valor correspondiente
			Map<String, String> environment = pb.environment();
			System.out.println(environment.toString());
			System.out.println("Numero de procesadores: " + environment.get("NUMBER_OF_PROCESSORS"));
			
			Process proceso = pb.start();
			
			System.out.println("El ID del proceso es: " + proceso.pid());
			int codigoRetorno = proceso.waitFor();
			
			// Devuelve 0 y ejecuta esta línea solamente cuando cerramos la aplicación
			System.out.println("Fin del Proceso con el codigo: " + codigoRetorno);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
