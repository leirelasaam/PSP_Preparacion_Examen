package hilos.ejercicio7.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

/**
 * Frame que contiene los paneles Trampas y Carrera.
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Carrera carrera = null;
	private Trampas trampas = null;

	public MainFrame() {
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * Se instancia primero Carrera porque tiene la creación de caballos, pero el
		 * panel que se muestra es el de Trampas.
		 */
		carrera = new Carrera();
		trampas = new Trampas(carrera.getCaballos(), this::mostrarPanelCarrera);
		contentPane.add(trampas, BorderLayout.CENTER);
	}

	public void mostrarPanelCarrera() {
		cambiarPanel(carrera);
	}

	/**
	 * Permite cambiar el panel mostrado en el Frame.
	 * 
	 * @param nuevoPanel Panel a mostrar.
	 */
	public void cambiarPanel(JPanel nuevoPanel) {
		getContentPane().removeAll();
		getContentPane().add(nuevoPanel, BorderLayout.CENTER);
		revalidate();
		repaint();
	}
}
