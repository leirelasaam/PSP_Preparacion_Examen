package hilos.ejercicio2;

public class HiloRunnable implements Runnable {
	
	public HiloRunnable() {
		
	}

	@Override
	public void run() {
		for (int i = 1; i < 4; i++) {
			System.out.println("> " + Thread.currentThread().getName() + " ejecutando operación " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
		System.out.println("> " + Thread.currentThread().getName() + " ha terminado la ejecución de operaciones");
	}

}
