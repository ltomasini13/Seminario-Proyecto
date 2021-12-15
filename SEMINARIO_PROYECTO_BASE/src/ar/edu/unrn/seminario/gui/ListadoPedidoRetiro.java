package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

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
import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class ListadoPedidoRetiro extends JFrame{

	private JPanel contentPane, pnlBotonesOperaciones;
	private DefaultTableModel modelo;
	private IApi api;
	private JTable table;
	private JPopupMenu popupMenu;
	private JButton botonCerrar, botonGenerar;
	private Integer idPedido;
	private ResourceBundle labels;
	
	/*LISTADO DE TODOS LOS PEDIDOS*/
	public ListadoPedidoRetiro(IApi api) throws EmptyListException {
		labels=api.obtenerIdioma();
		String[] titulos= {"ID", labels.getString("fecha.emision"), labels.getString("fecha.cumplimiento"), labels.getString("carga.pesada"), labels.getString("observacion"), labels.getString("dir.vivienda")};
		this.api=api;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle(labels.getString("listado.pedidos"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout( new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		
		pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		/*Pregunta si el usuario es un Administrador*/
		if(api.esUsuarioAdmin()) {
			
			cargarTablaAdmin();
			cargarBotonesAdmin();
		}
		
		
		/*Pregunta si el usuario es un Reciclador*/
		if(api.esUsuarioReciclador()) {
			cargarEstructuraTablaReciclador();
		}
		
		
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
		
		
		botonCerrar = new JButton(labels.getString("cerrar"));
	
		botonCerrar.addActionListener((ActionEvent e)-> {
			popupMenu.setVisible(false);
			dispose();
		});
		pnlBotonesOperaciones.add(botonCerrar);
		
		
		this.cargarMenuPopup();
		
	}
	
	
	
	
	/*LISTADO DEL PEDIDO PARA LA ORDEN CUYO ID SE PASA COMO PARAMETRO*/
	public ListadoPedidoRetiro(IApi api, Integer idOrden) throws EmptyListException {
		labels=api.obtenerIdioma();
		this.api=api;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setTitle(labels.getString("ventana.informacion.pedido"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout( new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		
		pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		String[] titulos= {"ID", labels.getString("fecha.emision"), labels.getString("fecha.cumplimiento"), labels.getString("carga.pesada"), labels.getString("observacion"), labels.getString("dir.vivienda")};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		/*Pregunta si el usuario es un Administrador*/
		if(api.esUsuarioAdmin()) {
			
			cargarTablaAdmin(idOrden);
			cargarBotonesAdmin();
		}
		
		
		/*Pregunta si el usuario es un Reciclador*/
		if(api.esUsuarioReciclador()) {
			cargarEstructuraTablaReciclador(idOrden);
		}
		
		
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
		
		
		botonCerrar = new JButton(labels.getString("cerrar"));
	
		botonCerrar.addActionListener((ActionEvent e)-> {
			dispose();
		});
		pnlBotonesOperaciones.add(botonCerrar);
		
		
		cargarMenuPopup();
		
	}
	
	
	
	private void cargarMenuPopup() {
		popupMenu= new JPopupMenu();
		
		JMenuItem menuItemInfoVivienda = new JMenuItem(labels.getString("mas.info.vivienda"));
		menuItemInfoVivienda.addActionListener((ActionEvent arg0) ->{
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, labels.getString("ninguna.fila"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				popupMenu.setVisible(false);
				Integer idPedido=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					ListadoVivienda listadoVivienda = new ListadoVivienda(api, idPedido);
					listadoVivienda.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),  labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
			}
		});
		popupMenu.add(menuItemInfoVivienda);
		
		JMenuItem menuItemResiduosARetirar = new JMenuItem(labels.getString("ver.residuos"));
		menuItemResiduosARetirar.addActionListener((ActionEvent arg0) ->{
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, labels.getString("ninguna.fila"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				popupMenu.setVisible(false);
				Integer idPedido=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);

				ListadoResiduosARetirar listadoResRetirar = new ListadoResiduosARetirar(api, idPedido);
				listadoResRetirar.setVisible(true);
			}
		});
		popupMenu.add(menuItemResiduosARetirar);
		
		JMenuItem menuItemResiduosRestantes = new JMenuItem(labels.getString("ver.residuos.restantes"));
		menuItemResiduosRestantes.addActionListener((ActionEvent arg0) ->{
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, labels.getString("ninguna.fila"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				popupMenu.setVisible(false);
				Integer idPedido=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				ListadoResiduosRestantes listadoResiduosRestantes = new ListadoResiduosRestantes(api, idPedido);
				listadoResiduosRestantes.setVisible(true);

			}
		});
		popupMenu.add(menuItemResiduosRestantes);
		
		
	
		
		
	
	}
	
	private void habilitarBotones(boolean b) {
		botonGenerar.setEnabled(b);
	}
	
	private void cargarEstructuraTablaReciclador() throws EmptyListException {
		table = new JTable();
		List<PedidoRetiroDTO> pedidosDTO;
		try {
			pedidosDTO = api.obtenerPedidos();
			for (PedidoRetiroDTO p : pedidosDTO) {
				modelo.addRow(new Object[] { p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
						p.obtenerCalle()+" "+p.obtenerNumero()});
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void cargarEstructuraTablaReciclador(Integer idOrden) throws EmptyListException {
		table = new JTable();
		PedidoRetiroDTO p;
		try {
			p = api.obtenerPedidoDeLaOrden(idOrden);
			modelo.addRow(new Object[] { p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
					p.obtenerCalle()+" "+p.obtenerNumero()});
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	

	
	private void cargarTablaAdmin() throws EmptyListException {
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		
	
		List<PedidoRetiroDTO> pedidosDTO;
		try {
			pedidosDTO = api.obtenerPedidos();
			for (PedidoRetiroDTO p : pedidosDTO) {
				modelo.addRow(new Object[] { p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
						p.obtenerCalle()+" "+p.obtenerNumero()});
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private void cargarTablaAdmin(Integer idOrden) throws EmptyListException {
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		
		PedidoRetiroDTO p;
		try {
			p = api.obtenerPedidoDeLaOrden(idOrden);
			modelo.addRow(new Object[] { p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
					p.obtenerCalle()+" "+p.obtenerNumero()});
		
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void cargarBotonesAdmin() {
		botonGenerar= new JButton(labels.getString("generar.orden"));
		botonGenerar.addActionListener((ActionEvent e) -> {
			this.idPedido=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
			try {
				api.generarOrden(idPedido);
				JOptionPane.showMessageDialog(null, labels.getString("orden.exito"), "", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (  CreationValidationException | AppException | InstanceException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		pnlBotonesOperaciones.add(botonGenerar);
		
		habilitarBotones(false);
	}
	
}
