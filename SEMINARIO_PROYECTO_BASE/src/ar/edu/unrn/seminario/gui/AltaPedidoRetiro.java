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
import javax.swing.JRadioButton;

public class AltaPedidoRetiro extends JFrame{

	private JPanel contentPane;
	private JTextField dueñoText;
	private JTextField longitudText;
	private JTextField latitudText;
	private JTextField barrioText;
	private JTextField calleText;
	private JTextField observacionText;
	private JTextField textField_1;
	
	public AltaPedidoRetiro(IApi api, UsuarioDTO usuario) {
		
		setTitle("Alta Pedido Retiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel fechaLabel = new JLabel("Fecha:");
		fechaLabel.setBounds(12, 13, 56, 16);
		contentPane.add(fechaLabel);
		
		JLabel cargaPesadaLabel = new JLabel("Carga Pesada:");
		cargaPesadaLabel.setBounds(12, 42, 84, 16);
		contentPane.add(cargaPesadaLabel);
		
		JLabel observacionLabel = new JLabel("Observaci\u00F3n:");
		observacionLabel.setBounds(12, 71, 84, 16);
		contentPane.add(observacionLabel);
		
		JLabel calleLabel = new JLabel("Calle");
		calleLabel.setBounds(12, 100, 56, 16);
		contentPane.add(calleLabel);
		
		JLabel barrioLabel = new JLabel("Barrio:");
		barrioLabel.setBounds(12, 129, 56, 16);
		contentPane.add(barrioLabel);
		
		JLabel latitudLabel = new JLabel("Latitud:");
		latitudLabel.setBounds(12, 166, 56, 16);
		contentPane.add(latitudLabel);
		
		JLabel longitudLabel = new JLabel("Longitud:");
		longitudLabel.setBounds(12, 195, 56, 16);
		contentPane.add(longitudLabel);
		
		JLabel dueñoLabel = new JLabel("Due\u00F1o:");
		dueñoLabel.setBounds(12, 224, 56, 16);
		contentPane.add(dueñoLabel);
		
		dueñoText = new JTextField();
		dueñoText.setBounds(103, 221, 153, 22);
		contentPane.add(dueñoText);
		dueñoText.setColumns(10);
		
		longitudText = new JTextField();
		longitudText.setBounds(103, 192, 153, 22);
		contentPane.add(longitudText);
		longitudText.setColumns(10);
		
		latitudText = new JTextField();
		latitudText.setBounds(103, 163, 153, 22);
		contentPane.add(latitudText);
		latitudText.setColumns(10);
		
		barrioText = new JTextField();
		barrioText.setBounds(103, 129, 153, 22);
		contentPane.add(barrioText);
		barrioText.setColumns(10);
		
		calleText = new JTextField();
		calleText.setBounds(103, 100, 153, 22);
		contentPane.add(calleText);
		calleText.setColumns(10);
		
		JButton seleccionarResiduosBoton = new JButton("SELECCIONAR RESIDUOS");
		seleccionarResiduosBoton.addActionListener((ActionEvent e) ->{
			
		});
		
		seleccionarResiduosBoton.setBounds(268, 60, 164, 43);
		contentPane.add(seleccionarResiduosBoton);
		
		JButton cancelarBoton = new JButton("CANCELAR");
		cancelarBoton.setBounds(295, 121, 99, 33);
		contentPane.add(cancelarBoton);
		
		observacionText = new JTextField();
		observacionText.setBounds(103, 71, 153, 22);
		contentPane.add(observacionText);
		observacionText.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 13, 153, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JRadioButton siRadioButton = new JRadioButton("SI");
		siRadioButton.setBounds(104, 38, 56, 25);
		contentPane.add(siRadioButton);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
