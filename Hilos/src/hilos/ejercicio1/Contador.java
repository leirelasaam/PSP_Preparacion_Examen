package hilos.ejercicio1;

public class Contador extends Thread {
	
	private int contador = 1;
	
	public Contador(String nombre) {
		super(nombre);
	}

	@Override
	public void run() {

		while (contador <= 10) {
			System.out.println("> " + this.getName() + ": " + contador);
			try {
				sleep(1000);
			} catch(InterruptedException e) {
				System.out.println("> " + this.getName() +" interrumpido");
			}
			contador++;
		}
		
		System.out.println("> " + this.getName() +" finalizado");
		
	}
}
