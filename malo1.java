// run() Llamado por start() en ppal.java

package salvado;

import java.io.*;
import java.util.*;
import java.lang.*;

public class malo1 extends Thread { // Herencia de clase
	ppal ini; //clase dentro de clase
	int posicionx; // posicion horizontal ed columna
	int posiciony; // posicion vertical ed fila
	int muerte; // muerte=0=Muerto ; muerte>0=Vivo ; 5=Malo tipo 1 tiene 1 vida ; 6=Malo tipo 1 tiene 2 vidas
	int ultimat; // ultimo movimiento
	int posppal; // p.game.nmalos ; numero de malos
	int capa;
	static int nmalos;

	malo1(ppal a, int x, int y, int posp, int tipo) { // Constructor de clase recibe 5 parametros ; x = numero de columna en tablero ; y=numero de linea en tablero ; tipo = tipo de malo
		ini = a; // 
		posicionx = y; // posicion vertical ed fila
		posiciony = x; // posicion horizontal ed columna
		muerte = 1; // muerte=0=Muerto ; muerte>0=Vivo ; tipo de malo 2 tiene 1 vida mas que tipo de malo 1
		ultimat = 5; // ultimo movimiento
		posppal = posp; // p.game.nmalos ; numero de malos
		capa = 5; // capa=5=Malo tipo 1 tiene 1 vida
		nmalos++; // Contador de malos
		if (tipo == 2) { // tipo de malo 2 tiene 1 vida mas que tipo de malo 1
			muerte = 2; // muerte=0=Muerto ; muerte>0=Vivo
			capa = 6; // capa=6=Malo tipo 1 tiene 2 vidas
		}
		System.out.println("Creados: " + nmalos);
	}

	public void run() { // Llamado por start() en ppal.java
		int mov = 0;
		int correcto = 0;
		System.out.println("activo el thread! y final de juego vale: " + ini.game.fingame); // fingame = 1 = Fin de partida
		try {
			sleep(1000); // Espera 1 seg
			// System.out.println("posicion: " + posiciony + " - " + posicionx + " ultima tirada:" + ultimat);
			while((muerte > 0) && (ini.game.fingame == 0)) { // mientras no lo maten ; muerte=0=Muerto ; muerte>0=Vivo ; fingame = 1 = Fin de partida
				System.out.println("la pausa vale " + ini.game.pausa + "  y final de juego vale: " + ini.game.fingame); // fingame = 1 = Fin de partida
				while ((ini.game.pausa == 0) && (muerte > 0) && (ini.game.fingame == 0)) { // si la pausa no esta activada y no he muerto ;  muerte=0=Muerto ; fingame = 1 = Fin de partida
					correcto = 1; // tirada incorrecta
					while (correcto == 1) { // dara una opcion de movimiento
						mov = (int) Math.floor(Math.random() * 5); // movimiento aleatorio ; si sale 0 o 5 lo dare por invalido ; Numero aleatorio entre 0 y 5 ; Math.floor redondea a la baja
						if (valida(mov)) { // funcion q me marca si la tirada es correcta
							correcto = 0;
						}
					}
					ultimat = mov;
					sleep((int)(500 * ini.game.speed)); // espera de juego medio segundo
				}
				sleep(1000); // pausa 1 seg
			}
			// Expansion vertical horizontal de bomba prota mata a un malo
			if (muerte < 1) { // muerte=0=Muerto ; muerte>0=Vivo ; Malo tipo 1 da menos puntos que malo tipo 2
				if (capa == 5) ini.game.puntos+=1000; // capa=5=Malo tipo 1 da menos puntos que malo tipo 2
				if (capa == 6) ini.game.puntos+=1500; // capa=6=Malo tipo 2 da mas puntos que malo tipo 1
			}
			nmalos--; // Disminuyo numero de malos porque este ha muerto por la expansion vertical horizontal de bomba
			System.out.println("Me han matado, quedan: " + nmalos + "  malos");
			System.out.println("muerte:" + muerte + "- fingame:" + ini.game.fingame); // muerte=0=Muerto ; muerte>0=Vivo ; fingame = 1 = Fin de partida
			ini.malos[posppal] = null; // 
			// ini.repaint();
		} catch(InterruptedException e) {
			System.out.println("S'ha produit una excepció d'un thread:");
		}
		System.out.println("valor de la variable :" + ini.game.fingame); // fingame = 1 = Fin de partida
		if ((nmalos == 0) && (ini.game.fingame == 0)) { // Si he matado todos los malos y no es fin de partida aumento el nivel ; fingame = 1 = Fin de partida
			ini.game.nivel++; // partida
			ini.tab.nextlevel = 1;
			ini.game.end = 1; // end = 1 = Fin de partida porque he matado a todos los malos
			ini.game.pausa = 1;
			ini.game.fingame = 1; // fingame = 1 = Fin de partida porque he matado a todos los malos
			ini.tab.repaint();
			ini.punts.repaint();
		}
	}
	// movimientos      1
	// movimientos   2     3
	// movimientos      4

	private boolean valida (int mov) { // Movimiento 
		// boolean ok;
		if((mov == 5) || (mov == 0)) { // Solo me interesan 1=Movimiento Arriba, 2=Movimiento Izquierda, 3=Movimiento Derecha y 4=Movimiento Abajo
			return(false);
		}
		switch(mov) { 
			case 1: // 1=Movimiento Arriba
				if((posiciony - 1) < 0) { // me salgo de margenes ; si estoy en la linea 0 devuelvo error ; y - 1 = va a Arriba
					return false;
				}
				if (ini.tab.matriz.tablero2[posiciony - 1][posicionx].getcapa() < 3) { // y - 1 = va a Arriba ; < 3 significa que no se puede pasar porque hay pared ; - 1 porque va a Arriba ; 1=Pared explotable blanca por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
					return false;
				}
				if(ultimat != 4) { // no contradigo mi ultima tirada ; Ultima tirada no fue 4=Movimiento Abajo
					ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa();
					posiciony-=1;
					ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini); // capa=5=Malo tipo 1 ; capa=6=Malo tipo 2
					ini.tab.repaint();
					ini.punts.repaint();
					return true;
				}
				// si contradigo mi ultima tirada me aseguro q no tenga mas opciones
				if ((posiciony + 1) < 10) { // no me salgo de limites ; y + 1 = Va Abajo
					if(ini.tab.matriz.tablero2[posiciony + 1][posicionx].getcapa() > 2) { // si puedo tirar abajo no la doy por valida ; > 2 significa que no hay pared
						return false;
					}
				}
				if ((posicionx + 1) < 10) { // no me salgo de limites ; x + 1 = Va a la derecha
					if(ini.tab.matriz.tablero2[posiciony][posicionx + 1].getcapa() > 2) { // si puedo tirar a la derecha no la doy por valida ; > 2 significa que no hay pared
						return false;
					}
				}
				if ((posicionx - 1) > 0) { // no me salgo de limites ; x - 1 = Va a la izquierda 
					if(ini.tab.matriz.tablero2[posiciony][posicionx - 1].getcapa() > 2) { // si puedo tirar a la izq no la doy por valida ; > 2 significa que no hay pared
						return false;
					}
				}
				ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa(); // Contradigo mi ultima tirada pero no puede ir a ningun otro sitio 
				posiciony-=1;
				ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini);
				ini.tab.repaint();
				ini.punts.repaint();
				return true;

			case 2: // 2=Movimiento Izquierda
				if((posicionx - 1) < 0) { // me salgo de margenes ; x - 1 porque va a la Izquierda
					return false;
				}
				if (ini.tab.matriz.tablero2[posiciony][posicionx - 1].getcapa() < 3) { // no se puede pasar porque hay pared ; x - 1 porque va a la Izquierda ; 1=Pared explotable blanca por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
					return false;
				}
				if(ultimat != 3) { // no contradigo mi ultima tirada ed no fue 3=Movimiento Derecha
					ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa(); // 
					posicionx-=1; // Decremento columna porque voy a la Izquierda
					ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini); // 
					ini.tab.repaint();
					ini.punts.repaint();
					return true;
				}
				// Contradigo mi ultima tirada pero me aseguro de que no puede ir a ningun otro sitio
				if ((posiciony) < 10) { // no me salgo de limites ; y + 1 = Va a abajo
					if(ini.tab.matriz.tablero2[posiciony + 1][posicionx].getcapa() > 2) { // si puedo tirar abajo no la doy por valida ; > 2 significa que no hay pared
						return false;
					}
				}
				if ((posicionx + 1) < 10) { // no me salgo de limites ; x + 1 = Va a la Derecha
					if(ini.tab.matriz.tablero2[posiciony][posicionx + 1].getcapa() > 2) { // si puedo tirar a la derecha no la doy por valida ; > 2 significa que no hay pared
						return false;
					}
				}
				if (posiciony != 0) { // no me salgo de limites
					if(ini.tab.matriz.tablero2[posiciony - 1][posicionx].getcapa() > 2) { // si puedo tirar a la arriba no la doy por valida ; > 2 significa que no hay pared
						return false;
					}
				}
				ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa(); // Contradigo mi ultima tirada pero no puede ir a ningun otro sitio 
				posicionx-=1;
				ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini); // 
				ini.tab.repaint();
				ini.punts.repaint();
				return true;

			case 3: // 3=Movimiento Derecha
				if((posicionx + 1) == 10) { // me salgo de margenes ; si la columna x + 1 es 10 devuelvo error
					return false;
				}
				if (ini.tab.matriz.tablero2[posiciony][posicionx + 1].getcapa() < 3) { // no se puede pasar porque hay pared ; x + 1 porque va a la derecha ; 1=Pared explotable blanca por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
					return false;
				}
				if(ultimat != 2) { // no contradigo mi ultima tirada ed no fue 2=Movimiento Izquierda
					ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa(); // 
					posicionx+=1; // Incremento columna porque voy a la derecha
					ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini); // 
					ini.tab.repaint();
					ini.punts.repaint();
					// System.out.println("aceptado xq no contradigo");
					return true;
				}
				// Contradigo mi ultima tirada pero me aseguro de que no puede ir a ningun otro sitio
				if ((posiciony + 1) < 10) { // no me salgo de limites ; y + 1 = Va a abajo
					if(ini.tab.matriz.tablero2[posiciony + 1][posicionx].getcapa() > 2) { // si puedo tirar abajo no la doy por valida ; > 2 significa que no hay pared
						return false;
					}
				}
				if (posiciony != 0) { // no me salgo de limites
					if(ini.tab.matriz.tablero2[posiciony - 1][posicionx].getcapa() > 2) { // si puedo tirar arriba no la doy por valida ; > 2 significa que no hay pared ; y - 1 = Va a arriba
						return false;
					}
				}
				if (posicionx != 0) { // no me salgo de limites
					if(ini.tab.matriz.tablero2[posiciony][posicionx - 1].getcapa() > 2) { // si puedo tirar a la Izquierda no la doy por valida ; > 2 significa que no hay pared ; x - 1 = Va a la izquierda
						return false;
					}
				}
				ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa(); // Contradigo mi ultima tirada pero no puede ir a ningun otro sitio 
				posicionx+=1;
				ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini); // 
				ini.tab.repaint();
				ini.punts.repaint();
				return true;
			case 4: // 4=Movimiento Abajo
				if((posiciony + 1) == 10) { // me salgo de margenes ; y + 1 = Abajo
					return false;
				}
				if (ini.tab.matriz.tablero2[posiciony + 1][posicionx].getcapa() < 3) { // no se puede pasar porque hay pared ; y + 1 porque va a Abajo ; 1=Pared explotable blanca por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
					return false;
				}
				if(ultimat != 1) { // no contradigo mi ultima tirada ; Ultima tirada no fue 1=Movimiento Arriba
					ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa(); // 
					posiciony+=1;
					ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini); // 
					ini.tab.repaint();
					ini.punts.repaint();
					return true;
				}
				// Contradigo mi ultima tirada pero me aseguro de que no puede ir a ningun otro sitio
				if (posiciony != 0) { // no me salgo de limites
					if(ini.tab.matriz.tablero2[posiciony - 1][posicionx].getcapa() > 2) { // si puedo tirar arriba no la doy por valida ; > 2 significa que no hay pared ; y - 1 = Va a arriba
						return false;
					}
				}
				if ((posicionx + 1) < 10) { // no me salgo de limites
					if(ini.tab.matriz.tablero2[posiciony][posicionx + 1].getcapa() > 2) { // si puedo tirar a la derecha no la doy por valida ; > 2 significa que no hay pared ; x + 1 = Va a la derecha
						return false;
					}
				}
				if (posicionx != 0) { // no me salgo de limites
					if(ini.tab.matriz.tablero2[posiciony][posicionx - 1].getcapa() > 2) { // si puedo tirar a la izquierda no la doy por valida ; > 2 significa que no hay pared ; x - 1 = Va a la izquierda
						return false;
					}
				}
				ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa(); // Contradigo mi ultima tirada pero no puede ir a ningun otro sitio 
				posiciony+=1;
				ini.tab.matriz.tablero2[posiciony][posicionx].addcapa(capa, 0, 0, ini); // 
				ini.tab.repaint();
				ini.punts.repaint();
				return true;

		}
	return false;
	}

	public void mata (int y, int x) { // Expansion Vertical Horizontal de bomba prota mata a un malo ; Llamado por casilla.addcapa ; x posicion horizontal ed columna ; y posicion vertical ed fila 
		if((posicionx == x) && (posiciony == y)) {
			muerte--; // muerte=0=Muerto ; muerte>0=Vivo
			System.out.println("a este thread le quedan " + muerte + " vidas"); // muerte=0=Muerto ; muerte>0=Vivo
			ini.tab.matriz.tablero2[posiciony][posicionx].quitcapa();
		}
	}
}
