package es;

public class ProcesoSecundario {

	/*
	 * Hace un sysout y termina mandando al sistema un error de 103.
	 */
	public static void main(String[] args) {
		System.out.println("Proceso secundario en marcha...");
		
		// Este error se puede utilizar para comunicarse con el sistema u otros procesos con los que interaccione.
		System.exit(103);
	}

}
