package hilos.ejercicio7_consola;

public class ManagerCaballos {

	private boolean enCarrera = true;
	private boolean enPausa = false;
	
	public synchronized void reanudarCarrera() {
		this.enPausa = false;
		System.out.println("> Se ha reanudado la carrera.");
		notifyAll();
	}
	
	public synchronized void pararCarrera() {
		this.enPausa = true;
		System.out.println("> Se ha parado la carrera.");
		notifyAll();
	}
	
	public synchronized void terminarCarrera() {
		this.enCarrera = false;
		System.out.println("> Se ha terminado la carrera.");
		notifyAll();
	}
	
	public boolean getEnPausa() {
		return this.enPausa;
	}
	
	public boolean getEnCarrera() {
		return this.enCarrera;
	}
}
