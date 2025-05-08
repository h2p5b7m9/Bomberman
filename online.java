/* start() llama a run () en socket_.java
start() llama a run () en serversocket_.java
start() llama a run () en tiempo.java
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

public class online extends JFrame implements ActionListener { // Herencia extends ; implements interface
   Button b, b2, b3, b4, b5, b6;
   Container c = null;
   ImageIcon im = new ImageIcon("arroba.jpg", "fondo");
   Panel p2, p0, p6;
   TextField t, t2, t3;
   ppal o;

   public online(ppal ppu) {
      super("Mode Online");  // titol frame ; super padre
		System.out.println("online");
      // creo el container
      o = ppu; // ppal o
      c = getContentPane(); // Container c
      c.setLayout(new BorderLayout()); // Container c
      c.setBackground(Color.black);
      // creo panels i tot el que inclouen
      Panel p = new Panel();  // creo panel
      p.setSize(300, 300);  // tamany panel
      p.setBackground(Color.black);  // color fons panel
      // creo i fico una imatge al panel
      JLabel et = new JLabel(im);  // creo label que es un ImageIcon
      p.add(et);  /** fico label que es imatge */
      et.reshape(0, 0, 300, 300);  // dono posicio (x,y) i tamany a la imatge (ample, altura)
      c.add("North", p);  // introdueixo al nord
      b = new Button("Crear partida");  // creo buto
      b.addActionListener(this);   // event buto ; Button b
      b2 = new Button ("Conectar a partida"); // Button b2
      b2.addActionListener(this);  // event buto
      p0 = new Panel();
      p0.setLayout(new BorderLayout());
      Panel p1 = new Panel();
      p1.add(b);
      p1.add(b2);
      p0.add("North", p1);
      p2 = new Panel();
      p2.setLayout(new BorderLayout());
      Panel p3 = new Panel();
      JLabel l = new JLabel ("IP........");
      JLabel l2 = new JLabel("PORT..");
      t = new TextField("", 20);
      t2 = new TextField("", 20);
      p3.add(l);
      p3.add(t);
      p2.add("North", p3);
      Panel p4 = new Panel();
      p4.add(l2);
      p4.add(t2);
      p2.add("Center", p4);
      Panel p5 = new Panel();
      p5.setLayout(new FlowLayout(FlowLayout.CENTER)); // situo tot lo del panel al centre
      b3 = new Button("Aceptar");
      b3.addActionListener(this);
      b4 = new Button("Atras");
      b4.addActionListener(this);
      p5.add(b3);
      p5.add(b4);
      p2.add("South", p5); // el panel p2 no l'introdueixo
      p6 = new Panel();
      p6.setLayout(new BorderLayout());
      Panel p7 = new Panel();
      t3 = new TextField("", 15);
      JLabel l3 = new JLabel("PORT... ");
      p7.add(l3);
      p7.add(t3);
      p6.add("North", p7);
      Panel p8 = new Panel();
      b5 = new Button("Aceptar");
      b5.addActionListener(this);
      p8.add(b5);
      b6 = new Button("Atras");
      b6.addActionListener(this);
      p8.add(b6);
      p6.add("South", p8);  // el panel p6 no l'introdueixo
      c.add("South", p0);
      // creo event, tamany del frame i el visualitzo
      addWindowListener(new DetectarFinestra()); // DetectarFinestra extends WindowAdapter
      setSize(308, 418);
      doLayout();
      show();
   }

   public void actionPerformed(ActionEvent e) { // events del butons
      if(e.getSource() == b) {
         System.out.println("Has seleccionat crear una partida nova!");
         // modifico el tamany del frame i afegeixo el panel p6
         setSize(308, 455);
         p0.add("South", p6);
         show();
      }
      else {
         if(e.getSource() == b2) {
            System.out.println("Has seleccionat conectarte a una partida ja creada!");
            // modifico el tamany del frame i afegeixo el panel p6
            setSize(308, 485);
            p0.add("South", p2);
            show();
         }
         else { // han seleccionat conectarse a partida ja creada
            if(e.getSource() == b3) {
       	      // Recullo les dades introduides per l'usuari
             	System.out.println("Llegint dades...");
       	      String s = t.getText();
       	      System.out.println("ip... " + s);
       	      String s2 = t2.getText();
       	      int port = Integer.parseInt(s2); // parseInt Convierte string en int
       	      System.out.println("port... " + port);
       	      socket_ sck = new socket_(port, s, o, this); // Crido al socket ; socket_ extends Thread
       	      sck.start(); // start() llama a run () en socket_.java
       	      empiezap2();
            }
       	   else {
               if(e.getSource() == b4 || e.getSource() == b6) {
                  online prueba2 = new online(o);
                  setVisible(false);
               }
               else {  // han seleccionat crear una partida
				      String s = t3.getText();
				      int port = Integer.parseInt(s); // parseInt Convierte string en int
				      System.out.println("port... " + port);
                  serversocket_ ss = new serversocket_(port, o, this); // Crido al ServerSocket_ ; serversocket_ extends Thread
                  ss.start(); // start() llama a run () en serversocket_.java
               }
            }
         }
      }
   }
   void empiezap2() {
      o.setVisible(true); // ppal o
      o.game.nombre = "Jugador2";
      o.game.puntos = 0;
      o.t = new tiempo (o); // tiempo extends Thread
      o.t.start(); // ppal o ; tiempo t ; start() llama a run () en tiempo.java
      setVisible(false);
      o.tab.requestFocus();
   }

   void inicializa () {
      o.setVisible(true); // ppal o
      //prin.arranca();
      o.game.nombre = "Jugador1";
      o.game.puntos = 0;
      o.t = new tiempo (o); // tiempo extends Thread
      o.t.start(); // ppal o ; tiempo t ; start() llama a run () en tiempo.java
      o.inicializaNivel();
      setVisible(false);
      o.tab.requestFocus();
	}
}
