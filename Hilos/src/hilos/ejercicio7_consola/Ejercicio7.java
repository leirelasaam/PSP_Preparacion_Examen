package hilos.ejercicio7_consola;

/**
 * #### Clase carrera Se van a crear 4 JProgressBar para 4 caballos. Un botón
 * Empieza la carrera Un Jlabel que indicará el ganador.
 * 
 * #### HiloCaballo Constructor: JProgressBar barra, String caballo, Jlabel
 * ganador Run: Se genera un numero aleatorio y se suma al value del
 * JprogressBar. Se espera un determinado tiempo para los siguientes cálculos.
 * Función: terminar(); (Termina el proceso)
 * 
 * #### Trampas Especifica las prioridades antes de cada partida. Por defecto,
 * aparecerá un 6.
 *
 * Importante: Los caballos se han de parar cuando uno de ellos llega al
 * final.
 */
public class Ejercicio7 {
	public static void main(String[] args) {

		ManagerCaballos manager = new ManagerCaballos();
		HiloCaballo caballo1 = new HiloCaballo("Caballo 1", manager);
		HiloCaballo caballo2 = new HiloCaballo("Caballo 2", manager);
		HiloCaballo caballo3 = new HiloCaballo("Caballo 3", manager);
		
		caballo1.start();
		caballo2.start();
		caballo3.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
		
		manager.pararCarrera();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
		}
		
		manager.reanudarCarrera();
	}
}
