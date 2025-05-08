Bomberman
Clásico juego Bomberman. El protagonista se mueve en un tablero de 10 filas y 10 columnas, y puede poner bombas pulsando espacio, pero tiene que desplazarse rapidamente y salir de la fila y columna porque la expansion de la bomba mata todo lo que hay en esa fila columna incluidos los malos. Los malos te matan si los tocas pero no ponen bombas. Hay 2 tipos de paredes que forman un laberinto (tipo pacman), una explotable (blanca) con la bomba y otra no explotable. Con la Tecla P=Pausa se detiene el juego. 

Cada vez que hace un new a una clase Thread se arranca un hilo de ejecucion independiente paralelo

KK addcapa

KK
El nombre directorio salvado12-Ago-04_18 se deberia cambiar a Bomberman

Eclipse Debuga, el error previo era que las imagenes y ficheros txt tienen que estar en el proyecto, no en el paquete package, sino da error.

Jugar.bar Ejecutar

Tablero de 10 filas y 10 columnas por donde se mueve el prota y los malos, se pueden poner bombas, y hay paredes que forman un laberinto tipo pacman. No hay trampas ni bombas enemigas pero el prota puede poner una bomba manualm mediante el espacio pero tiene que desplazarse rapidam y salir de la fila o columna porque la expansion de la bomba mata todo lo que hay en esa fila columna incluidos los malos. Con la Tecla P=Pausa se detiene el juego. 

java.lang.Thread
java.net.Socket: Solo se usa en online.java

Ver D:\Mis Documentos\AS400\Stanhome\ifp\Programación 2\p01\Threads01\Thread01.java
Ver D:\Mis Documentos\AS400\Stanhome\ifp\Programación 2\p01\Threads02\Thread02.java
Ver D:\Mis Documentos\AS400\Stanhome\ifp\Programación 2\p01\Threads03\Thread03.java
Ver D:\Mis Documentos\AS400\Stanhome\ifp\Programación 2\p01\Threads04\Thread04.java
Ver D:\Mis Documentos\Java\MyServer.bat
Ver D:\Mis Documentos\Java\MyClient.bat

Pablo Mayoral de Accenture Zurich Seguros

Los ficheros pantalla1.txt, pantalla2.txt, etc. contienen la posicion inicial de:
1=Pared explotable blanca por prota/bueno si pulsa espacio, 2=Pared no explotable, 3=Pasillo transitable, 4=Protagonista (1 solo), 5=Malos tipo 1 (4), 6=Malos tipo 2 (0) leidos de pantalla1.txt, 13=Expansion Vertical Arriba bomba y 14=Expansion Horizontal bomba, 7, 8, 9, 10, 11 y 12 obtenidos en getopcion()
0=Bomba prota
- 1=Pared explotable blanca que el prota/bueno puede derribar pulsando espacio
- 4=Protagonista (1 solo)
- 5=Malos (4)
- 6=Malos (0)

Prota puede comer premios como corazon, 30 temporary (30.gif), bomba (bombup.gif), golosinas (speedup.gif), etc.

Menu:
- Partida:
   - Partida bueva
   - Cargar partida
   - Guardar partida en curso
   - Final de partida
   - Salir
- Ver / Ver Ranking
- Opciones:
   - Introducir jugador nuevo
   - Seleccionar jugador existente
   - Cambiar las teclas
   - Cambiar nivel de dificultad
   - Cambiar de nivel

OJO D:\Mis Documentos\Java\salvado12-Ago-04_18 Debug\KK.txt contiene los prints de la ejecucion

OJO
Para ejecutar en cmd:
// package salvado;
Para ejecutar en Eclipse:
package salvado;

KK
Comparar casilla.java con Debug y enriquecer
Kk.txt seguir enriqueciendo

KK
Por que solo deja 2 segundos desde que hago espacio=crea bomba hasta que hace game over y acaba partida?

Varios malos pueden ocupar la misma casilla y en ese momento parece que ha desaparecido alguno pero despues vuelven a apaecer

KK
Que es una capa?
Podria ser que cada FPS Frame per second cambia la situacion de los characters personajes en el tablero de la partida ed su posicion x, y es diferente porque se mueven todos, y si se tira una bomba desaparecen los malos y las paredes blancas explotables.



KK
Diferencia entre nDificultad, nNivel, level
game.nivel = 1 -> pantalla1.txt
game.nivel = 2 -> pantalla2.txt
Creo que a mayor nivel mayor dificultad

KK
Google: juego pacman en java

KK
Enriquecer javas con resumen de clases.txt aunque no aporta nada

KK
Google: juego Bomberman en java

https://www.youtube.com/watch?v=SFUzOL_9aJA
Bomberman in Java, Programming Tutorial

Syncronized
No se usa en Bomber
Se usa en:
- C:\Users\ignac\Documents\Java\threads
- DownloadTool00


Acaba finaliza en casilla.java cuando malo toca a prota
Buscar finito en Debug\kk.txt

- - -
NO SIRVE:
Clases:
online > ppal > presentacion > ppal
tablero > situacion > casilla > capa
        > ppal
teclado > ppal
tiempo > ppal
socket_ > ppal
        > online
serversocket_ > ppal
              > online
- - -

BomberMan*.png

NO SIRVE:
Linea X    Columna Y
1          1
2          2
3          3
4          4
5          5
6          6
7          7
8          8
9          9
10         10





