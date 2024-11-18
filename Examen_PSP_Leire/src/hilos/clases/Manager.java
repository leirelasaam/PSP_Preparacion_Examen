package hilos.clases;

import javax.swing.JLabel;

/**
 * Gestiona el pause del cronómetro y también el establecer el estado del hilo
 * en el labelInfo.
 */
public class Manager {

	private JLabel labelInfo = null;
	private boolean enPausa = false;

	public Manager(JLabel labelInfo) {
		this.labelInfo = labelInfo;
	}

	public synchronized void pausar() {
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

	public synchronized void setLabelInfo(boolean haComenzado) {
		String estado = haComenzado ? "comenzado" : "finzalizado";
		labelInfo.setText("El hilo <<" + Thread.currentThread().getName() + ">> ha " + estado + ".");
	}

}
