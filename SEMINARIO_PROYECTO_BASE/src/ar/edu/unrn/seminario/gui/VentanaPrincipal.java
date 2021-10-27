package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;

import ar.edu.unrn.seminario.exception.EmptyListException;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;


public class VentanaPrincipal extends JFrame {

	private JPanel ventanaPrincipalContentPane;

	/**
	 * Create the frame.

	 */
	public VentanaPrincipal(IApi api) {
		
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		if(api.esUsuarioAdmin()) { // pregunta que tipo de usuario para mostrar el menu
			JMenu adminUsuarioMenu = new JMenu("Usuarios");
			menuBar.add(adminUsuarioMenu);

			JMenuItem adminAltaUsuarioMenuItem = new JMenuItem("Alta/Modificaci\u00F3n");
			adminAltaUsuarioMenuItem.addActionListener((ActionEvent e) -> {
					AltaUsuario alta = new AltaUsuario(api);
					alta.setLocationRelativeTo(null);
					alta.setVisible(true);
				
			});
			adminUsuarioMenu.add(adminAltaUsuarioMenuItem);

			JMenuItem listadoUsuarioMenuItem = new JMenuItem("Listado");
			listadoUsuarioMenuItem.addActionListener((ActionEvent e) -> {
					ListadoUsuario listado= new ListadoUsuario(api);
					listado.setLocationRelativeTo(null);
					listado.setVisible(true);
			});
			adminUsuarioMenu.add(listadoUsuarioMenuItem);
			
			JMenu viviendasMenu = new JMenu("Viviendas");
			menuBar.add(viviendasMenu);
			
			JMenuItem altaModificacionMenuItem = new JMenuItem("Alta/Modificacion");
			altaModificacionMenuItem.addActionListener((ActionEvent e) -> {
					AltaVivienda altaViv = new AltaVivienda(api);
					altaViv.setLocationRelativeTo(null);
					altaViv.setVisible(true);
			});
			viviendasMenu.add(altaModificacionMenuItem);
			
			JMenuItem listadoMenuItem = new JMenuItem("Listado");
			listadoMenuItem.addActionListener((ActionEvent e) -> {
					ListadoVivienda listadoViv;
					try {
						listadoViv = new ListadoVivienda(api);
						listadoViv.setLocationRelativeTo(null);
						listadoViv.setVisible(true);
					} catch (EmptyListException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			});
			viviendasMenu.add(listadoMenuItem);

			JMenu residuosMenu = new JMenu("Residuos");
			menuBar.add(residuosMenu);
			
			JMenuItem registrarMenuItem = new JMenuItem("Registrar");
			registrarMenuItem.addActionListener((ActionEvent e) -> {
				RegistrarResiduo residuo = new RegistrarResiduo(api);
				residuo.setLocationRelativeTo(null);
				residuo.setVisible(true);
			});
			residuosMenu.add(registrarMenuItem);
			
			JMenuItem listadoResiduosMenuItem = new JMenuItem("Listado");
			listadoResiduosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoResiduo residuos;
				try {
					residuos = new ListadoResiduo(api);
					residuos.setLocationRelativeTo(null);
					residuos.setVisible(true);
				} catch (SintaxisSQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NotNullException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (DataEmptyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumbersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			});
			residuosMenu.add(listadoResiduosMenuItem);
			
			JMenu pedidosMenu = new JMenu("Pedidos");
			menuBar.add(pedidosMenu);
			
			JMenuItem altaModificacionPedidosMenuItem = new JMenuItem("Alta/Modificacion");
			altaModificacionPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoVivienda altaPedido;
				try {
					altaPedido = new ListadoVivienda(api);
					altaPedido.setLocationRelativeTo(null);
					altaPedido.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			pedidosMenu.add(altaModificacionPedidosMenuItem);
			
			JMenuItem listadoPedidosMenuItem = new JMenuItem("Listado");
			listadoPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoPedidoRetiro pedidos;
				try {
					pedidos = new ListadoPedidoRetiro(api);
					pedidos.setLocationRelativeTo(null);
					pedidos.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);

				}
				
			});
			pedidosMenu.add(listadoPedidosMenuItem);
	
			JMenu ordenMenu = new JMenu("Ordenes");
			menuBar.add(ordenMenu);
			
			JMenuItem ordenesMenuItem = new JMenuItem("Generar Orden");
			ordenesMenuItem.addActionListener((ActionEvent e) -> {
					ListadoPedidoRetiro listado;
					try {
						listado = new ListadoPedidoRetiro(api);
						listado.setVisible(true);
					} catch (EmptyListException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
					}
					
			});
			
			ordenMenu.add(ordenesMenuItem);

			JMenuItem listadoOrdenesMenuItem = new JMenuItem("Listado");
			listadoOrdenesMenuItem.addActionListener((ActionEvent e) -> {
				
				
			});
			ordenMenu.add(listadoOrdenesMenuItem);
			
			JMenu recolectorMenu = new JMenu("Recolectores");
			menuBar.add(recolectorMenu);
			
			JMenuItem registrarRecolectorMenuItem = new JMenuItem("Registrar Recolector");
			registrarRecolectorMenuItem.addActionListener((ActionEvent e) -> {
				RegistrarRecolector regRecolector = new RegistrarRecolector(api);
				regRecolector.setLocationRelativeTo(null);
				regRecolector.setVisible(true);
			});
			recolectorMenu.add(registrarRecolectorMenuItem);
			
			JMenuItem listadoRecolectoresMenuItem = new JMenuItem("Listado");
			listadoRecolectoresMenuItem.addActionListener((ActionEvent e) -> {
				ListadoRecolector recolectores;
				try {
					recolectores = new ListadoRecolector(api);
					recolectores.setLocationRelativeTo(null);
					recolectores.setVisible(true);
				} catch (SintaxisSQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			recolectorMenu.add(listadoRecolectoresMenuItem);

		}

		if(api.esUsuarioReciclador()) {
			
			JMenu viviendasMenu = new JMenu("Mis viviendas");
			menuBar.add(viviendasMenu);
			
			JMenuItem altaModificacionMenuItem = new JMenuItem("Alta/Modificacion");
			altaModificacionMenuItem.addActionListener((ActionEvent e) -> {
					AltaVivienda altaViv = new AltaVivienda(api);
					altaViv.setLocationRelativeTo(null);
					altaViv.setVisible(true);
			});
			viviendasMenu.add(altaModificacionMenuItem);
			
			JMenuItem listadoMenuItem = new JMenuItem("Listado");
			listadoMenuItem.addActionListener((ActionEvent e) -> {
					ListadoVivienda listadoViv;
					try {
						listadoViv = new ListadoVivienda(api);
						listadoViv.setLocationRelativeTo(null);
						listadoViv.setVisible(true);
					} catch (EmptyListException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
					}
					
			});
			viviendasMenu.add(listadoMenuItem);
			
			JMenu pedidosMenu = new JMenu("Mis Pedidos");
			menuBar.add(pedidosMenu);
			
			JMenuItem altaModificacionPedidosMenuItem = new JMenuItem("Alta/Modificacion");
			altaModificacionPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoVivienda altaPedido;
				try {
					altaPedido = new ListadoVivienda(api);
					altaPedido.setLocationRelativeTo(null);
					altaPedido.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			pedidosMenu.add(altaModificacionPedidosMenuItem);
			
			JMenuItem listadoPedidosMenuItem = new JMenuItem("Listado");
			listadoPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoPedidoRetiro pedidos;
				try {
					pedidos = new ListadoPedidoRetiro(api);
					pedidos.setLocationRelativeTo(null);
					pedidos.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			pedidosMenu.add(listadoPedidosMenuItem);
		
		}
		
		
		
		
		JMenu configuracionMenu = new JMenu("Configuraci\u00F3n");
		menuBar.add(configuracionMenu);
		JMenuItem salirMenuItem = new JMenuItem("Salir");
		salirMenuItem.addActionListener((ActionEvent e) -> {
			api.cerrarSesion();
			dispose();
			Loguin loguin = new Loguin(api);
			loguin.setVisible(true);
			
		});
		configuracionMenu.add(salirMenuItem);
	}

}
