/*
run() Llamado por el metodo start() de online.java solo
Thread

*/

package salvado;

import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

public class socket_ extends Thread { // Herencia extends
   Socket sck = null; // java.net.Socket
   String s;
   int port;
   ppal p;
   DataOutputStream out;
   DataInputStream in;
   online onl;

   socket_(int port2, String s2, ppal ini, online joc) {
      s = s2;
      port = port2;
      p = ini;
      onl = joc;
   }

   public void run() { // Llamado por el metodo start() de online.java
      // Creo el socket
      try {
       	InetAddress d = InetAddress.getByName(s);
       	sck = new Socket(d, port); // java.net.Socket
       	// creo els streams d'entrada i sortida
       	in = new DataInputStream(sck.getInputStream());
       	out = new DataOutputStream(sck.getOutputStream()); // DataOutputStream out

       	int probando;
       	while(p.game.end == 0) {
       	 // llegeixo
	         for(int i=0; i<10; i++) {
	            for (int a = 0; a<10; a++) {
	               probando = in.readInt();
	               System.out.println("he recibido:" + probando);
	               p.tab.matriz.tablero2[i][a].addOnline(probando);
	            }
		     	}
       	   // leo codigo
       	   int code = in.readInt();
       	   //actuo  codigo
       	   actuacode(code);
       	   // envio codigo
       	   int envio = getcode();
       	   out.writeInt(envio);
       	   // envio posicion
       	   out.writeInt(1); // envio p.prota.x
       	   out.writeInt(2); // envio p.prota.x
       	   p.tab.repaint();
       	   try {
       	  	   sleep(100); // Espera 0,1 segundos
       	   } catch(Exception e) {
       	  	   System.err.println("S'ha produit l'excepcio" + e);
       	   }
       	}
       	in.close(); // tanco stream
         out.close(); // tanco stream
  	   }catch(Exception e0) {
	 	   System.err.println("S'ha produit l'excepcio : " + e0);
      }
  	   try {
	 	   if(sck != null) sck.close(); // Socket sck
  	   }catch(IOException ioe) {
	 	   System.err.println("Error al tancar el socket : " + ioe);
  	   }
   }

   int getcode() {
	   if (p.game.end == 1) return 1;
	   if (p.game.pausa == 1) return 3;
		return 0;
	}

   void actuacode(int code) {
		try {
         switch (code) {
				case 0:
					if (p.game.pausa == 1) {
						p.game.pausa = 0;
					}
					//nada
					break;
				case 1:
					// p1 muerto decir q he ganado
					p.game.pausa = 1;
					out.close();
		      in.close();
		      sck.close(); // Socket sck
					break;
				case 3:
					//pausa
					p.game.pausa = 1; // ppal p ; partida game ; int pausa
					break;
			}
		} catch(Exception e0) {
	 	   System.err.println("S'ha produit l'excepcio : " + e0);
  	   }
	}
}
