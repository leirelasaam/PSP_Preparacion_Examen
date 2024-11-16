package hilos.ejercicio3;

public class Escritora extends Thread {
	
	private boolean tipo = true;
	
	public Escritora(String nombre, boolean tipo) {
		super(nombre);
		this.tipo = tipo;
	}
	
	@Override
	public void run() {
		if (tipo) {
			for (int i = 1; i <= 30; i++) {
				System.out.println("> " + this.getName() + ": " + i);
				try {
					sleep(1000);
				} catch(InterruptedException e) {
					
				}
			}
		} else {
			for (char c = 'a'; c <= 'z'; c++) {
	            System.out.println("> " + this.getName() + ": " + c);
	            try {
					sleep(1000);
				} catch(InterruptedException e) {
					
				}
	        }
		}
	}

}
