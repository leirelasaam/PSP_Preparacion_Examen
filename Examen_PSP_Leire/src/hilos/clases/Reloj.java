package hilos.clases;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;

public class Reloj extends Thread {

	private Manager manager = null;
	private boolean ejecutando = true;
	private JLabel label = null;

	public Reloj(Manager manager, JLabel label) {
		this.manager = manager;
		this.label = label;
	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		manager.setLabelInfo(true);
		while (ejecutando) {
			Date date = new java.util.Date();
			String horaActual = sdf.format(date);
			label.setText(horaActual);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void terminar() {
		this.ejecutando = false;
	}
}
