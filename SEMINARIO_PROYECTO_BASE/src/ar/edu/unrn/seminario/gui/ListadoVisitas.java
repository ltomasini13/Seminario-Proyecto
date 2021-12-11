package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.VisitaDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Visita;

public class ListadoVisitas extends JFrame {

	private JPanel contentPane;
	private IApi api;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel modelo;
	private JPopupMenu popupMenu;
	String[] titulos = { "ID", "FECHA VISITA", "OBSERVACIÓN", "FECHA ORDEN"}; 
	private JButton cerrarButton; 
	/**
	 * Create the frame.
	 * @throws EmptyListException 
	 */
	public ListadoVisitas(IApi api) throws EmptyListException {
		setTitle("LISTADO VISITAS");
		this.api = api;

		inicializarVentana();
		cargarEstructuraTabla();
		cargarDatosTabla();
		visibilizarTabla();
		cargarPanelDeOperaciones();
		cargarMenuPopup();
		
	}

	public ListadoVisitas(IApi api, Integer idOrden) throws EmptyListException {
		setTitle("LISTADO VISITAS");
		this.api = api;

		inicializarVentana();
		cargarEstructuraTabla();
		cargarDatosTabla(idOrden);
		visibilizarTabla();
		cargarPanelDeOperaciones();
		cargarMenuPopup();
		
	}
	


	private void cargarPanelDeOperaciones() {
		cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener((ActionEvent e) -> {
				popupMenu.setVisible(false);
				dispose();
		});
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		pnlBotonesOperaciones.add(cerrarButton);
	}



	private void visibilizarTabla() {
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(arg0.getButton()==1) {
					popupMenu.setVisible(false);
				}
				
				if(table.getSelectedRow()!=-1) {
						
						if (arg0.getButton()==3) {
							if(!popupMenu.isVisible()) {
								popupMenu.setLocation(arg0.getLocationOnScreen());
								popupMenu.setVisible(true);
							}
							
							
							
						}
				}
			}
		});
		scrollPane.setViewportView(table);
	}



	private void cargarDatosTabla() throws EmptyListException {
		List<VisitaDTO> visitas;
		try {
			visitas = api.obtenerVisitas();
			for (VisitaDTO v : visitas) {
				modelo.addRow(new Object[] {v.obtenerId(), v.obtenerFechaVisita(), v.obtenerObservacion(), v.obtenerFechaOrden()});
				
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	private void cargarDatosTabla(Integer idOrden) throws EmptyListException {
		List<VisitaDTO> visitas;
		try {
			visitas = api.obtenerVisitasDeLaOrden(idOrden);
			for (VisitaDTO vis : visitas) {
				modelo.addRow(new Object[] {vis.obtenerId(), vis.obtenerFechaVisita(), vis.obtenerObservacion(), vis.obtenerFechaOrden()});
				
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	private void cargarEstructuraTabla() {
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		table = new JTable();
		

//		table.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				// Habilitar botones
//				habilitarBotones(true);
//
//			}
//
//			
//		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
	}



	private void inicializarVentana() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}



	private void cargarMenuPopup() {
		popupMenu= new JPopupMenu();
		JMenuItem menuItemPopopInfoDeLaOrden = new JMenuItem("Mas info. de la orden");
		menuItemPopopInfoDeLaOrden.addActionListener((ActionEvent arg0) ->{
			popupMenu.setVisible(false);
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				
				try {
					
					Integer idVisita= (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
					ListadoOrdenDeRetiro listadoOrdenes = new ListadoOrdenDeRetiro(api, idVisita);
					listadoOrdenes.setVisible(true);
				} catch (SintaxisSQLException e1) {
					// VER!!!
				}
			}
		});
		popupMenu.add(menuItemPopopInfoDeLaOrden);
		
		JMenuItem menuItemPopupResiduosRetirados = new JMenuItem("Ver los residuos retirados");
		menuItemPopupResiduosRetirados.addActionListener((ActionEvent arg0) ->{
			popupMenu.setVisible(false);
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				
				Integer idVisita= (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				ListadoResiduosRetirados listadoResiduosRetirados=new ListadoResiduosRetirados(api, idVisita);
				listadoResiduosRetirados.setVisible(true);
			}
		});
		popupMenu.add(menuItemPopupResiduosRetirados);
	}
//	private void habilitarBotones(boolean b) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
