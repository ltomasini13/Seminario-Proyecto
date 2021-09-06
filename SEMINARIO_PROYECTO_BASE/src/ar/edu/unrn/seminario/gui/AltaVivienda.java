package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaVivienda extends JFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDni;
	private JTextField textFieldBarrio;
	private JTextField textFieldCalle;
	private JTextField textFieldLatitud;
	private JTextField textFieldLongitud;
	private JTextField textFieldNro;
	public AltaVivienda() {
		setTitle("Registrar Vivienda");
		getContentPane().setLayout(null);
		
		JPanel panelDatosPersonales = new JPanel();
		panelDatosPersonales.setBounds(40, 24, 499, 138);
		getContentPane().add(panelDatosPersonales);
		panelDatosPersonales.setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(103, 31, 157, 22);
		panelDatosPersonales.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JTextPane txtpnDatosPersonales = new JTextPane();
		txtpnDatosPersonales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDatosPersonales.setBackground(UIManager.getColor("Button.light"));
		txtpnDatosPersonales.setText("Datos Personales");
		txtpnDatosPersonales.setBounds(117, 0, 131, 22);
		panelDatosPersonales.add(txtpnDatosPersonales);
		
		JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnNombre.setText("Nombre:");
		txtpnNombre.setBounds(37, 31, 63, 22);
		panelDatosPersonales.add(txtpnNombre);
		
		JTextPane txtpnApellido = new JTextPane();
		txtpnApellido.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnApellido.setText("Apellido:");
		txtpnApellido.setBounds(37, 66, 56, 22);
		panelDatosPersonales.add(txtpnApellido);
		
		JTextPane txtpnDni = new JTextPane();
		txtpnDni.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnDni.setText("DNI:");
		txtpnDni.setBounds(37, 101, 49, 22);
		panelDatosPersonales.add(txtpnDni);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(103, 66, 157, 22);
		panelDatosPersonales.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(103, 101, 158, 22);
		panelDatosPersonales.add(textFieldDni);
		textFieldDni.setColumns(10);
		
		JPanel panelDatosVivienda = new JPanel();
		panelDatosVivienda.setBounds(40, 175, 499, 219);
		getContentPane().add(panelDatosVivienda);
		panelDatosVivienda.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(246, 5, -120, 22);
		panelDatosVivienda.add(textPane);
		
		JTextPane txtpnBarrio = new JTextPane();
		txtpnBarrio.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnBarrio.setText("Barrio:");
		txtpnBarrio.setBounds(30, 38, 60, 22);
		panelDatosVivienda.add(txtpnBarrio);
		
		JTextPane txtpnCalle = new JTextPane();
		txtpnCalle.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnCalle.setText("Calle:");
		txtpnCalle.setBounds(30, 85, 60, 22);
		panelDatosVivienda.add(txtpnCalle);
		
		JTextPane txtpnLatitud = new JTextPane();
		txtpnLatitud.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnLatitud.setText("Latitud:");
		txtpnLatitud.setBounds(30, 133, 60, 22);
		panelDatosVivienda.add(txtpnLatitud);
		
		JTextPane txtpnLongitud = new JTextPane();
		txtpnLongitud.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnLongitud.setText("Longitud:");
		txtpnLongitud.setBounds(30, 179, 60, 22);
		panelDatosVivienda.add(txtpnLongitud);
		
		JTextPane txtpnNmero = new JTextPane();
		txtpnNmero.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnNmero.setText("N\u00FAmero:");
		txtpnNmero.setBounds(277, 85, 70, 22);
		panelDatosVivienda.add(txtpnNmero);
		
		JTextPane txtpnDatosDeVivienda = new JTextPane();
		txtpnDatosDeVivienda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDatosDeVivienda.setBackground(UIManager.getColor("Button.light"));
		txtpnDatosDeVivienda.setText("Datos de Vivienda");
		txtpnDatosDeVivienda.setBounds(102, 0, 151, 22);
		panelDatosVivienda.add(txtpnDatosDeVivienda);
		
		textFieldBarrio = new JTextField();
		textFieldBarrio.setBounds(102, 38, 163, 22);
		panelDatosVivienda.add(textFieldBarrio);
		textFieldBarrio.setColumns(10);
		
		textFieldCalle = new JTextField();
		textFieldCalle.setBounds(102, 85, 163, 22);
		panelDatosVivienda.add(textFieldCalle);
		textFieldCalle.setColumns(10);
		
		textFieldLatitud = new JTextField();
		textFieldLatitud.setBounds(102, 133, 163, 22);
		panelDatosVivienda.add(textFieldLatitud);
		textFieldLatitud.setColumns(10);
		
		textFieldLongitud = new JTextField();
		textFieldLongitud.setBounds(102, 179, 163, 22);
		panelDatosVivienda.add(textFieldLongitud);
		textFieldLongitud.setColumns(10);
		
		textFieldNro = new JTextField();
		textFieldNro.setBounds(347, 85, 116, 22);
		panelDatosVivienda.add(textFieldNro);
		textFieldNro.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAceptar.setBounds(120, 432, 97, 25);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(249, 432, 97, 25);
		getContentPane().add(btnCancelar);
	}
}
