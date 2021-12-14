package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class RegistrarBeneficio extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField puntosText;
	private ResourceBundle labels;
	public RegistrarBeneficio(IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("registrar.beneficio"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel nombreLabel = new JLabel(labels.getString("nombre.beneficio"));
		nombreLabel.setBounds(12, 29, 138, 16);
		contentPane.add(nombreLabel);
		
		JLabel puntosLabel = new JLabel(labels.getString("puntos"));
		puntosLabel.setBounds(12, 72, 56, 16);
		contentPane.add(puntosLabel);
		
		nombreText = new JTextField();
		nombreText.setBounds(149, 26, 183, 22);
		contentPane.add(nombreText);
		nombreText.setColumns(10);
		
		puntosText = new JTextField();
		puntosText.setBounds(114, 69, 116, 22);
		contentPane.add(puntosText);
		puntosText.setColumns(10);
		
		JButton btnCerrar = new JButton(labels.getString("cerrar"));
		btnCerrar.addActionListener((ActionEvent arg0) ->{
			this.dispose();
		});
		btnCerrar.setBounds(299, 181, 97, 25);
		contentPane.add(btnCerrar);
		
		JButton btnAceptar = new JButton(labels.getString("continuar"));
		btnAceptar.addActionListener((ActionEvent arg0) ->{
			
			try {
				api.registrarBeneficio(nombreText.getText(), puntosText.getText());
				JOptionPane.showMessageDialog(null, labels.getString("beneficio.exito"),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			} catch (AppException | DataEmptyException | NotNullException | NumbersException | InstanceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
		});
		btnAceptar.setBounds(24, 181, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnBorrar = new JButton(labels.getString("limpiar"));
		btnBorrar.addActionListener((ActionEvent arg0) ->{
			nombreText.setText(null);
			puntosText.setText(null);
		});
		btnBorrar.setBounds(155, 181, 97, 25);
		contentPane.add(btnBorrar);
	}
}
