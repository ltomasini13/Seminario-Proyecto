package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.modelo.Usuario;

import javax.swing.JButton;
public class ListadoVivienda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private  Integer id_vivienda;
	private JButton botonPedirRecoleccion ;
	private IApi api;
	private JButton botonCambiarDueno;
	private JPopupMenu popupMenu;
	
	
	private JScrollPane scrollPane;
	private List<ViviendaDTO> viviendas=new ArrayList<ViviendaDTO>();
	private ViviendaDTO viv = null;
	private ResourceBundle labels;

	/**
	 * Create the frame.
	 */
	public ListadoVivienda(IApi api) throws EmptyListException {
		labels=api.obtenerIdioma();
		
		setTitle(labels.getString("listado.viviendas"));
		this.api=api;
		String[] adminTitulos= {"ID", labels.getString("titulo.calle"),labels.getString("titulo.numero"), labels.getString("titulo.barrio"),
				labels.getString("titulo.latitud"), labels.getString("titulo.longitud"),labels.getString("titulo.dueno")};
		String[] recicTitulos = {"ID", labels.getString("titulo.calle"),labels.getString("titulo.numero"), labels.getString("titulo.barrio"),
				labels.getString("titulo.latitud"), labels.getString("titulo.longitud")};
		
		inicializarVentana();
		if(api.esUsuarioAdmin()) {
			modelo = new DefaultTableModel(new Object[][] {}, adminTitulos);
			cargarEstructuraTabla();
			cargarDatosTablaAdmin();
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
			popupMenu= new JPopupMenu();
			JMenuItem menuItemInfoDueño = new JMenuItem(labels.getString("info.dueno"));
			menuItemInfoDueño.addActionListener((ActionEvent arg0) ->{
				
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null,labels.getString("ninguna.fila"),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					popupMenu.setVisible(false);
					Integer idVivienda=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
					ListadoCiudadano listadoCiudadano= new ListadoCiudadano(api, idVivienda);
					listadoCiudadano.setVisible(true);
				}
			});
			popupMenu.add(menuItemInfoDueño);
		}
		
		
		if(api.esUsuarioReciclador()) {
				modelo = new DefaultTableModel(new Object[][] {}, recicTitulos);
				cargarEstructuraTabla();
				cargarDatosTablaReciclador();
		}
		
		visibilizarTabla();
		cargarPanelDeOperaciones();
	}
	
	
	
	



	public ListadoVivienda(IApi api, Integer idPedido) throws EmptyListException {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("info.vivienda"));
		this.api=api;
		inicializarVentana();
		
		String[] adminTitulos= {"ID", labels.getString("titulo.calle"),labels.getString("titulo.numero"), labels.getString("titulo.barrio"),
				labels.getString("titulo.latitud"), labels.getString("titulo.longitud"),labels.getString("titulo.dueno")};
		String[] recicTitulos = {"ID", labels.getString("titulo.calle"),labels.getString("titulo.numero"), labels.getString("titulo.barrio"),
				labels.getString("titulo.latitud"), labels.getString("titulo.longitud")};

		if (api.esUsuarioAdmin()) {
			modelo = new DefaultTableModel(new Object[][] {}, adminTitulos);
			cargarEstructuraTabla();
			cargarDatosTablaAdmin(idPedido);
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
			popupMenu= new JPopupMenu();
			JMenuItem menuItemInfoDueño = new JMenuItem(labels.getString("info.dueno"));
			menuItemInfoDueño.addActionListener((ActionEvent arg0) ->{
				
				if(table.getSelectedRow()==-1) {
					JOptionPane.showMessageDialog(null, labels.getString("ninguna.fila"), "", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					popupMenu.setVisible(false);
					Integer idVivienda=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
					ListadoCiudadano listadoCiudadano= new ListadoCiudadano(api, idVivienda);
					listadoCiudadano.setVisible(true);
				}
			});
			popupMenu.add(menuItemInfoDueño);
		}
		
		
		if(api.esUsuarioReciclador()) {
				modelo = new DefaultTableModel(new Object[][] {}, recicTitulos);
				cargarEstructuraTabla();
				cargarDatosTablaReciclador(idPedido);
				
		}
		
		visibilizarTabla();
		
		cargarPanelDeOperaciones();
	}

	







	private void inicializarVentana() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	
	
	private void cargarEstructuraTabla() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		table = new JTable();
		
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}

		});
	}
	
	
	private void cargarDatosTablaAdmin() throws EmptyListException {
		try {
			viviendas=api.obtenerViviendas();
			for (ViviendaDTO viv : viviendas) {
				modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
						viv.obtenerLatitud(), viv.obtenerLongitud(), viv.obtenerNomApeDueño()});
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	private void cargarDatosTablaAdmin(Integer idPedido) {
		try {
			viv=api.obtenerViviendaDelPedido(idPedido);
			modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
					viv.obtenerLatitud(), viv.obtenerLongitud(), viv.obtenerNomApeDueño()});
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	
	private void cargarDatosTablaReciclador() throws EmptyListException {
		try {
			viviendas=api.obtenerViviendas();
			for (ViviendaDTO viv : viviendas) {
				modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
						viv.obtenerLatitud(), viv.obtenerLongitud()});
				
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),  labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	
	private void cargarDatosTablaReciclador(Integer idPedido) throws EmptyListException {
		try {
			viv=api.obtenerViviendaDelPedido(idPedido);
			modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
					viv.obtenerLatitud(), viv.obtenerLongitud()});
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),  labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void visibilizarTabla() {
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		scrollPane.setViewportView(table);
	}
	
	private void cargarPanelDeOperaciones() {
		JButton cerrarButton = new JButton(labels.getString("cerrar"));
		cerrarButton.addActionListener((ActionEvent e) -> {
			if(popupMenu!=null) {
				popupMenu.setVisible(false);
			}
				dispose();
		});
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		botonPedirRecoleccion = new JButton(labels.getString("pedir.recoleccion"));
		botonPedirRecoleccion.addActionListener((ActionEvent e)->{
			
				this.id_vivienda=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					api.pedidoPendiente(id_vivienda);
					SeleccionResiduos sr = new SeleccionResiduos(api, id_vivienda);
					sr.setVisible(true);
					dispose();
				} catch (CreationValidationException | AppException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
				}
			
		});
		
		botonCambiarDueno = new JButton(labels.getString("cambiar.dueno"));
		botonCambiarDueno.addActionListener((ActionEvent a) -> {
			int seleccion = JOptionPane.showOptionDialog(null, labels.getString("eleccion"), labels.getString("dialog.opciones"), JOptionPane.YES_NO_CANCEL_OPTION,
					   JOptionPane.QUESTION_MESSAGE, null,
					   new Object[] { labels.getString("opcion.elegir"), labels.getString("opcion.crear")}, null);
					this.id_vivienda=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
					if (seleccion != -1) {
						if(seleccion==0) {
							
							SeleccionCiudadano seleccionCiudadano = new SeleccionCiudadano(api, id_vivienda);
							seleccionCiudadano.setVisible(true);
							dispose();
						}
						else {
							AltaCiudadano altaCiu = new AltaCiudadano(api, id_vivienda);
							altaCiu.setVisible(true);
							dispose();
						}
					}
		});
		
		if(api.esUsuarioAdmin()) {
			pnlBotonesOperaciones.add(botonCambiarDueno);
		}
		
		pnlBotonesOperaciones.add(botonPedirRecoleccion);
		pnlBotonesOperaciones.add(cerrarButton);


		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false);
	}
	
	private void habilitarBotones(boolean b) {
		botonPedirRecoleccion.setEnabled(b);
		botonCambiarDueno.setEnabled(b);
		
	}
}
