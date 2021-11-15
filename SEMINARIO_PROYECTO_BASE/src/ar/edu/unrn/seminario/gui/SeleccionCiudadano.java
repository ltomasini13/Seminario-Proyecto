package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import ar.edu.unrn.seminario.dto.CiudadanoDTO;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.EmptyListException;

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
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SeleccionCiudadano(IApi api, Integer idVivienda) {
		setTitle("SELECCION CIUDADANO");
		
		
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
		String[] titulos = { "ID", "NOMBRE", "APELLIDO", "DNI"};

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		
		List<CiudadanoDTO> ciudadanosDTO = api.obtenerCiudadanos();
		
		for (CiudadanoDTO c : ciudadanosDTO) {
			modelo.addRow(new Object[] {c.obtenerId(), c.obtenerNombre(), c.obtenerApellido(), c.obtenerDni()});
			
		}
	
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
		scrollPane.setViewportView(table);
		
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		botonElegir = new JButton("Elegir");
		botonElegir.addActionListener((ActionEvent arg0)-> {
			this.dniCiudadano=(String)modelo.getValueAt(table.getSelectedRow(), 3);
			ViviendaDTO viviendaDTO = api.obtenerVivienda(idVivienda);
			
			int seleccion = JOptionPane.showOptionDialog(null, "DATOS DE LA VIVIENDA\n\nDireccion: "+viviendaDTO.obtenerCalle()+
					" "+viviendaDTO.obtenerNumero()+"\nLatitud: "+viviendaDTO.obtenerLatitud()+"\nLongitud: "+viviendaDTO.obtenerLongitud()+"\n\n", "CONFIRMACION", JOptionPane.YES_NO_CANCEL_OPTION,
					   JOptionPane.INFORMATION_MESSAGE, null,
					   new Object[] { "CONFIRMAR", "CANCELAR"}, null);
					
					if (seleccion != -1) {
						if(seleccion==0) {
							api.cambiarDueño(idVivienda, dniCiudadano);
							JOptionPane.showMessageDialog(null, "El cambio de dueño se realizó exitosamente", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
						
					}
			
			
		});
		
		pnlBotonesOperaciones.add(botonElegir);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener((ActionEvent arg0)-> {
			try {
				dispose();
				ListadoVivienda listadoVivienda = new ListadoVivienda(api);
				listadoVivienda.setVisible(true);
			} catch (EmptyListException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		pnlBotonesOperaciones.add(btnCerrar);
		habilitarBotones(false);
		
	}
	
	
	private void habilitarBotones(boolean b) {
		botonElegir.setEnabled(b);
	}
}
