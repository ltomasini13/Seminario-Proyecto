package ar.edu.unrn.seminario.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
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
	private IApi api;
	private ResourceBundle labels;
	

	public AltaVivienda(IApi api) {
		this.api=api;
		labels=api.obtenerIdioma();
		setTitle(labels.getString("registrar.vivienda"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panelDatosPersonales;
		JPanel panelDatosVivienda;
		
		if (api.esUsuarioAdmin()) {
			panelDatosPersonales=new JPanel();
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
			txtpnDatosPersonales.setText(labels.getString("registro.datos.personales"));
			txtpnDatosPersonales.setBounds(117, 0, 140, 22);
			panelDatosPersonales.add(txtpnDatosPersonales);
			
			JTextPane txtpnNombre = new JTextPane();
			txtpnNombre.setEditable(false);
			txtpnNombre.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnNombre.setText(labels.getString("nombre"));
			txtpnNombre.setBounds(37, 31, 63, 22);
			panelDatosPersonales.add(txtpnNombre);
			
			JTextPane txtpnApellido = new JTextPane();
			txtpnApellido.setEditable(false);
			txtpnApellido.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnApellido.setText(labels.getString("apellido"));
			txtpnApellido.setBounds(37, 55, 56, 22);
			panelDatosPersonales.add(txtpnApellido);
			
			JTextPane txtpnDni = new JTextPane();
			txtpnDni.setEditable(false);
			txtpnDni.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnDni.setText(labels.getString("dni"));
			txtpnDni.setBounds(37, 79, 49, 22);
			panelDatosPersonales.add(txtpnDni);
			
			textFieldApellido = new JTextField();
			textFieldApellido.setBounds(103, 55, 157, 22);
			panelDatosPersonales.add(textFieldApellido);
			textFieldApellido.setColumns(10);
			
			textFieldDni = new JTextField();
			textFieldDni.setBounds(103, 79, 157, 22);
			panelDatosPersonales.add(textFieldDni);
			textFieldDni.setColumns(10);
			
			JButton btnAceptar = new JButton(labels.getString("continuar"));
			btnAceptar.addActionListener((ActionEvent e) -> {
				
									
					

						try {
							api.registrarVivienda(textFieldCalle.getText(), textFieldNro.getText(), textFieldBarrio.getText(), textFieldLatitud.getText(), textFieldLongitud.getText(),
									textFieldNombre.getText(), textFieldApellido.getText(), textFieldDni.getText());
							JOptionPane.showMessageDialog(null, labels.getString("registro.ciudadano.vivienda"),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (NotNullException | DataEmptyException | NumbersException 
								| AuthenticationException | AppException | InstanceException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
						}
						
						
			});
			btnAceptar.setBounds(313, 52, 92, 30);
			getContentPane().add(btnAceptar);
			
			JButton btnCancelar = new JButton(labels.getString("cancelar"));
			btnCancelar.addActionListener((ActionEvent e) -> {
					dispose();
			});
			btnCancelar.setBounds(311, 103, 94, 30);
			getContentPane().add(btnCancelar);
			
			panelDatosVivienda= new JPanel();
			panelDatosVivienda.setBounds(0, 108, 299, 145);
			contentPane.add(panelDatosVivienda);
			panelDatosVivienda.setLayout(null);
			
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setBounds(246, 5, -120, 22);
			panelDatosVivienda.add(textPane);
			
			JTextPane txtpnBarrio = new JTextPane();
			txtpnBarrio.setEditable(false);
			txtpnBarrio.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnBarrio.setText(labels.getString("vivienda.barrio"));
			txtpnBarrio.setBounds(30, 27, 60, 22);
			panelDatosVivienda.add(txtpnBarrio);
			
			JTextPane txtpnCalle = new JTextPane();
			txtpnCalle.setEditable(false);
			txtpnCalle.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnCalle.setText(labels.getString("vivienda.calle"));
			txtpnCalle.setBounds(30, 51, 60, 22);
			panelDatosVivienda.add(txtpnCalle);
			
			JTextPane txtpnLatitud = new JTextPane();
			txtpnLatitud.setEditable(false);
			txtpnLatitud.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnLatitud.setText(labels.getString("vivienda.latitud"));
			txtpnLatitud.setBounds(30, 98, 60, 22);
			panelDatosVivienda.add(txtpnLatitud);
			
			JTextPane txtpnLongitud = new JTextPane();
			txtpnLongitud.setEditable(false);
			txtpnLongitud.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnLongitud.setText(labels.getString("vivienda.longitud"));
			txtpnLongitud.setBounds(30, 123, 60, 22);
			panelDatosVivienda.add(txtpnLongitud);
			
			JTextPane txtpnNmero = new JTextPane();
			txtpnNmero.setEditable(false);
			txtpnNmero.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnNmero.setText(labels.getString("vivienda.numero"));
			txtpnNmero.setBounds(30, 74, 70, 22);
			panelDatosVivienda.add(txtpnNmero);
			
			JTextPane txtpnDatosDeVivienda = new JTextPane();
			txtpnDatosDeVivienda.setEditable(false);
			txtpnDatosDeVivienda.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtpnDatosDeVivienda.setBackground(SystemColor.activeCaption);
			txtpnDatosDeVivienda.setText(labels.getString("datos.vivienda"));
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
		
		
		if(api.esUsuarioReciclador()) {
			
			JButton btnAceptar = new JButton(labels.getString("continuar"));
			btnAceptar.addActionListener((ActionEvent e) -> {
	
						try {
							api.registrarVivienda(textFieldCalle.getText(), textFieldNro.getText(), textFieldBarrio.getText(), textFieldLatitud.getText(), textFieldLongitud.getText());
							JOptionPane.showMessageDialog(null,labels.getString("registro.vivienda.exito"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (DataEmptyException | NumbersException | NotNullException
								| AuthenticationException | AppException | InstanceException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
						}
			});
			btnAceptar.setBounds(313, 52, 92, 30);
			getContentPane().add(btnAceptar);
			
			JButton btnCancelar = new JButton(labels.getString("cancelar"));
			btnCancelar.addActionListener((ActionEvent e) -> {
					dispose();
				
			});
			btnCancelar.setBounds(311, 103, 94, 30);
			getContentPane().add(btnCancelar);
			panelDatosVivienda= new JPanel();
			panelDatosVivienda.setBounds(0, 0, 299, 145);
			contentPane.add(panelDatosVivienda);
			panelDatosVivienda.setLayout(null);
			
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);
			textPane.setBounds(246, 5, -120, 22);
			panelDatosVivienda.add(textPane);
			
			JTextPane txtpnBarrio = new JTextPane();
			txtpnBarrio.setEditable(false);
			txtpnBarrio.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnBarrio.setText(labels.getString("vivienda.barrio"));
			txtpnBarrio.setBounds(30, 27, 60, 22);
			panelDatosVivienda.add(txtpnBarrio);
			
			JTextPane txtpnCalle = new JTextPane();
			txtpnCalle.setEditable(false);
			txtpnCalle.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnCalle.setText(labels.getString("vivienda.calle"));
			txtpnCalle.setBounds(30, 51, 60, 22);
			panelDatosVivienda.add(txtpnCalle);
			
			JTextPane txtpnLatitud = new JTextPane();
			txtpnLatitud.setEditable(false);
			txtpnLatitud.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnLatitud.setText(labels.getString("vivienda.latitud"));
			txtpnLatitud.setBounds(30, 98, 60, 22);
			panelDatosVivienda.add(txtpnLatitud);
			
			JTextPane txtpnLongitud = new JTextPane();
			txtpnLongitud.setEditable(false);
			txtpnLongitud.setEditable(false);
			txtpnLongitud.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnLongitud.setText(labels.getString("vivienda.longitud"));
			txtpnLongitud.setBounds(30, 123, 60, 22);
			panelDatosVivienda.add(txtpnLongitud);
			
			JTextPane txtpnNmero = new JTextPane();
			txtpnNmero.setEditable(false);
			txtpnNmero.setBackground(UIManager.getColor("CheckBox.background"));
			txtpnNmero.setText(labels.getString("vivienda.numero"));
			txtpnNmero.setBounds(30, 74, 70, 22);
			panelDatosVivienda.add(txtpnNmero);
			
			JTextPane txtpnDatosDeVivienda = new JTextPane();
			txtpnDatosDeVivienda.setEditable(false);
			txtpnDatosDeVivienda.setFont(new Font("Tahoma", Font.PLAIN, 15));
			txtpnDatosDeVivienda.setBackground(SystemColor.activeCaption);
			txtpnDatosDeVivienda.setText(labels.getString("datos.vivienda"));
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
}
