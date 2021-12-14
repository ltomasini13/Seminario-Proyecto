package ar.edu.unrn.seminario.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.ZeroNegativeNumberException;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JMenuItem;

public class SeleccionResiduos extends JFrame{

	private JPanel contentPane;
	
	private JTextField observacionText;
	private IApi api;
	private	JScrollPane scrollPanelListaRes;
	private JRadioButton noRadioButton;
	private JRadioButton siRadioButton;
	private JList jListResiduos;
	private List<ResiduoDTO> residuos;
	private JList jListResAgregados;
	private DefaultListModel modeloResAgregados;
	private List<ResiduoARetirarDTO> residuosAgregados;
	private JFormattedTextField formatoCantidad ;
	private boolean cargaPesada;
	private JPopupMenu popupMenu ;
	private ResourceBundle labels;
	
	public SeleccionResiduos(IApi api, Integer id_vivienda) {
		this.api=api;
		labels=api.obtenerIdioma();
		setTitle(labels.getString("pedido.recoleccion"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		
		JLabel cargaPesadaLabel = new JLabel(labels.getString("texto.carga.pesada"));
		cargaPesadaLabel.setBounds(12, 22, 84, 16);
		contentPane.add(cargaPesadaLabel);
		
		JLabel observacionLabel = new JLabel(labels.getString("texto.observacion"));
		observacionLabel.setBounds(12, 180, 84, 16);
		contentPane.add(observacionLabel);
		
		JButton cancelarBoton = new JButton(labels.getString("cancelar"));
		cancelarBoton.addActionListener((ActionEvent arg0) ->{
			popupMenu.setVisible(false);
			dispose();
		});
		cancelarBoton.setBounds(284, 227, 89, 23);
		contentPane.add(cancelarBoton);
		
		observacionText = new JTextField();
		observacionText.setBounds(96, 172, 144, 32);
		contentPane.add(observacionText);
		observacionText.setColumns(10);
		
		 siRadioButton = new JRadioButton(labels.getString("radio.si"));
		siRadioButton.addActionListener((ActionEvent arg0) ->{
			if (noRadioButton.isSelected()) {
				noRadioButton.setSelected(false);
			}
			this.cargaPesada=true;
		});
		siRadioButton.setBounds(102, 18, 56, 25);
		contentPane.add(siRadioButton);
		
		noRadioButton = new JRadioButton(labels.getString("radio.no"));
		noRadioButton.setSelected(true);
		noRadioButton.addActionListener((ActionEvent arg0) ->{
			if (siRadioButton.isSelected()) {
				siRadioButton.setSelected(false);
			}
			this.cargaPesada=false;
		});
		noRadioButton.setBounds(164, 14, 110, 32);
		contentPane.add(noRadioButton);
		
		
		
		
		
		
		JButton botonVolver = new JButton(labels.getString("volver"));
		botonVolver.addActionListener((ActionEvent arg0) ->{
			try {
				ListadoVivienda listado = new ListadoVivienda(api);
				listado.setVisible(true);
				dispose();
			} catch (EmptyListException e) {
				
			}
		});
		botonVolver.setBounds(175, 227, 89, 23);
		contentPane.add(botonVolver);
		
		
		JButton btnContinuar = new JButton(labels.getString("continuar"));
		contentPane.add(btnContinuar);
		btnContinuar.addActionListener((ActionEvent arg0) ->{
			
			try {
				if(!residuosAgregados.isEmpty()) {
					api.generarPedido(id_vivienda, cargaPesada, observacionText.getText(), residuosAgregados);
					JOptionPane.showMessageDialog(null, labels.getString("pedido.exito"),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE); 
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,labels.getString("ningun.residuo"),labels.getString("informacion"), JOptionPane.WARNING_MESSAGE);
				}
			} catch (NotNullException | ZeroNegativeNumberException | EmptyListException | AppException | InstanceException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE); 
			};
		
		});
		btnContinuar.setBounds(47, 227, 93, 23);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 144, 102);
		contentPane.add(scrollPane);
		
		
		jListResiduos = new JList();
		jListResiduos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		DefaultListModel modelo = new DefaultListModel();
		try {
			residuos = api.obtenerResiduos();
				for(ResiduoDTO r : residuos) {
					modelo.addElement(r.obtenerTipo());
				}
		
			jListResiduos.setModel(modelo);
			scrollPane.setViewportView(jListResiduos);
		} catch (AppException | InstanceException e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage(),labels.getString("informacion"), JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		JLabel lblSeleccioneUnResiduo = new JLabel(labels.getString("seleccione.residuo"));
		scrollPane.setColumnHeaderView(lblSeleccioneUnResiduo);
		
		JLabel labelCantidad = new JLabel(labels.getString("ingrese.peso"));
		labelCantidad.setBounds(164, 61, 110, 14);
		contentPane.add(labelCantidad);
		
		formatoCantidad = new JFormattedTextField();
		formatoCantidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				formatoCantidad.setText("");
			}
		});
		formatoCantidad.setText("Ej: 124.5");
		formatoCantidad.setBounds(164, 86, 89, 20);
		contentPane.add(formatoCantidad);
		
		JButton btnAgregar = new JButton(labels.getString("menu.agregar"));
		btnAgregar.addActionListener((ActionEvent arg0) ->{
			
			try {
				
				Double peso=Double.parseDouble(formatoCantidad.getText());
				if(peso<=0) {
					throw new ZeroNegativeNumberException(labels.getString("peso.mayor"));
				}
				
				if(!jListResiduos.isSelectionEmpty()) {
					String tipoResiduo = (String)modelo.getElementAt(jListResiduos.getSelectedIndex());
					ResiduoARetirarDTO residuoARetirarDTO = new ResiduoARetirarDTO(null, tipoResiduo, peso);
					
					if(estaAgregado(residuoARetirarDTO)) {

						JOptionPane.showMessageDialog(null, labels.getString("res.agregado")+" "+tipoResiduo, labels.getString("informacion"), JOptionPane.WARNING_MESSAGE); 
					}
					else {
						residuosAgregados.add(residuoARetirarDTO);
						modeloResAgregados.addElement(peso+"KG-"+tipoResiduo);
						jListResAgregados.removeAll();
						jListResAgregados.setModel(modeloResAgregados);
						
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null, labels.getString("no.seleccionado.residuo"), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE); 
				}
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, labels.getString("peso.correcto"), labels.getString("error"), JOptionPane.ERROR_MESSAGE); 
			} catch (ZeroNegativeNumberException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE); 
			}
			
			
			
		});
		btnAgregar.setBounds(164, 117, 89, 23);
		contentPane.add(btnAgregar);
		
		JScrollPane scrollPaneResiduosAgregados = new JScrollPane();
		scrollPaneResiduosAgregados.setBounds(284, 60, 140, 98);
		contentPane.add(scrollPaneResiduosAgregados);
		
		 JLabel lblResiduosAgregados = new JLabel(labels.getString("res.agregados"));
		scrollPaneResiduosAgregados.setColumnHeaderView(lblResiduosAgregados);
		
		
		residuosAgregados=new ArrayList<ResiduoARetirarDTO>();
		jListResAgregados = new JList();
		jListResAgregados.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if(arg0.getButton()==1) {
					popupMenu.setVisible(false);
				}
				if(!jListResAgregados.isSelectionEmpty()) {
						if (arg0.getButton()==3) {
							if(!popupMenu.isVisible()) {
								popupMenu.setLocation(arg0.getLocationOnScreen());
								popupMenu.setVisible(true);
							}
						}
				}
			}
		});
		 modeloResAgregados = new DefaultListModel();
		jListResAgregados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
		jListResAgregados.setModel(modeloResAgregados);
		scrollPaneResiduosAgregados.setViewportView(jListResAgregados);
		
		popupMenu = new JPopupMenu();
		JMenuItem menuItemPopupMenu = new JMenuItem(labels.getString("eliminar"));
		menuItemPopupMenu.addActionListener((ActionEvent arg0) ->{
			int confirmacion = JOptionPane.showConfirmDialog(null, labels.getString("eliminar.confirmacion"));
			
			if(confirmacion==0) {
				popupMenu.setVisible(false);
				Iterator<ResiduoARetirarDTO> itResiduosAgregados = residuosAgregados.iterator();
				while(itResiduosAgregados.hasNext()) {
					ResiduoARetirarDTO resRetirar = itResiduosAgregados.next();
					String tipoResiduo = (String)(modeloResAgregados.getElementAt(jListResAgregados.getSelectedIndex()));
					String[] partResAgregado = tipoResiduo.split("-");
					if(resRetirar.obetenerTipoResiduo().equals(partResAgregado[1])) {   
						itResiduosAgregados.remove();
					}
				}
				modeloResAgregados.remove(jListResAgregados.getSelectedIndex());
				jListResAgregados.removeAll();
				jListResAgregados.setModel(modeloResAgregados);
			}
			
			if(confirmacion==1 || confirmacion==2) {
				popupMenu.setVisible(false);
			}
		});
		popupMenu.add(menuItemPopupMenu);
		
		
		
		
		
		
		
		
	
	}
	
	private boolean estaAgregado(ResiduoARetirarDTO residuoARetirarDTO) {
		boolean existe=false;
		for(ResiduoARetirarDTO res : residuosAgregados) {
			if(res.equals(residuoARetirarDTO)) {
				existe=true;
				break;
			}	
		}
		return existe;
	}

	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	}
