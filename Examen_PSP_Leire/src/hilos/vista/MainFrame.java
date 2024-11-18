package hilos.vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * Frame de la aplicación.
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Hilos");
		setBounds(100, 100, 800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());

		Panel panel = new Panel();
		add(panel, BorderLayout.CENTER);
	}

}
