package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.*;

public class situacion {
	casilla [][] tablero2;
	int ancho, alto, actualX, actualY;
	
	public situacion() { // Constructor de clase Sin parametros
      int X = 0; // posicion x en tablero 10x10 
      int Y = 0; // posicion y en tablero 10x10
		tablero2 = new casilla [10][10]; // 10 filas y 10 columnas = 100 casillas
		for(int i = 0; i < 10; i++) { // Crea tablero de 10 x 10 = 100 casillas
 			for(int z = 0; z < 10; z++) {
 				tablero2[i][z] = new casilla ();
 				X += 50;
 			}
 			Y += 50;
 			X = 0;
 		}
	}

	public void cargaPantalla (String nivel, ppal p) { // nivel=pantalla1.txt que es un fichero que contiene la situacion inicial del prota y los malos en una matriz 10x10. Las bombas se ponen manualm mediante el espacio.
		BufferedReader br;
		FileReader fr;
		String info;
		int resultado = 0, resultado2 = 0;
		// nivel = "prueba.txt";
		int i = 0, j = 0, aux = 0, aux2;
      try {
         System.out.println("cargaPantalla " + nivel);
         fr = new FileReader (nivel); 
         br = new BufferedReader(fr);
			for(i=0; i<10; i++) { // 10 lineas de pantalla1.txt
			   info = br.readLine();
			   aux = 0;
				for(j = 0; j < 10; j++) { // 10 columnas de pantalla1.txt
					// System.out.println("mirando de cargar la posicion:" + i + " - " + j);
					resultado = (int)info.charAt(aux); // charAt(index) Devuelve el codigo ASCII del carácter situado en la posición index del registro leido de pantalla1.txt, ed 1=49, 2=50, 3=51, 4=52, 5=53
					resultado -= 48; // Cod ASCII de 1,2,3,4,5,6 del registro leido de pantalla1.txt ejemplo 1=49, 2=50, 3=51, 4=52, 5=53, 6=54 // 1=Pared explotable por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) 
					if(resultado == 4) { // 4=Protagonista (1 solo)
						p.ponprota(j, i);
					}
					if(resultado == 5) { // 5=Malos tipo 1 (4)
						p.malos[p.game.nmalos] = new malo1 (p, i, j, p.game.nmalos, 1); // malo tipo 1 ; malo1 extends Thread
						p.game.nmalos++; // Incrementa numero de malos tipo 1
					}
					if(resultado == 6) { // 6=Malos tipo 2 (0)
						p.malos[p.game.nmalos] = new malo1 (p, i, j, p.game.nmalos, 2); // malo tipo 2 ; malo1 extends Thread
						p.game.nmalos++; // Incrementa numero de malos tipo 2
					}
					if(resultado == 8) { // No hay ningun 8 en pantalla?.txt
						resultado = 12; // casilla.addcapa aumenta nivel, pasa a siguiente nivel y finaliza partida si recibe un 12
					}
					tablero2[i][j].rellenacas(resultado);
					aux++;
				}
			}
			j = 0;
		while (j < 10) { // 10 Lineas de pantalla1.txt
			System.out.println("----" + tablero2[j][0].getcapa() + "-" + tablero2[j][1].getcapa() + "-" + tablero2[j][2].getcapa() + "-" + tablero2[j][3].getcapa() + "-" + tablero2[j][4].getcapa() + "-" + tablero2[j][5].getcapa() + "-" + tablero2[j][6].getcapa() + "-" + tablero2[j][7].getcapa() + "-" + tablero2[j][8].getcapa() + "-" + tablero2[j][9].getcapa()); // Linea de pantalla1.txt
			j++;
		}
		}catch (java.io.FileNotFoundException e) {
			System.out.println ("Error al abrir el fichero...");
		}catch (java.io.IOException e) {
			System.out.println ("Error al leer el fichero...");
		}
		System.out.println("Tablero cargado con exito");
	}
}
