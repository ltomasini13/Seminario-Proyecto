package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.dto.CanjeDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListadoCanjes extends JFrame {

	private JPanel contentPane;
	private IApi api;
	private JPanel pnlBotonesOperaciones;
	private JTable table;
	private DefaultTableModel modelo;
	private JButton btnCerrar;
	private ResourceBundle labels;
	
	public ListadoCanjes(IApi api) throws EmptyListException, AppException, InstanceException {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("listado.canjes"));
		this.api=api;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		btnCerrar = new JButton(labels.getString("cerrar"));
		btnCerrar.addActionListener((ActionEvent arg0) ->{
			dispose();
		});
		pnlBotonesOperaciones.add(btnCerrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		table = new JTable();
		String[] titulos = { labels.getString("fecha.canje"), labels.getString("puntos.canjeados"),labels.getString("beneficio.canjeado"), labels.getString("ciudadano.canjeo")};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<CanjeDTO> canjes;
			canjes = api.obtenerCanjes();
			
			for (CanjeDTO canje : canjes) {
				modelo.addRow(new Object[] {canje.obtenerFecha(), canje.obtenerPuntosCanjeados(), canje.obtenerBeneficio(), canje.obtenerCiudadano()});
			}
		
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		
	}

}
