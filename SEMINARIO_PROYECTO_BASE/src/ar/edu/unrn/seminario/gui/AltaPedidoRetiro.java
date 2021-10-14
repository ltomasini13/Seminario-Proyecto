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
	private JTextField observacionText;
	
	public AltaPedidoRetiro(IApi api, UsuarioDTO usuario) {
		
		setTitle("Alta Pedido Retiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel cargaPesadaLabel = new JLabel("Carga Pesada:");
		cargaPesadaLabel.setBounds(12, 42, 84, 16);
		contentPane.add(cargaPesadaLabel);
		
		JLabel observacionLabel = new JLabel("Observaci\u00F3n:");
		observacionLabel.setBounds(12, 71, 84, 16);
		contentPane.add(observacionLabel);
		
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
		
		JRadioButton siRadioButton = new JRadioButton("SI");
		siRadioButton.setBounds(104, 38, 56, 25);
		contentPane.add(siRadioButton);
		
		JRadioButton noRadioButton = new JRadioButton("NO");
		noRadioButton.setBounds(164, 34, 110, 32);
		contentPane.add(noRadioButton);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
