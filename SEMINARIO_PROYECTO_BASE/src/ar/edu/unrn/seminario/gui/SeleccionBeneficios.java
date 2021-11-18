package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class SeleccionBeneficios extends JFrame{

	private JPanel contentPane, panelBotones;
	private JTable table;
	private DefaultTableModel modelo;
	private JButton elegirBeneficioBoton, realizarCanjeBoton, cerrarBoton;
	
	public SeleccionBeneficios (IApi api, Integer idCampaña) {
		
		setTitle("CATÁLOGO");
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
		String[] titulos = { "ID BENEFICIO", "NOMBRE BENEFICIO", "PUNTOS"};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<BeneficioDTO> beneficios;
		try {
			
			beneficios = api.obtenerCatalogo(idCampaña);
			for (BeneficioDTO beneficio : beneficios) {
			modelo.addRow(new Object[] {beneficio.obtenerId(), beneficio.obtenerNombreBeneficio(), beneficio.obtenerPuntos() });
			}
		} catch (AppException | NotNullException | DataEmptyException | DateException | NumbersException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(table);

		realizarCanjeBoton = new JButton("REALIZAR CANJE");
		realizarCanjeBoton.addActionListener((ActionEvent e) -> {
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Integer id=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				ListadoCiudadano listado = new ListadoCiudadano(id, api);
				listado.setVisible(true);
				dispose();
			}	
		});
		
		panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		panelBotones.add(realizarCanjeBoton);
		
		cerrarBoton = new JButton("CERRAR");
		cerrarBoton.addActionListener((ActionEvent e) -> {
				dispose();
		});
		
		panelBotones.add(cerrarBoton);		
	}

	public SeleccionBeneficios (IApi api, Integer idBeneficio, Integer idCampaña) {
		
		setTitle("CATÁLOGO");
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
		String[] titulos = { "ID BENEFICIO", "NOMBRE BENEFICIO", "PUNTOS"};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<BeneficioDTO> beneficios;
		try {
			beneficios = api.obtenerCatalogo(idCampaña);
			for (BeneficioDTO beneficio : beneficios) {
			modelo.addRow(new Object[] {beneficio.obtenerId(), beneficio.obtenerNombreBeneficio(), beneficio.obtenerPuntos() });
			}
		} catch (AppException | NotNullException | DataEmptyException | DateException | NumbersException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(table);

		realizarCanjeBoton = new JButton("REALIZAR CANJE");
		realizarCanjeBoton.addActionListener((ActionEvent e) -> {
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Integer id=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				ListadoCiudadano listado = new ListadoCiudadano(id, api);
				listado.setVisible(true);
				dispose();
			}	
		});
		
		panelBotones = new JPanel();
		panelBotones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		panelBotones.add(realizarCanjeBoton);
		
		cerrarBoton = new JButton("CERRAR");
		cerrarBoton.addActionListener((ActionEvent e) -> {
				dispose();
		});
		
		panelBotones.add(cerrarBoton);
		
		
		if(api.esUsuarioAdmin()) {
			elegirBeneficioBoton = new JButton("AGREGAR BENEFICIO");
			elegirBeneficioBoton.addActionListener((ActionEvent e) -> {
				try {
					ListadoBeneficio listado = new ListadoBeneficio(api, idCampaña);
					listado.setVisible(true);
					dispose();
				} catch (AppException | DataEmptyException | NotNullException | NumbersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			});
			panelBotones.add(elegirBeneficioBoton);
		}
		
	}
	
}
