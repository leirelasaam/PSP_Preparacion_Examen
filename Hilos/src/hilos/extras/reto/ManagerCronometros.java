package hilos.extras.reto;

public class ManagerCronometros {
	
	private boolean enPausa = false;
	
	public synchronized void parar() {
		this.enPausa = true;
		System.out.println("> Pausado");
		notifyAll();
	}
	
	public synchronized void reanudar() {
		this.enPausa = false;
		System.out.println("> Reanudado");
		notifyAll();
	}

	public boolean getEnPausa() {
		return this.enPausa;
	}

}
