package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class SeleccionIdioma extends JFrame {

	private JPanel contentPane;

	 ResourceBundle labels;
	
		
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IApi api = new PersistenceApi();
					SeleccionIdioma frame = new SeleccionIdioma(api);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SeleccionIdioma(IApi api) {
		labels=ResourceBundle.getBundle("labels");
		setTitle(labels.getString("seleccion.idioma"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton botonIngles = new JButton(labels.getString("ingles"));
		botonIngles.addActionListener((ActionEvent arg0) ->{
				labels= ResourceBundle.getBundle("labels", new Locale("en"));
				api.asignarIdioma(labels);
				Loguin loguin= new Loguin(api);
				loguin.setVisible(true);
				dispose();
		});
		botonIngles.setBounds(176, 93, 89, 23);
		contentPane.add(botonIngles);
		
		JButton botonEspanol = new JButton(labels.getString("espanol"));
		botonEspanol.addActionListener((ActionEvent arg0) ->{
			labels= ResourceBundle.getBundle("labels", new Locale("es"));
			api.asignarIdioma(labels);
			Loguin loguin= new Loguin(api);
			loguin.setVisible(true);
			dispose();
		});
		botonEspanol.setBounds(176, 160, 89, 23);
		contentPane.add(botonEspanol);
	}
}
