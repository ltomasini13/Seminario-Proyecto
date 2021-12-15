package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class Loguin extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioTextField;
	private JPasswordField contrasenaPasswordField;
	private ResourceBundle labels;

	/**
	 * Create the frame.
	 */
	public Loguin(IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("loguin"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usuarioLabel = new JLabel(labels.getString("loguin.usuario"));
		usuarioLabel.setBounds(65, 65, 69, 14);
		contentPane.add(usuarioLabel);
		
		JLabel contrasenaLabel = new JLabel(labels.getString("loguin.contrasena"));
		contrasenaLabel.setBounds(65, 122, 69, 14);
		contentPane.add(contrasenaLabel);
		
		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(157, 62, 140, 20);
		contentPane.add(usuarioTextField);
		usuarioTextField.setColumns(10);
		
		contrasenaPasswordField = new JPasswordField();
		contrasenaPasswordField.setBounds(157, 119, 140, 20);
		contentPane.add(contrasenaPasswordField);
		
		JButton botonIniciarSesion = new JButton(labels.getString("loguin.iniciar.sesion"));
		botonIniciarSesion.addActionListener((ActionEvent e) -> {
			
				try {
					api.loguearUsuario(usuarioTextField.getText(), new String(contrasenaPasswordField.getPassword()));
					VentanaPrincipal vp = new VentanaPrincipal(api);
					vp.setVisible(true);
					dispose();
				} catch (AppException | AuthenticationException | NotNullException | DataEmptyException
						| InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
				}
				
			
			
		});
		botonIniciarSesion.setBounds(65, 184, 161, 23);
		contentPane.add(botonIniciarSesion);
		
		JButton registrarseBoton = new JButton(labels.getString("loguin.registrarse"));
		registrarseBoton.addActionListener((ActionEvent e) -> {
			RegistroUsuario registro = new RegistroUsuario(api);
			registro.setVisible(true);
			
		});
		registrarseBoton.setBounds(252, 184, 161, 23);
		contentPane.add(registrarseBoton);
	}
}
