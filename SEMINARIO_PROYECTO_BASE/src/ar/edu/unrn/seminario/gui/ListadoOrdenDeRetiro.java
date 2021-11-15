package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;

public class ListadoOrdenDeRetiro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton botonAsignar, botonRealizarVisita;
	private DefaultTableModel modelo;
	private IApi api;
	private JButton botonCancelarOrden;
	private JScrollPane scrollPane;
	
	
	public ListadoOrdenDeRetiro(IApi api) throws SintaxisSQLException {
		setTitle("LISTADO DE ORDENES");
		this.api = api;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		
		/*Carga los datos a la tabla*/
		cargarTabla();
		cargarBotones();
		
		
		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false);
	}

	
	
	
	
	public ListadoOrdenDeRetiro(IApi api, Integer idVisita) throws SintaxisSQLException {
		setTitle("INFORMACIÓN DE LA ORDEN DE RETIRO");
		this.api = api;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		
		/*Carga los datos a la tabla*/
		cargarTablaParaVisita(idVisita);
		
		cargarBotones();

		
		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false);
	}	
	
	
	private void cargarBotones() {

		botonAsignar = new JButton("ASIGNAR RECOLECTOR");
		botonAsignar.addActionListener((ActionEvent e) -> {
			
			try {
				
				Integer idOrden= (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				if(idOrden!=-1) {
					SeleccionRecolector seleccionRecolector = new SeleccionRecolector(api, idOrden);
					seleccionRecolector.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SintaxisSQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});
		
		botonRealizarVisita = new JButton("REALIZAR VISITA");
		botonRealizarVisita.addActionListener((ActionEvent e) -> {
				Integer idOrden = (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				SeleccionResiduosRetirados seleccionResiduosRetirados = new SeleccionResiduosRetirados(api, idOrden);
				seleccionResiduosRetirados.setVisible(true);
			
		});

		JButton botonCerrarButton = new JButton("Cerrar");
		botonCerrarButton.addActionListener((ActionEvent e) -> {
				dispose();
		});

		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		botonCancelarOrden = new JButton("CANCELAR ORDEN");
		botonCancelarOrden.addActionListener((ActionEvent e) -> {
			int confirmacion = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea cancelar la orden?");
			
			if(confirmacion==0) {
				Integer idOrden= (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					api.cancelarOrden(idOrden);				
					JOptionPane.showMessageDialog(null, "La orden ha sido cancelada", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
					
					cargarTabla();
					
				} catch (StateException | SintaxisSQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "INFORMACIÓN", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			
			
		});

		pnlBotonesOperaciones.add(botonCancelarOrden);
		pnlBotonesOperaciones.add(botonAsignar);
		pnlBotonesOperaciones.add(botonRealizarVisita);
		pnlBotonesOperaciones.add(botonCerrarButton);
	}
	
	private void habilitarBotones(boolean b) {
		botonAsignar.setEnabled(b);
		botonRealizarVisita.setEnabled(b);
		botonCancelarOrden.setEnabled(b);

	}
	
	
	private void crearEstructuraTabla() {
		table = new JTable();
		String[] titulos = { "ID", "FECHA ORDEN", "ESTADO", "FECHA DEL PEDIDO", "RECOLECTOR"};  //falta agregar para que pueda ver el pedido a que esta asociada

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
	}
	
	
	private void visibilizarTabla() {
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
		scrollPane.setViewportView(table);
	}
	
	private void cargarTabla() throws SintaxisSQLException {
		
		crearEstructuraTabla();
		
		List<OrdenDeRetiroDTO> ordenes = api.obtenerOrdenes();
		
		for (OrdenDeRetiroDTO o : ordenes) {
			modelo.addRow(new Object[] { o.obtenerId(), o.obtenerFecha(), o.obtenerEstado(), o.obtenerFechaPedido(), o.obtenerNombreApeRecolector() });
			
		}
		
		visibilizarTabla();
	}
	
	
	private void cargarTablaParaVisita(Integer idVisita) throws SintaxisSQLException {
		
		crearEstructuraTabla();
		
		OrdenDeRetiroDTO o= api.obtenerOrden(idVisita);
		
		modelo.addRow(new Object[] { o.obtenerId(), o.obtenerFecha(), o.obtenerEstado(), o.obtenerFechaPedido(), o.obtenerNombreApeRecolector() });
		
		visibilizarTabla();
	}
	
}
