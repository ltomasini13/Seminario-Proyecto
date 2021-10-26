package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
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
import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class ListadoPedidoRetiro extends JFrame{

	private JPanel contentPane;
	private DefaultTableModel modelo;
	private IApi api;
	private JTable table;


	public ListadoPedidoRetiro(IApi api) throws EmptyListException {
		this.api=api;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout( new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 251);
		contentPane.add(scrollPane);

		table = new JTable();
		String[] titulosAdmin = {"ID", "FECHA EMISIÓN", "FECHA CUMPLIMIENTO", "CARGA PESADA", "OBSERVACION", "DIR. VIVIENDA", "DUEÑO"};
		String[] titulosRecic = {"ID", "FECHA EMISIÓN", "FECHA CUMPLIMIENTO", "CARGA PESADA", "OBSERVACION", "DIR. VIVIENDA"};
		
		
		if(api.esUsuarioAdmin()) {
			modelo = new DefaultTableModel(new Object[][] {}, titulosAdmin);
			List<PedidoRetiroDTO> pedidosDTO= api.obtenerPedidos();
			for (PedidoRetiroDTO p : pedidosDTO) {
				modelo.addRow(new Object[] { p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
						p.obtenerCalle()+" "+p.obtenerNumero(), p.obtenerNombre()+" "+p.obtenerApellido()});
			}
		}
		
		if(api.esUsuarioReciclador()) {
			modelo = new DefaultTableModel(new Object[][] {}, titulosRecic);
			List<PedidoRetiroDTO> pedidosDTO= api.obtenerPedidos();
			for (PedidoRetiroDTO p : pedidosDTO) {
				modelo.addRow(new Object[] { p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
						p.obtenerCalle()+" "+p.obtenerNumero()});
			}
		}
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setMaxWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setMinWidth(0); //para ocultar la columna ID
		table.getColumnModel().getColumn(0).setPreferredWidth(0);//para ocultar la columna ID
		scrollPane.setViewportView(table);
		
		
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
