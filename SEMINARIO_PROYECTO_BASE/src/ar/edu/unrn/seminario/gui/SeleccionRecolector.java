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

public class SeleccionRecolector extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton asignar;
	private DefaultTableModel modelo;
	private IApi api;
	private Integer idOrden;
	private ResourceBundle labels;
	
	public SeleccionRecolector(IApi api, Integer idOrden) throws SintaxisSQLException {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("listado.recolectores"));
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
		String[] titulos = { "ID" ,labels.getString("titulo.nombre"), labels.getString("titulo.apellido"), labels.getString("titulo.dni"), labels.getString("titulo.email") };

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		
		List<RecolectorDTO> recolectores;
		try {
			recolectores = api.obtenerRecolectores();
			// Agrega los usuarios en el model
			for (RecolectorDTO r : recolectores) {
				modelo.addRow(new Object[] { r.obtenerId(), r.obtenerNombre(), r.obtenerApellido(), r.obtenerDni(), r.obtenerEmail() });
				
			}
		
			table.setModel(modelo);
			table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
			scrollPane.setViewportView(table);
		} catch (AppException | InstanceException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		

		asignar = new JButton(labels.getString("elegir"));
		asignar.addActionListener((ActionEvent e) -> {
			Integer idRecolector = (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
			try {
				api.asignarRecolector(idOrden, idRecolector);
				JOptionPane.showMessageDialog(null, labels.getString("asignacion.recolector"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				
				ListadoOrdenDeRetiro listadoOrdenes = new ListadoOrdenDeRetiro(api);
				listadoOrdenes.setVisible(true);
				dispose();
			} catch (AppException | InstanceException  e1) {
				JOptionPane.showMessageDialog(null,e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
			
			
		});

		JButton cerrarButton = new JButton(labels.getString("cerrar"));
		cerrarButton.addActionListener((ActionEvent e) -> {
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
