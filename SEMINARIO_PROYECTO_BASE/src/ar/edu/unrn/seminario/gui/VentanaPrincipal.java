package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

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
import ar.edu.unrn.seminario.dto.CiudadanoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;

import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;


public class VentanaPrincipal extends JFrame {

	private JPanel ventanaPrincipalContentPane;
	private ResourceBundle labels;
	/**
	 * Create the frame.

	 */
	public VentanaPrincipal(IApi api) {
		labels=api.obtenerIdioma();
		setTitle(labels.getString("sistema"));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		if(api.esUsuarioAdmin()) { // pregunta que tipo de usuario es  para mostrar el menu
			JMenu adminUsuarioMenu = new JMenu(labels.getString("principal.menu.usuarios"));
			menuBar.add(adminUsuarioMenu);

			JMenuItem adminAltaUsuarioMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			adminAltaUsuarioMenuItem.addActionListener((ActionEvent e) -> {
					AltaUsuario alta = new AltaUsuario(api);
					alta.setLocationRelativeTo(null);
					alta.setVisible(true);
				
			});
			adminUsuarioMenu.add(adminAltaUsuarioMenuItem);

			JMenuItem listadoUsuarioMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoUsuarioMenuItem.addActionListener((ActionEvent e) -> {
					ListadoUsuario listado= new ListadoUsuario(api);
					listado.setLocationRelativeTo(null);
					listado.setVisible(true);
			});
			adminUsuarioMenu.add(listadoUsuarioMenuItem);
			
			JMenu adminCiudadanoMenu = new JMenu(labels.getString("ciudadano"));
			menuBar.add(adminCiudadanoMenu);

			JMenuItem adminListadoCiuMenuItem = new JMenuItem(labels.getString("menu.listado"));
			adminListadoCiuMenuItem.addActionListener((ActionEvent e) -> {
					ListadoCiudadano lisCiudadano=new ListadoCiudadano(api);
					lisCiudadano.setVisible(true);
				
			});
			adminCiudadanoMenu.add(adminListadoCiuMenuItem);
			
			JMenu viviendasMenu = new JMenu(labels.getString("menu.vivienda"));
			menuBar.add(viviendasMenu);
			
			JMenuItem altaModificacionMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			altaModificacionMenuItem.addActionListener((ActionEvent e) -> {
					AltaVivienda altaViv = new AltaVivienda(api);
					altaViv.setLocationRelativeTo(null);
					altaViv.setVisible(true);
			});
			viviendasMenu.add(altaModificacionMenuItem);
			
			JMenuItem listadoMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoMenuItem.addActionListener((ActionEvent e) -> {
					ListadoVivienda listadoViv;
					try {
						listadoViv = new ListadoVivienda(api);
						listadoViv.setLocationRelativeTo(null);
						listadoViv.setVisible(true);
					} catch (EmptyListException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
					}
					
			});
			viviendasMenu.add(listadoMenuItem);

			JMenu residuosMenu = new JMenu(labels.getString("menu.residuo"));
			menuBar.add(residuosMenu);
			
			JMenuItem registrarMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			registrarMenuItem.addActionListener((ActionEvent e) -> {
				RegistrarResiduo residuo = new RegistrarResiduo(api);
				residuo.setLocationRelativeTo(null);
				residuo.setVisible(true);
			});
			residuosMenu.add(registrarMenuItem);
			
			JMenuItem listadoResiduosMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoResiduosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoResiduo residuos;
				try {
					residuos = new ListadoResiduo(api);
					residuos.setLocationRelativeTo(null);
					residuos.setVisible(true);
				} catch (SintaxisSQLException | NotNullException | DataEmptyException | NumbersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("error"), JOptionPane.ERROR_MESSAGE);
				}
				
			});
			residuosMenu.add(listadoResiduosMenuItem);
			
			JMenu pedidosMenu = new JMenu(labels.getString("menu.pedido"));
			menuBar.add(pedidosMenu);
			
			JMenuItem altaModificacionPedidosMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			altaModificacionPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoVivienda altaPedido;
				try {
					altaPedido = new ListadoVivienda(api);
					altaPedido.setLocationRelativeTo(null);
					altaPedido.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			pedidosMenu.add(altaModificacionPedidosMenuItem);
			
			JMenuItem listadoPedidosMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoPedidoRetiro pedidos;
				try {
					pedidos = new ListadoPedidoRetiro(api);
					pedidos.setLocationRelativeTo(null);
					pedidos.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);

				}
				
			});
			pedidosMenu.add(listadoPedidosMenuItem);
	
			JMenu ordenMenu = new JMenu(labels.getString("menu.orden"));
			menuBar.add(ordenMenu);
			
			JMenuItem ordenesMenuItem = new JMenuItem(labels.getString("menu.orden.generar"));
			ordenesMenuItem.addActionListener((ActionEvent e) -> {
					ListadoPedidoRetiro listado;
					try {
						listado = new ListadoPedidoRetiro(api);
						listado.setVisible(true);
						
					} catch (EmptyListException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
					}
					
			});
			
			ordenMenu.add(ordenesMenuItem);

			JMenuItem listadoOrdenesMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoOrdenesMenuItem.addActionListener((ActionEvent e) -> {
					ListadoOrdenDeRetiro listado = new ListadoOrdenDeRetiro(api);
					listado.setVisible(true);
				
				
			});
			ordenMenu.add(listadoOrdenesMenuItem);
			
			
			JMenu visitaMenu = new JMenu(labels.getString("menu.visita"));
			menuBar.add(visitaMenu);
			
			JMenuItem visitasMenuItemAgregar = new JMenuItem(labels.getString("menu.agregar"));
			visitasMenuItemAgregar.addActionListener((ActionEvent e) -> {
					ListadoOrdenDeRetiro listadoOrdenes;
						listadoOrdenes = new ListadoOrdenDeRetiro(api);
						listadoOrdenes.setVisible(true);
						
					
					
			});
			
			visitaMenu.add(visitasMenuItemAgregar);
			
			
			JMenuItem visitasMenuItemListado = new JMenuItem(labels.getString("menu.listado"));
			visitasMenuItemListado.addActionListener((ActionEvent e) -> {
					try {
						ListadoVisitas listadoVisitas = new ListadoVisitas(api);
						listadoVisitas.setVisible(true);
					} catch (EmptyListException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
					}
					
			});
			
			visitaMenu.add(visitasMenuItemListado);
			
			
			JMenu recolectorMenu = new JMenu(labels.getString("menu.recolector"));
			menuBar.add(recolectorMenu);
			
			JMenuItem registrarRecolectorMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			registrarRecolectorMenuItem.addActionListener((ActionEvent e) -> {
				RegistrarRecolector regRecolector = new RegistrarRecolector(api);
				regRecolector.setLocationRelativeTo(null);
				regRecolector.setVisible(true);
			});
			recolectorMenu.add(registrarRecolectorMenuItem);
			
			JMenuItem listadoRecolectoresMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoRecolectoresMenuItem.addActionListener((ActionEvent e) -> {
				ListadoRecolector recolectores;
					recolectores = new ListadoRecolector(api);
					recolectores.setLocationRelativeTo(null);
					recolectores.setVisible(true);
				
				
			});
			recolectorMenu.add(listadoRecolectoresMenuItem);
			
			JMenu beneficioMenu = new JMenu(labels.getString("menu.beneficio"));
			menuBar.add(beneficioMenu);
			
			JMenuItem registrarBeneficioMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			registrarBeneficioMenuItem.addActionListener((ActionEvent e) -> {
				RegistrarBeneficio regBeneficio = new RegistrarBeneficio(api);
				regBeneficio.setLocationRelativeTo(null);
				regBeneficio.setVisible(true);
			});
			beneficioMenu.add(registrarBeneficioMenuItem);
			
			JMenuItem listadoBeneficiosMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoBeneficiosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoBeneficio beneficios;
				try {
					beneficios = new ListadoBeneficio(api);
					beneficios.setLocationRelativeTo(null);
					beneficios.setVisible(true);
				} catch (AppException | DataEmptyException | NotNullException | NumbersException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);

				}
				
			});
			beneficioMenu.add(listadoBeneficiosMenuItem);

			JMenu campañaMenu = new JMenu(labels.getString("menu.campana"));
			menuBar.add(campañaMenu);
			
			JMenuItem registrarCampañaMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			registrarCampañaMenuItem.addActionListener((ActionEvent e) -> {
				RegistrarCampaña regCampaña = new RegistrarCampaña(api);
				regCampaña.setVisible(true);
			});
			campañaMenu.add(registrarCampañaMenuItem);
			
			JMenuItem listadoCampañasMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoCampañasMenuItem.addActionListener((ActionEvent e) -> {
				ListadoCampaña listaCampaña = new ListadoCampaña(api);
				listaCampaña.setVisible(true);
			});
			campañaMenu.add(listadoCampañasMenuItem);
			
			
			JMenu canjeMenu = new JMenu(labels.getString("menu.canje"));
			menuBar.add(canjeMenu);
			
			JMenuItem elegirCanjeMenuItem = new JMenuItem(labels.getString("menu.canjear"));
			elegirCanjeMenuItem.addActionListener((ActionEvent e) -> {
				Integer idCampaña;
				try {
					idCampaña = api.obtenerCampañaVigente().obtenerId();
					SeleccionBeneficios selBeneficio = new SeleccionBeneficios(api, idCampaña);
					selBeneficio.setVisible(true);
				} catch (AppException | DateException | NotNullException | DataEmptyException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
			});
			canjeMenu.add(elegirCanjeMenuItem);
			
			JMenuItem listadoCanjesMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoCanjesMenuItem.addActionListener((ActionEvent e) -> {
				ListadoCanjes listadoCanjes;
				try {
					listadoCanjes = new ListadoCanjes(api);
					listadoCanjes.setVisible(true);
				} catch (EmptyListException | AppException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			canjeMenu.add(listadoCanjesMenuItem);
		}
		
		
		
		
		
		//pregunta si el usuario es de rol RECICLADOR
		if(api.esUsuarioReciclador()) {
			
			JMenu viviendasMenu = new JMenu(labels.getString("menu.mis.viviendas"));
			menuBar.add(viviendasMenu);
			
			JMenuItem altaModificacionMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			altaModificacionMenuItem.addActionListener((ActionEvent e) -> {
					AltaVivienda altaViv = new AltaVivienda(api);
					altaViv.setLocationRelativeTo(null);
					altaViv.setVisible(true);
			});
			viviendasMenu.add(altaModificacionMenuItem);
			
			JMenuItem listadoMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoMenuItem.addActionListener((ActionEvent e) -> {
					ListadoVivienda listadoViv;
					try {
						listadoViv = new ListadoVivienda(api);
						listadoViv.setLocationRelativeTo(null);
						listadoViv.setVisible(true);
					} catch (EmptyListException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("menu.listado"), JOptionPane.INFORMATION_MESSAGE);
					}
					
			});
			viviendasMenu.add(listadoMenuItem);
			
			JMenu pedidosMenu = new JMenu(labels.getString("menu.mis.pedidos"));
			menuBar.add(pedidosMenu);
			
			JMenuItem altaModificacionPedidosMenuItem = new JMenuItem(labels.getString("menu.alta.modificacion"));
			altaModificacionPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoVivienda altaPedido;
				try {
					altaPedido = new ListadoVivienda(api);
					altaPedido.setLocationRelativeTo(null);
					altaPedido.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			pedidosMenu.add(altaModificacionPedidosMenuItem);
			
			JMenuItem listadoPedidosMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoPedidosMenuItem.addActionListener((ActionEvent e) -> {
				ListadoPedidoRetiro pedidos;
				try {
					pedidos = new ListadoPedidoRetiro(api);
					pedidos.setLocationRelativeTo(null);
					pedidos.setVisible(true);
				} catch (EmptyListException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),  labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			pedidosMenu.add(listadoPedidosMenuItem);
		
			JMenu canjeMenu = new JMenu(labels.getString("menu.mis.canjes"));
			menuBar.add(canjeMenu);
			
			JMenuItem elegirCanjeMenuItem = new JMenuItem(labels.getString("menu.canjear"));
			elegirCanjeMenuItem.addActionListener((ActionEvent e) -> {
				Integer idCampaña;
				try {
					idCampaña = api.obtenerCampañaVigente().obtenerId();
					SeleccionBeneficios selBeneficio = new SeleccionBeneficios(api, idCampaña);
					selBeneficio.setVisible(true);
				} catch (AppException | DateException | NotNullException | DataEmptyException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),  labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			canjeMenu.add(elegirCanjeMenuItem);
			
			JMenuItem listadoCanjesMenuItem = new JMenuItem(labels.getString("menu.listado"));
			listadoCanjesMenuItem.addActionListener((ActionEvent e) -> {
				ListadoCanjes listadoCanjes;
				try {
					listadoCanjes = new ListadoCanjes(api);
					listadoCanjes.setVisible(true);
				} catch (EmptyListException | AppException | InstanceException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),  labels.getString("informacion"), JOptionPane.INFORMATION_MESSAGE);
				}
				
			});
			canjeMenu.add(listadoCanjesMenuItem);
			
			
			JMenu puntosMenu = new JMenu(labels.getString("menu.mis.puntos"));
			menuBar.add(puntosMenu);
			
			JMenuItem verPuntoseMenuItem = new JMenuItem(labels.getString("menu.ver.puntos"));
			verPuntoseMenuItem.addActionListener((ActionEvent e) -> {
				CiudadanoDTO ciudadano= api.obtenerCiudadanoSesion();
				JOptionPane.showMessageDialog(null, "Puntos acumulados: "+ciudadano.obtenerPuntosObtenidos(), labels.getString("mis.puntos"), JOptionPane.INFORMATION_MESSAGE);
			});
			puntosMenu.add(verPuntoseMenuItem);
		}
		
		
		
		
		JMenu configuracionMenu = new JMenu(labels.getString("menu.config"));
		menuBar.add(configuracionMenu);
		JMenuItem salirMenuItem = new JMenuItem(labels.getString("menu.salir"));
		salirMenuItem.addActionListener((ActionEvent e) -> {
			api.cerrarSesion();
			dispose();
			Loguin loguin = new Loguin(api);
			loguin.setVisible(true);
			
		});
		
		configuracionMenu.add(salirMenuItem);
		JMenuItem cambiarIdiomaMenuItem = new JMenuItem(labels.getString("cambiar.idioma"));
		cambiarIdiomaMenuItem.addActionListener((ActionEvent e) -> {
			SeleccionIdioma seleccionIdioma=new SeleccionIdioma(api);
			seleccionIdioma.setVisible(true);
			dispose();
			
		});
		configuracionMenu.add(cambiarIdiomaMenuItem);
	}

}
