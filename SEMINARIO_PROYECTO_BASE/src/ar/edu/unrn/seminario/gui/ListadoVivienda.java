package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import ar.edu.unrn.seminario.exception.UnfinishedException;
import ar.edu.unrn.seminario.modelo.Usuario;

import javax.swing.JButton;
public class ListadoVivienda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	public  Integer id_vivienda;
	
	IApi api;



	/**
	 * Create the frame.
	 */
	public ListadoVivienda(IApi api) throws EmptyListException {
		setTitle("LISTADO DE VIVIENDAS");


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
		String[] adminTitulos = { "ID", "CALLE", "NUMERO", "BARRIO", "LATITUD", "LONGITUD", "DUEÑO"};
		
		
		
		String[] recicTitulos = {"ID", "CALLE", "NUMERO", "BARRIO", "LATITUD", "LONGITUD"};
		List<ViviendaDTO> viviendas= new ArrayList<ViviendaDTO>();
		if (api.esUsuarioAdmin()) {
			modelo = new DefaultTableModel(new Object[][] {}, adminTitulos);
			
			viviendas=api.obtenerViviendas();
			for (ViviendaDTO viv : viviendas) {
				modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
						viv.obtenerLatitud(), viv.obtenerLongitu(), viv.obtenerNomApeDueño()});
			}
			
			
		}
		
		
		if(api.esUsuarioReciclador()) {
				modelo = new DefaultTableModel(new Object[][] {}, recicTitulos);
				
				viviendas=api.obtenerViviendas();
				for (ViviendaDTO viv : viviendas) {
					modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
							viv.obtenerLatitud(), viv.obtenerLongitu()});
					
				}
		}
		
		
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(table);
		
		
		
		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener((ActionEvent e) -> {
				dispose();
		});
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		JButton btnPedirRecoleccion = new JButton("Pedir recolecci\u00F3n");
		btnPedirRecoleccion.addActionListener((ActionEvent e)->{
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna vivienda", "", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				
				this.id_vivienda=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					api.pedidoPendiente(id_vivienda);
					SeleccionResiduos sr = new SeleccionResiduos(api, id_vivienda);
					sr.setVisible(true);
					dispose();
				} catch (UnfinishedException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		pnlBotonesOperaciones.add(btnPedirRecoleccion);
		pnlBotonesOperaciones.add(cerrarButton);

	}
}
