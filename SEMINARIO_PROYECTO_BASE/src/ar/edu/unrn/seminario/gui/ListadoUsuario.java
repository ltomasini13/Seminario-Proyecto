package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

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
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import jdk.nashorn.internal.ir.Labels;

public class ListadoUsuario extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	IApi api;
	JButton activarButton, desactivarButton,cerrarButton ;
	private JScrollPane scrollPane;
	private ResourceBundle labels;

	/**
	 * Create the frame.
	 */
	public ListadoUsuario(IApi api) {
		this.api = api;
		labels=api.obtenerIdioma();
		setTitle(labels.getString("listado.usuarios"));
		
		inicializarVentana();
		
		cargarEstructuraTabla();
		
		cargarDatosTabla();
		
		visibilizarTabla();
		
		cargarPanelDeOperaciones();
		
	}

	
	
	
	public ListadoUsuario(IApi api, String nombreUsuario) throws SintaxisSQLException, NotNullException, DataEmptyException {
		this.api = api;
		labels=api.obtenerIdioma();
		setTitle(labels.getString("info.usuario"));
		
		inicializarVentana();
		
		cargarEstructuraTabla();
		
		cargarDatosTabla(nombreUsuario);
		
		visibilizarTabla();
		
		cargarPanelDeOperaciones();
	}

	
	private void cargarPanelDeOperaciones() {
		activarButton = new JButton(labels.getString("listado.activar"));
		activarButton.addActionListener((ActionEvent e) -> {
				int reply = JOptionPane.showConfirmDialog(null,
						labels.getString("listado.confirmacion"), labels.getString("listado.confirmacion.dialogo"),
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					String username = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);

					try {
						api.activarUsuario(username);
						reloadGrid();
					} catch (StateException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"),JOptionPane.ERROR_MESSAGE);
					}

				}
		});

		desactivarButton = new JButton(labels.getString("listado.desactivar"));
		desactivarButton.addActionListener((ActionEvent e) -> {
				int reply = JOptionPane.showConfirmDialog(null,
						labels.getString("listado.confirmacion"), labels.getString("listado.confirmacion.dialogo"),
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					String username = (String) table.getModel().getValueAt(table.getSelectedRow(), 0);

					try {
						api.desactivarUsuario(username);
						reloadGrid();
					} catch (StateException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
					}

				}
		
		});

		cerrarButton = new JButton(labels.getString("cerrar"));
		cerrarButton.addActionListener((ActionEvent e) -> {
				dispose();
		});
//		contentPane.add(cerrarButton, BorderLayout.SOUTH);

		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		pnlBotonesOperaciones.add(desactivarButton);
		pnlBotonesOperaciones.add(activarButton);
		pnlBotonesOperaciones.add(cerrarButton);

		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false);
	}

	private void visibilizarTabla() {
		table.setModel(modelo);

		scrollPane.setViewportView(table);
	}

	private void cargarDatosTabla() {
		// Obtiene la lista de usuarios a mostrar
		List<UsuarioDTO> usuarios;
		try {
			usuarios = api.obtenerUsuarios();
			// Agrega los usuarios en el model
			for (UsuarioDTO u : usuarios) {
				modelo.addRow(new Object[] { u.getUsername(), u.getNombre(), u.getEmail(), u.getEstado(), u.getRol() });
				
			}
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		

	}

	
	private void cargarDatosTabla(String nombreUsuario) throws SintaxisSQLException, NotNullException, DataEmptyException {
		// Obtiene el usuario para el ciudadano pasado como parametro
		UsuarioDTO u;
		try {
			u = api.obtenerUsuario(nombreUsuario);
			// Agrega el usuario en el model
			modelo.addRow(new Object[] { u.getUsername(), u.getNombre(), u.getEmail(), u.getEstado(), u.getRol() });
			
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		

	}

	private void cargarEstructuraTabla() {
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		String[] titulos = {labels.getString("titulo.usuario"),labels.getString("titulo.nombre"), labels.getString("titulo.email"),
				labels.getString("titulo.estado"), labels.getString("titulo.rol")};
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
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

	private void habilitarBotones(boolean b) {
		activarButton.setEnabled(b);
		desactivarButton.setEnabled(b);

	}

	private void reloadGrid() {
		// Obtiene el model del table
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		// Obtiene la lista de usuarios a mostrar
		List<UsuarioDTO> usuarios;
		try {
			usuarios = api.obtenerUsuarios();
			// Resetea el model
			modelo.setRowCount(0);

			// Agrega los usuarios en el model
			for (UsuarioDTO u : usuarios) {
				modelo.addRow(new Object[] { u.getUsername(), u.getNombre(), u.getEmail(), u.getEstado(), u.getRol() });
			}

		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
