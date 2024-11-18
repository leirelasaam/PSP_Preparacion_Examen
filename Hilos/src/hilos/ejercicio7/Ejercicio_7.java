package hilos.ejercicio7;

import java.awt.EventQueue;

import hilos.ejercicio7.vista.MainFrame;

public class Ejercicio_7 {

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
