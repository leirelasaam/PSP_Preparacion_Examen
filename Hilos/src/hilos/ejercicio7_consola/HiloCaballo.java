package hilos.ejercicio7_consola;

import java.util.Random;

public class HiloCaballo extends Thread {

	private ManagerCaballos manager = null;

	public HiloCaballo(String caballo, ManagerCaballos manager) {
		super(caballo);
		this.manager = manager;
	}

	@Override
	public void run() {
		int i = 0;
		int x = 0;
		while (manager.getEnCarrera()) {
			synchronized (manager) {
				// Espera mientras la carrera esté en pausa
				while (manager.getEnPausa()) {
					try {
						// Espera hasta que el manager notifique
						manager.wait();
					} catch (InterruptedException e) {
						System.out.println(this.getName() + " interrumpido.");
						break;
					}
				}
			}

			Random random = new Random();
			x = random.nextInt(20);
			i += x;
			if (i >= 100) {
				System.out.println(this.getName() + " ha ganado");
				terminar();
			} else {
				System.out.println(this.getName() + ": " + i);
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				break;
			}

		}
	}

	public void terminar() {
		manager.terminarCarrera();
	}
}
