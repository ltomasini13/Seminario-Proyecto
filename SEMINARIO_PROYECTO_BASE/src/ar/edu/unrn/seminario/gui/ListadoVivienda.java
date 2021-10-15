package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.modelo.Usuario;

import javax.swing.JButton;
public class ListadoVivienda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	
	IApi api;

<<<<<<< HEAD

	/**
	 * Create the frame.
	 */
	public ListadoVivienda(IApi api) throws EmptyListException {
=======
	public ListadoVivienda(IApi api, UsuarioDTO usuarioDto) {
>>>>>>> 8b000275cd62562d603c30581e4c5c1694e0bb84
		this.api=api;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		table = new JTable();
		String[] adminTitulos = { "CALLE", "NUMERO", "BARRIO", "LATITUD", "LONGITUD", "DUEÑO"};
		
		
		
		String[] recicTitulos = { "CALLE", "NUMERO", "BARRIO", "LATITUD", "LONGITUD"};
		List<ViviendaDTO> viviendas;
		if (api.esUsuarioAdmin()) {
			modelo = new DefaultTableModel(new Object[][] {}, adminTitulos);
			
			viviendas=api.obtenerViviendas();
			for (ViviendaDTO viv : viviendas) {
				modelo.addRow(new Object[] { viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
						viv.obtenerLatitud(), viv.obtenerLongitu(), viv.obtenerNomApeDueño()});
			}
			
			
		}
		
		
		if(api.esUsuarioReciclador()) {
				modelo = new DefaultTableModel(new Object[][] {}, recicTitulos);
				
				viviendas=api.obtenerViviendas();
				for (ViviendaDTO viv : viviendas) {
					modelo.addRow(new Object[] { viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
							viv.obtenerLatitud(), viv.obtenerLongitu()});
					
				}
		}
		
		
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		
		
		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener((ActionEvent e) -> {
				setVisible(false);
				dispose();
		});
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		JButton btnPedirRecoleccin = new JButton("Pedir recolecci\u00F3n");
		btnPedirRecoleccin.addActionListener((ActionEvent e)->{
			System.out.println(modelo.getValueAt(table.getSelectedRow(), 0));
		});
		pnlBotonesOperaciones.add(btnPedirRecoleccin);
		pnlBotonesOperaciones.add(cerrarButton);

	}
}
