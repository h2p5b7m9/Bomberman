package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class teclado extends KeyAdapter implements KeyListener { // Teclado
	ppal ini;
	
	public teclado(ppal ini2) { // Constructor
		ini = ini2;
	}

 	public void keyPressed(KeyEvent e) {
 		// if (e.getKeyCode() == 40) { // Tecla Flecha Abajo
 		if (e.getKeyCode() == KeyEvent.VK_DOWN) { // Tecla Flecha Abajo
 			//System.out.println("APRETADO abajo");
 			ini.prota.movimiento(4);
 		}
 		// if (e.getKeyCode() == 39) { // Tecla Flecha Derecha
 		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // Tecla Flecha Derecha
 			//System.out.println("APRETADO derecha");
 			ini.prota.movimiento(3);
 		}
 		if (e.getKeyCode() == 32) { // ascii 32 = Tecla Espacio = Prota crea bomba
 			//System.out.println("APRETADO EL ESPACIO");
 			ini.prota.lanzabomb();
 			System.out.println("bombas" + bomba.nbombas);
 		}
 		if (e.getKeyCode() == 38) { // Tecla Flecha Arriba
 			//System.out.println("APRETADO arriba");
 			ini.prota.movimiento(1);
 		}
 		// if (e.getKeyCode() == 37) { // Tecla Flecha Izquierda Left
 		if (e.getKeyCode() == KeyEvent.VK_LEFT) { // Tecla Flecha Izquierda Left
 			//System.out.println("APRETADO izquierda");
 			ini.prota.movimiento(2);
 			//System.out.println("bombas activas: " + bomba.nbombas);
 		}
 		if (e.getKeyCode() == 80) { // Tecla P=Pausa detiene el juego
 			/*
 			System.out.println("quedan malos: " + malo1.nmalos);
 			System.out.println("la pausa vale " + ini.game.pausa);
 			System.out.println("la end vale " + ini.game.end);
 			System.out.println("la fingame vale " + ini.game.fingame);
 			for (int j=0; j<10; j++) {
 				if(ini.malos[j] == null){
 					System.out.println("el malo numero " + j + " esta en null");
 				} else {
 					System.out.println("el malo numero " + j + " no es null");
 					System.out.println("x: " + ini.malos[j].posicionx + "y: " + ini.malos[j].posiciony);
 				}
 			}
 			// ini.tab.control();
 			*/
 			if (ini.game.pausa == 1) { // Si hay pausa quito pausa
 			   ini.game.pausa = 0;
 			}
 			else { // Si no hay pausa pongo pausa
 				ini.game.pausa = 1;
 				int i;
 			      for( i=0; i<10; i++) {
 			         System.out.println(ini.tab.matriz.tablero2[i][0].getcapa() + " - " + ini.tab.matriz.tablero2[i][1].getcapa() + " - " + ini.tab.matriz.tablero2[i][2].getcapa() + " - " + ini.tab.matriz.tablero2[i][3].getcapa() + " - " + ini.tab.matriz.tablero2[i][4].getcapa() + " - " + ini.tab.matriz.tablero2[i][5].getcapa() + " - " + ini.tab.matriz.tablero2[i][6].getcapa() + " - " + ini.tab.matriz.tablero2[i][7].getcapa() + " - " + ini.tab.matriz.tablero2[i][8].getcapa() + " - " + ini.tab.matriz.tablero2[i][9].getcapa());
	     	      }
	      }
 		   ini.tab.repaint();
 		}
	}
}