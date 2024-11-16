package procesos2;

import java.awt.EventQueue;

/**
 * El programa consistirá en tres botones que iniciarán diferentes procesos:
 * 1. Ejecutará un programa del sistema.
 * 2. Ejecutará un comando sobre la consola de Windows (cmd).
 * 3. Llamará 5 veces al programa ya implementado el cual pide un dato y lo
 * imprime por pantalla.
 * 
 * Además de ejecutar los diferentes procesos, deberemos obtener el PID de cada
 * proceso (en el caso del tercer proceso serán 5 PID diferentes). También
 * obtendremos el PID del proceso padre y el resultado de la ejecución del
 * segundo y el tercer proceso.
 */
public class Ejercicio {
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
