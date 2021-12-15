package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class SeleccionBeneficios extends JFrame{

	private JPanel contentPane, panelBotones;
	private JTable table;
	private DefaultTableModel modelo;
	private JButton elegirBeneficioBoton, realizarCanjeBoton, cerrarBoton;
	private ResourceBundle labels;
	public SeleccionBeneficios (IApi api, Integer idCampaña) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("catalogo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		table = new JTable();
		String[] titulos = { "ID", labels.getString("nombre.beneficio"), labels.getString("puntos.beneficio")};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<BeneficioDTO> beneficios;
		try {
			
			beneficios = api.obtenerCatalogo(idCampaña);
			for (BeneficioDTO beneficio : beneficios) {
			modelo.addRow(new Object[] {beneficio.obtenerId(), beneficio.obtenerNombreBeneficio(), beneficio.obtenerPuntos() });
			}
		} catch (AppException | NotNullException | DataEmptyException | DateException | NumbersException | InstanceException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(table);

		realizarCanjeBoton = new JButton(labels.getString("realizar.canje"));
		realizarCanjeBoton.addActionListener((ActionEvent e) -> {
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, labels.getString("ninguna.fila"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				String nombreBeneficio=(String)modelo.getValueAt(table.getSelectedRow(), 1);
				if(api.esUsuarioAdmin()) {
					
					ListadoCiudadano listado = new ListadoCiudadano(nombreBeneficio, api);
					listado.setVisible(true);
					dispose();
				}
				else {
					if(api.esUsuarioReciclador()) {
						try {
							api.realizarCanje(nombreBeneficio);
							JOptionPane.showMessageDialog(null, labels.getString("canje.exito"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (AppException | InstanceException | NumbersException | NotNullException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),labels.getString("error"), JOptionPane.ERROR_MESSAGE);
						}
						
					}
				}
			}	
		});
		
		panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		panelBotones.add(realizarCanjeBoton);
		
		cerrarBoton = new JButton(labels.getString("cerrar"));
		cerrarBoton.addActionListener((ActionEvent e) -> {
				dispose();
		});
		
		panelBotones.add(cerrarBoton);		
	}

	public SeleccionBeneficios (IApi api, Integer idBeneficio, Integer idCampaña) {
		labels=api.obtenerIdioma();
		
		setTitle(labels.getString("catalogo"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		table = new JTable();
		String[] titulos = { "ID", labels.getString("nombre.beneficio"), labels.getString("puntos.beneficio")};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<BeneficioDTO> beneficios;
		try {
			beneficios = api.obtenerCatalogo(idCampaña);
			for (BeneficioDTO beneficio : beneficios) {
			modelo.addRow(new Object[] {beneficio.obtenerId(), beneficio.obtenerNombreBeneficio(), beneficio.obtenerPuntos() });
			}
		} catch (AppException | NotNullException | DataEmptyException | DateException | NumbersException | InstanceException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(table);
		
		panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		cerrarBoton = new JButton(labels.getString("cerrar"));
		cerrarBoton.addActionListener((ActionEvent e) -> {
				dispose();
		});
		
		panelBotones.add(cerrarBoton);
		
		
		if(api.esUsuarioAdmin()) {
			elegirBeneficioBoton = new JButton(labels.getString("agregar.beneficio"));
			elegirBeneficioBoton.addActionListener((ActionEvent e) -> {
				try {
					ListadoBeneficio listado = new ListadoBeneficio(api, idCampaña);
					listado.setVisible(true);
					dispose();
				} catch (AppException | DataEmptyException | NotNullException | NumbersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
				}
				
			});
			panelBotones.add(elegirBeneficioBoton);
		}
		
	}
	
}
