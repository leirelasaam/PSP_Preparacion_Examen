package hilos.extras.cajero;

public class Cajero {

	private boolean cajeroEnUso = false;
	private int cantidadDisponible = 0;

	public Cajero(int cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public synchronized void retirarDinero(int cantidad) {
		System.out.println("!!! " + Thread.currentThread().getName() + " entrando en el cajero");
		System.out.println("- Retirador: " + Thread.currentThread().getName());
		System.out.println("- Cantidad: " + cantidad);
		System.out.println("- Cantidad disponible: " + this.cantidadDisponible);

		cajeroEnUso = true;
		this.cantidadDisponible -= cantidad;
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}

		cajeroEnUso = false;
		notifyAll();
	}

	public synchronized void ingresarDinero(int cantidad) {
		System.out.println("!!! " + Thread.currentThread().getName() + " cajero: entrando en el cajero");
		System.out.println("+ Ingresor: " + Thread.currentThread().getName());
		System.out.println("+ Cantidad: " + cantidad);
		System.out.println("+ Cantidad disponible: " + this.cantidadDisponible);

		cajeroEnUso = true;
		this.cantidadDisponible += cantidad;
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		
		cajeroEnUso = false;
		notifyAll();
	}

	public int getCantidadDisponible() {
		return this.cantidadDisponible;
	}
	
	public boolean getCajeroEnUso() {
		return this.cajeroEnUso;
	}
}
