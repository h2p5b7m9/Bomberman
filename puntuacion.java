// Parte grafica de la derecha durante la partida. Ver BomberMan*.png

package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class puntuacion extends Canvas { // Herencia extends
	/* doble buffer */
 	BufferedImage dobleb = null;
 	Graphics db2 = null; // java.awt.Graphics
 	ppal ini;
 	int profundidad;

 	puntuacion(ppal ini2) { // Constructor de clase
		ini = ini2;
		setSize(305, 705);
		dobleb = new BufferedImage(getWidth(), getHeight(), dobleb.TYPE_INT_RGB); // java.awt.*
	}

	public void update(Graphics g) {
 		paint(g);
 	}

 	public void paint(Graphics g) {
 		BufferedImage head, fondo;
 		Font f = new Font("Arial", Font.BOLD, 16);
		profundidad = 0; // java.awt.Graphics
		try {
			fondo = ImageIO.read(new File ("fondoizq.gif")); // javax.imageio.ImageIO
			db2 = dobleb.getGraphics(); // java.awt.Graphics
			db2.drawImage(fondo, 0, 0, 305, 705, this); // java.awt.Graphics
	 		// db = doubleBuffer.getGraphics();
		 	head = ImageIO.read(new File ("logo.gif")); // javax.imageio.ImageIO
	 		db2.drawImage(head, 0 , 0, 300, 200, this); // java.awt.Graphics
	 		String puntuacion = new String ("Puntuacio: " + ini.game.puntos);
	 		String nombre = new String ("Jugador: " + ini.game.nombre);
	 		String vidas = new String ("Vides :" + (ini.game.vidas - 1));
	 		String potencia = new String ("Potencia: " + ini.game.potencia); // potencia = Numero de casillas que explotan con la bomba verticales y horizontales ed onda expansiva de la bomba 
	 		String bombas = new String ("Bombes: " + ini.game.nbombas);
	 		String nivel = new String ("Nivell: " + ini.game.nivel);
	 		db2.setColor(Color.gray); // java.awt.Graphics
			db2.setFont(f);
			profundidad = 230; // java.awt.Graphics
			db2.drawString(nivel, 10, profundidad);
			profundidad += 20; // java.awt.Graphics
			db2.drawString(nombre, 10, profundidad);
			profundidad += 20; // java.awt.Graphics
			db2.drawString(puntuacion, 10, profundidad);
			profundidad += 20;
			db2.drawString(vidas, 10, profundidad); // java.awt.Graphics
			profundidad += 20; // java.awt.Graphics
			pintaa(db2, 2, ini.game.vidas - 1); // 2=vidas ; db2 java.awt.Graphics
			db2.drawString(bombas, 10, profundidad); // java.awt.Graphics
			profundidad += 20; // java.awt.Graphics
			pintaa(db2, 1, ini.game.nbombas); // 1=bombas ; db2 java.awt.Graphics
			db2.drawString(potencia, 10, profundidad); // java.awt.Graphics
			profundidad += 20; // java.awt.Graphics
			pintaa(db2, 3, ini.game.potencia); // 3=potencia ; db2 java.awt.Graphics
		} catch (java.io.IOException e) {
			System.out.println ("error cargando imagenes...");
		}
		g.drawImage(dobleb, 0, 0, this); // volco el doble buffer
 	}

 	private void pintaa(Graphics g, int opcion, int bucle) {
 		int j = 0, x = 20;
 		BufferedImage opc = null;
 		try {
 			switch(opcion) {
 				case 1: // 1=bombas
 						opc = ImageIO.read(new File ("bomba.gif"));
 						break;
 				case 2: // 2=vidas
 						opc = ImageIO.read(new File ("life.gif"));
 						break;
 				case 3: // 3=potencia
 						opc = ImageIO.read(new File ("potencia.gif"));
 						break;
 			}

	 		while(j < bucle) {
	 			g.drawImage(opc, x, profundidad, 50, 50, this);
	 			x += 50;
	 			j++;
	 			if((j % 5 == 0) && (j < bucle)) { // Divisible por 5 ed cada 5
	 				x = 10;
	 				profundidad += 60;
	 			}
	 		}
	 		profundidad += 80;
 		}catch (java.io.IOException e) {
			System.out.println ("error cargando imagenes...");
		}
 	}
}
