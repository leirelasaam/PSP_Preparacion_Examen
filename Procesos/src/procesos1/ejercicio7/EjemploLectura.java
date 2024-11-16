package procesos1.ejercicio7;

public class EjemploLectura {

	public static void main(String[] args) {
		if (args.length > 0) {
			String cadena = args[0].trim();
			
			if (cadena.isEmpty())
				System.out.println("> Texto vacío.");
			else
				System.out.println("> Texto recibido: " + cadena);
		} else {
			System.out.println("> No se ha recibido ningun texto.");
		}
	}

}