package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class RegistrarCampaņa extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField descripcionText;
	private ResourceBundle labels;
	public RegistrarCampaņa(IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("registrar.campana"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel nombreLabel = new JLabel(labels.getString("nombre.campana"));
		nombreLabel.setBounds(12, 13, 106, 16);
		contentPane.add(nombreLabel);
		
		JLabel descripcionLabel = new JLabel(labels.getString("descripcion"));
		descripcionLabel.setBounds(12, 54, 78, 16);
		contentPane.add(descripcionLabel);
		
		JButton aceptarBoton = new JButton(labels.getString("continuar"));
		aceptarBoton.addActionListener((ActionEvent e) -> {
			try {
				api.registrarCampaņa(nombreText.getText(), descripcionText.getText());
				JOptionPane.showMessageDialog(null,labels.getString("campana.exito"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} catch (AppException | DataEmptyException | NotNullException | DateException | CreationValidationException | InstanceException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);

			}
			this.dispose();
		});
		aceptarBoton.setBounds(12, 193, 97, 25);
		contentPane.add(aceptarBoton);
		
		JButton borrarBoton = new JButton(labels.getString("limpiar"));
		borrarBoton.addActionListener((ActionEvent e) -> {
			nombreText.setText(null);
			descripcionText.setText(null);
		});
		borrarBoton.setBounds(138, 193, 97, 25);
		contentPane.add(borrarBoton);
		
		JButton cancelarBoton = new JButton(labels.getString("cancelar"));
		cancelarBoton.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		cancelarBoton.setBounds(267, 193, 97, 25);
		contentPane.add(cancelarBoton);
		
		nombreText = new JTextField();
		nombreText.setBounds(130, 10, 165, 22);
		contentPane.add(nombreText);
		nombreText.setColumns(10);
		
		descripcionText = new JTextField();
		descripcionText.setBounds(102, 51, 193, 22);
		contentPane.add(descripcionText);
		descripcionText.setColumns(10);
		
//		JButton seleccionarCatalogo = new JButton("SELECCIONAR CAT\u00C1LOGO");
//		seleccionarCatalogo.addActionListener((ActionEvent e) -> {
//			try {
//				ListadoBeneficio beneficios = new ListadoBeneficio(api);
//				beneficios.setVisible(true);
//			} catch (AppException | DataEmptyException | NotNullException | NumbersException e1) {
//				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE); 
//				
//			}
//			
//		});
//		seleccionarCatalogo.setBounds(102, 120, 199, 25);
//		contentPane.add(seleccionarCatalogo);
	}
}
