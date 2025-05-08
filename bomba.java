// Buscar run()
// Pulsando espacio=32 se pone  una bomba
// Cada vez que hace un new a una clase Thread se arranca un hilo de ejecucion independiente paralelo
// KK addcapa

package salvado;

import java.io.*;
import java.util.*;
import java.lang.*;

public class bomba extends Thread { // Herencia extends
	static int nbombas = 0; // Numero de bombas ; OJO static 1 vez para todos los objetos de la clase
	int x;
	int y;
	int potencia; // potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba 
	ppal ini;

	bomba (int posx, int posy, int poten, ppal ini2) { // Constructor de clase de 4 parametros
		x = posx;
		y = posy;
		potencia = poten; // potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba 
		ini = ini2;
		nbombas++;
	}

	public void run() { // Llamado por start() en ppal.java ; Expande explosion bomba (onda expansiva) vertical horizontal y Limpia la explosion
		int j = 0, stopup = 0, up = 0, stopdown = 0, down = 0, stopright = 0, right = 0, stopleft = 0, left = 0, valor;
		try {
			sleep(2000); // Espera 2 segundos
			System.out.println("explosion");
			ini.tab.matriz.tablero2[y][x].quitcapa();
			// Expande explosion arriba
			// poner en la casilla de la bomba dos imagenes una horiz y otra vertical
			ini.tab.matriz.tablero2[y][x].addcapa(15, x, y, ini); // 15=Bomba pero addcapa no lo guarda 
			while(j < potencia) { // potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba 
				valor = y - 1 - up; // No se usa ; y - 1 = Arriba ed Expande explosion bomba (onda expansiva) Arriba; 
				if(((y - 1 - up) > -1) && (stopup == 0)) { // y - 1 = Arriba ed Expande explosion bomba (onda expansiva) Arriba; 
					// System.out.println("puedo pintar arriba");
					if(ini.tab.matriz.tablero2[(y - 1 - up)][x].getcapa() != 2) { // y - 1 = Arriba ; No 2=Pared no explotable ; 
						// System.out.println("la capa arriba diferente de dos es:" + ini.tab.matriz.tablero2[y][x].getcapa());
						up++;
						ini.tab.matriz.tablero2[(y - up)][x].addcapa(13, x, (y - up), ini); // 13=Expansion Vertical Arriba bomba
					} else {
						stopup = 1;
					}
				}
				if(((y + 1 + down) < 10) && (stopdown == 0)) { // y + 1 = Abajo ed Expande explosion bomba (onda expansiva) Abajo; 
					if(ini.tab.matriz.tablero2[(y + 1 + down)][x].getcapa() != 2) { // y + 1 = Abajo ; 2=Pared no explotable ; 
						down++;
						ini.tab.matriz.tablero2[(y + down)][x].addcapa(13, x, (y + down), ini); // 13=Expansion Vertical Abajo bomba 
					} else {
						stopdown = 1;
					}
				}
				if(((x - 1 - left) > -1) && (stopleft == 0)) { // x - 1 = Izquierda ed Expande explosion bomba (onda expansiva) Izquierda; 
					if(ini.tab.matriz.tablero2[y][(x - 1 - left)].getcapa() != 2) { // x - 1 = Izquierda ; 2=Pared no explotable ; 
						left++;
						ini.tab.matriz.tablero2[y][(x - left)].addcapa(14, (x - left), y, ini); // 14=Expansion Horizontal Izquierda bomba 
					}else {
						stopleft = 1;
					}
				}
				if(((x + 1 + right) < 10) && (stopright == 0)) { // x + 1 = Derecha ed Expande explosion bomba (onda expansiva) Derecha; 
					if(ini.tab.matriz.tablero2[y][(x + 1 + right)].getcapa() != 2) { // x + 1 = Derecha ; 2=Pared no explotable ; 
						right++;
						ini.tab.matriz.tablero2[y][(x + right)].addcapa(14, (x + right), y, ini); // 14=Expansion Horizontal Derecha bomba 
					} else {
						stopright = 1;
					}
				}
				j++;
			}
			ini.tab.repaint();
			sleep(1000); // Espera 1 segundo
			j = 0;
			// Limpia la explosion Arriba Abajo Izquierda Derecha
			ini.tab.matriz.tablero2[y][x].quitcapa();
			while(j < potencia) { // potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba 
				if(up > 0){
					ini.tab.matriz.tablero2[y - up][x].quitcapa(); // y - up = Limpia Expansion explosion bomba (onda expansiva) Arriba
					up--;
				}
				if(down > 0){
					ini.tab.matriz.tablero2[y + down][x].quitcapa(); // y + down = Limpia Expansion explosion bomba (onda expansiva) Abajo
					down--;
				}
				if(left > 0){
					ini.tab.matriz.tablero2[y][x - left].quitcapa(); // x - left = Limpia Expansion explosion bomba (onda expansiva) Izquierda
					left--;
				}
				if(right > 0){
					ini.tab.matriz.tablero2[y][x + right].quitcapa(); // x + right = Limpia Expansion explosion bomba (onda expansiva) Derecha
					right--;
				}
				j++;
			}
			ini.tab.repaint();
			nbombas--;
			// ini.nbombas-=1;
			System.out.println("****************** numero de bombas activas: " + nbombas);
		} catch(InterruptedException e) {
			System.out.println("S'ha produit una excepció d'un thread:");
		}
	}
}
