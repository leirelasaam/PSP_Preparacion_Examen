package hilos.ejercicio6;

/**
 * ∙ Buffer
 * 
 * El objeto compartido entre el productor y el consumidor está descrito por la
 * clase denominada Buffer.
 * 
 * La clase Buffer tiene dos atributos: el primero contenido guarda un carácter
 * (es el buffer), el segundo, bufferLleno indica si el buffer está lleno o está
 * vacío, según que ésta variable valga true o false.
 * 
 * La definición de las funciones poner y recoger son muy simples:
 * 
 * - La función poner guarda el carácter que se le pasa en su parámetro c en el
 * miembro dato contenido, y pone el miembro bufferLleno en true (el buffer está
 * lleno) - La función recoger devuelve el carácter guardado en el atributo
 * contenido, siempre que este bufferLleno (el buffer lleno, o bufferLleno valga
 * true). Si el buffer esta lleno, lo deberé poner a false. En el caso de que no
 * esté bufferLleno (bufferLleno valga false) devuelve un carácter no
 * alfabético: un espacio, un tabulador, lo que desee el usuario.
 */
public class Buffer {

	private char contenido;
	private boolean bufferLleno = false;
	
	public Buffer() {
		
	}

	public synchronized void poner(char c) {
		while (bufferLleno) {
			System.out.println("BUFFER> Buffer lleno, esperando para poner.");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		contenido = c;
		bufferLleno = true;
		notify();
	}

	public synchronized char recoger() {
		while (!bufferLleno) {
			System.out.println("BUFFER> Buffer vacío, esperando para recoger.");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		bufferLleno = false;
		notify();

		return contenido;
	}
}
