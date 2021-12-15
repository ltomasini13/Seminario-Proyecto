package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class AltaCiudadano extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField dniText;
	private JTextField emailText;
	private Integer idVivienda;
	private ResourceBundle labels;

	/**
	 * Create the frame.
	 */
	public AltaCiudadano(IApi api, Integer idVivienda) {
		labels=api.obtenerIdioma();
		this.idVivienda=idVivienda;
		setTitle(labels.getString("nuevo.ciudadano"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel nombreLabel = new JLabel(labels.getString("nombre"));
		nombreLabel.setBounds(12, 44, 56, 25);
		contentPane.add(nombreLabel);
		
		JLabel apellidoLabel = new JLabel(labels.getString("apellido"));
		apellidoLabel.setBounds(12, 84, 56, 16);
		contentPane.add(apellidoLabel);
		
		JLabel dniLabel = new JLabel(labels.getString("dni"));
		dniLabel.setBounds(12, 124, 56, 16);
		contentPane.add(dniLabel);
		
		JLabel lblEmail = new JLabel(labels.getString("email"));
		lblEmail.setBounds(12, 164, 56, 16);
		contentPane.add(lblEmail);
		
		nombreText = new JTextField();
		nombreText.setBounds(78, 44, 116, 22);
		contentPane.add(nombreText);
		nombreText.setColumns(10);
		
		apellidoText = new JTextField();
		apellidoText.setBounds(78, 84, 116, 22);
		contentPane.add(apellidoText);
		apellidoText.setColumns(10);
		
		dniText = new JTextField();
		dniText.setBounds(78, 124, 116, 22);
		contentPane.add(dniText);
		dniText.setColumns(10);
		
		emailText = new JTextField();
		emailText.setBounds(78, 164, 116, 22);
		contentPane.add(emailText);
		emailText.setColumns(10);
		
		JButton aceptarButton = new JButton(labels.getString("continuar"));
		aceptarButton.addActionListener((ActionEvent e) -> {
			
			try {
				ViviendaDTO viviendaDTO = api.obtenerVivienda(idVivienda);
				int seleccion = JOptionPane.showOptionDialog(null,labels.getString("dialog.datos.vivienda")+"\n\n"+labels.getString("dialog.direccion")+viviendaDTO.obtenerCalle()+
						" "+viviendaDTO.obtenerNumero()+"\n"+labels.getString("vivienda.latitud")+" "+viviendaDTO.obtenerLatitud()+labels.getString("vivienda.longitud")+" "+viviendaDTO.obtenerLongitud()+"\n\n", labels.getString("dialog.confirmacion"), JOptionPane.YES_NO_CANCEL_OPTION,
						   JOptionPane.INFORMATION_MESSAGE, null,
						   new Object[] { labels.getString("confirmar"), labels.getString("cancelar")}, null);
						
						if (seleccion != -1) {
							if(seleccion==0) {
								api.cambiarDueño(idVivienda, nombreText.getText(), apellidoText.getText(), dniText.getText());
								JOptionPane.showMessageDialog(null, labels.getString("cambio.dueno.exitoso"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
								this.dispose();
							}
							
						}
				
				
				
			} catch (NotNullException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			} catch (DataEmptyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"),JOptionPane.ERROR_MESSAGE);
			} catch (NumbersException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			} catch (AppException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"),JOptionPane.ERROR_MESSAGE);
			} catch (InstanceException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
		});
		aceptarButton.setBounds(286, 44, 97, 25);
		contentPane.add(aceptarButton);
		
		JButton cancelarButton = new JButton(labels.getString("cancelar"));
		cancelarButton.addActionListener((ActionEvent e) -> {
			this.dispose();
			ListadoVivienda listadoViv;
			try {
				listadoViv = new ListadoVivienda(api);
				listadoViv.setVisible(true);
			} catch (EmptyListException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		cancelarButton.setBounds(286, 94, 97, 25);
		contentPane.add(cancelarButton);
	}

}
