// run() llamado por start() en online.java solo

package salvado;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class serversocket_ extends Thread { // Herencia extends
   ServerSocket ss = null;
   Socket scks = null;
   int port;
   ppal p;
   DataOutputStream out;
   DataInputStream in;
   online onl;

   public serversocket_(int port2, ppal ini, online onlin) { // Constructor de 3 parametros
      port = port2;
      p = ini; // ppal p
      onl = onlin;
   }

   public void run() { // run() llamado por start() en online.java
	   // Creo el servidor i el socket
	   int i, a, xxd;
	   try{
	      ss = new ServerSocket(port);
	   } catch(IOException ioe) {
	      System.err.println("Error al obrir el Socket de servidor: " + ioe);
	   }
	   try{
	  	   // Esperem q algu es conecti al nostre Socket
	      scks = ss.accept(); //Socket scks
	      System.out.println("Conexion aceptada");
	      out = new DataOutputStream(scks.getOutputStream()); // DataOutputStream out ; Socket scks 
	      in = new DataInputStream(scks.getInputStream()); // Socket scks
	      onl.inicializa();
	      System.out.println("antes de mandar enteros");
 	      while(p.game.end == 0) { // mentres no s'acabi la partida ; ppal p ; partida game
 	    	   // envio la pantalla
 	    	   for (i=0; i<10; i++) {
 	    		   for (a=0; a<10; a++) {
						xxd = p.tab.matriz.tablero2[i][a].getcapa();
						System.out.println("enviando:" + xxd);
						out.writeInt(xxd);
	     		   }
	     	   }
            System.out.println("despues de mandar tablero");
            out.writeInt(getcode());
            System.out.println("Codigo enviado");
            int code = in.readInt();
            System.out.println("Codigo recibido");
            actuacode(code);
            System.out.println("actuo segun el codigo");
            int x = in.readInt();
            System.out.println("recibo la x");
            int y = in.readInt();
            System.out.println("recibo la y");
            // situap2(x, y);
            try{
               sleep(200); // Espera 0,2 segs
            }catch(Exception e) {
               System.out.println("S'ha produit l'excepcio" + e);
            }
         }
	      in.close(); // tanco stream
	      out.close(); // tanco stream
	   } catch(Exception e2) {
         System.err.println("S'ha produït l'excepcio : " + e2);
	   }
	   try {
	      if(scks != null) scks.close(); // Socket scks
  	   }catch(IOException ioe) {
	 	   System.err.println("Error al tancar el socket : " + ioe);
  	   }
   }

	int getcode() {
	   if (p.game.end == 1) return 1; // ppal p ; partida game ; end fin
	   if (p.game.pausa == 1) return 3;
		return 0;
   }

	void actuacode(int code) {
		try{
			switch (code) {
				case 0:
					if (p.game.pausa == 1) { // ppal p ; partida game
						p.game.pausa = 0;
					}
					//nada
					break;
				case 2:
					// p2 muerto decir q he ganado
					p.game.pausa = 1; // ppal p ; partida game
					out.close();
		      in.close();
		      scks.close(); // Socket scks
					break;
				case 3:
					//pausa
					p.game.pausa = 1; // ppal p ; partida game
					break;
			}
		} catch(Exception e0) {
	 		System.err.println("S'ha produit l'excepcio : " + e0);
  	   }
	}
}
/* posibilidades de code:
			0 -> la partida sigue
		 	solo lo puede enviar el server 1-> muerte player1
			solo lo puede enviar el cliente 2-> muerte player2
			3-> pausa puesta/quitada
*/


/*	     	if(finalp2 == 1){
	      	out.close();  // tanco stream
	      	in.close();  // tanco stream
	      	scks.close(); // Socket scks
	     	}
*/
