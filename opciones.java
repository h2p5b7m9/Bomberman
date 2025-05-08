/*
main
Llamada desde presentacion actionPerformed
Se puede ejecutar pero no hace nada
start() llama a run() de tiempo.java
Ver BomberMan-02.png
*/

package salvado;

import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.awt.event.*;


public class opciones extends JFrame implements ActionListener { // Herencia extends ; implements interface
	Container c = null; // java.awt.Container
	Panel derecha, izquierda, p1, p2, p3, p11; // java.awt.Panel
	Label selNombre, selJuego, oJuego; // java.awt.Panel
	Font pr, se; // java.awt.Font
	Checkbox sn1, sn2, mj1, mj2, mj3; // java.awt.Checkbox
	FileDialog fichero; // java.awt.FileDialog
	TextField nNombre; // java.awt.TextField
	CheckboxGroup agrup1, agrup2; // java.awt.CheckboxGroup
	Button iniciar; // java.awt.Button
	ImageIcon ima = new ImageIcon("22.gif", "fondo"); // javax.swing.ImageIcon
	ppal prin;
	presentacion press;

	public static void main (String [] args){}

	opciones (ppal juego, presentacion pres) { // Constructor
		super("..:: Bomberman - Ignacio Macipe - IS09765 ::.."); // Llama al padre
		c = getContentPane(); // java.awt.Container
		c.setLayout(new BorderLayout()); // java.awt.Container
		press = pres; // presentacion press
		prin = juego; // ppal prin
		c.setBackground(Color.gray); // java.awt.Container
		derecha = new Panel(); // java.awt.Panel.Panel()
		derecha.setBackground(Color.gray);
		derecha.setSize(500, 500);
		JLabel et = new JLabel(ima); // javax.swing.JLabel
		et.reshape(0, 0, 100, 150);
		derecha.add(et);
		izquierda = new Panel(); // java.awt.Panel.Panel()
		izquierda.setLayout(new GridLayout(3, 1));
		p1 = new Panel(); // Panel p1
		p1.setLayout(new GridLayout(2, 1));
		p1.setBackground(Color.gray);
		pr = new Font("", Font.BOLD, 12);
		selNombre = new Label("", Label.LEFT);
		selNombre.setFont(pr);
		selNombre.setText("Nombre del jugador:");
		agrup1 = new CheckboxGroup();
		sn1 = new Checkbox("Cargar jugador existente ", agrup1, false);
		sn2 = new Checkbox("Jugador nuevo ", agrup1, true);
		fichero = new FileDialog(this, "Selecciona el fichero a cargar");
		nNombre = new TextField();
		p11 = new Panel ();
		p11.setLayout(new GridLayout (2, 2));
		p11.setBackground(Color.gray);
		//p11.add(fichero);
		p11.add(sn2);
		p11.add(nNombre);
		p11.add(sn1);
		p1.add(selNombre);
		p1.add(p11); // Panel p1 ; Panel p11

		/********************************/
		selJuego = new Label ("", Label.LEFT);
		selJuego.setFont(pr);
		selJuego.setText("Selecciona la modalidad de juego");
		p2 = new Panel ();
		p2.setLayout(new GridLayout(4, 1));
		p2.setBackground(Color.gray);
		agrup2 = new CheckboxGroup();
		mj1 = new Checkbox("Modalidad Online", agrup2, false);
		mj2 = new Checkbox("Modalidad por niveles", agrup2, true);
		mj3 = new Checkbox("Modalidad cargar mapa", agrup2, false);
		p2.add(selJuego);
		p2.add(mj2);
		p2.add(mj3);
		p2.add(mj1);
		/*********************/
		p3 = new Panel();
		oJuego = new Label("", Label.CENTER);
		oJuego.setFont(pr);
		oJuego.setText("Opciones de juego");
		iniciar = new Button ("iniciar partida");
		iniciar.addActionListener(this);
		p3.add(iniciar);
		/*********************/
		izquierda.add(p1); // Panel p1
		izquierda.add(p2);
		izquierda.add(p3);
		c.add(izquierda, BorderLayout.WEST);
		c.add(derecha, BorderLayout.EAST);
		pres.setVisible(false); // presentacion pres
		setSize(700, 450);
		addWindowListener(new DetectarFinestra()); // DetectarFinestra extends WindowAdapter
		show();
		}

	 public void actionPerformed (ActionEvent e) {
		if (e.getSource() == iniciar) {
			setVisible(false);
			prin.setVisible(true); // ppal prin
			// prin.arranca();
			if (nNombre.getText() != "") {
				prin.game.nombre = nNombre.getText(); // ppal prin
			}
			else {
				prin.game.nombre = "Jugador1"; // ppal prin
			}
			prin.game.puntos = 0; // ppal prin
			prin.t = new tiempo (prin); // ppal prin ; Instancia de clase ; tiempo t ; tiempo extends Thread
			prin.t.start(); // ppal prin ; start() llama a run() de tiempo.java
			prin.inicializaNivel(); // ppal prin
			press.setVisible(false); // presentacion press
		}
	}
}
