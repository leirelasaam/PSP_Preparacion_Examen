package hilos.ejercicio5;

import javax.swing.JLabel;

public class HiloContador extends Thread {

	private JLabel label = null;
	private boolean ejecutando = true;

	public HiloContador(String nombre, JLabel label) {
		super(nombre);
		this.label = label;
	}

	@Override
	public void run() {
		int i = 0;
		while (ejecutando) {
			label.setText(this.getName() + ": " + i);
			i++;
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void terminar() {
		ejecutando = false;
		this.interrupt();
	}
}
