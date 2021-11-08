package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Loguin extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioTextField;
	private JPasswordField contrasenaPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IApi api = new PersistenceApi();
					Loguin frame = new Loguin(api);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Loguin(IApi api) {
		setTitle("INICIAR SESIÓN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usuarioLabel = new JLabel("Usuario: ");
		usuarioLabel.setBounds(65, 65, 69, 14);
		contentPane.add(usuarioLabel);
		
		JLabel contrasenaLabel = new JLabel("Contrase\u00F1a:");
		contrasenaLabel.setBounds(65, 122, 69, 14);
		contentPane.add(contrasenaLabel);
		
		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(157, 62, 140, 20);
		contentPane.add(usuarioTextField);
		usuarioTextField.setColumns(10);
		
		contrasenaPasswordField = new JPasswordField();
		contrasenaPasswordField.setBounds(157, 119, 140, 20);
		contentPane.add(contrasenaPasswordField);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener((ActionEvent e) -> {
			try {
				api.loguearUsuario(usuarioTextField.getText(), new String(contrasenaPasswordField.getPassword()));
				VentanaPrincipal vp = new VentanaPrincipal(api);
				vp.setVisible(true);
				dispose();
				
			} catch (SintaxisSQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (AuthenticationException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (NotNullException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (DataEmptyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		botonAceptar.setBounds(137, 184, 89, 23);
		contentPane.add(botonAceptar);
		
		JButton registrarseBoton = new JButton("Registrarse");
		registrarseBoton.addActionListener((ActionEvent e) -> {
			RegistroUsuario registro = new RegistroUsuario(api);
			registro.setVisible(true);
			
		});
		registrarseBoton.setBounds(275, 184, 112, 23);
		contentPane.add(registrarseBoton);
	}
}
