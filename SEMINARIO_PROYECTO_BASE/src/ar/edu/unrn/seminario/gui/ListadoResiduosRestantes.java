package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.dto.ResiduoRestanteDTO;

public class ListadoResiduosRestantes extends JFrame {

	private JPanel contentPane;
	private IApi api;
	private JTable table;
	private DefaultTableModel modelo;
	private JButton btnCerrar;

	/**
	 * Create the frame.
	 */
	public ListadoResiduosRestantes(IApi api, Integer idPedido) {
		this.api=api;
		
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
		String[] titulos = {"ID", "TIPO DE RESIDUO", "CANTIDAD A RETIRAR"};
		
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		List<ResiduoRestanteDTO> residuosRestantesDTO= api.obtenerResiduosRestantes(idPedido);
		for (ResiduoRestanteDTO r : residuosRestantesDTO) {
			modelo.addRow(new Object[] { r.obtenerId(), r.obtenerTipo(), r.obtenerCantidadKg()});
		}
		
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
		scrollPane.setViewportView(table);
		
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener((ActionEvent arg0) -> {
			dispose();
		});
		pnlBotonesOperaciones.add(btnCerrar);
	}

}
