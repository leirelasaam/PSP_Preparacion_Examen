package procesos2;

import javax.swing.JFrame;
import java.awt.BorderLayout;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Ejercicio");
		setBounds(100, 100, 1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());

		PanelMenu panel = new PanelMenu(this);
		getContentPane().add(panel, BorderLayout.CENTER);
	}

}
