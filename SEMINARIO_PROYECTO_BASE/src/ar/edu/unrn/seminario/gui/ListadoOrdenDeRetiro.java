package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import ar.edu.unrn.seminario.dto.CiudadanoDTO;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;

public class ListadoOrdenDeRetiro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton botonAsignar, botonRealizarVisita;
	private DefaultTableModel modelo;
	private IApi api;
	private JButton botonCancelarOrden;
	private JScrollPane scrollPane;
	private JPopupMenu popupMenu;
<<<<<<< HEAD
	private ResourceBundle labels;
	private JPanel panel;
	private JLabel lblFiltros;
	private JCheckBox checkboxOrdenConcretada;
	private JButton botonAplicar;
	private JCheckBox checkboxOrdenPendiente;
=======
	
>>>>>>> parent of 4977a25 (INTERNACIONALIZACION)
	
	public ListadoOrdenDeRetiro(IApi api)  {
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
		
		cargarMenuPopup();
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
		
		cargarMenuPopup();
	}	
	
	
	private void cargarBotones() {

		botonAsignar = new JButton("ASIGNAR RECOLECTOR");
		botonAsignar.addActionListener((ActionEvent e) -> {
			
			try {
				
				Integer idOrden= (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				if(idOrden!=-1) {
					dispose();
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
				popupMenu.setVisible(false);
				dispose();
				Integer idOrden = (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				SeleccionResiduosRetirados seleccionResiduosRetirados = new SeleccionResiduosRetirados(api, idOrden);
				seleccionResiduosRetirados.setVisible(true);
			
		});

		JButton botonCerrarButton = new JButton("CERRAR");
		botonCerrarButton.addActionListener((ActionEvent e) -> {
				popupMenu.setVisible(false);
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
					
				} catch (StateException | AppException | InstanceException e1) {
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
		cargarTitulos();
		
		table = new JTable();
<<<<<<< HEAD
		 // o.obtenerId(), o.obtenerFecha(), o.obtenerEstado(), o.obtenerFechaPedido(), o.obtenerNombreApeRecolector()
=======
		String[] titulos = { "ID", "FECHA ORDEN", "ESTADO", "FECHA DEL PEDIDO", "RECOLECTOR"};  //falta agregar para que pueda ver el pedido a que esta asociada

>>>>>>> parent of 4977a25 (INTERNACIONALIZACION)
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
	}
	
	private void cargarTitulos() {
		String[] titulos = { "ID", labels.getString("fecha.orden"), labels.getString("titulo.estado"), labels.getString("fecha.pedido"), labels.getString("recolector.orden")};modelo = new DefaultTableModel(new Object[][] {}, titulos);
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
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

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		contentPane.add(panel, BorderLayout.WEST);
		
		lblFiltros = new JLabel("FILTROS");
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFiltros.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblFiltros.setBackground(new Color(240, 240, 240));
		panel.add(lblFiltros);
		
		checkboxOrdenPendiente= new JCheckBox("Ordenes Pendientes");
		panel.add(checkboxOrdenPendiente);
		
		checkboxOrdenConcretada = new JCheckBox("Ordenes Concretadas");
		panel.add(checkboxOrdenConcretada);
		
		
		botonAplicar = new JButton(labels.getString("aplicar"));
		botonAplicar.addActionListener((ActionEvent e) ->{
				List<OrdenDeRetiroDTO> ordenesDTO=new ArrayList<>();
				
				try {	
					if(checkboxOrdenConcretada.isSelected()&& checkboxOrdenPendiente.isSelected()) {
							
								ordenesDTO= Filtros.filter(api.obtenerOrdenes(), (OrdenDeRetiroDTO orden)->orden.obtenerEstado().equals("PENDIENTE")||orden.obtenerEstado().equals("CONCRETADA"));
								
						
					}
					else {
						if(checkboxOrdenConcretada.isSelected()) {
							ordenesDTO= Filtros.filter(api.obtenerOrdenes(), (OrdenDeRetiroDTO orden)->orden.obtenerEstado().equals("CONCRETADA"));
							
						}
						else{
							if(checkboxOrdenPendiente.isSelected()) {
								ordenesDTO= Filtros.filter(api.obtenerOrdenes(), (OrdenDeRetiroDTO orden)->orden.obtenerEstado().equals("PENDIENTE"));
							}
							else {
								ordenesDTO=api.obtenerOrdenes();
							}
						}
						
						
						
						
					}
			}
				
			catch (AppException | InstanceException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
			
				cargarTitulos();
				
				for (OrdenDeRetiroDTO o : ordenesDTO) {
					modelo.addRow(new Object[] { o.obtenerId(), o.obtenerFecha(), o.obtenerEstado(), o.obtenerFechaPedido(), o.obtenerNombreApeRecolector() });
					
				}
				
			
			
			table.setModel(modelo);
			table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
			
			
		});
		panel.add(botonAplicar);
	}
	
	private void cargarMenuPopup() {
		popupMenu= new JPopupMenu();
		JMenuItem menuItemResiduosARetirar = new JMenuItem("Más info. del pedido");
		menuItemResiduosARetirar.addActionListener((ActionEvent arg0) ->{
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				popupMenu.setVisible(false);
				Integer idOrden=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					ListadoPedidoRetiro listadoPedidoRetiro = new ListadoPedidoRetiro(api, idOrden);
					listadoPedidoRetiro.setVisible(true);
				} catch (EmptyListException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		popupMenu.add(menuItemResiduosARetirar);
		
		
		JMenuItem menuItemVisitas = new JMenuItem("Ver las visitas");
		menuItemVisitas.addActionListener((ActionEvent arg0) ->{
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				popupMenu.setVisible(false);
				Integer idOrden=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					ListadoVisitas listadoVisitas = new ListadoVisitas(api, idOrden);
					listadoVisitas.setVisible(true);
				} catch (EmptyListException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		popupMenu.add(menuItemVisitas);
		
	
	}
	
	
	private void cargarTabla() {
		
		crearEstructuraTabla();
		
		
		try {
			List<OrdenDeRetiroDTO> ordenes;
			ordenes = api.obtenerOrdenes();
			for (OrdenDeRetiroDTO o : ordenes) {
				modelo.addRow(new Object[] { o.obtenerId(), o.obtenerFecha(), o.obtenerEstado(), o.obtenerFechaPedido(), o.obtenerNombreApeRecolector() });
				
			}
			visibilizarTabla();
		} catch (AppException | InstanceException  e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
		
	}
	
	
	private void cargarTablaParaVisita(Integer idVisita) throws SintaxisSQLException {
		
		crearEstructuraTabla();
		
	
		try {
			OrdenDeRetiroDTO o;
			o = api.obtenerOrden(idVisita);
			modelo.addRow(new Object[] { o.obtenerId(), o.obtenerFecha(), o.obtenerEstado(), o.obtenerFechaPedido(), o.obtenerNombreApeRecolector() });
			visibilizarTabla();
			
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		
	}
	
}
