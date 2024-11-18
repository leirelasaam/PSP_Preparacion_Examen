package hilos.clases;

import javax.swing.JLabel;

/**
 * Runnable que contiene el run para una cuenta atrás de 90 minutos.
 * Este tiempo se puede cambiar en max.
 * Se gestiona que se imprima el estado del hilo en el labelInfo.
 */
public class CuentaAtras implements Runnable {

	private Manager manager = null;
	private int max = 90 * 60;
	private JLabel label = null;

	public CuentaAtras(Manager manager, JLabel label) {
		this.manager = manager;
		this.label = label;
	}

	@Override
	public void run() {
		manager.setLabelInfo(true);
		for (int i = max; i >= 0; i--) {
			while (manager.getEnPausa()) {
				synchronized (manager) {
					try {
						manager.wait();
					} catch (InterruptedException e) {
						// Si está en pausa y se interrumpe, debe salir del bucle también
						manager.setLabelInfo(false);
						i = -1;
						break;
					}
				}
			}

			// Salir de este bucle
			if (i == -1) {
				break;
			}
			
			String tiempoFormateado = formatearTiempoCronometro(i);
			label.setText(tiempoFormateado);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				manager.setLabelInfo(false);
				break;
			}
		}
	}

	/**
	 * Formatea el tiempo del cronómetro para pasar de segundos a HH:mm:ss.
	 * 
	 * @param tiempoEnSegundos Número entero en segundos
	 * @return String con el tiempo formateado
	 */
	public String formatearTiempoCronometro(int tiempoEnSegundos) {
		int horas = tiempoEnSegundos / 3600;
		int minutos = (tiempoEnSegundos % 3600) / 60;
		int segundos = tiempoEnSegundos % 60;
		return String.format("%02d:%02d:%02d", horas, minutos, segundos);
	}
}
