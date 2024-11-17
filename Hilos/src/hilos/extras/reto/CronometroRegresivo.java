package hilos.extras.reto;

public class CronometroRegresivo extends Thread {

	private ManagerCronometros manager = null;
	private boolean ejecutando = true;
	private int max = 0;

	public CronometroRegresivo(String nombre, int max, ManagerCronometros manager) {
		super(nombre);
		this.manager = manager;
		this.max = max;
	}

	@Override
	public void run() {
		while (ejecutando) {
			for (int i = max; i >= 0; i--) {
				while (manager.getEnPausa()) {
					synchronized (manager) {
						try {
							manager.wait();
						} catch (InterruptedException e) {

						}
					}
				}
				System.out.println(this.getName() + ": " + i);

				try {
					sleep(1000);
				} catch (InterruptedException e) {
					break;
				}
				
				if (i == 0) {
					terminar();
				}
			}
		}
	}

	public void terminar() {
		this.ejecutando = false;
		interrupt();
		System.out.println(this.getName() + " finalizado.");
	}
}
