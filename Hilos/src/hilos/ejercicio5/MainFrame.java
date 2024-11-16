package hilos.ejercicio5;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private HiloContador hilo1, hilo2, hilo3;
	private JLabel hilo1Contador, hilo2Contador, hilo3Contador;
	private JLabel hilo1Prioridad, hilo2Prioridad, hilo3Prioridad;

	public MainFrame() {
		initialize();
	}

	public void initialize() {
		setBounds(100, 100, 400, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panel, BorderLayout.CENTER);

		// HILO1
		JPanel panelHilo1 = new JPanel(new GridLayout(2, 3, 20, 20));
		panelHilo1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panelHilo1);

		JButton hilo1Reducir = new JButton("--");
		hilo1Reducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerPrioridad(hilo1, Thread.MIN_PRIORITY, hilo1Prioridad);
			}
		});
		panelHilo1.add(hilo1Reducir);

		JButton hilo1Finalizar = new JButton("Finalizar");
		hilo1Finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hilo1 != null && hilo1.isAlive())
					hilo1.terminar();
			}
		});
		panelHilo1.add(hilo1Finalizar);

		JButton hilo1Aumentar = new JButton("++");
		hilo1Aumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerPrioridad(hilo1, Thread.MAX_PRIORITY, hilo1Prioridad);
			}
		});
		panelHilo1.add(hilo1Aumentar);

		hilo1Contador = new JLabel("Hilo 1: 0");
		panelHilo1.add(hilo1Contador);

		hilo1Prioridad = new JLabel("Pri: 0");
		panelHilo1.add(hilo1Prioridad);

		// HILO2
		JPanel panelHilo2 = new JPanel(new GridLayout(2, 3, 20, 20));
		panelHilo2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panelHilo2);

		JButton hilo2Reducir = new JButton("--");
		hilo2Reducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerPrioridad(hilo2, Thread.MIN_PRIORITY, hilo2Prioridad);
			}
		});
		panelHilo2.add(hilo2Reducir);

		JButton hilo2Finalizar = new JButton("Finalizar");
		hilo2Finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hilo2 != null && hilo2.isAlive())
					hilo2.terminar();
			}
		});
		panelHilo2.add(hilo2Finalizar);

		JButton hilo2Aumentar = new JButton("++");
		hilo2Aumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerPrioridad(hilo2, Thread.MAX_PRIORITY, hilo2Prioridad);
			}
		});
		panelHilo2.add(hilo2Aumentar);

		hilo2Contador = new JLabel("Hilo 2: 0");
		panelHilo2.add(hilo2Contador);

		hilo2Prioridad = new JLabel("Pri: 0");
		panelHilo2.add(hilo2Prioridad);

		// HILO3
		JPanel panelHilo3 = new JPanel(new GridLayout(2, 3, 20, 20));
		panelHilo3.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(panelHilo3);

		JButton hilo3Reducir = new JButton("--");
		hilo3Reducir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerPrioridad(hilo3, Thread.MIN_PRIORITY, hilo3Prioridad);
			}
		});
		panelHilo3.add(hilo3Reducir);

		JButton hilo3Finalizar = new JButton("Finalizar");
		hilo3Finalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hilo3 != null && hilo3.isAlive())
					hilo3.terminar();
			}
		});
		panelHilo3.add(hilo3Finalizar);

		JButton hilo3Aumentar = new JButton("++");
		hilo3Aumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerPrioridad(hilo3, Thread.MAX_PRIORITY, hilo3Prioridad);
			}
		});
		panelHilo3.add(hilo3Aumentar);

		hilo3Contador = new JLabel("Hilo 3: 0");
		panelHilo3.add(hilo3Contador);

		hilo3Prioridad = new JLabel("Pri: 0");
		panelHilo3.add(hilo3Prioridad);

		JButton finalizarHilos = new JButton("Finalizar todos");
		finalizarHilos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (hilo1 != null && hilo1.isAlive())
					hilo1.terminar();
				if (hilo2 != null && hilo2.isAlive())
					hilo2.terminar();
				if (hilo3 != null && hilo3.isAlive())
					hilo3.terminar();
			}
		});
		panel.add(finalizarHilos);
		
		hilo1 = new HiloContador("Hilo 1", hilo1Contador);
		hilo2 = new HiloContador("Hilo 2", hilo2Contador);
		hilo3 = new HiloContador("Hilo 3", hilo3Contador);

		establecerPrioridad(hilo1, Thread.NORM_PRIORITY, hilo1Prioridad);
		establecerPrioridad(hilo2, Thread.NORM_PRIORITY, hilo2Prioridad);
		establecerPrioridad(hilo3, Thread.NORM_PRIORITY, hilo3Prioridad);

		hilo1.start();
		hilo2.start();
		hilo3.start();
	}

	private void establecerPrioridad(HiloContador hilo, int prioridad, JLabel label) {
		hilo.setPriority(prioridad);
		label.setText(hilo.getName() + ": " + hilo.getPriority());
	}
}
