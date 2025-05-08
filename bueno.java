// Prota

package salvado;

import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class bueno {
   ppal ini;
   int x,y;

   bueno(ppal ini2) { // Constructor de clase con 1 parametro
      ini = ini2;
	}

	void coloca(int posx, int posy) {
		x = posx;
		y = posy;
	}

	// añadir el tema de pausa y el de morir
	public void lanzabomb() { // Tecla Espacio = Prota crea bomba
		if(bomba.nbombas < ini.game.nbombas) { // Si le quedan bombas por poner
			ini.creaBomba(x, y);
		}
	}

	public void movimiento(int mov) { // Movimiento
		boolean tirada = true;
		if ((ini.game.end == 0) && (ini.game.pausa == 0)) { // No es fin de partida ni pausa activada ; end = 1 = Fin de partida porque te ha matado un malo
			switch (mov) {
				case 1: // Tecla Flecha Arriba
					if((y - 1) < 0) { // me salgo de limites ; y empieza arriba, no abajo
						tirada = false;
					}
					if (tirada == true) {
						if (ini.tab.matriz.tablero2[y - 1][x].getcapa() < 3) { // no se puede pasar porque hay pared ; 1=Pared explotable por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
							tirada = false;
						}
					}
					if (tirada) {
						ini.tab.matriz.tablero2[y][x].quitcapa();
						y-=1;
						ini.tab.matriz.tablero2[y][x].addcapa(4, 0, 0, ini); // 4=Bueno
					}
					break;
				case 2: // Tecla Flecha Izquierda Left
					if((x - 1) < 0) { // me salgo de limites
						tirada = false;
					}
					if (tirada == true) {
						if (ini.tab.matriz.tablero2[y][x - 1].getcapa() < 3) { // no se puede pasar porque hay pared ; 1=Pared explotable por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
							tirada = false;
						}
					}
					if (tirada) {
						ini.tab.matriz.tablero2[y][x].quitcapa();
						x-=1;
						ini.tab.matriz.tablero2[y][x].addcapa(4, 0, 0, ini); // 4=Bueno
					}
					break;
				case 3: // Tecla Flecha Derecha
					if((x + 1) == 10) { // me salgo de limites
						tirada = false;
					}
					if (tirada == true) {
						if (ini.tab.matriz.tablero2[y][x + 1].getcapa() < 3) { // no se puede pasar porque hay pared ; 1=Pared explotable por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
							tirada = false;
						}
					}
					if (tirada) {
						ini.tab.matriz.tablero2[y][x].quitcapa();
						x+=1;
						ini.tab.matriz.tablero2[y][x].addcapa(4, 0, 0, ini); // 4=Bueno
					}
					break;
				case 4: // Tecla Flecha Abajo
					if((y + 1) == 10) { // me salgo de limites ; y empieza arriba, no abajo
						tirada = false;
					}
					if (tirada == true) {
						if (ini.tab.matriz.tablero2[y + 1][x].getcapa() < 3) { // no se puede pasar porque hay pared ; 1=Pared explotable por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
							tirada = false;
						}
					}
					if (tirada) {
						ini.tab.matriz.tablero2[y][x].quitcapa();
						y+=1;
						ini.tab.matriz.tablero2[y][x].addcapa(4, 0, 0, ini); // 4=Bueno
					}
					break;
			}
			ini.tab.repaint();
		//wait(400);
		}
	}
}
