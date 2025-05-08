// Buscar casilla-038-finito en ..\Debug\KK.txt para saber como detecta que el prota bueno choca con un malo y muere game over
// KK addcapa
// KK nobjetos

package salvado;

import java.awt.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class casilla {
   int nobjetos; // Numero de objetos
   int [] capa;

	casilla() { // Constructor de clase sin parametros ; OJO Inicializa nobjetos y capa [] en cada new ed en cada linea de pantalla1.txt
		nobjetos = 0;
		capa = new int [10]; // characters 4=Bueno, 5=Malos
		capa[0] = 3;
	}

	public void rellenacas(int cap1) { // cap1=1=Pared explotable por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt
		nobjetos = 0;
		int capa2;
		if((cap1 == 4) || (cap1 == 5) || (cap1 == 6)) { // 4=Protagonista (1 solo), 5=Malos tipo 1 (4), 6=Malos tipo 2 (0) leido de pantalla1.txt
			capa[1] = cap1;
			cap1 = 3; // 3=nada o 3=Pasillo transitable
			nobjetos++;
		}
		if(cap1 == 1) { // 1=Pared explotable por prota/bueno si pulsa espacio y sale de linea y columna para que no le mate la onda expansiva
			capa[1] = cap1;
			cap1 = getopcion(); // Devuelve 10, 9, 11, 8, 7, 3 de forma aleatoria 
			nobjetos++;
		}
		capa[0] = cap1;
	}

	public void addOnline(int a) {
      nobjetos = 0;
      capa[nobjetos] = a;
	}

	public void addcapa (int a, int x, int y, ppal j) { // a=0=Bomba prota, 1=Pared explotable blanca por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos tipo 1 (4), 6=Malos tipo 2 (0) leido de pantalla1.txt, 13=Expansion Vertical Arriba bomba y 14=Expansion Horizontal bomba, 15 entra pero no lo guarda 
      int jj = 0, aux;
      int cont = 0;
      switch(a) { // 
         	case 0: // 0=Bomba prota
				aux = capa[nobjetos];
				capa[nobjetos] = 0;
				nobjetos++;
				capa[nobjetos] = aux;
				break;
			case 4: // Entra cuando mueves el prota/bueno
				nobjetos++;
				capa[nobjetos] = a;
				while (jj < nobjetos) {
					if((capa[jj] == 5) || (capa[jj] == 6)) { // 5=Malo tipo 1 ; 6=Malo tipo 2
						j.game.end = 1; // end = 1 = Fin de partida porque te ha matado un malo
						// j.game.pausa = 1;
						j.game.vidas -= 1; // Resta 1 vida
						j.game.fingame = 1; // fingame = 1 = Fin de partida porque te ha matado un malo
						if(j.game.vidas == 0) { // game.vidas=0=Game over o Fin de Juego
							j.tab.finito(); // Game over o Fin de Juego ; tablero ; 
						} else {
							j.tab.muerto(); // tablero ; 
						}
						System.out.println("el bueno se ha suicidado");
					}
					jj++; // ppal j
				}
				if(capa[nobjetos - 1] > 6) { // rellenacas pone al principio capa[1]=1,4,5
				   switch(capa[nobjetos - 1]) { // 7=Aumenta 1 bomba y Aumenta puntos, 8=Aumenta potencia y Aumenta puntos, 9=Reduce velocidad y aumenta puntos, 10=Aumenta 1 vida y Aumenta puntos, 11=Aumenta velocidad y aumenta puntos, 12=Aumenta nivel, pasa a siguiente nivel y finaliza partida.
						case 7: // 7=Aumenta 1 bomba prota y Aumenta puntos ; obtenido en casilla.getopcion() de forma aleatoria 
						System.out.println("bombas maximas actuales" + j.game.nbombas);
							j.game.nbombas += 1; // partida
							System.out.println("bombas maximas actuales" + j.game.nbombas);
							j.game.puntos += 150;
							break;
						case 8: // 8=Aumenta potencia y Aumenta puntos ; obtenido en casilla.getopcion() de forma aleatoria 
							j.game.potencia += 1; // potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba ; partida
							j.game.puntos += 150;
							break;
						case 9: // 9=Reduce velocidad y aumenta puntos ; obtenido en casilla.getopcion() de forma aleatoria 
							j.game.incspeed();
							j.game.puntos += 150;
							break;
						case 10: // 10=Aumenta 1 vida y Aumenta puntos ; obtenido en casilla.getopcion() de forma aleatoria 
							j.game.vidas += 1;
							j.game.puntos+=150;
							break;
						case 11: // 11=Aumenta velocidad y aumenta puntos ; obtenido en casilla.getopcion() de forma aleatoria 
							j.game.decspeed();
							j.game.puntos+=150;
							break;
						case 12: // No entra ; 12=Aumenta nivel, pasa a siguiente nivel y finaliza partida ; situacion.cargaPantalla pone 12 si encuentra un 8 en pantalla?.txt pero no hay ninguno ; 
							j.game.nivel++; // partida
							j.tab.nextlevel = 1; // nextlevel=0=Sigue en mismo nivel ; nextlevel=1=Aumenta nivel ; tablero
							j.game.end = 1; // end = 1 = Fin de partida porque he matado a todos los malos creo
							j.game.fingame = 1; // fingame = 1 = Fin de partida porque he matado a todos los malos creo
							break;
							// salida
						default:
						   System.out.println("actual: " + capa[nobjetos] + " objeto: " + nobjetos);
						   // muere
					}
					nobjetos--;
					capa[nobjetos] = a;
				}
				break;
			case 5: // OJO Ejecuta tanto si es 5 o 6 porque el primero no tiene break ; 5=Malo tipo 1
			case 6: // OJO Ejecuta tanto si es 5 o 6 porque el primero no tiene break ; 6=Malo tipo 2
			nobjetos++;
			capa[nobjetos] = a;
				while (jj < nobjetos) {
					if (capa[jj] == 4) { // Muere y game over ; 4=Prota
						j.game.vidas -= 1;
						j.game.end = 1; // end = 1 = Fin de partida porque te ha matado un malo
						j.game.fingame = 1; // fingame = 1 = Fin de partida porque te ha matado un malo
						if(j.game.vidas == 0) { // game.vidas=0=Game over o Fin de Juego
							System.out.println("casilla-038-finito");
							j.tab.finito(); // tablero ; Game over o Fin de Juego
						} else {
							j.tab.muerto(); // tablero ; 
						}
					}
					if ((capa[jj] == 13) || (capa[jj] == 14)) { // 13=Expansion Vertical bomba ; 14=Expansion Horizontal bomba 
						while(cont < j.game.nmalos) {
							if (j.malos[cont] != null) {
								j.malos[cont].mata(y, x); // malo1 mata( ; 
							}
						cont++;
						}
					}
					jj++;
				}
				break;
			case 13: // 13=Expansion Vertical bomba
			case 14: // 14=Expansion Horizontal bomba
				switch (capa[nobjetos]) {
					case 1: // 1=Pared explotable
						nobjetos--;
						break;
					case 4: // 4=Bueno 
						j.game.vidas-=1;
						j.game.end = 1; // Fin de partida porque te ha matado la expansion de la explosion de la bomba
						j.game.pausa = 1;
						j.game.fingame = 1; // Fin de partida porque te ha matado la expansion de la explosion de la bomba
						if(j.game.vidas == 0) { // game.vidas=0=Game over o Fin de Juego
							j.tab.finito(); // tablero ; Game over o Fin de Juego
						}
						else {
							j.tab.muerto(); // tablero
						}
						break;
					case 6: // Malo tipo 2
					case 5: // Malo tipo 1
						while(cont < j.game.nmalos) {
							if (j.malos[cont] != null) {
								j.malos[cont].mata(y, x); // Expansion Vertical Horizontal de bomba prota mata a un malo ; 
							}
							cont++;
						}
						break;
				}
				nobjetos++;
				capa[nobjetos] = a;
				break;
		}
	}

	public int getopcion() { // Llamado por rellenacas( solo cuando lee 1=Pared explotable por prota/bueno ; Devuelve 10, 9, 11, 8, 7, 3 de forma aleatoria
		int mov;
		mov = (int) Math.floor(Math.random() * 120); // Numero aleatorio entre 0 y 120
		if(mov < 6) { // vida 15 ; 10=Aumenta 1 vida y Aumenta puntos ; Probabilidad muy baja 6/120
			return(10); // 10=Aumenta 1 vida y Aumenta puntos ; Probabilidad muy baja 6/120
		}
		if(mov < 21) { // speedup 15 ; Probabilidad baja 15/120=(21-6)/120
			return(9); // 9=Reduce velocidad haciendo el juego mas facil porque tienes mas tiempo para reaccionar ante los malos y aumenta puntos
		}
		if(mov < 36) { // slow 15 ; Probabilidad baja 15/120=(36-21)/120
			return(11); // 11=Aumenta velocidad haciendo el juego mas dificil porque tienes menos tiempo para reaccionar ante los malos y aumenta puntos
		}
		if(mov < 46) { // powerup ; Probabilidad baja 10/120=(46-36)/120
			return(8); // 8=Aumenta potencia y Aumenta puntos ; potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba ; 
		}
		if(mov < 56) { // bombup ; Probabilidad baja 10/120=(56-46)/120
			return(7); // 7=Aumenta 1 bomba y Aumenta puntos
		}
		return (3); // 3=nada
	}

	public int getcapa() { // Devuelve 1=Pared explotable por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos (4), 6=Malos (0) leido de pantalla1.txt o 13=Expansion Vertical Arriba bomba y 14=Expansion Horizontal bomba ; Permite saber si bueno/malos pueden pasar porque no hay pared ;
		return capa[nobjetos];
	}

	public void quitcapa () {
		if (nobjetos != 0) {
			nobjetos--;
		}
		else {
			capa[nobjetos] = 3;
		}
	}
}
