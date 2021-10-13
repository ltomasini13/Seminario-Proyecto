package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.MemoryApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.dto.UsuarioDTO;

public class VentanaPrincipal extends JFrame {

	private JPanel ventanaPrincipalContentPane;

	/**
	 * Create the frame.

	 */
	public VentanaPrincipal(IApi api, UsuarioDTO usuarioDto) {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		if(usuarioDto.getRol().equals("ADMIN")) { // pregunta que tipo de usuario para mostrar el menu
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
					AltaVivienda altaViv = new AltaVivienda(api, usuarioDto);
					altaViv.setLocationRelativeTo(null);
					altaViv.setVisible(true);
			});
			viviendasMenu.add(altaModificacionMenuItem);
			
			JMenuItem listadoMenuItem = new JMenuItem("Listado");
			listadoMenuItem.addActionListener((ActionEvent e) -> {
					ListadoVivienda listadoViv = new ListadoVivienda(api, usuarioDto);
					listadoViv.setLocationRelativeTo(null);
					listadoViv.setVisible(true);
			});
			viviendasMenu.add(listadoMenuItem);

			JMenu residuosMenu = new JMenu("Residuos");
			menuBar.add(residuosMenu);
			
			JMenuItem registrarMenuItem = new JMenuItem("Registrar");
			registrarMenuItem.addActionListener((ActionEvent e) -> {
				RegistrarResiduo residuo = new RegistrarResiduo(api, usuarioDto);
				residuo.setVisible(true);
			});
			residuosMenu.add(registrarMenuItem);
			
			JMenuItem listadoResiduosMenuItem = new JMenuItem("Listado");
			residuosMenu.add(listadoResiduosMenuItem);
			
			JMenu pedidosMenu = new JMenu("Pedidos");
			menuBar.add(pedidosMenu);
			
			JMenuItem altaModificacionPedidosMenuItem = new JMenuItem("Alta/Modificacion");
			pedidosMenu.add(altaModificacionPedidosMenuItem);
			
			JMenuItem listadoPedidosMenuItem = new JMenuItem("Listado");
			pedidosMenu.add(listadoPedidosMenuItem);

			JMenu configuracionMenu = new JMenu("Configuraci\u00F3n");
			menuBar.add(configuracionMenu);

			JMenuItem salirMenuItem = new JMenuItem("Salir");
			salirMenuItem.addActionListener((ActionEvent e) -> {
				setVisible(false);
				dispose();
			});
			configuracionMenu.add(salirMenuItem);
		}
		

		if(usuarioDto.getRol().equals("RECICLADOR")) {
			
			JMenu viviendasMenu = new JMenu("Mis viviendas");
			menuBar.add(viviendasMenu);
			
			JMenuItem altaModificacionMenuItem = new JMenuItem("Alta/Modificacion");
			altaModificacionMenuItem.addActionListener((ActionEvent e) -> {
					AltaVivienda altaViv = new AltaVivienda(api, usuarioDto);
					altaViv.setLocationRelativeTo(null);
					altaViv.setVisible(true);
			});
			viviendasMenu.add(altaModificacionMenuItem);
			
			JMenuItem listadoMenuItem = new JMenuItem("Listado");
			listadoMenuItem.addActionListener((ActionEvent e) -> {
					ListadoVivienda listadoViv = new ListadoVivienda(api, usuarioDto);
					listadoViv.setLocationRelativeTo(null);
					listadoViv.setVisible(true);
			});
			viviendasMenu.add(listadoMenuItem);
			
			

			JMenu configuracionMenu = new JMenu("Configuraci\u00F3n");
			menuBar.add(configuracionMenu);

			JMenuItem salirMenuItem = new JMenuItem("Salir");
			salirMenuItem.addActionListener((ActionEvent e) -> {
				setVisible(false);
				dispose();
			});
			configuracionMenu.add(salirMenuItem);
		}
		
		
	}

}
