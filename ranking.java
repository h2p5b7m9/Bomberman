package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.*;
import java.util.*;

class ranking extends JFrame { // Herencia extends
	Container c = null;
	Button salir;
	//ppal ini;
	jugador [] rank;
	int nplayer = 0; // Numero de jugadores
	BufferedWriter bw;
	BufferedReader br;
	FileWriter fw;
	PrintWriter pw;

	ranking() { // Constructor de clase
		rank = new jugador[6]; // Instancia de clase
	}

	void Mranking() { // Muestra el ranking mediante panel
 	   //super(".:: Ranking de Puntuaciones ::."); // titol frame ; super padre
		// creo el container
		setResizable(false);
		c = getContentPane();
		c.setBackground(Color.gray);
		Panel pp = new Panel(new BorderLayout());
		Panel arriba = new Panel();
		Font f = new Font("", Font.BOLD, 18);
		Label ran = new Label("",Label.CENTER);
		ran.setFont(f);
		ran.setText("Ranking");
		arriba.add(ran);
		Panel abajo = new Panel(new BorderLayout());
		Panel abajoDere = new Panel();
		salir = new Button("Salir");
		salir.addActionListener(new ActionListener() { // Funcion anonima
         public void actionPerformed(ActionEvent e) {
		  	   setVisible(false);
 		  	   // reiniciar el ppal
			}
		});
		abajoDere.add(salir);
		abajo.add(abajoDere, BorderLayout.EAST);
		Panel centro;
		f = new Font("", Font.BOLD, 12);
		centro = new Panel(new GridLayout(0, 2)); 
		Font f1 = new Font("", Font.BOLD, 15);
		ran = new Label("", Label.CENTER);
		ran.setFont(f1);
		ran.setText("Nombre");
		centro.add(ran);
		ran = new Label("", Label.CENTER);
		ran.setFont(f1);
		ran.setText("Puntos");
		centro.add(ran);
		cargaVector();
		ordenaVector();
		for(int i = 0; i < nplayer; i++) {
			ran = new Label("", Label.CENTER);
			ran.setFont(f);
			ran.setText(rank[i].nombre);
			centro.add(ran);
			ran=new Label("", Label.CENTER);
			ran.setFont(f);
			ran.setText(String.valueOf(rank[i].puntos));
			centro.add(ran);
		}
		pp.add(centro, BorderLayout.CENTER);
		pp.add(arriba, BorderLayout.NORTH);
		pp.add(abajo, BorderLayout.SOUTH);
		c.add(pp);
	   addWindowListener(new WindowAdapter() { // Funcion anonima
       public void windowClosing (WindowEvent we) {
         setVisible(false);
  		 }
      });
	   setSize(300, 300);
	   show();
 	}

   void cargaVector() { // Carga el archivo ranking.info que contiene jugador CR puntos
   	nplayer = 0;
   	String aux;
   	System.out.println("cargando los records");
   	try {
   		br = new BufferedReader(new FileReader("ranking.info")); // import java.io.FileReader
   		while ((aux = br.readLine()) != null) {
   			rank[nplayer] = new jugador();
   			System.out.println(" he pedido memoria para el jugador:" + nplayer);
   			rank[nplayer].puntos = Integer.parseInt(br.readLine()); // parseInt Convierte string en int
   			rank[nplayer].nombre = aux;
   			nplayer++;
   		}
   		System.out.println("cargado los records");
   	}catch (java.io.FileNotFoundException e) {
   		System.out.println ("Error al abrir el fichero...");
   	}catch (java.io.IOException e) {
   		System.out.println ("Error al leer el fichero...");
   	}
   }

   void ordenaVector() { // Ordena los players jugadores de mayor a menor puntuacion puntos
      int aux = 0, j = 0;
      jugador swap = new jugador();
      System.out.println("Ordenando los records");
   	for(int z = 0; z < nplayer; z++) {
   		System.out.println("Ordenado el player:" + z);
   		for(j = z; j < nplayer; j++) {
   			System.out.println(rank[z].puntos + " - " + rank[j].puntos);
   			if (rank[z].puntos < rank[j].puntos) {
/* 				swap.nombre = rank[z].nombre;
   				swap.puntos = rank[z].puntos;
   				rank[z].nombre = rank[j].nombre;
   				rank[z].puntos = rank[j].puntos;
   				rank[j].nombre = swap.nombre;
   				rank[j].puntos = swap.puntos;
*/
   				System.out.println("swap = rank[k]" + z);
   				swap = rank[z];
   				System.out.println("rank[k] = rank[j]" + j);
   				rank[z] = rank[j];
   				System.out.println("rank[j] = swap;");
   				System.out.println("swap.nombre:" + swap.nombre);
   				System.out.println("swap.puntos:" + swap.puntos);
   				rank[j] = swap;
   				System.out.println("****************");
   			}
   		}
   	}
   	System.out.println("ordenado los records");
   }

   void guardaPlayer(String nombre, int puntuacion) { // Escribe el fichero ranking.info quedandose con los 5 mejores
      cargaVector();
      rank[nplayer] = new jugador();
      System.out.println("memoria para el " + nplayer);
      rank[nplayer].nombre = nombre;
      rank[nplayer].puntos = puntuacion;
      nplayer++;
      System.out.println("nplayers=" + nplayer);
      for (int aa = 0; aa < nplayer; aa++) {
      	if (rank[aa] == null) {
      		System.out.println(" posicion es nula:" + aa);
      	}
      }
      ordenaVector();
      if (nplayer == 6) {
      	System.out.println("dice q los players son menores de 5");
      	nplayer--;
      }
      try {
      	fw = new FileWriter("ranking.info", false);
      	bw = new BufferedWriter(fw);
      	pw = new PrintWriter(bw);
      	System.out.println("0");
      	for( int h=0; h < nplayer; h++) {
            pw.println(rank[h].nombre);
            pw.println(rank[h].puntos);
            System.out.println("2");
      	}
      	pw.close();
      }catch (java.io.IOException e) {
         System.out.println("Error al escribir el fichero...");
      }
   }
}

class jugador {
	String nombre;
	int puntos;
	jugador() { // Constructor de clase sin parametros
		nombre = "";
		puntos = 0;
	}
}
