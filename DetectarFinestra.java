package salvado;

import java.lang.*;
import java.awt.event.*;
import java.awt.*;

public class DetectarFinestra extends WindowAdapter { // Herencia de clase
  DetectarFinestra () { } // Constructor vacio

  public void windowClosing (WindowEvent we) { // cuando apretas la 'x'
    //System.out.println ("windowClosing-->Gracies per participar!Fins una altre!");
    System.exit(0); // cierra la ventana
  }

  public void windowOpened (WindowEvent we) { // ya abierta la ventana
    //System.out.println ("windowOpened-->Benvingut al Tetris!");
  }

  public void windowActivated (WindowEvent we) { // cuando 'estas' en la ventana
    //System.out.println ("windowActivated-->Estas jugant al Tetris!");
  }

   public void windowDeactivated (WindowEvent we){ // cuando 'no tas' en la ventana
    // System.out.println ("windowsDeactivated-->Presta mes atencio al joc!");
  }
}
