package hilos.ejercicio7.vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;

import hilos.ejercicio7.hilos.HiloCaballo;

/**
 * Clase para el panel de la carrera de caballos.
 */
public class Carrera extends JPanel {

	private static final long serialVersionUID = -6315731833918124933L;
	private ArrayList<HiloCaballo> caballos = null;
	private ArrayList<JProgressBar> progressBars = null;
	private JLabel lblGanador;
	private boolean hayCarrera = false;
	private JButton btnEmpezar;

	public Carrera() {
		this.caballos = new ArrayList<>();
		this.progressBars = new ArrayList<>();
		initialize();
	}

	private void initialize() {
		setBounds(5, 5, 374, 451);
		setLayout(null);

		lblGanador = new JLabel("");
		lblGanador.setBounds(84, 24, 177, 14);
		add(lblGanador);

		crearProgressBars();
		crearCaballos();

		btnEmpezar = new JButton("Comenzar carrera");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gestionarCarrera();
			}
		});
		btnEmpezar.setBounds(84, 340, 269, 23);
		add(btnEmpezar);
	}

	/**
	 * Si no hay una carrera, la inicia y deshabilita la opción.
	 */
	private void gestionarCarrera() {
		if (!hayCarrera) {
			for (HiloCaballo caballo : caballos) {
				caballo.start();
			}

			hayCarrera = true;
			btnEmpezar.setEnabled(false);
		}
	}

	/**
	 * Crea todos los progressbar junto con su label correspondiente, añadiendo un
	 * color aleatorio.
	 */
	private void crearProgressBars() {
		Random random = new Random();
		UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
		UIManager.put("ProgressBar.selectionBackground", Color.BLACK);

		for (int i = 1; i < 5; i++) {
			Color randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));

			JLabel lblCaballo = new JLabel("Caballo " + i + ":");
			lblCaballo.setBounds(10, 70 * i, 77, 24);
			lblCaballo.setForeground(randomColor);
			add(lblCaballo);

			JProgressBar progressBar = new JProgressBar();
			progressBar.setStringPainted(true);
			progressBar.setBounds(84, 70 * i, 269, 37);
			progressBar.setBackground(randomColor);
			add(progressBar);

			progressBars.add(progressBar);
		}

	}

	/**
	 * Instancia los hilos y se añaden al ArrayList.
	 */
	private void crearCaballos() {
		for (int i = 1; i < 5; i++) {
			HiloCaballo caballo = new HiloCaballo("Caballo " + i, progressBars.get(i - 1), lblGanador);
			caballos.add(caballo);
		}
	}

	/**
	 * Método get para obtener el ArrayList de los hilos.
	 * 
	 * @return ArrayList que contiene los hilos.
	 */
	public ArrayList<HiloCaballo> getCaballos() {
		return caballos;
	}

}
