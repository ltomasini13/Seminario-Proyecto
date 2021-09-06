package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import javax.swing.JButton;
public class ListadoVivienda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	IApi api;


	/**
	 * Create the frame.
	 */
	public ListadoVivienda(IApi api) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		String[] titulos = { "CALLE", "NUMERO", "BARRIO", "LATITUD", "LONGITUD", "DUEÑO", "FECHA Y HORA"};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		List<ViviendaDTO> viviendas= api.obtenerViviendas();
		for (ViviendaDTO viv : viviendas) {
			modelo.addRow(new Object[] { viv.obtenerCalle(), viv.obtenerNumero(),viv.obtenerBarrio(), 
					viv.obtenerLatitud(), viv.obtenerLongitu(), viv.obtenerNomApeDueño(), viv.obtenerFechaYhora()});
		}
		table.setModel(modelo);

		scrollPane.setViewportView(table);
	}
	
	

}
