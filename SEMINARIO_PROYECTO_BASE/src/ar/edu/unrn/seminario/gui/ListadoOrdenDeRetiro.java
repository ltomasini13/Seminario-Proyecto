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
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;

public class ListadoOrdenDeRetiro extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton asignar, realizarVisita;
	DefaultTableModel modelo;
	IApi api;
	
	public ListadoOrdenDeRetiro(IApi api) throws SintaxisSQLException {
		setTitle("LISTADO DE ORDENES");
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
		String[] titulos = { "ID", "FECHA ORDEN", "ESTADO", "FECHA DEL PEDIDO", "RECOLECTOR"};  //falta agregar para que pueda ver el pedido a que esta asociada

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// Habilitar botones
				habilitarBotones(true);

			}
		});
		modelo = new DefaultTableModel(new Object[][] {}, titulos);

		
		List<OrdenDeRetiroDTO> ordenes = api.obtenerOrdenes();
		// Agrega los usuarios en el model
		for (OrdenDeRetiroDTO o : ordenes) {
			modelo.addRow(new Object[] { o.obtenerId(), o.obtenerFecha(), o.obtenerEstado(), o.obtenerFechaPedido(), o.obtenerNombreApeRecolector() });
			
		}
	
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
		scrollPane.setViewportView(table);

		asignar = new JButton("ASIGNAR RECOLECTOR");
		asignar.addActionListener((ActionEvent e) -> {
			
			try {
				Integer idOrden= (Integer)modelo.getValueAt(table.getSelectedRow(), 0);
				SeleccionRecolector seleccionRecolector = new SeleccionRecolector(api, idOrden);
				seleccionRecolector.setVisible(true);
				dispose();
			} catch (SintaxisSQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			
		});
		
		realizarVisita = new JButton("REALIZAR VISITA");
		realizarVisita.addActionListener((ActionEvent e) -> {
			
				
			
		});

		JButton cerrarButton = new JButton("Cerrar");
		cerrarButton.addActionListener((ActionEvent e) -> {
				dispose();
		});

		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		pnlBotonesOperaciones.add(asignar);
		pnlBotonesOperaciones.add(realizarVisita);
		pnlBotonesOperaciones.add(cerrarButton);

		// Deshabilitar botones que requieren tener una fila seleccionada
		habilitarBotones(false);
	}

	private void habilitarBotones(boolean b) {
		asignar.setEnabled(b);
		realizarVisita.setEnabled(b);

	}

}
