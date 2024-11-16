package hilos.ejercicio5;

import java.awt.EventQueue;

/**
 * Se van a definir 3 hilos contadores, para ello hace falta definir la clase
 * HiloContador. Cada hilo tendrá asociado 3 botones:
 * 
 * - El botón para finalizar el hilo
 * 
 * - El botón -- que hace que el hilo tenga la prioridad mínima
 * 
 * - El botón ++ que hace que el hilo tenga la prioridad máxima
 * 
 * Todos los hilos parten con una prioridad normal.
 * 
 * El botón “Finalizar todos” finaliza la ejecución de todos los hilos.
 * 
 * En las etiquetas de abajo a la izquierda se muestran los contadores de cada
 * hilo. Al iniciarse el programa cada hilo empieza a contar desde cero y cada
 * segundo incrementa su contador, mostrándolo en la etiqueta.
 * 
 * En las etiquetas de abajo a la derecha se muestra la prioridad que tiene cada
 * hilo. Cada vez que se cambie la prioridad se debe actualizar la etiqueta.
 */
public class Ejercicio5 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
