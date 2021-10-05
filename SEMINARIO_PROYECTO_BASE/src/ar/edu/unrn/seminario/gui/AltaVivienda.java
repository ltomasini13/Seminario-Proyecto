package ar.edu.unrn.seminario.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

import java.awt.SystemColor;

public class AltaVivienda extends JFrame {
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDni;
	private JTextField textFieldBarrio;
	private JTextField textFieldCalle;
	private JTextField textFieldLatitud;
	private JTextField textFieldLongitud;
	private JTextField textFieldNro;
	private JPanel contentPane;
	IApi api;
	
	public AltaVivienda(IApi api) {
		setTitle("Registrar Vivienda");
		setTitle("Alta Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panelDatosPersonales = new JPanel();
		panelDatosPersonales.setBounds(0, 0, 299, 108);
		contentPane.add(panelDatosPersonales);
		panelDatosPersonales.setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(103, 31, 157, 22);
		panelDatosPersonales.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JTextPane txtpnDatosPersonales = new JTextPane();
		txtpnDatosPersonales.setEditable(false);
		txtpnDatosPersonales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDatosPersonales.setBackground(SystemColor.activeCaption);
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
		txtpnApellido.setBounds(37, 55, 56, 22);
		panelDatosPersonales.add(txtpnApellido);
		
		JTextPane txtpnDni = new JTextPane();
		txtpnDni.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnDni.setText("DNI:");
		txtpnDni.setBounds(37, 79, 49, 22);
		panelDatosPersonales.add(txtpnDni);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(103, 55, 157, 22);
		panelDatosPersonales.add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(103, 79, 158, 22);
		panelDatosPersonales.add(textFieldDni);
		textFieldDni.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener((ActionEvent e) -> {
			
								
				try {
	
					api.registrarVivienda(textFieldCalle.getText(), textFieldNro.getText(), textFieldBarrio.getText(), textFieldLatitud.getText(), textFieldLongitud.getText(),
								textFieldNombre.getText(), textFieldApellido.getText(), textFieldDni.getText());
					
					JOptionPane.showMessageDialog(null, "La vivienda y el ciudadano se registraron con éxito!", "", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				} catch (NotNullException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (DataEmptyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumbersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);					}
				catch (SintaxisSQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (DuplicateUniqueKeyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
		});
		btnAceptar.setBounds(313, 52, 92, 30);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener((ActionEvent e) -> {
			
				setVisible(false);
				dispose();
			
		});
		btnCancelar.setBounds(311, 103, 94, 30);
		getContentPane().add(btnCancelar);
		
		JPanel panelDatosVivienda = new JPanel();
		panelDatosVivienda.setBounds(0, 108, 299, 145);
		contentPane.add(panelDatosVivienda);
		panelDatosVivienda.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(246, 5, -120, 22);
		panelDatosVivienda.add(textPane);
		
		JTextPane txtpnBarrio = new JTextPane();
		txtpnBarrio.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnBarrio.setText("Barrio:");
		txtpnBarrio.setBounds(30, 27, 60, 22);
		panelDatosVivienda.add(txtpnBarrio);
		
		JTextPane txtpnCalle = new JTextPane();
		txtpnCalle.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnCalle.setText("Calle:");
		txtpnCalle.setBounds(30, 51, 60, 22);
		panelDatosVivienda.add(txtpnCalle);
		
		JTextPane txtpnLatitud = new JTextPane();
		txtpnLatitud.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnLatitud.setText("Latitud:");
		txtpnLatitud.setBounds(30, 98, 60, 22);
		panelDatosVivienda.add(txtpnLatitud);
		
		JTextPane txtpnLongitud = new JTextPane();
		txtpnLongitud.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnLongitud.setText("Longitud:");
		txtpnLongitud.setBounds(30, 123, 60, 22);
		panelDatosVivienda.add(txtpnLongitud);
		
		JTextPane txtpnNmero = new JTextPane();
		txtpnNmero.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnNmero.setText("N\u00FAmero:");
		txtpnNmero.setBounds(30, 74, 70, 22);
		panelDatosVivienda.add(txtpnNmero);
		
		JTextPane txtpnDatosDeVivienda = new JTextPane();
		txtpnDatosDeVivienda.setEditable(false);
		txtpnDatosDeVivienda.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDatosDeVivienda.setBackground(SystemColor.activeCaption);
		txtpnDatosDeVivienda.setText("Datos de Vivienda");
		txtpnDatosDeVivienda.setBounds(116, 0, 133, 22);
		panelDatosVivienda.add(txtpnDatosDeVivienda);
		
		textFieldBarrio = new JTextField();
		textFieldBarrio.setBounds(102, 27, 163, 22);
		panelDatosVivienda.add(textFieldBarrio);
		textFieldBarrio.setColumns(10);
		
		textFieldCalle = new JTextField();
		textFieldCalle.setBounds(102, 51, 163, 22);
		panelDatosVivienda.add(textFieldCalle);
		textFieldCalle.setColumns(10);
		
		textFieldLatitud = new JTextField();
		textFieldLatitud.setBounds(102, 98, 163, 22);
		panelDatosVivienda.add(textFieldLatitud);
		textFieldLatitud.setColumns(10);
		
		textFieldLongitud = new JTextField();
		textFieldLongitud.setBounds(102, 123, 163, 22);
		panelDatosVivienda.add(textFieldLongitud);
		textFieldLongitud.setColumns(10);
		
		textFieldNro = new JTextField();
		textFieldNro.setBounds(102, 74, 73, 22);
		panelDatosVivienda.add(textFieldNro);
		textFieldNro.setColumns(10);
	}
}
