package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;

public class SeleccionResiduos extends JFrame{

	private JPanel contentPane;
	
	private JTextField observacionText;
	private IApi api;
	private	JScrollPane scrollPanelListaRes;
	private JRadioButton noRadioButton;
	private JRadioButton siRadioButton;
	private JList jListResiduos;
	private List<ResiduoDTO> residuos;
	private JList JListResAgregados;
	private DefaultListModel modeloResAgregados;
	private List<String> residuosAgregados;
	
	public SeleccionResiduos(IApi api) {
		this.api=api;
		System.out.println(ListadoVivienda.id_vivienda);
		setTitle("Datos del pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel cargaPesadaLabel = new JLabel("Carga Pesada:");
		cargaPesadaLabel.setBounds(12, 22, 84, 16);
		contentPane.add(cargaPesadaLabel);
		
		JLabel observacionLabel = new JLabel("Observaci\u00F3n:");
		observacionLabel.setBounds(12, 169, 84, 16);
		contentPane.add(observacionLabel);
		
		JButton cancelarBoton = new JButton("CANCELAR");
		cancelarBoton.addActionListener((ActionEvent arg0) ->{
			dispose();
		});
		cancelarBoton.setBounds(214, 227, 89, 23);
		contentPane.add(cancelarBoton);
		
		observacionText = new JTextField();
		observacionText.setBounds(81, 169, 144, 32);
		contentPane.add(observacionText);
		observacionText.setColumns(10);
		
		 siRadioButton = new JRadioButton("SI");
		siRadioButton.addActionListener((ActionEvent arg0) ->{
			if (noRadioButton.isSelected()) {
				noRadioButton.setSelected(false);
			}
		});
		siRadioButton.setBounds(102, 18, 56, 25);
		contentPane.add(siRadioButton);
		
		noRadioButton = new JRadioButton("NO");
		noRadioButton.addActionListener((ActionEvent arg0) ->{
			if (siRadioButton.isSelected()) {
				siRadioButton.setSelected(false);
			}
		});
		noRadioButton.setBounds(164, 14, 110, 32);
		contentPane.add(noRadioButton);
		
		
		
		
		
		
		JButton botonVolver = new JButton("VOLVER");
		botonVolver.addActionListener((ActionEvent arg0) ->{
			try {
				ListadoVivienda listado = new ListadoVivienda(api);
				listado.setVisible(true);
				dispose();
			} catch (EmptyListException e) {
				
			}
		});
		botonVolver.setBounds(115, 227, 89, 23);
		contentPane.add(botonVolver);
		
		

		
		
		
		
		
	
		
		
		JButton btnContinuar = new JButton("CONTINUAR");
		contentPane.add(btnContinuar);
		btnContinuar.addActionListener((ActionEvent arg0) ->{
			
		});
		btnContinuar.setBounds(12, 227, 93, 23);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 144, 102);
		contentPane.add(scrollPane);
		
		
		try {
			residuos = api.obtenerResiduos();
		} catch (SintaxisSQLException | NotNullException | DataEmptyException | NumbersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jListResiduos = new JList();
		jListResiduos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		DefaultListModel modelo = new DefaultListModel();
	
			for(ResiduoDTO r : residuos) {
				modelo.addElement(r.obtenerTipo());
			}
	
		jListResiduos.setModel(modelo);
		scrollPane.setViewportView(jListResiduos);
		
		
		JLabel lblSeleccioneUnResiduo = new JLabel("Seleccione un residuo:");
		scrollPane.setColumnHeaderView(lblSeleccioneUnResiduo);
		
		JLabel labelCantidad = new JLabel("Ingrese el peso:");
		labelCantidad.setBounds(164, 61, 110, 14);
		contentPane.add(labelCantidad);
		
		JFormattedTextField formatoCantidad = new JFormattedTextField();
		formatoCantidad.setText("Ej: 124.5");
		formatoCantidad.setBounds(164, 86, 89, 20);
		contentPane.add(formatoCantidad);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener((ActionEvent arg0) ->{
			
		
			String residuo = (String)modelo.getElementAt(jListResiduos.getSelectedIndex());
			
			
			
		});
		btnAgregar.setBounds(164, 117, 89, 23);
		contentPane.add(btnAgregar);
		
		JScrollPane scrollPaneResiduosAgregados = new JScrollPane();
		scrollPaneResiduosAgregados.setBounds(284, 60, 140, 98);
		contentPane.add(scrollPaneResiduosAgregados);
		
		 JLabel lblResiduosAgregados = new JLabel("Residuos agregados:");
		scrollPaneResiduosAgregados.setColumnHeaderView(lblResiduosAgregados);
		
		
		residuosAgregados=new ArrayList<String>();
		JListResAgregados = new JList();
		 modeloResAgregados = new DefaultListModel();
		JListResAgregados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		JListResAgregados.setModel(modeloResAgregados);
		scrollPaneResiduosAgregados.setViewportView(JListResAgregados);
		
		
		
		
		
		
		
	
	}
	
	private void cargarResiduos() throws SintaxisSQLException, NotNullException, DataEmptyException, NumbersException {
		List<ResiduoDTO> residuos = api.obtenerResiduos();

		
		
		
	
		
//		}
		}
	}
