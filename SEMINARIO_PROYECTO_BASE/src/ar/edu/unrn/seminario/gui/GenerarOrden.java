package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenerarOrden extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField dniText;
	private JTextField emailText;
	private JTextField estadoText;
	
	public GenerarOrden(IApi api) {
		setTitle("GENERAR ORDEN DE RETIRO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel estadoLabel = new JLabel("Estado:");
		estadoLabel.setBounds(12, 26, 56, 16);
		contentPane.add(estadoLabel);
		
		estadoText = new JTextField();
		estadoText.setBounds(80, 23, 140, 22);
		contentPane.add(estadoText);
		estadoText.setColumns(10);
		
		JButton asignarBoton = new JButton("ASIGNAR RECOLECTOR");
		asignarBoton.addActionListener((ActionEvent e) -> {
			RegistrarRecolector regRecolector = new RegistrarRecolector(api);
			regRecolector.setVisible(true);
			this.dispose();
		});
		asignarBoton.setBounds(12, 124, 177, 25);
		contentPane.add(asignarBoton);
		
		JButton cancelarBoton = new JButton("CANCELAR");
		cancelarBoton.setBounds(261, 124, 97, 25);
		contentPane.add(cancelarBoton);
		
		JButton aceptarBoton = new JButton("ACEPTAR");
		aceptarBoton.setBounds(261, 22, 97, 25);
		contentPane.add(aceptarBoton);
	}
}
