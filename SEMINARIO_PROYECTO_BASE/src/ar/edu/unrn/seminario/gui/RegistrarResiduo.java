package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class RegistrarResiduo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldTipoResiduo;
	private JTextField textFieldPuntos;
	
	public RegistrarResiduo(IApi api) {
		setTitle("Registrar Residuo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textFieldTipoResiduo = new JTextField();
		textFieldTipoResiduo.setBounds(110, 58, 131, 22);
		contentPane.add(textFieldTipoResiduo);
		textFieldTipoResiduo.setColumns(10);
		
		JLabel lblTipoResiduo = new JLabel("Tipo Residuo:");
		lblTipoResiduo.setBounds(12, 60, 86, 19);
		contentPane.add(lblTipoResiduo);
		
		JLabel lblPuntos = new JLabel("Puntos:");
		lblPuntos.setBounds(12, 100, 56, 16);
		contentPane.add(lblPuntos);
		
		textFieldPuntos = new JTextField();
		textFieldPuntos.setBounds(110, 93, 131, 22);
		contentPane.add(textFieldPuntos);
		textFieldPuntos.setColumns(10);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBounds(151, 184, 97, 25);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(260, 184, 97, 25);
		contentPane.add(btnCancelar);
	}
}
