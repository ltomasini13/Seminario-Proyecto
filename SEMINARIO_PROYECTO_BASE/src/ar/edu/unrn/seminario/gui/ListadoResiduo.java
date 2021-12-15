package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
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
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import java.awt.event.ActionListener;

public class ListadoResiduo extends JFrame{

	JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo;
	
	public ListadoResiduo(IApi api) throws SintaxisSQLException, NotNullException, DataEmptyException, NumbersException {
		

		setTitle("LISTADO DE RESIDUOS");
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
		String[] titulos = { "ID", "TIPO RESIDUO", "PUNTOS"};
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		
		
		
		try {
			List<ResiduoDTO> residuos;
			residuos = api.obtenerResiduos();
			for (ResiduoDTO residuo : residuos) {
				modelo.addRow(new Object[] { residuo.obtenerId(), residuo.obtenerTipo(), residuo.obtenerPunto() });
			}
			table.setModel(modelo);
			table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
			table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
			scrollPane.setViewportView(table);
		} catch (AppException | InstanceException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "INFORMACIÓN", JOptionPane.ERROR_MESSAGE);
		}
		
		
		JPanel pnlBotonesOperaciones = new JPanel();
		pnlBotonesOperaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnlBotonesOperaciones, BorderLayout.SOUTH);
		
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener((ActionEvent e)-> {
			dispose();
		});
		pnlBotonesOperaciones.add(botonCerrar);

	}
}
