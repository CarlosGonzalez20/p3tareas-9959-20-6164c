/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Totito;

import java.util.Scanner;

/**
 *
 * @author visitante
 */
public class Totito {

    /**
     * @param args the command line arguments
     */
    static Scanner teclado = new Scanner(System.in);
 
    public static void main(String[] args) {
        jugar();
    }
 
    /**
     * Metodo donde empezaremos a jugar
     */
    public static void jugar() {
 
        //Reprentaciones de los jugadores y del simbolo vacio
        char J1 = 'X';
        char J2 = 'O';
        char vacio = '-';
 
        //turno actual
        //true = J1, false = J2
        boolean turno = true;
 
        //tablero donde vamos a jugar
        char tablero[][] = new char[3][3];
 
        //rellenamos la matriz con los guiones
        rellenarMatriz(tablero, vacio);
 
        int fila, columna;
        boolean posValida, correcto;
 
        //No salimos hasta que uno gane o no haya mas posibilidades
        while (!finPartida(tablero, vacio)) {
 
            do {
 
                //mostramos el jugador al que le toca
                mostrarTurnoActual(turno);
 
                //muestro el tablero
                mostrarMatriz(tablero);
 
                correcto = false;
                fila = pedirInteger("Dame la fila");
                columna = pedirInteger("Dame la columna");
 
                //Validamos la posicion
                posValida = validarPosicion(tablero, fila, columna);
 
                //Si es valido, comprobamos que no haya ninguna marca
                if (posValida) {
 
                    //Si no hay marca, significa que es correcto
                    if (!hayValorPosicion(tablero, fila, columna, vacio)) {
                        correcto = true;
                    } else {
                        System.out.println("Ya hay una marca en esa posicion");
                    }
                } else {
                    System.out.println("La posicion no es valida");
                }
 
                //Mientras no sea correcto, no salgo
            } while (!correcto);
 
            //depende del turno, inserta un simbolo u otro
            if (turno) {
                insertarEn(tablero, fila, columna, J1);
            } else {
                insertarEn(tablero, fila, columna, J2);
            }
 
            //cambio de turno
            turno = !turno;
        }
 
        //Muestra el tablero
        mostrarMatriz(tablero);
 
        //Mostramos el ganador
        mostrarGanador(tablero, J1, J2, vacio);
 
    }
 
    /**
     *
     */
    public static void mostrarGanador(char[][] matriz, char J1, char J2, char simDef) {
 
        char simbolo = coincidenciaLinea(matriz, simDef);
 
        if (simbolo != simDef) {
 
            ganador(simbolo, J1, J2, 1);
 
            return;
 
        }
 
        simbolo = coincidenciaColumna(matriz, simDef);
 
        if (simbolo != simDef) {
 
            ganador(simbolo, J1, J2, 2);
 
            return;
 
        }
 
        simbolo = coincidenciaDiagonal(matriz, simDef);
 
        if (simbolo != simDef) {
 
            ganador(simbolo, J1, J2, 3);
 
            return;
 
        }
 
        System.out.println("Hay empate");
 
    }
 
    /**
     */
    public static void ganador(char simbolo, char J1, char J2, int tipo) {
 
        switch (tipo) {
            case 1:
                if (simbolo == J1) {
                    System.out.println("Ha ganado el Jugador 1 por linea");
                } else {
                    System.out.println("Ha ganado el Jugador 2 por linea");
                }
 
                break;
            case 2:
                if (simbolo == J1) {
                    System.out.println("Ha ganado el Jugador 1 por columna");
                } else {
                    System.out.println("Ha ganado el Jugador 2 por columna");
                }
                break;
            case 3:
                if (simbolo == J1) {
                    System.out.println("Ha ganado el Jugador 1 por diagonal");
                } else {
                    System.out.println("Ha ganado el Jugador 2 por diagonal");
                }
                break;
        }
 
    }
 
    /**
     * Insertamos en una posicion de una matriz un simbolo en concreto
          */
    public static void insertarEn(char[][] matriz, int fila, int columna, char simbolo) {
        matriz[fila][columna] = simbolo;
    }
 
    /**
     * Mostramos el turno actual
          */
    public static void mostrarTurnoActual(boolean turno) {
 
        if (turno) {
            System.out.println("Le toca al jugador 1");
        } else {
            System.out.println("Le toca al jugador 2");
        }
 
    }
 
    /**
     * Pedimos un numero y lo devolvemos
          */
    public static int pedirInteger(String mensaje) {
 
        System.out.println(mensaje);
        int numero = teclado.nextInt();
 
        return numero;
 
    }
 
    /**
     * Rellena la matriz con un simbolo
          */
    public static void rellenarMatriz(char[][] matriz, char simbolo) 
    {
         for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) { matriz[i][j] = simbolo; } 
         } 
    } /** * Validamos la posicion que nos insertan * * @param tablero * @param fila * @param columna * @return */ 
    public static boolean validarPosicion(char[][] tablero, int fila, int columna) 
    { 
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero.length) {
            return true;
        }
        return false;
    }
 
    /**
     * Indicamos si en una posicion hay una marca
     */
    public static boolean hayValorPosicion(char[][] matriz, int fila, int columna, char simboloDef) {
        if (matriz[fila][columna] != simboloDef) {
            return true;
        }
         return false;
    }
 
    /**
     * Muestra la matriz
     *
          */
    public static void mostrarMatriz(char[][] matriz) {
 
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
 
    }
 
    /**
     * Indica si la matriz esta llena cuando el simbolo por defecto aparezca, no
     * esta llena
      */
    public static boolean matrizLlena(char[][] matriz, char simboloDef) { //funcion revisada por Carlos Gonzalez, 9959-20-6164
        //con el for anidado revisa si la matriz está llena
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] == simboloDef) {
                    return false;
                }
            }
        }
 
        return true;
 
    }
 
    /**
     * La partida acaba cuando la matriz esta llena o hay un ganador
      */
    public static boolean finPartida(char[][] matriz, char simboloDef) {//funcion revisada por Carlos Gonzalez, 9959-20-6164
        //el if revisa si la matriz está llena, o si de alguna de las tres formas hay un ganador
        if (matrizLlena(matriz, simboloDef) || coincidenciaLinea(matriz, simboloDef) != simboloDef
                || coincidenciaColumna(matriz, simboloDef) != simboloDef
                || coincidenciaDiagonal(matriz, simboloDef) != simboloDef)
        {
            return true;
        }
 
        return false;
    }
 
    /**
     * Indica si hay un ganador en una linea
     *
      */
    public static char coincidenciaLinea(char[][] matriz, char simboloDef) {//funcion revisada por Carlos Gonzalez, 9959-20-6164
        
        char simbolo;
        boolean coincidencia;
 
        for (int i = 0; i < matriz.length; i++) {
 
            //Reiniciamos la coincidencia
            coincidencia = true;
            //Cogemos el simbolo de la fila
            //se le da el caracter de la posición definida en la matriz a la variable simbolo
            simbolo = matriz[i][0];
            //el if revisa si el simbolo de la matriz es diferente al simbolo que se le da a uno de los jugadores
            if (simbolo != simboloDef) {
                //el ciclo for revisa cada fila de la matriz
                for (int j = 1; j < matriz[0].length; j++) {
                    //sino coincide ya no habra ganador en esta fila
                    if (simbolo != matriz[i][j]) {
                        coincidencia = false;
                    }
                }
 
                //Si no se mete en el if, devuelvo el simbolo ganador
                if (coincidencia) {
                    return simbolo;
                }
 
            }
 
        }
 
        //Si no hay ganador, devuelvo el simbolo por defecto
        return simboloDef;
 
    }
 
    public static char coincidenciaColumna(char[][] matriz, char simboloDef) {//funcion revisada por Carlos Gonzalez, 9959-20-6164
 
        char simbolo;
        boolean coincidencia;
 
        for (int j = 0; j < matriz.length; j++) {
 
            //Reiniciamos la coincidencia
            coincidencia = true;
            //Cogemos el simbolo de la columna
            simbolo = matriz[0][j];
            if (simbolo != simboloDef) {
                // el ciclo for itera en cada fila
                for (int i = 1; i < matriz[0].length; i++) {
                    //sino coincide ya no habra ganadro en esta fila
                    //el if revisa si el simbolo de cada fila No coincide con el simbolo del jugador en cada columna, para asi encontrar la columna en la que gana
                    if (simbolo != matriz[i][j]) {
                        coincidencia = false;
                    }
                }
 
                //Si no se mete en el if, devuelvo el simbolo ganador
                if (coincidencia) {
                    return simbolo;
                }
 
            }
 
        }
 
        //Si no hay ganador, devuelvo el simbolo por defecto
        return simboloDef;
 
    }
 
    public static char coincidenciaDiagonal(char[][] matriz, char simboloDef) {//funcion revisada por Carlos Gonzalez, 9959-20-6164
 
        char simbolo;
        boolean coincidencia = true;
 
        //Diagonal principal
        simbolo = matriz[0][0];
        if (simbolo != simboloDef) {
            // el ciclo for genera las coordenas a revisar
            for (int i = 1; i < matriz.length; i++) {
                //sino coincide ya no habra ganadro en esta fila
                //el if revisa si el simbolo de un jugador No coincide en una diagonal, de esta forma (0,0),(1,1),(2,2)
                if (simbolo != matriz[i][i]) {
                    coincidencia = false;
                }
            }
 
            //Si no se mete en el if, devuelvo el simbolo ganador
            if (coincidencia) {
                return simbolo;
            }
 
        }
 
        //Diagonal inversa
        simbolo = matriz[0][2];
        if (simbolo != simboloDef) {
            //el ciclo for genera coordenas a revisar, con las filas de forma ascendente y las columnas de forma descendente
            for (int i = 1, j = 1; i < matriz.length; i++, j--) {
                //sino coincide ya no habra ganadro en esta fila
                if (simbolo != matriz[i][j]) {
                    coincidencia = false;
                }
            }
 
            //Si no se mete en el if, devuelvo el simbolo ganador
            if (coincidencia) {
                return simbolo;
            }
        }
 
        //Si no hay ganador, devuelvo el simbolo por defecto
        return simboloDef;
 
    }
}
