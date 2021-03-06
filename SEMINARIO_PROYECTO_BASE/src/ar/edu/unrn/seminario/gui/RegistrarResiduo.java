package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class RegistrarResiduo extends JFrame {

	private JPanel contentPane;
	private JTextField tipoResiduoTextField;
	private JTextField puntosTextField;
	private ResourceBundle labels;
	public RegistrarResiduo(IApi api) {

		labels=api.obtenerIdioma();
		setTitle(labels.getString("registrar.residuo"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTipoResiduo = new JLabel(labels.getString("tipo.residuo"));
		lblTipoResiduo.setBounds(12, 60, 86, 19);
		contentPane.add(lblTipoResiduo);
		
		JLabel lblPuntos = new JLabel(labels.getString("puntos"));
		lblPuntos.setBounds(12, 100, 56, 16);
		contentPane.add(lblPuntos);
		
		JButton btnAceptar = new JButton(labels.getString("continuar"));
		btnAceptar.addActionListener((ActionEvent e) -> {
				
				
					
					try {
						api.registrarResiduo(tipoResiduoTextField.getText(), puntosTextField.getText());
						JOptionPane.showMessageDialog(null, labels.getString("residuo.exito"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
						this.dispose();
					} catch (AppException | NumbersException | NotNullException | DataEmptyException
							| DuplicateUniqueKeyException | InstanceException e1) {
						JOptionPane.showMessageDialog(null,e1.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
						
					}
					
					
			
		});
		btnAceptar.setBounds(151, 184, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton(labels.getString("cancelar"));
		btnCancelar.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		btnCancelar.setBounds(260, 184, 97, 25);
		contentPane.add(btnCancelar);
		
		tipoResiduoTextField = new JTextField();
		tipoResiduoTextField.setBounds(115, 58, 175, 22);
		contentPane.add(tipoResiduoTextField);
		tipoResiduoTextField.setColumns(10);
		
		puntosTextField = new JTextField();
		puntosTextField.setText("");
		puntosTextField.setBounds(115, 97, 175, 22);
		contentPane.add(puntosTextField);
		puntosTextField.setColumns(10);
	}
}
