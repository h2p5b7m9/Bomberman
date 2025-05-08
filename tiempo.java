/* run() Llamado por start() en online.java
extends Thread
*/

package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class tiempo extends Thread {
   ppal p;
   int seg = 0; // segundos
   int min = 0; // minutos

   public tiempo(ppal ini) { // Constructor de clase de 1 parametro
      p = ini; // ppal p
   }

   public void run() { // Llamado por start() en online.java
   	min = 0; // minutos
   	seg = 0; // segundos
   	while(p.tab.over == 0) { // ppal p ; tablero tab ; over = 0 = No final de Juego ; over = 1 = Game Over o Fin de Juego 
   		try {
   			if(p.game.pausa == 0) { // ppal p ; pausa=0=No pausa
   	 		   seg++; // incrementa segundos
   	 		   if (seg == 60) { // Si llega a 60 segs, suma 1 minuto
   	 		      seg = 0;
   	 		      min++;
   	 		   }
   	  	      p.min = min; // ppal p ; segundos
   	  	      p.seg = seg; // ppal p; minutos
   	      }
   	      sleep(1000);  // Espera 1 segundo adormeixo el thread 1 segon (1000 milis)  
         } catch(InterruptedException e) {
   		   System.out.println("El Thread no ha pogut adormir-se!");
         }
      }
   }
}
