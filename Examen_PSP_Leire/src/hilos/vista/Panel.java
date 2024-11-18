package hilos.vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import hilos.clases.CuentaAtras;
import hilos.clases.Manager;
import hilos.clases.Reloj;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Panel que contiene los elementos de la interfaz.
 */
public class Panel extends JPanel {

	private static final long serialVersionUID = 843723200842319527L;
	private Manager manager = null;
	private CuentaAtras cuentaAtras = null;
	private Thread tCuentaAtras = null;
	private Reloj tReloj = null;
	private JButton btnPausarReanudar = null;
	private JButton btnParar = null;

	public Panel() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 800, 500);
		setLayout(new GridLayout(3, 1, 10, 10));
		setBorder(new EmptyBorder(20, 20, 20, 20));

		JLabel lblCuentaAtras = new JLabel("01:30:00");
		lblCuentaAtras.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblCuentaAtras.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCuentaAtras);

		JPanel panelControles = new JPanel(new GridLayout(1, 4, 10, 10));
		add(panelControles);

		// Instancia e inicia la ejecución del hilo de la cuenta atrás
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tCuentaAtras = new Thread(cuentaAtras);
				// Establecer prioridad normal
				tCuentaAtras.setPriority(Thread.NORM_PRIORITY);
				tCuentaAtras.setName("cuenta atrás");
				if (!tCuentaAtras.isAlive()) {
					manager.reanudar();
					btnPausarReanudar.setText("Pausar");
					tCuentaAtras.start();
					btnIniciar.setEnabled(false);
					btnPausarReanudar.setEnabled(true);
					btnParar.setEnabled(true);
				}
			}
		});
		panelControles.add(btnIniciar);

		// Gestiona el pausado de la cuenta atrás
		btnPausarReanudar = new JButton("Pausar");
		btnPausarReanudar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tCuentaAtras != null && tCuentaAtras.isAlive()) {
					if (manager.getEnPausa()) {
						manager.reanudar();
						btnPausarReanudar.setText("Pausar");
						btnParar.setEnabled(true);
					} else {
						manager.pausar();
						btnPausarReanudar.setText("Reanudar");
						btnParar.setEnabled(false);
					}
				}
			}
		});
		panelControles.add(btnPausarReanudar);

		// Interrumpe el hilo de la cuenta atrás
		btnParar = new JButton("Parar");
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tCuentaAtras != null && tCuentaAtras.isAlive()) {
					tCuentaAtras.interrupt();
					btnParar.setEnabled(false);
					btnIniciar.setEnabled(true);
					btnPausarReanudar.setEnabled(false);
				}
			}
		});
		panelControles.add(btnParar);

		// Label que contiene la hora del sistema
		JLabel lblHora = new JLabel("00:00:00");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 15));
		panelControles.add(lblHora);

		JLabel lblInfo = new JLabel("El hilo <<x>> ha comenzado.");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblInfo);

		manager = new Manager(lblInfo);
		// Runnable que se le va a pasar al Thread
		cuentaAtras = new CuentaAtras(manager, lblCuentaAtras);

		// Se inicia en la creación del panel el hilo del reloj
		tReloj = new Reloj(manager, lblHora);
		tReloj.setName("reloj");
		// Establecer prioridad normal
		tReloj.setPriority(Thread.NORM_PRIORITY);
		tReloj.start();
	}

}
