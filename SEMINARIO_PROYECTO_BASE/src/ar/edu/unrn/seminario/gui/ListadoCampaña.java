package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
import ar.edu.unrn.seminario.dto.CampañaDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class ListadoCampaña extends JFrame
{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private Integer idCampaña;
	private List<BeneficioDTO> beneficiosDTO=new ArrayList<BeneficioDTO>();
	private ResourceBundle labels;
	
	public ListadoCampaña(IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("listado.campanas"));
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
		String[] titulos = { "ID",labels.getString("nombre.campana"),labels.getString("fecha.campana"), labels.getString("fecha.fin.campana"), labels.getString("descripcion.campana")};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<CampañaDTO> campañas;
		try {
			campañas = api.obtenerCampañas();
			for (CampañaDTO camp : campañas) {
				modelo.addRow(new Object[] { camp.obtenerId(), camp.obtenerNombreCampaña(), camp.obtenerFechaInicio(), camp.obtenerFechaFin(), camp.obtenerDescripcion() });
			}
		} catch (AppException | NotNullException | DateException | DataEmptyException | InstanceException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(table);
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		JButton botonCerrar = new JButton(labels.getString("cerrar"));
		botonCerrar.addActionListener((ActionEvent e)-> {
			dispose();
		});
		
		JButton crearCatalogoBoton = new JButton(labels.getString("crear.catalogo"));
		crearCatalogoBoton.addActionListener((ActionEvent e)-> {
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, labels.getString("ninguna.fila"), "", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				
				try {
					this.idCampaña=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
					ListadoBeneficio beneficio = new ListadoBeneficio(api, idCampaña);
					beneficio.setVisible(true);
					dispose();
				} catch (AppException | DataEmptyException | NotNullException | NumbersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
					
				};
			}
		});
		pnlBotonesOperaciones.add(crearCatalogoBoton);
		pnlBotonesOperaciones.add(botonCerrar);
		
	}
	
}
