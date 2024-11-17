package hilos.extras.reto;

public class Reto {

	public static void main(String[] args) {
		ManagerCronometros manager = new ManagerCronometros();
		CronometroProgresivo cronGeneral = new CronometroProgresivo("GENERAL", manager);
		CronometroRegresivo cronSerie = new CronometroRegresivo("SERIE", 10, manager);	
		
		cronGeneral.start();
		cronSerie.start();
		
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			
		}
		
		manager.parar();
		
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			
		}
		
		manager.reanudar();
		
		try {
			cronSerie.join();
		} catch (InterruptedException e) {
			
		}
		
		try {
			Thread.sleep(3000);
		} catch(InterruptedException e) {
			
		}
		
		cronGeneral.terminar();
	}

}
