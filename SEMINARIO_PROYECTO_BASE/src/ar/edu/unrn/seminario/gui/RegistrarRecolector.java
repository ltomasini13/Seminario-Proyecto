package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class RegistrarRecolector extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField dniText;
	private JTextField emailText;
	private ResourceBundle labels;
	public RegistrarRecolector(IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("registrar.recolector"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel nombreLabel = new JLabel(labels.getString("nombre"));
		nombreLabel.setBounds(12, 24, 109, 25);
		contentPane.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel(labels.getString("apellido"));
		apellidoLabel.setBounds(12, 62, 109, 16);
		contentPane.add(apellidoLabel);
		
		JLabel dniLabel = new JLabel(labels.getString("dni"));
		dniLabel.setBounds(12, 98, 109, 16);
		contentPane.add(dniLabel);
		
		JLabel lblEmail = new JLabel(labels.getString("email"));
		lblEmail.setBounds(12, 138, 109, 16);
		contentPane.add(lblEmail);
		
		nombreText = new JTextField();
		nombreText.setBounds(131, 25, 116, 22);
		contentPane.add(nombreText);
		nombreText.setColumns(10);
		
		apellidoText = new JTextField();
		apellidoText.setBounds(131, 59, 116, 22);
		contentPane.add(apellidoText);
		apellidoText.setColumns(10);
		
		dniText = new JTextField();
		dniText.setBounds(131, 95, 116, 22);
		contentPane.add(dniText);
		dniText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(131, 135, 116, 22);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		JButton aceptarButton = new JButton(labels.getString("continuar"));
		aceptarButton.addActionListener((ActionEvent e) -> {
			
				try {
					api.registrarRecolector(nombreText.getText(), apellidoText.getText(), dniText.getText(), emailText.getText());
					JOptionPane.showMessageDialog(null, labels.getString("recolector.exito"),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				} catch (AppException | NotNullException | DataEmptyException | DuplicateUniqueKeyException
						| NumbersException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
				}
			

		});
		aceptarButton.setBounds(286, 44, 97, 25);
		contentPane.add(aceptarButton);
		
		JButton cancelarButton = new JButton(labels.getString("cancelar"));
		cancelarButton.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		cancelarButton.setBounds(286, 94, 97, 25);
		contentPane.add(cancelarButton);
	}
}
