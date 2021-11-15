package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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



	/**
	 * Create the frame.
	 */
	public ListadoVivienda(IApi api) throws EmptyListException {
		setTitle("LISTADO DE VIVIENDAS");


		this.api=api;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}

		});
		
		String[] recicTitulos = {"ID", "CALLE", "NUMERO", "BARRIO", "LATITUD", "LONGITUD"};
		List<ViviendaDTO> viviendas= new ArrayList<ViviendaDTO>();
		if (api.esUsuarioAdmin()) {
			modelo = new DefaultTableModel(new Object[][] {}, adminTitulos);
			
			viviendas=api.obtenerViviendas();
			for (ViviendaDTO viv : viviendas) {
				modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
						viv.obtenerLatitud(), viv.obtenerLongitud(), viv.obtenerNomApeDueño()});
			}
			
			
		}
		
		
		if(api.esUsuarioReciclador()) {
				modelo = new DefaultTableModel(new Object[][] {}, recicTitulos);
				
				viviendas=api.obtenerViviendas();
				for (ViviendaDTO viv : viviendas) {
					modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
							viv.obtenerLatitud(), viv.obtenerLongitud()});
					
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
		
		botonPedirRecoleccion = new JButton("Pedir recolecci\u00F3n");
		botonPedirRecoleccion.addActionListener((ActionEvent e)->{
			
				this.id_vivienda=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					api.pedidoPendiente(id_vivienda);
					SeleccionResiduos sr = new SeleccionResiduos(api, id_vivienda);
					sr.setVisible(true);
					dispose();
				} catch (CreationValidationException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			
		});
		
		botonCambiarDueno = new JButton("Cambiar due\u00F1o");
		botonCambiarDueno.addActionListener((ActionEvent a) -> {
			int seleccion = JOptionPane.showOptionDialog(null, "¿Que desea hacer?", "OPCIONES", JOptionPane.YES_NO_CANCEL_OPTION,
					   JOptionPane.QUESTION_MESSAGE, null,
					   new Object[] { "Elegir un ciudadano", "Crear uno nuevo "}, null);
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
	
	
	
	public ListadoVivienda(IApi api, Integer idPedido) throws EmptyListException {
		setTitle("INFORMACIÓN DE LA VIVIENDA");


		this.api=api;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}

		});
		
		String[] recicTitulos = {"ID", "CALLE", "NUMERO", "BARRIO", "LATITUD", "LONGITUD"};
		List<ViviendaDTO> viviendas= new ArrayList<ViviendaDTO>();
		if (api.esUsuarioAdmin()) {
			modelo = new DefaultTableModel(new Object[][] {}, adminTitulos);
			
			viviendas=api.obtenerViviendas();
			for (ViviendaDTO viv : viviendas) {
				modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
						viv.obtenerLatitud(), viv.obtenerLongitud(), viv.obtenerNomApeDueño()});
			}
			
			
		}
		
		
		if(api.esUsuarioReciclador()) {
				modelo = new DefaultTableModel(new Object[][] {}, recicTitulos);
				
				viviendas=api.obtenerViviendas();
				for (ViviendaDTO viv : viviendas) {
					modelo.addRow(new Object[] { viv.obtenerId(), viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
							viv.obtenerLatitud(), viv.obtenerLongitud()});
					
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
		
		botonPedirRecoleccion = new JButton("Pedir recolecci\u00F3n");
		botonPedirRecoleccion.addActionListener((ActionEvent e)->{
			
				this.id_vivienda=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					api.pedidoPendiente(id_vivienda);
					SeleccionResiduos sr = new SeleccionResiduos(api, id_vivienda);
					sr.setVisible(true);
					dispose();
				} catch (CreationValidationException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			
		});
		
		botonCambiarDueno = new JButton("Cambiar due\u00F1o");
		botonCambiarDueno.addActionListener((ActionEvent a) -> {
			int seleccion = JOptionPane.showOptionDialog(null, "¿Que desea hacer?", "OPCIONES", JOptionPane.YES_NO_CANCEL_OPTION,
					   JOptionPane.QUESTION_MESSAGE, null,
					   new Object[] { "Elegir un ciudadano", "Crear uno nuevo "}, null);
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
