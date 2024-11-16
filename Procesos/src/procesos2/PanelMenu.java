package procesos2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelMenu extends JPanel {

	private static final long serialVersionUID = 1284894348988323244L;
	private JFrame mainFrame = null;
	private JTextField textFieldProceso1, textFieldProceso2, textFieldProceso3;
	private JLabel lblPID1, lblPID2, lblPID3;
	private JLabel lblPIDPadre1, lblPIDPadre2, lblPIDPadre3;
	private JTextArea textArea1, textArea2;
	private Procesos2 procesos = null;

	public PanelMenu(JFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.procesos = new Procesos2();
		initialize();
	}

	private void initialize() {
		setLayout(null);

		JLabel lblProceso1 = new JLabel("Ejecutar programa del sistema:");
		lblProceso1.setHorizontalAlignment(SwingConstants.LEFT);
		lblProceso1.setBounds(100, 10, 200, 25);
		add(lblProceso1);

		// Ajustar los campos de texto
		textFieldProceso1 = new JTextField();
		textFieldProceso1.setColumns(10);
		textFieldProceso1.setBounds(100, 40, 155, 25);
		add(textFieldProceso1);

		JLabel lblProceso2 = new JLabel("Ejecutar comando:");
		lblProceso2.setHorizontalAlignment(SwingConstants.LEFT);
		lblProceso2.setBounds(358, 10, 200, 25);
		add(lblProceso2);

		textFieldProceso2 = new JTextField();
		textFieldProceso2.setColumns(10);
		textFieldProceso2.setBounds(358, 40, 155, 25);
		add(textFieldProceso2);

		JLabel lblProceso3 = new JLabel("Ejecutar Ejer7:");
		lblProceso3.setHorizontalAlignment(SwingConstants.LEFT);
		lblProceso3.setBounds(616, 10, 200, 25);
		add(lblProceso3);

		textFieldProceso3 = new JTextField();
		textFieldProceso3.setColumns(10);
		textFieldProceso3.setBounds(616, 40, 155, 25);
		add(textFieldProceso3);

		// Ajustar los botones
		JButton btnStart1 = new JButton("Start");
		btnStart1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarPrograma();
			}
		});
		btnStart1.setBounds(100, 80, 155, 40);
		add(btnStart1);

		JButton btnStart2 = new JButton("Start");
		btnStart2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarComando();
			}
		});
		btnStart2.setBounds(358, 80, 155, 40);
		add(btnStart2);

		JButton btnStart3 = new JButton("Start");
		btnStart3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ejecutarEjer7();
			}
		});
		btnStart3.setBounds(616, 80, 155, 40);
		add(btnStart3);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		btnSalir.setBounds(100, 480, 155, 40);
		add(btnSalir);

		// Etiquetas para PID
		JLabel lblPID = new JLabel("PID");
		lblPID.setHorizontalAlignment(SwingConstants.LEFT);
		lblPID.setBounds(10, 140, 100, 20);
		add(lblPID);

		lblPID1 = new JLabel("");
		lblPID1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPID1.setBounds(100, 140, 155, 20);
		add(lblPID1);

		lblPID2 = new JLabel("");
		lblPID2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPID2.setBounds(358, 140, 155, 20);
		add(lblPID2);

		lblPID3 = new JLabel("");
		lblPID3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPID3.setBounds(616, 140, 250, 20);
		add(lblPID3);

		// Etiquetas para PID Padre
		JLabel lblPIDPadre = new JLabel("PID Padre");
		lblPIDPadre.setHorizontalAlignment(SwingConstants.LEFT);
		lblPIDPadre.setBounds(10, 180, 155, 20);
		add(lblPIDPadre);

		lblPIDPadre1 = new JLabel("");
		lblPIDPadre1.setHorizontalAlignment(SwingConstants.LEFT);
		lblPIDPadre1.setBounds(100, 180, 100, 20);
		add(lblPIDPadre1);

		lblPIDPadre2 = new JLabel("");
		lblPIDPadre2.setHorizontalAlignment(SwingConstants.LEFT);
		lblPIDPadre2.setBounds(358, 180, 155, 20);
		add(lblPIDPadre2);

		lblPIDPadre3 = new JLabel("");
		lblPIDPadre3.setHorizontalAlignment(SwingConstants.LEFT);
		lblPIDPadre3.setBounds(616, 180, 250, 20);
		add(lblPIDPadre3);

		// Etiquetas para Resultado
		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setHorizontalAlignment(SwingConstants.LEFT);
		lblResultado.setBounds(10, 220, 100, 20);
		add(lblResultado);

		textArea1 = new JTextArea();
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);
		JScrollPane scrollPane1 = new JScrollPane(textArea1);
		scrollPane1.setBounds(358, 220, 250, 300);
		add(scrollPane1);

		textArea2 = new JTextArea();
		textArea2.setLineWrap(true);
		textArea2.setWrapStyleWord(true);
		JScrollPane scrollPane2 = new JScrollPane(textArea2);
		scrollPane2.setBounds(650, 220, 250, 300);
		add(scrollPane2);
	}

	private void salir() {
		mainFrame.dispose();
	}

	private void ejecutarPrograma() {
		String[] programa = { textFieldProceso1.getText() };
		try {
			ArrayList<String> salida = procesos.ejecutarProcesoPB(programa, false);
			lblPID1.setText(salida.get(0));
			lblPIDPadre1.setText(salida.get(1));
		} catch (IOException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar el programa " + programa[0]);
		}
	}

	private void ejecutarComando() {
		String[] programa = { "cmd.exe", "/c", textFieldProceso2.getText() };
		try {
			ArrayList<String> salida = procesos.ejecutarProcesoPB(programa, true);
			lblPID2.setText(salida.get(0));
			lblPIDPadre2.setText(salida.get(1));
			textArea1.setText(salida.get(2));
		} catch (IOException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar el programa " + programa[0]);
		}
	}

	private void ejecutarEjer7() {
		lblPID3.setText("");
		lblPIDPadre3.setText("");
		textArea2.setText("");

		String[] programa = { "java", "-cp", "src", "procesos1.ejercicio7.EjemploLectura",
				textFieldProceso3.getText() };
		try {
			for (int i = 0; i < 5; i++) {
				ArrayList<String> salida = procesos.ejecutarProcesoPB(programa, true);
				lblPID3.setText(lblPID3.getText() + salida.get(0) + " ");
				lblPIDPadre3.setText(salida.get(1));
				textArea2.setText(textArea2.getText() + salida.get(2) + "\n");
			}
		} catch (IOException | InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Error al ejecutar el programa " + programa[0]);
		}
	}

}
