package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class RegistroUsuario extends JFrame {

	private JPanel panelLogin;
	private JTextField usuarioTextField;
	private JPasswordField contrasenaPasswordField;
	private JPasswordField confirContrasenaPasswordField;
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField dniTextField;
	private JTextField emailTextField;
	/**
	 * Create the frame.
	 */
	public RegistroUsuario(IApi api) {

		
		setTitle(api.obtenerIdioma().getString("registro.titulo"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 450, 300);
		panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLogin);
		panelLogin.setLayout(null);
		
		JTextPane txtpnLogin = new JTextPane();
		txtpnLogin.setEditable(false);
		txtpnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnLogin.setBackground(SystemColor.activeCaption);
		txtpnLogin.setText(api.obtenerIdioma().getString("datos.loguin"));
		txtpnLogin.setBounds(170, 9, 126, 22);
		panelLogin.add(txtpnLogin);
		
		JLabel lblUsuario = new JLabel(api.obtenerIdioma().getString("loguin.usuario"));
		lblUsuario.setBounds(39, 39, 112, 14);
		panelLogin.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel(api.obtenerIdioma().getString("loguin.contrasena"));
		lblContrasea.setBounds(39, 64, 112, 14);
		panelLogin.add(lblContrasea);
		
		JLabel lblConfirmar = new JLabel(api.obtenerIdioma().getString("registro.confirmacion.contrasena"));
		lblConfirmar.setBounds(39, 89, 141, 14);
		panelLogin.add(lblConfirmar);
		
		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(170, 33, 126, 20);
		panelLogin.add(usuarioTextField);
		usuarioTextField.setColumns(10);
		
		contrasenaPasswordField = new JPasswordField();
		contrasenaPasswordField.setBounds(170, 61, 126, 20);
		panelLogin.add(contrasenaPasswordField);
		
		confirContrasenaPasswordField = new JPasswordField();
		confirContrasenaPasswordField.setBounds(170, 86, 126, 20);
		panelLogin.add(confirContrasenaPasswordField);
		
		
		JTextPane txtpnDatosPersonales = new JTextPane();
		txtpnDatosPersonales.setEditable(false);
		txtpnDatosPersonales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDatosPersonales.setBackground(SystemColor.activeCaption);
		txtpnDatosPersonales.setText(api.obtenerIdioma().getString("registro.datos.personales"));
		txtpnDatosPersonales.setBounds(170, 150, 165, 22);
		panelLogin.add(txtpnDatosPersonales);
		
		JLabel nombreLabel = new JLabel(api.obtenerIdioma().getString("nombre"));
		nombreLabel.setBounds(40, 177, 74, 14);
		panelLogin.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel(api.obtenerIdioma().getString("apellido"));
		apellidoLabel.setBounds(39, 202, 98, 14);
		panelLogin.add(apellidoLabel);
		
		JLabel dniLabel = new JLabel(api.obtenerIdioma().getString("dni"));
		dniLabel.setBounds(39, 233, 98, 14);
		panelLogin.add(dniLabel);
		
		nombreTextField = new JTextField();
		nombreTextField.setColumns(10);
		nombreTextField.setBounds(170, 174, 165, 20);
		panelLogin.add(nombreTextField);
		
		apellidoTextField = new JTextField();
		apellidoTextField.setColumns(10);
		apellidoTextField.setBounds(170, 199, 165, 20);
		panelLogin.add(apellidoTextField);
		
		dniTextField = new JTextField();
		dniTextField.setColumns(10);
		dniTextField.setBounds(170, 230, 165, 20);
		panelLogin.add(dniTextField);
		
		JButton aceptarButton = new JButton(api.obtenerIdioma().getString("continuar"));
		aceptarButton.addActionListener((ActionEvent e) -> {
			
			
				
				try {
					api.registrarCiudadano(usuarioTextField.getText(), new String(contrasenaPasswordField.getPassword()),
							emailTextField.getText(), nombreTextField.getText(), 2, apellidoTextField.getText(), dniTextField.getText());
					JOptionPane.showMessageDialog(null, api.obtenerIdioma().getString("registro.usuario.exito"),api.obtenerIdioma().getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
					
				} catch (AppException | DataEmptyException | NotNullException | NumbersException
						| AuthenticationException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),api.obtenerIdioma().getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				} 
				
		
		});
		aceptarButton.setBounds(313, 52, 111, 30);
		panelLogin.add(aceptarButton);
		
		JButton cancelarButton = new JButton(api.obtenerIdioma().getString("cancelar"));
		cancelarButton.addActionListener((ActionEvent e) -> {
			dispose();
		});
		cancelarButton.setBounds(311, 103, 113, 30);
		panelLogin.add(cancelarButton);
		
		JLabel emailLabel = new JLabel(api.obtenerIdioma().getString("email"));
		emailLabel.setBounds(39, 119, 98, 14);
		panelLogin.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(170, 114, 126, 20);
		panelLogin.add(emailTextField);
		
	}
}
