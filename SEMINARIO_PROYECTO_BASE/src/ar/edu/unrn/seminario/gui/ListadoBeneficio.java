package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

import java.awt.event.ActionListener;

public class ListadoBeneficio extends JFrame{

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	
	public ListadoBeneficio(IApi api) throws AppException, DataEmptyException, NotNullException, NumbersException {
		
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
		String[] titulos = { "NOMBRE BENEFICIO", "PUNTOS"};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<BeneficioDTO> beneficios= api.obtenerBeneficios();
		for (BeneficioDTO beneficio : beneficios) {
			modelo.addRow(new Object[] { beneficio.obtenerNombreBeneficio(), beneficio.obtenerPuntos() });
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
		
		if(api.esUsuarioAdmin()){
			JButton btnRegistrarNuevoBeneficio = new JButton("AGREGAR BENEFICIO");
			btnRegistrarNuevoBeneficio.addActionListener((ActionEvent e) -> {
				RegistrarBeneficio regBeneficio = new RegistrarBeneficio(api);
				regBeneficio.setVisible(true);
				this.dispose();
			});
			pnlBotonesOperaciones.add(btnRegistrarNuevoBeneficio);
			pnlBotonesOperaciones.add(botonCerrar);
		}
		
	}
	
}
