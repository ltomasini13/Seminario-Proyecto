package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class ListadoRecolector extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton asignar;
	DefaultTableModel modelo;
	IApi api;
	
	public ListadoRecolector(IApi api) throws SintaxisSQLException {
		setTitle("LISTADO RECOLECTORES");
		this.api = api;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		String[] titulos = { "NOMBRE", "APELLIDO", "DNI", "EMAIL" };

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		
		List<RecolectorDTO> recolectores = api.obtenerRecolectores();
		// Agrega los usuarios en el model
		for (RecolectorDTO r : recolectores) {
			modelo.addRow(new Object[] { r.obtenerNombre(), r.obtenerApellido(), r.obtenerDni(), r.obtenerEmail() });
			
		}

		table.setModel(modelo);

		scrollPane.setViewportView(table);

		asignar = new JButton("ELEGIR");
		asignar.addActionListener((ActionEvent e) -> {
		
		});

		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener((ActionEvent e) -> {
				setVisible(false);
				dispose();
		});

		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		pnlBotonesOperaciones.add(asignar);
		pnlBotonesOperaciones.add(cerrarButton);

		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false);
	}

	private void habilitarBotones(boolean b) {
		asignar.setEnabled(b);

	}

}
