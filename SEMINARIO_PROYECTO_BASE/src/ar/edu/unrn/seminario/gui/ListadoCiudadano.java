package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import ar.edu.unrn.seminario.dto.CiudadanoDTO;
import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class ListadoCiudadano extends JFrame {

	private JPanel contentPane;
	private JPanel pnlBotonesOperaciones;
	private DefaultTableModel modelo;
	private JTable table;
	private IApi api;
	private JScrollPane scrollPane;
	private JButton botonCerrar, botonElegir;
	private JPopupMenu popupMenu;
	private ResourceBundle labels;
	
	/**
	 * Create the frame.
	 */
	public ListadoCiudadano(IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("listado.ciudadanos"));
		this.api=api;
		inicializarVentana();
		cargarEstructuraDeLaTabla();
		cargarDatosTabla();
		visibilizarTabla();
		cargarPanelDeOperaciones();
		cargarMenuPopop();
	}
	
	public ListadoCiudadano(IApi api, Integer idVivienda) {
		setTitle(labels.getString("info.ciudadano"));
		labels=api.obtenerIdioma();
		this.api=api;
		inicializarVentana();
		cargarEstructuraDeLaTabla();
		cargarDatosTabla(idVivienda);
		visibilizarTabla();
		cargarPanelDeOperaciones();
		cargarMenuPopop();
	}
	
	public ListadoCiudadano(String nombreBeneficio, IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("listado.ciudadanos"));
		this.api=api;
		inicializarVentana();
		cargarEstructuraDeLaTabla();
		cargarDatosTabla();
		visibilizarTabla();
		cargarPanelDeOperacionesSeleccionarCiudadano(nombreBeneficio);
		cargarMenuPopop();
	}
	private void cargarEstructuraDeLaTabla() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);
		String[] titulos= {"ID", labels.getString("titulo.nombre"), labels.getString("titulo.apellido"), labels.getString("titulo.dni"), labels.getString("titulo.nombre.usuario"), labels.getString("titulo.puntos.obtenidos")};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
	}
	
	private void cargarDatosTabla() {
		List<CiudadanoDTO> ciudadanosDTO;
		try {
			ciudadanosDTO = api.obtenerCiudadanos();
			for (CiudadanoDTO c : ciudadanosDTO) {
				
				modelo.addRow(new Object[] { c.obtenerId(), c.obtenerNombre(), c.obtenerApellido(), c.obtenerDni(), c.obtenerNombreDeUsuario(), c.obtenerPuntosObtenidos()});
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void cargarDatosTabla(Integer idVivienda) {
			
			try {
				CiudadanoDTO c;
				c = api.obtenerCiudadanoDeLaVivienda(idVivienda);
				modelo.addRow(new Object[] { c.obtenerId(), c.obtenerNombre(), c.obtenerApellido(), c.obtenerDni(), c.obtenerNombreDeUsuario(), c.obtenerPuntosObtenidos()});
				
			} catch (AppException | InstanceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
			
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
	
	private void  cargarPanelDeOperaciones() {
		pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		botonCerrar = new JButton(labels.getString("cerrar"));
		
		botonCerrar.addActionListener((ActionEvent e)-> {
			popupMenu.setVisible(false);
			dispose();
		});
		pnlBotonesOperaciones.add(botonCerrar);
	}
	
	private void  cargarPanelDeOperacionesSeleccionarCiudadano(String nombreBeneficio) {
		pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		botonElegir = new JButton(labels.getString("elegir"));
		botonElegir.addActionListener((ActionEvent e)-> {
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, labels.getString("ninguna.fila"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				popupMenu.setVisible(false);
				try {
					String dni=(String)modelo.getValueAt(table.getSelectedRow(), 3);
					api.realizarCanje(nombreBeneficio, dni);
					JOptionPane.showMessageDialog(null,labels.getString("canje.exito"), labels.getString("informacion"),JOptionPane.INFORMATION_MESSAGE);
					dispose();
					
				} catch (NumbersException  | AppException | NotNullException | DataEmptyException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),  labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}			
			}
		});
		pnlBotonesOperaciones.add(botonElegir);
		
		botonCerrar = new JButton(labels.getString("cerrar"));
		botonCerrar.addActionListener((ActionEvent e)-> {
			dispose();
		});
		pnlBotonesOperaciones.add(botonCerrar);
	}
	
	private void inicializarVentana() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	private void cargarMenuPopop() {
		popupMenu= new JPopupMenu();
		JMenuItem menuItemResiduosARetirar = new JMenuItem(labels.getString("info.usuario"));
		menuItemResiduosARetirar.addActionListener((ActionEvent arg0) ->{
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null,labels.getString("ninguna.fila"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				popupMenu.setVisible(false);
				String usuario=(String)modelo.getValueAt(table.getSelectedRow(), 4);
				ListadoUsuario listadoUsuario;
				try {
					listadoUsuario = new ListadoUsuario(api, usuario);
					listadoUsuario.setVisible(true);
				} catch (SintaxisSQLException | NotNullException | DataEmptyException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),  labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		popupMenu.add(menuItemResiduosARetirar);
	}
	
	private void habilitarBotones(boolean b) {
		
	}
}
