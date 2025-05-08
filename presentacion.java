package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;
import java.awt.MediaTracker;


public class presentacion extends JFrame implements ActionListener { // Herencia extends ; implements interface
	Container c = null; // java.awt.*
	Panel bajo, pfondo,vacio; // java.awt.*
	Button inicio, salir; // java.awt.*
	ppal partida;
	ImageIcon ima = new ImageIcon("4.jpg", "fondo"); // javax.swing.ImageIcon
	opciones opt = null;

	public static void main (String [] args){}

	presentacion(ppal pr) {
		super("..:: Bomberman - Ignacio Macipe - IS09765 ::..");
		// setResizable(false);
		partida = pr;
		c = getContentPane(); // Container javax.swing.JFrame
		c.setLayout(new BorderLayout()); // BorderLayout() java.awt.*
		c.setBackground(Color.black);
		inicio = new Button ("Modalidad 1 player");
		inicio.addActionListener(this);
		salir = new Button ("Modalidad Online");
		salir.addActionListener(this);
		bajo = new Panel ();
		vacio = new Panel();
		vacio.setBackground(Color.black);
		bajo.setLayout(new GridLayout(2, 2));
		bajo.add(inicio);
		bajo.add(salir);
		bajo.add(vacio);
		pfondo = new Panel();
		pfondo.setBackground(Color.black);
		pfondo.setSize(500, 500);
		JLabel et = new JLabel(ima); // javax.swing.ImageIcon
		// et.reshape(0, 0, 100, 150);
		pfondo.add(et);
		c.add(pfondo, BorderLayout.NORTH);
		c.add(bajo, BorderLayout.SOUTH);
		setSize(500, 600);
		addWindowListener(new DetectarFinestra());
		show();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salir) {
			online p = new online(partida);
			setVisible(false);
		}
		if(e.getSource() == inicio) {
			System.out.println("antes de petar!");
			opt = new opciones (partida, this);
			System.out.println("despues de e!");
			setVisible(false);
		}
	}
}
