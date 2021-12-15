package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class GenerarOrden extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField dniText;
	private JTextField emailText;
	private JTextField estadoText;
	List<PedidoRetiro> listaPedido = null;
	List<Recolector> listaRecolector = null;
	
	
	public GenerarOrden(IApi api, Integer idPedido) {
		setTitle("GENERAR ORDEN DE RETIRO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel estadoLabel = new JLabel("Estado:");
		estadoLabel.setBounds(12, 26, 56, 16);
		contentPane.add(estadoLabel);
		
		estadoText = new JTextField();
		estadoText.setBounds(80, 23, 140, 22);
		contentPane.add(estadoText);
		estadoText.setColumns(10);
		
		JButton asignarBoton = new JButton("ASIGNAR RECOLECTOR");
		asignarBoton.addActionListener((ActionEvent e) -> {
			ListadoRecolector listado;
			try {
				listado = new ListadoRecolector(api);	
				listado.setVisible(true);
				this.dispose();
			} catch (SintaxisSQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		asignarBoton.setBounds(12, 124, 177, 25);
		contentPane.add(asignarBoton);
		
		JButton cancelarBoton = new JButton("CANCELAR");
		cancelarBoton.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		cancelarBoton.setBounds(261, 124, 97, 25);
		contentPane.add(cancelarBoton);
		
		JButton aceptarBoton = new JButton("ACEPTAR");
		aceptarBoton.addActionListener((ActionEvent arg0) ->{
			
				try {
					api.generarOrden(idPedido);
					JOptionPane.showMessageDialog(null, "La orden se generó con éxito", "Confirmar", JOptionPane.INFORMATION_MESSAGE); 
					dispose();
				} catch (AppException | SintaxisSQLException | CreationValidationException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Confirmar", JOptionPane.INFORMATION_MESSAGE); 
					
				}
				
			
		
		});
		aceptarBoton.setBounds(261, 22, 97, 25);
		contentPane.add(aceptarBoton);
	}
}
