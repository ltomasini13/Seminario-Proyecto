package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class ListadoRecolector extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private IApi api;
	private ResourceBundle labels;
	
	public ListadoRecolector(IApi api){
		labels=api.obtenerIdioma();
		setTitle(labels.getString("listado.recolectores"));
		this.api = api;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		String[] titulos = { labels.getString("titulo.nombre"), labels.getString("titulo.apellido"), labels.getString("titulo.dni"), labels.getString("titulo.email") };

		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		
		
		try {
			List<RecolectorDTO> recolectores;
			recolectores = api.obtenerRecolectores();
			// Agrega los usuarios en el model
			for (RecolectorDTO r : recolectores) {
				modelo.addRow(new Object[] { r.obtenerNombre(), r.obtenerApellido(), r.obtenerDni(), r.obtenerEmail() });
				
			}

			table.setModel(modelo);

			scrollPane.setViewportView(table);

		} catch (AppException | InstanceException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
	

		JButton cerrarButton = new JButton(labels.getString("cerrar"));
		cerrarButton.addActionListener((ActionEvent e) -> {
				dispose();
		});

		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		pnlBotonesOperaciones.add(cerrarButton);

		
	}

	

}
