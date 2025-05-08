package salvado;

public class partida {
   String nombre;
   int nivel; // Nivel de juego ; Hay tantos niveles como Partida?.txt
   float speed;
   int nmalos; // p.game.nmalos ; numero de malos
   int pausa; // p=0=pausa no activada
   int end; // end = 1 = Fin de partida porque me ha matado un malo o porque he matado a todos los malos
   int nbombas; // Numero de bombas inicial
   int potencia; // potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba 
   int vidas; // Numero de vidas inicial 
   int puntos;
   int fingame; // fingame = 1 = Fin de partida porque me ha matado un malo o he matado a todos los malos

   public void inicializapartida() {
	 	nivel = 1; // Nivel de juego ; Hay tantos niveles como Partida?.txt
	 	fingame = 0; // fingame = 1 = Fin de partida porque me ha matado un malo o he matado a todos los malos
	 	speed = 1;
	 	nmalos = 0; // p.game.nmalos ; numero de malos
	 	pausa = 0; // p=0=pausa no activada
	 	end = 0; // end = 1 = Fin de partida porque me ha matado un malo o porque he matado a todos los malos, No finito game over 
	 	nbombas = 3; // Numero de bombas inicial
	 	potencia = 5; // potencia = Numero de casillas verticales y horizontales que explotan con la bomba ed onda expansiva de la bomba colocada por el prota con espacio
	 	vidas = 1; // Numero de vidas inicial 
 	}

	// falta por poner el nombre del jugador

	void incspeed() { // Reduce velocidad haciendo el juego mas facil porque tienes mas tiempo para reaccionar ; Llamado por casilla
		speed -= 0.1;
	}
	void decspeed() { // Aumenta velocidad haciendo el juego mas dificil porque tienes menos tiempo para reaccionar ; Llamado por casilla
		speed += 0.1;
	}
}
