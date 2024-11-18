package hilos;

import java.awt.EventQueue;

import hilos.vista.MainFrame;

/**
 * Clase que ejecuta el programa.
 */
public class Ejecutor {

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
