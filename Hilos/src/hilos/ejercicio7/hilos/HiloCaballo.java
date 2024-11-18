package hilos.ejercicio7.hilos;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class HiloCaballo extends Thread {
	private JLabel label;
	private JProgressBar barra;

	// Variable común para los hilos, para comprobar si ya existe un ganador.
	private static volatile boolean hayGanador = false;

	public HiloCaballo(String nombre, JProgressBar barra, JLabel label) {
		super(nombre);
		this.label = label;
		this.barra = barra;
	}

	public void run() {
		while (barra.getValue() <= 100 && !hayGanador) {
			int random = (int) (Math.random() * 21);
			try {
				barra.setValue(barra.getValue() + random);
				if (barra.getValue() >= 100 && !hayGanador) {
					label.setText("Ganador: " + getName());
					hayGanador = true;
					terminar();
				}

				sleep(1000);
			} catch (InterruptedException ignore) {
				break;
			}
		}
	}

	public void terminar() {
		this.interrupt();
	}

}
