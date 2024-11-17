package hilos.extras.reto;

public class CronometroProgresivo extends Thread {

	private ManagerCronometros manager = null;
	private boolean ejecutando = true;

	public CronometroProgresivo(String nombre, ManagerCronometros manager) {
		super(nombre);
		this.manager = manager;
	}

	@Override
	public void run() {
		while (ejecutando) {
			for (int i = 0;; i++) {
				while (manager.getEnPausa()) {
					synchronized (manager) {
						try {
							manager.wait();
						} catch (InterruptedException e) {
							break;
						}
					}
				}
				System.out.println(this.getName() + ": " + i);

				try {
					sleep(1000);
				} catch (InterruptedException e) {
					break;
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
