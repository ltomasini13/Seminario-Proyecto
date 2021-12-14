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
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class GenerarOrden extends JFrame {

	private JPanel contentPane;
	private JTextField nombreText;
	private JTextField apellidoText;
	private JTextField dniText;
	private JTextField emailText;
	private JTextField estadoText;
	private List<PedidoRetiro> listaPedido = null;
	private List<Recolector> listaRecolector = null;
	private ResourceBundle labels;
	
	public GenerarOrden(IApi api, Integer idPedido) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("generar.orden"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel estadoLabel = new JLabel(labels.getString("estado.orden"));
		estadoLabel.setBounds(12, 26, 56, 16);
		contentPane.add(estadoLabel);
		
		estadoText = new JTextField();
		estadoText.setBounds(80, 23, 140, 22);
		contentPane.add(estadoText);
		estadoText.setColumns(10);
		
		JButton asignarBoton = new JButton(labels.getString("asignar.recolector"));
		asignarBoton.addActionListener((ActionEvent e) -> {
			ListadoRecolector listado;
				listado = new ListadoRecolector(api);	
				listado.setVisible(true);
				this.dispose();
			
		});
		asignarBoton.setBounds(12, 124, 177, 25);
		contentPane.add(asignarBoton);
		
		JButton cancelarBoton = new JButton(labels.getString("cancelar"));
		cancelarBoton.addActionListener((ActionEvent e) -> {
			this.dispose();
		});
		cancelarBoton.setBounds(261, 124, 97, 25);
		contentPane.add(cancelarBoton);
		
		JButton aceptarBoton = new JButton(labels.getString("continuar"));
		aceptarBoton.addActionListener((ActionEvent arg0) ->{
			
				try {
					api.generarOrden(idPedido);
					JOptionPane.showMessageDialog(null, labels.getString("orden.exito"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE); 
					dispose();
				} catch (AppException | CreationValidationException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.INFORMATION_MESSAGE); 
					
				}
				
			
		
		});
		aceptarBoton.setBounds(261, 22, 97, 25);
		contentPane.add(aceptarBoton);
	}
}
