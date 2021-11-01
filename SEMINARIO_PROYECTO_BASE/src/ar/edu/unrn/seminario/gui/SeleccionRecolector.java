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

public class SeleccionRecolector extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton asignar;
	private DefaultTableModel modelo;
	private IApi api;
	private Integer idOrden;
	
	public SeleccionRecolector(IApi api, Integer idOrden) throws SintaxisSQLException {
		setTitle("LISTADO RECOLECTORES");
		this.api = api;
		this.idOrden=idOrden;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		String[] titulos = { "ID" ,"NOMBRE", "APELLIDO", "DNI", "EMAIL" };

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
			modelo.addRow(new Object[] { r.obtenerId(), r.obtenerNombre(), r.obtenerApellido(), r.obtenerDni(), r.obtenerEmail() });
			
		}
	
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
		scrollPane.setViewportView(table);

		asignar = new JButton("ELEGIR");
		asignar.addActionListener((ActionEvent e) -> {
			Integer idRecolector = (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
			api.asignarRecolector(idOrden, idRecolector);
			JOptionPane.showMessageDialog(null, "Se asigno el recolecor correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
			try {
				ListadoOrdenDeRetiro listadoOrdenes = new ListadoOrdenDeRetiro(api);
				listadoOrdenes.setVisible(true);
			} catch (SintaxisSQLException e1) {
				
			}
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
