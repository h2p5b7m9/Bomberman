/* Pausa
Thread
*/

package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.awt.MediaTracker;
import javax.imageio.ImageIO;

public class tablero extends Canvas { // Clase hereda extends
	Image fPartida, fondo3;
	BufferedImage doubleBuffer = null, dobleb; // java.awt
 	Graphics db = null, db2 = null; // java.awt
	ppal ini;
	// Character [] teclas; // No se usa
	situacion matriz;
	String mapa;
	int over = 0, fvida = 0, nextlevel = 0; // over = 1 = Game Over o Fin de Juego ; fvida = 0 = No final de vida ; fvida = 1 = Final de vida ; nextlevel = final de nivel

	tablero(ppal p) { // Constructor de clase recibe 1 parametro
		ini = p;
 		matriz = new situacion (); // crea situacion q sera casillas [][]tablero;
		setSize(700, 700);
		// creaMapa();
		mapa = "mapa1.jpg";
		doubleBuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB); // java.awt ; getWidth()=700 ; getHeight()=700
		dobleb = new BufferedImage(getWidth(), getHeight(), dobleb.TYPE_INT_RGB); // java.awt ; getWidth()=700 ; getHeight()=700
		db = doubleBuffer.getGraphics(); // java.awt ; 
		MediaTracker tracker = new MediaTracker(this); // java.awt ; 
		fPartida = Toolkit.getDefaultToolkit().getImage(mapa); // java.awt ; 
		tracker.addImage(fPartida, 0); // java.awt ; 
		try {
		    tracker.waitForID(0);
		    while(!tracker.checkAll()) { // No entra
		    	System.out.println("Esperar, por favor...");
		    }
		    System.out.println("Precarga de fondo hecho...");
		} catch (InterruptedException e) {
		    System.out.println("Error en la Precarga del fondo...");
		}
	}

	void cargamapa(String map) { // map=pantalla1.txt
		matriz.cargaPantalla(map, ini);
	}

	void creaMapa() { // No se llama
		Random rand;
		rand = new Random();
		int numero = rand.nextInt(5); // Crea numero aleatorio random entre 0 y 5
		mapa = "mapa" + numero + ".jpg";
		System.out.println(mapa);
	}

	public void finito() {
		over = 1; // over = 1 = Game Over o Fin de Juego 
	}

	public void muerto() { // Llamado por casilla.java
		fvida = 1; // fvida = 0 = No final de vida ; fvida = 1 = Final de vida o Final de partida, No final de juego o game over
	}
	public void finpantalla() { // No se llama
		nextlevel = 1;
	}

	public void update(Graphics g) {
 		paint(g);
 	}

 	public void paint(Graphics g) {
 		int x = 0, y = 0, pintar = 0;
	 	BufferedImage cua, cua0, cua1, cua2, cua3, cua4, cua5, cua6, cua7, cua8, cua9, cua10, cua11, cua12, cua13;
	 	try {
	 		cua13 = ImageIO.read(new File ("fuego.gif"));
	 		cua12 = ImageIO.read(new File ("salida.gif"));
	 		cua11 = ImageIO.read(new File ("slow.gif"));
	 		cua10 = ImageIO.read(new File ("vida.gif"));
	 		cua9 = ImageIO.read(new File ("speedup.gif"));
	 		cua8 = ImageIO.read(new File ("powerup.gif"));
	 		cua7 = ImageIO.read(new File ("bombup.gif"));
	 		cua6 = ImageIO.read(new File ("monstruo2.gif"));
	 		cua5 = ImageIO.read(new File ("monstruo1.gif"));
	 		cua4 = ImageIO.read(new File ("22.gif"));
	 		cua3 = ImageIO.read(new File ("pared2.gif"));
	 		cua2 = ImageIO.read(new File ("pared1.gif"));
	 		cua1 = ImageIO.read(new File ("bomba.gif"));
	 		cua = ImageIO.read(new File ("fondo1.gif"));
	 		db2 = dobleb.getGraphics(); // Graphics db2
	 		db2.drawImage(cua, 0, 0, 700, 700, this); // Graphics db2
	 		// System.out.println("cargoPantalla");
	 		for(int i=0; i<10; i++) { // bucle 10 filas x 10 columnas
				for (int a=0; a<10; a++) {
					switch(matriz.tablero2[i][a].getcapa()) { // 1=Pared explotable blanca por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos tipo 1 (4), 6=Malos tipo 2 (0) leido de pantalla1.txt, 13=Expansion Vertical Arriba bomba, 14=Expansion Horizontal bomba
						case 0: // Bomba blanca mia
								db2.drawImage(cua1, a * 70, i * 70, 70, 70, this);
								break;
						case 1: // 1=Pared explotable blanca por prota/bueno si pulsa espacio
								db2.drawImage(cua2, a * 70, i * 70, 70, 70, this);
								break;
						case 2: // 2=Pared no explotable
								db2.drawImage(cua3, a * 70, i * 70, 70, 70, this);
								break;
						case 3: // 3=Pasillo transitable
								break;
						case 4: // 4=Protagonista (1 solo)
								db2.drawImage(cua4, a * 70, i * 70, 70, 70, this);
								break;
						case 5: // Malos tipo 1
								db2.drawImage(cua5, a * 70, i * 70, 70, 70, this);
								break;
						case 6: // 6=Malos tipo 2
								db2.drawImage(cua6, a * 70, i * 70, 70, 70, this);
								break;
						case 7: // bombup ; 7=Aumenta 1 bomba y Aumenta puntos
								db2.drawImage(cua7, a * 70, i * 70, 70, 70, this);
								break;
						case 8: // powerup ; 8=Aumenta potencia y Aumenta puntos
								db2.drawImage(cua8, a * 70, i * 70, 70, 70, this);
								break;
						case 9: // speedup ; 9=Reduce velocidad y aumenta puntos
								db2.drawImage(cua9, a * 70, i * 70, 70, 70, this);
								break;
						case 10: // 10=Aumenta 1 vida y Aumenta puntos ; 
								db2.drawImage(cua10, a * 70, i * 70, 70, 70, this);
								break;
						case 11: // 11=Aumenta velocidad y aumenta puntos
								db2.drawImage(cua11, a * 70, i * 70, 70, 70, this);
								break;
						case 12:
								db2.drawImage(cua12, a * 70, i * 70, 70, 70, this);
								break;
						case 13: // 13=Expansion Vertical bomba
								db2.drawImage(cua13,(a * 70) + 15, i * 70, 40, 70, this);
								break;
						case 14: // 14=Expansion Horizontal bomba
								db2.drawImage(cua13, a * 70, (i * 70) + 15, 70, 40, this);
								break;
						case 15:
								db2.drawImage(cua13, (a * 70) + 15, i * 70, 40, 70, this);
								db2.drawImage(cua13, a * 70, (i * 70) + 15, 70, 40, this);
								break;
					}
				}
			}
		} catch (java.io.FileNotFoundException e) {
			System.out.println ("error imagenes...");
		} catch (java.io.IOException e) {
			System.out.println ("error cargando imagenes...");
		}
		g.drawImage(dobleb, 0, 0, this); // volco el doble buffer ; Graphics g
		Font f = new Font("Arial", Font.BOLD, 70);
		if(over == 1) { // over = 1 = Game Over o Fin de Juego
			over = 0;
			g.setColor(Color.white);
			g.setFont(f);
			g.drawString("GAME OVER  " + ini.min + ":" + ini.seg, 50, 400); // min=minutos, seg=segundos
			try{
				Thread.sleep(3000); // Espera 3 segs
			}catch(InterruptedException e) {
				System.out.println("S'ha produit una excepció d'un thread:");
			}
			ranking r = new ranking(); // class ranking extends JFrame
			r.guardaPlayer(ini.game.nombre, ini.game.puntos); // Escribe el fichero ranking.info quedandose con los 5 mejores
			r.Mranking(); // Muestra el ranking mediante panel
		}
		if(fvida == 1) { // fvida = 0 = No final de vida ; fvida = 1 = Final de vida ; 
			fvida = 0; // Muere
			g.setColor(Color.white);
			g.setFont(f); // Font f
			g.drawString("  HAS MORT", 10, 400);
			try {
				Thread.sleep(3000); // Espera 3 segs
			}catch(InterruptedException e) {
				System.out.println("S'ha produit una excepció d'un thread:");
			}
			ini.inicializaNivel();
		}
		if(nextlevel == 1) {
			nextlevel = 0;
			g.setColor(Color.white); // Graphics g
			g.setFont(f); // Graphics g ; Font f
			g.drawString("  LEVEL UP", 10, 400);
			try {
				Thread.sleep(3000); // Espera 3 segs
			}catch(InterruptedException e) {
				System.out.println("S'ha produit una excepció d'un thread:");
			}
			ini.inicializaNivel();
			if(ini.game.pausa == 1) {
				g.setColor(Color.white);
				g.setFont(f);
				g.drawString("        PAUSE", 10, 400);
			}
		}
 	}
}

// instruccio per carregar imatges  -> bufferedImage a = ImageIO.read(new File (path));
// canviar toolkit per g.drawImage(fondo3, i * 50, a * 50, 50, 50, this);
