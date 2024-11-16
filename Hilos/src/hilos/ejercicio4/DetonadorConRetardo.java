package hilos.ejercicio4;

public class DetonadorConRetardo extends Thread {
	
	private int contador = 0;
	
	public DetonadorConRetardo(String nombre, int contador) {
		super(nombre);
		this.contador = contador;
	}

	@Override
	public void run() {
		for (int i = contador; i >= 0; i--) {
			System.out.println("> " + this.getName() + ": " + i);
			
			try {
				sleep(1000);
			} catch(InterruptedException e) {
				
			}
		}
		
		System.out.println("> " + this.getName() + " BOOOOOM!!!!");
	}
}
