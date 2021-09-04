package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ar.edu.unrn.seminario.api.IApi;

public class ListadoViviendas extends JFrame {

	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	IApi api;


	/**
	 * Create the frame.
	 */
	public ListadoViviendas(IApi api) {
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
		
		table.setModel(modelo);

		scrollPane.setViewportView(table);
	}
	
	

}
