package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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

import com.mysql.fabric.xmlrpc.base.Array;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

import java.awt.event.ActionListener;

public class ListadoBeneficio extends JFrame{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private Integer idBeneficio, idCampaña;
	private List<BeneficioDTO> beneficiosDTO=new ArrayList<BeneficioDTO>();
	
	public ListadoBeneficio(IApi api) throws AppException, DataEmptyException, NotNullException, NumbersException {
		
		setTitle("LISTADO DE BENEFICIOS");
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
		String[] titulos = { "NOMBRE BENEFICIO", "PUNTOS"};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<BeneficioDTO> beneficios;
		try {
			beneficios = api.obtenerBeneficios();
			
			for (BeneficioDTO beneficio : beneficios) {
				modelo.addRow(new Object[] { beneficio.obtenerNombreBeneficio(), beneficio.obtenerPuntos() });
			}
		} catch (InstanceException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "INFROMACIÓN", JOptionPane.INFORMATION_MESSAGE);
		}
		
		table.setModel(modelo);
		scrollPane.setViewportView(table);
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		JButton botonCerrar = new JButton("CERRAR");
		botonCerrar.addActionListener((ActionEvent e)-> {
			dispose();
		});
		pnlBotonesOperaciones.add(botonCerrar);
		
		if(api.esUsuarioAdmin()){
			JButton btnRegistrarNuevoBeneficio = new JButton("AGREGAR BENEFICIO");
			btnRegistrarNuevoBeneficio.addActionListener((ActionEvent e) -> {
				RegistrarBeneficio regBeneficio = new RegistrarBeneficio(api);
				regBeneficio.setVisible(true);
				this.dispose();
			});
			pnlBotonesOperaciones.add(btnRegistrarNuevoBeneficio);
			
		}
	}
		
	public ListadoBeneficio(IApi api, Integer idCampaña) throws AppException, DataEmptyException, NotNullException, NumbersException {
		
		setTitle("LISTADO DE BENEFICIOS");
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
			beneficios = api.obtenerBeneficios();
			for (BeneficioDTO beneficio : beneficios) {
				modelo.addRow(new Object[] { beneficio.obtenerId(), beneficio.obtenerNombreBeneficio(), beneficio.obtenerPuntos() });
			}
		} catch (InstanceException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "INFROMACIÓN", JOptionPane.INFORMATION_MESSAGE);
		}
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0);

		table.getColumnModel().getColumn(0).setMinWidth(0);

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		scrollPane.setViewportView(table);
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		JButton botonCerrar = new JButton("CERRAR");
		botonCerrar.addActionListener((ActionEvent e)-> {
			this.dispose();
		});
		pnlBotonesOperaciones.add(botonCerrar);
		
		JButton elegirBeneficio = new JButton("ELEGIR");
		elegirBeneficio.addActionListener((ActionEvent e)-> {
			
			if(table.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun beneficio", "", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				
				this.idBeneficio=(Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				try {
					api.agregarBeneficio(idCampaña, idBeneficio);
					SeleccionBeneficios seleccion = new SeleccionBeneficios(api, idBeneficio, idCampaña);
					seleccion.setVisible(true);
					JOptionPane.showMessageDialog(null, "El beneficio se agregó correctamente al catálogo", "Confirmar", JOptionPane.INFORMATION_MESSAGE);
				} catch (AppException | NotNullException | DataEmptyException | NumbersException | CreationValidationException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		pnlBotonesOperaciones.add(elegirBeneficio);
		
	}
	
}
