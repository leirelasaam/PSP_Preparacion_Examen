package ejemplo_join;

public class HiloJoin extends Thread {
	private int n;
	
	// Se le pasa el nombre que es el identificador del hilo.
	public HiloJoin(String nom, int n) {
		super(nom);
		this.n = n;
	}
	
	public void run() {
		for (int i = 1; i < n; i++) {
			// El identificador del hilo, se recoge con la función getName()
			System.out.println(getName() + ": " + i);
			try {
				sleep(1000);
			} catch (InterruptedException ignore) {
				// Mensaje para cuando se interrumpe el hilo
				System.out.println(getName() + " se ha interrumpido en el número " + i);
				break;
			}
		}
		System.out.println("Fin bucle " + getName());
	}
}
