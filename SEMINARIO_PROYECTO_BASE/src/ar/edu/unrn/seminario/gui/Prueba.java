package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

public class Prueba extends JFrame {

	private JPanel contentPaneMedio;
	private JPanel contentPaneArriba;
	private JPanel contentFila;
	private JPanel contentPaneAbajo;
	private JPanel costadoIzq;
	private JPanel contentPane;
	private JCheckBox checkResiduo;
	private JTextField textoKilo;
	private JScrollPane scroll;
	/**
	 * Launch the application.
	 *

	/**
	 * Create the frame.
	 */
	public Prueba(IApi api) {
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		contentPaneArriba= new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPaneArriba.setBounds(0, 0, 434, 67);
		contentPaneArriba.setLayout(null);
		contentPane.add(contentPaneArriba);
		
		
		contentPaneMedio=new JPanel();
		contentPaneMedio.setBounds(0, 66, 261, 23);
		contentPaneMedio.setLayout(new BoxLayout(contentPaneMedio, BoxLayout.Y_AXIS));//vertical
		contentPane.add(contentPaneMedio);
		
		for(ViviendaDTO v : api.) {
			
		}
		contentFila=new JPanel();
		checkResiduo=new JCheckBox("Vidrio");
		checkResiduo.setSize(5, 5);
		textoKilo=new JTextField();
		textoKilo.setSize(5, 5);
		contentFila.setLayout(new BoxLayout(contentFila, BoxLayout.X_AXIS )); //horizontal, dos valores
		contentFila.setSize(5, 5);
		contentFila.add(checkResiduo);
		contentFila.add(textoKilo);
	
		contentPaneMedio.add(contentFila);
		scroll= new JScrollPane();
		scroll.setViewportView(contentPaneMedio);
		
		
		
		
	}

}
