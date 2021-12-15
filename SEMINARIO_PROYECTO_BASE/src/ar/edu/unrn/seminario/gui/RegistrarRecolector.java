package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;

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
	
	public RegistrarRecolector(IApi api) {
		setTitle("REGISTRAR RECOLECTOR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel nombreLabel = new JLabel("Nombre:");
		nombreLabel.setBounds(12, 24, 56, 25);
		contentPane.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel("Apellido:");
		apellidoLabel.setBounds(12, 62, 56, 16);
		contentPane.add(apellidoLabel);
		
		JLabel dniLabel = new JLabel("DNI:");
		dniLabel.setBounds(12, 98, 56, 16);
		contentPane.add(dniLabel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(12, 138, 56, 16);
		contentPane.add(lblEmail);
		
		nombreText = new JTextField();
		nombreText.setBounds(80, 25, 116, 22);
		contentPane.add(nombreText);
		nombreText.setColumns(10);
		
		apellidoText = new JTextField();
		apellidoText.setBounds(80, 59, 116, 22);
		contentPane.add(apellidoText);
		apellidoText.setColumns(10);
		
		dniText = new JTextField();
		dniText.setBounds(80, 95, 116, 22);
		contentPane.add(dniText);
		dniText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(80, 135, 116, 22);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		JButton aceptarButton = new JButton("ACEPTAR");
		aceptarButton.addActionListener((ActionEvent e) -> {
			try {
				api.registrarRecolector(nombreText.getText(), apellidoText.getText(), dniText.getText(), emailText.getText());
				JOptionPane.showMessageDialog(null, "El recolector se creo exitosamente", "CONFIRMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} catch (NotNullException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (DataEmptyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (DuplicateUniqueKeyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (SintaxisSQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (NumbersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (AppException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (InstanceException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		aceptarButton.setBounds(286, 44, 97, 25);
		contentPane.add(aceptarButton);
		
		JButton cancelarButton = new JButton("CANCELAR");
		cancelarButton.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		cancelarButton.setBounds(286, 94, 97, 25);
		contentPane.add(cancelarButton);
	}
}
