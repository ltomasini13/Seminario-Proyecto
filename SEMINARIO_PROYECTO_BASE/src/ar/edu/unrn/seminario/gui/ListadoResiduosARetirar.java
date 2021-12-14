package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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
import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import java.awt.event.ActionListener;

public class ListadoResiduosARetirar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modelo;
	private IApi api;
	private JButton btnCerrar;
	private ResourceBundle labels;

	/**
	 * Create the frame.
	 */
	public ListadoResiduosARetirar(IApi api, Integer idPedido) {
		labels=api.obtenerIdioma();
		this.api=api;
		setTitle(labels.getString("listado.residuos.retirar"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);
		
		table = new JTable();
		String[] titulos = {"ID", labels.getString("titulo.tipo.residuo"), labels.getString("titulo.cantidad.retirar")};
		
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		try {
			List<ResiduoARetirarDTO> residuosRetirarDTO;
			residuosRetirarDTO = api.obtenerResiduosARetirar(idPedido);
			for (ResiduoARetirarDTO r : residuosRetirarDTO) {
				modelo.addRow(new Object[] { r.obetenerId(), r.obetenerTipoResiduo(), r.obetenerCantidad()});
			}
			
			table.setModel(modelo);
			table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
			scrollPane.setViewportView(table);
		} catch (AppException | InstanceException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		btnCerrar = new JButton(labels.getString("cerrar"));
		btnCerrar.addActionListener((ActionEvent arg0) -> {
			dispose();
		});
		pnlBotonesOperaciones.add(btnCerrar);
	}

}
