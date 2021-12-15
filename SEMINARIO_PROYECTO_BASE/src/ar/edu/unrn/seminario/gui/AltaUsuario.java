package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import javax.swing.JPasswordField;

public class AltaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioTextField;
	private JTextField nombreTextField;
	private JTextField emailTextField;
	private JComboBox rolComboBox;
	private JPasswordField contrasenaField;
	private List<RolDTO> roles = new ArrayList<>();
	private ResourceBundle labels;

	/**
	 * Create the frame.
	 */
	public AltaUsuario(IApi api) {
		labels=api.obtenerIdioma();
		// Obtengo los roles
		try {
			this.roles = api.obtenerRoles();
		} catch (AppException  | InstanceException e2) {
			JOptionPane.showMessageDialog(null,e2.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
			
		}
			
		


		setTitle(labels.getString("alta.usuario"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel usuarioLabel = new JLabel(labels.getString("loguin.usuario"));
		usuarioLabel.setBounds(43, 16, 76, 16);
		contentPane.add(usuarioLabel);

		JLabel contrasenaLabel = new JLabel(labels.getString("loguin.contrasena"));
		contrasenaLabel.setBounds(43, 56, 93, 16);
		contentPane.add(contrasenaLabel);

		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(148, 13, 160, 22);
		contentPane.add(usuarioTextField);
		usuarioTextField.setColumns(10);

		JButton aceptarButton = new JButton(labels.getString("continuar"));
		aceptarButton.addActionListener((ActionEvent e) -> {

				RolDTO rol = roles.get(rolComboBox.getSelectedIndex());
				
					
						try {
							api.registrarUsuario(usuarioTextField.getText(), contrasenaField.getText(),
									nombreTextField.getText(), emailTextField.getText(), rol.getCodigo());
							JOptionPane.showMessageDialog(null, labels.getString("registro.usuario.exito"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (AppException | DataEmptyException | NotNullException
								| InstanceException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
						}
						
						
				
		});
		aceptarButton.setBounds(218, 215, 97, 25);
		contentPane.add(aceptarButton);

		JButton cancelarButton = new JButton(labels.getString("cancelar"));
		cancelarButton.addActionListener((ActionEvent e) -> {
				dispose();
		});
		cancelarButton.setBounds(323, 215, 97, 25);
		contentPane.add(cancelarButton);

		JLabel nombreLabel = new JLabel(labels.getString("nombre"));
		nombreLabel.setBounds(43, 88, 56, 16);
		contentPane.add(nombreLabel);

		JLabel emailLabel = new JLabel(labels.getString("email"));
		emailLabel.setBounds(43, 125, 56, 16);
		contentPane.add(emailLabel);

		JLabel rolLabel = new JLabel(labels.getString("rol"));
		rolLabel.setBounds(43, 154, 56, 16);
		contentPane.add(rolLabel);

		nombreTextField = new JTextField();
		nombreTextField.setBounds(148, 85, 160, 22);
		contentPane.add(nombreTextField);
		nombreTextField.setColumns(10);

		emailTextField = new JTextField();
		emailTextField.setBounds(148, 122, 160, 22);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);

		rolComboBox = new JComboBox();
		rolComboBox.setBounds(148, 151, 160, 22);
		contentPane.add(rolComboBox);
		
		contrasenaField = new JPasswordField();
		contrasenaField.setBounds(148, 54, 160, 20);
		contentPane.add(contrasenaField);

		for (RolDTO rol : this.roles) {
			rolComboBox.addItem(rol.getNombre());
		}

	}
}
