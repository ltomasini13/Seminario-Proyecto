package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.CiudadanoDTO;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SeleccionCiudadano extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private JButton botonElegir ;
	private IApi api;
	private JButton btnCerrar;
	private String dniCiudadano ;
	private ResourceBundle labels;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SeleccionCiudadano(IApi api, Integer idVivienda) {
		labels=api.obtenerIdioma();
		this.api=api;
		setTitle(labels.getString("seleccion.ciudadano"));
		
		
		this.api=api;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		String[] titulos = { "ID", labels.getString("titulo.nombre"), labels.getString("titulo.apellido"), labels.getString("titulo.dni")};

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		
		List<CiudadanoDTO> ciudadanosDTO;
		try {
			ciudadanosDTO = api.obtenerCiudadanos();
			for (CiudadanoDTO c : ciudadanosDTO) {
				modelo.addRow(new Object[] {c.obtenerId(), c.obtenerNombre(), c.obtenerApellido(), c.obtenerDni()});
				
			}
		
			table.setModel(modelo);
			table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
			scrollPane.setViewportView(table);
		} catch (AppException | InstanceException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		botonElegir = new JButton(labels.getString("elegir"));
		botonElegir.addActionListener((ActionEvent arg0)-> {
			this.dniCiudadano=(String)modelo.getValueAt(table.getSelectedRow(), 3);
			ViviendaDTO viviendaDTO;
			try {
				viviendaDTO = api.obtenerVivienda(idVivienda);
				int seleccion = JOptionPane.showOptionDialog(null, labels.getString("datos.vivienda")+"\n\n"+labels.getString("dialog.direccion")+" "+viviendaDTO.obtenerCalle()+
						" "+viviendaDTO.obtenerNumero()+"\n"+labels.getString("vivienda.latitud")+viviendaDTO.obtenerLatitud()+"\n"+labels.getString("vivienda.latitud")+viviendaDTO.obtenerLongitud()+"\n\n", labels.getString("dialog.confirmacion"), JOptionPane.YES_NO_CANCEL_OPTION,
						   JOptionPane.INFORMATION_MESSAGE, null,
						   new Object[] { labels.getString("confirmar"), labels.getString("cancelar")}, null);
						
						if (seleccion != -1) {
							if(seleccion==0) {
								api.cambiarDueño(idVivienda, dniCiudadano);
								JOptionPane.showMessageDialog(null, labels.getString("cambio.dueno.exitoso"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							
						}
				
			} catch (AppException | InstanceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
			
		});
		
		pnlBotonesOperaciones.add(botonElegir);
		
		btnCerrar = new JButton(labels.getString("cerrar"));
		btnCerrar.addActionListener((ActionEvent arg0)-> {
			try {
				
				dispose();
				ListadoVivienda listadoVivienda = new ListadoVivienda(api);
				listadoVivienda.setVisible(true);
			} catch (EmptyListException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
		});
		pnlBotonesOperaciones.add(btnCerrar);
		habilitarBotones(false);
		
	}
	
	
	private void habilitarBotones(boolean b) {
		botonElegir.setEnabled(b);
	}
}
