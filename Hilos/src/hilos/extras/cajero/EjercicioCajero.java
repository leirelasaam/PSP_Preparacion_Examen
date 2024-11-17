package hilos.extras.cajero;

public class EjercicioCajero {
	public static void main(String[] args) {
		Cajero cajero = new Cajero(1000);
		
		Ingreso ingreso = new Ingreso("Ingresor", cajero);
		Retirada retirada1 = new Retirada("Retirador 1", cajero);
		Retirada retirada2 = new Retirada("Retirador 2", cajero);
		
		ingreso.start();
		retirada1.start();
		retirada2.start();
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			
		}
		
		ingreso.terminar();
		retirada1.terminar();
		retirada2.terminar();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
		
		System.out.println("Terminado, cantidad actual: " + cajero.getCantidadDisponible());
	}
}
