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

public class RegistrarCampaña extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField descripcionText;
	
	public RegistrarCampaña(IApi api) {
		
		setTitle("REGISTRAR CAMPAÑA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel nombreLabel = new JLabel("Nombre campa\u00F1a:");
		nombreLabel.setBounds(12, 13, 106, 16);
		contentPane.add(nombreLabel);
		
		JLabel descripcionLabel = new JLabel("Descripci\u00F3n:");
		descripcionLabel.setBounds(12, 54, 78, 16);
		contentPane.add(descripcionLabel);
		
		JButton aceptarBoton = new JButton("ACEPTAR");
		aceptarBoton.setBounds(12, 193, 97, 25);
		contentPane.add(aceptarBoton);
		
		JButton borrarBoton = new JButton("BORRAR");
		borrarBoton.setBounds(138, 193, 97, 25);
		contentPane.add(borrarBoton);
		
		JButton cancelarBoton = new JButton("CANCELAR");
		cancelarBoton.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		cancelarBoton.setBounds(267, 193, 97, 25);
		contentPane.add(cancelarBoton);
		
		nombreText = new JTextField();
		nombreText.setBounds(130, 10, 165, 22);
		contentPane.add(nombreText);
		nombreText.setColumns(10);
		
		descripcionText = new JTextField();
		descripcionText.setBounds(102, 51, 193, 22);
		contentPane.add(descripcionText);
		descripcionText.setColumns(10);
		
		JButton seleccionarCatalogo = new JButton("SELECCIONAR CAT\u00C1LOGO");
		seleccionarCatalogo.addActionListener((ActionEvent e) -> {
			
		});
		seleccionarCatalogo.setBounds(96, 120, 181, 25);
		contentPane.add(seleccionarCatalogo);
	}
}
