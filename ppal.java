/*
main
start()
Buscar vida en *.java
awt.event.ActionListener AWT Abstract Window Toolkit

 */

package salvado;

import java.awt.*; // AWT Abstract Window Toolkit
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.awt.MediaTracker;

public class ppal extends JFrame implements ActionListener { // Herencia extends ; implements interface
	Container c = null; // java.awt.* Abstract Window Toolkit
	MenuItem nPartida, cPartida, gPartida, salir, vRanking, nJugador, sJugador, fin, cTeclas, nDificultad, nNivel; // java.awt.*
	Menu Partida, Ver, Juego, Opciones; // java.awt.*
	MenuBar barra; // java.awt.*
	Panel est, west; // java.awt.*
	presentacion p = null; // presentacion extends JFrame implements ActionListener
	tablero tab; // tablero extends Canvas

	malo1 [] malos; // array de malos ; public class malo1 extends Thread
	int bombusadas; // numero de malos activos, numero de bombas posibles, numero de bombas activas
	puntuacion punts; // public class puntuacion extends Canvas
	bueno prota; // public class bueno
	bomba bomb []; // public class bomba extends Thread  ; [] Array
	partida game; // public class partida
	int seg, min; // segundos, minutos
	tiempo t; // public class tiempo extends Thread

	public static void main(String [] args) {
		new ppal(); // Instancia de clase
	}

	public ppal() { // Constructor de clase
		super("..:: Bomberman - Ignacio Macipe - IS09765 ::.."); // super padre
		//setResizable(false);
		//*********inicializamos alguna variable ********//
		malos = new malo1[10]; // No entra por el Constructor de clase de 5 parametros ; malo1 extends Thread
		bombusadas = 0;
		bomb = new bomba [100]; // podria crear un creabomba como E creaprota ; 100 bombas ; bomba extends Thread
		game = new partida();
		game.inicializapartida();
		//***********************************************//
		c = getContentPane(); // java.awt.Container
		c.setLayout(new BorderLayout());  // java.awt.Container
		// c.setBackground(Color.black); // java.awt.Container
		Partida = new Menu ("Partida"); // java.awt.*
		Ver = new Menu ("Ver"); // java.awt.*
		Juego = new Menu ("Juego"); // java.awt.*
		Opciones = new Menu ("Opciones"); // java.awt.*
		nPartida = new MenuItem ("Partida nueva"); // java.awt.*
		cPartida = new MenuItem ("Cargar Partida"); // java.awt.*
		gPartida = new MenuItem ("Guardar partida en curso"); // java.awt.*
		salir = new MenuItem ("Salir"); // java.awt.*
		vRanking = new MenuItem ("Ver Ranking"); // java.awt.*
		nJugador = new MenuItem ("Introducir jugador nuevo"); // java.awt.*
		sJugador = new MenuItem ("Seleccionar jugador existente"); // java.awt.*
		fin = new MenuItem ("Final de la partida"); // java.awt.*
		cTeclas = new MenuItem ("Cambiar las teclas"); // java.awt.*
		nDificultad = new MenuItem ("Cambiar nivel dificultad"); // java.awt.*
		nNivel = new MenuItem ("Cambio de nivel"); // java.awt.*
		nPartida.addActionListener(this); // java.awt.*
		cPartida.addActionListener(this); // java.awt.*
		gPartida.addActionListener(this); // java.awt.*
		salir.addActionListener(this); // java.awt.*
		vRanking.addActionListener(this); // java.awt.*
		nJugador.addActionListener(this); // java.awt.*
		sJugador.addActionListener(this); // java.awt.*
		fin.addActionListener(this); // java.awt.*
		cTeclas.addActionListener(this); // java.awt.*
		nDificultad.addActionListener(this); // java.awt.*
		nNivel.addActionListener(this); // java.awt.*
		Partida.add(nPartida); // java.awt.*
		Partida.add(cPartida); // java.awt.*
		Partida.add(gPartida); // java.awt.*
		Partida.add(fin); // java.awt.*
		Partida.addSeparator(); // java.awt.*
		Partida.add(salir); // java.awt.*
		Ver.add(vRanking); // java.awt.*
		Opciones.add(nJugador); // java.awt.*
		Opciones.add(sJugador); // java.awt.*
		Opciones.add(cTeclas); // java.awt.*
		Opciones.add(nDificultad); // java.awt.*
		Opciones.add(nNivel); // java.awt.*
		barra = new MenuBar(); // java.awt.*
		barra.add(Partida); // java.awt.*
		barra.add(Ver); // java.awt.*
		barra.add(Opciones); // java.awt.*
		setMenuBar(barra); // java.awt.*
		// setSize(1000, 734);
		prota = new bueno(this);
		punts = new puntuacion(this); // puntuacion extends Canvas
		tab = new tablero(this); // tablero extends Canvas
		//inicializaNivel();
		tab.addKeyListener(new teclado(this)); // tablero extends Canvas ; teclado extends KeyAdapter implements KeyListener
		c.add(tab, BorderLayout.WEST); // java.awt.*
		c.add(punts, BorderLayout.EAST); // java.awt.*
		/* crear la pantalla de presentacion */
		/*************************************/
		addWindowListener(new DetectarFinestra()); // DetectarFinestra extends WindowAdapter
		setSize(1014, 750); // java.awt.*
		p = new presentacion(this); // presentacion extends JFrame implements ActionListener
	}

	public void arranca() {
		int jj = 0;
		System.out.println("numero de malos= " + game.nmalos); // 
		while(jj < game.nmalos) {
			malos[jj].start(); // malo1 extends Thread ; start() llama a run() en malo1.java
			jj++;
		}
		tab.requestFocus(); // tablero extends Canvas
	}

	public void ponprota(int x, int y) {
		prota.coloca(x, y);
	}

	public void creaBomba(int x, int y) {
		int a = 0, fin = 0;
		if(bombusadas < 500) {
			bomb[bombusadas] = new bomba(x, y, game.potencia, this); // bomba extends Thread
			tab.matriz.tablero2[y][x].addcapa(0, 0, 0, this); // 0=Bomba prota blanca ; tablero extends Canvas
			bomb[bombusadas].start(); // bomba extends Thread ; start() llama a run() en bomba.java
			bombusadas++;
		}
	}

	public void inicializaNivel() {
		game.fingame = 0; // partida game ; fingame = 1 = Fin de partida
		bombusadas = 0;
		game.pausa = 0;
		game.end = 0; // partida game ; end = 1 = Fin de partida
		bombusadas = 0;
		game.nmalos = 0;

		switch(game.nivel) { // Aumenta nivel -> Aumenta pantalla*.txt y Reduce velocidad ; partida game
			case 1:
				tab.cargamapa("pantalla1.txt"); // Dificultad nivel de pantalla1.txt > Dificultad nivel de pantalla2.txt ; tablero tab
				game.speed = 1; // partida game
				game.nivel = 1; // partida game
				break;
			case 2:
				tab.cargamapa("pantalla2.txt"); // Aumenta pantalla*.txt ; Tiene 2 characters 6=Malos tipo 2 que tienen aspecto de bombas oscuras y se mueven
				game.speed = 1;
				game.speed-=0.1; // Reduce velocidad
				game.nivel = 2; // Aumenta nivel
				break;
			case 3:
				tab.cargamapa("pantalla3.txt"); // Aumenta pantalla*.txt 
				game.speed = 1;
				game.speed-=0.2; // Reduce velocidad
				game.nivel = 3; // Aumenta nivel
				break;
			case 4:
				tab.cargamapa("pantalla4.txt"); // Aumenta pantalla*.txt 
				game.speed = 1;
				game.speed-=0.3; // Reduce velocidad
				game.nivel = 4;
				break;
			case 5:
				tab.cargamapa("pantalla5.txt"); // Aumenta pantalla*.txt 
				game.speed = 1;
				game.speed-=0.4; // Reduce velocidad
				game.nivel = 5;
				break;
			case 6:
				tab.cargamapa("pantalla6.txt"); // Aumenta pantalla*.txt 
				game.speed = 1;
				game.speed-=0.5; // Reduce velocidad
				game.nivel = 6;
			}
			tab.repaint(); // 
			System.out.println("antes de arrancar");
			arranca();
			System.out.println("despues de arrancar");
			tab.requestFocus(); // 
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == nPartida) {
			game.inicializapartida();
			p.opt.setVisible(true);
			setVisible(false);
		}
		if(e.getSource() == cPartida) {
		}
		if(e.getSource() == gPartida) {
		}
		if(e.getSource() == salir) {
			System.exit(0);
		}
		if(e.getSource() == vRanking) {
		}
		if(e.getSource() == nJugador) {
		}
		if(e.getSource() == sJugador) {
		}
		if(e.getSource() == fin) {
			game.fingame = 1; // partida game ; fingame = 1 = Fin de partida
			game.end = 1; // partida game ; end = 1 = Fin de partida
			game.vidas = 0; // vidas=0=Muere
			tab.finito(); // tablero ; game over o Fin de Juego
			tab.repaint(); // 
		}
		if(e.getSource() == cTeclas) {
		}
		if(e.getSource() == nDificultad) {
		}
		if(e.getSource() == nNivel) {
		}
	}
}
		//en lloc de JRadioButto fer checkboxGroups -
