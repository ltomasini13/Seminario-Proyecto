package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaPedidoRetiro extends JFrame{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField due�oText;
	private JTextField longitudText;
	private JTextField latitudText;
	private JTextField barrioText;
	private JTextField calleText;
	
	public AltaPedidoRetiro(IApi api, UsuarioDTO usuario) {
		
		setTitle("Alta Pedido Retiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setBounds(12, 34, 56, 16);
		contentPane.add(fechaLabel);
		
		JLabel cargaPesadaLabel = new JLabel("Carga Pesada:");
		cargaPesadaLabel.setBounds(12, 63, 84, 16);
		contentPane.add(cargaPesadaLabel);
		
		JLabel observacionLabel = new JLabel("Observaci\u00F3n:");
		observacionLabel.setBounds(12, 92, 84, 16);
		contentPane.add(observacionLabel);
		
		JLabel calleLabel = new JLabel("Calle");
		calleLabel.setBounds(12, 121, 56, 16);
		contentPane.add(calleLabel);
		
		JLabel barrioLabel = new JLabel("Barrio:");
		barrioLabel.setBounds(12, 150, 56, 16);
		contentPane.add(barrioLabel);
		
		JLabel latitudLabel = new JLabel("Latitud:");
		latitudLabel.setBounds(12, 179, 56, 16);
		contentPane.add(latitudLabel);
		
		JLabel longitudLabel = new JLabel("Longitud:");
		longitudLabel.setBounds(12, 208, 56, 16);
		contentPane.add(longitudLabel);
		
		JLabel due�oLabel = new JLabel("Due\u00F1o:");
		due�oLabel.setBounds(12, 237, 56, 16);
		contentPane.add(due�oLabel);
		
		textField = new JTextField();
		textField.setBounds(103, 46, 153, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		due�oText = new JTextField();
		due�oText.setBounds(103, 234, 153, 22);
		contentPane.add(due�oText);
		due�oText.setColumns(10);
		
		longitudText = new JTextField();
		longitudText.setBounds(103, 205, 153, 22);
		contentPane.add(longitudText);
		longitudText.setColumns(10);
		
		latitudText = new JTextField();
		latitudText.setBounds(103, 176, 153, 22);
		contentPane.add(latitudText);
		latitudText.setColumns(10);
		
		barrioText = new JTextField();
		barrioText.setBounds(103, 147, 153, 22);
		contentPane.add(barrioText);
		barrioText.setColumns(10);
		
		calleText = new JTextField();
		calleText.setBounds(103, 118, 153, 22);
		contentPane.add(calleText);
		calleText.setColumns(10);
		
		JButton btnSeleccionarResiduos = new JButton("SELECCIONAR RESIDUOS");
		btnSeleccionarResiduos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnSeleccionarResiduos.setBounds(268, 60, 164, 43);
		contentPane.add(btnSeleccionarResiduos);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(295, 121, 99, 33);
		contentPane.add(btnCancelar);
	}
}
