package hilos.extras.cajero;

import java.util.Random;

public class Retirada extends Thread {

	public Retirada(String nombre) {
		super(nombre);
	}

	private boolean ejecutando = true;
	private Cajero cajero = null;

	public Retirada(String nombre, Cajero cajero) {
		super(nombre);
		this.cajero = cajero;
	}

	@Override
	public void run() {
		while (ejecutando && cajero.getCantidadDisponible() > 0) {
			Random random = new Random();
			int cantidad = random.nextInt(400);
			cajero.retirarDinero(cantidad);

			try {
				sleep(1000);
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
