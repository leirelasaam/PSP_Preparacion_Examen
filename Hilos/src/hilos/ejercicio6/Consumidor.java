package hilos.ejercicio6;

/**
 * ∙ Consumidor
 * 
 * La clase que describe al consumidor denominado Consumidor, deriva también de
 * la clase Thread y redefine el método run
 * 
 * La definición de run es similar a la de la clase Productor, salvo que, en vez
 * de poner un carácter en el buffer, recoge el carácter guardado en el buffer
 * intermedio llamando a la función recoger
 * 
 * El tiempo de pausa (argumento de la función sleep) puede ser distinto en la
 * clase Productor que en la clase Consumidor. Por ejemplo, para hacer que el
 * productor sea más rápido que el consumidor, se pone un tiempo menor en la
 * primera que en la segunda.
 */
public class Consumidor extends Thread {

	private Buffer buffer;
	
	public Consumidor(Buffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			char c = buffer.recoger();

			System.out.println("CONSUMIDOR> Caracter recogido: " + c);

			try {
				sleep(2000);
			} catch (InterruptedException e) {
			}
		}
	}

}
