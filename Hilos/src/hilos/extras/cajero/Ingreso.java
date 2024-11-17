package hilos.extras.cajero;

import java.util.Random;

public class Ingreso extends Thread {

	private boolean ejecutando = true;
	private Cajero cajero = null;

	public Ingreso(String nombre, Cajero cajero) {
		super(nombre);
		this.cajero = cajero;
	}

	@Override
	public void run() {
		while (ejecutando) {
			Random random = new Random();
			int cantidad = random.nextInt(200);
			cajero.ingresarDinero(cantidad);

			try {
				sleep(5000);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	public void terminar() {
		this.ejecutando = false;
		interrupt();
	}
}
