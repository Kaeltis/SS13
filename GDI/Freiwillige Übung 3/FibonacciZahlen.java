/*
@author: Max Jando - 1312825 
UIB SS2013 - GDI - Prof. Dr. Sven Klaus
Freiwillige Uebung 3

Lösungsversuch: Ausgabe FibonacciZahlen
*/

import java.util.Scanner;

public class FibonacciZahlen {

    public static int counter = 0;

    public static void main(String[] args) {

        long eingabe;
        Scanner in = new Scanner(System.in);

        System.out.println("Programm zum Ausgeben der Fibonacci-Zahlen.");
        System.out.println("Bitte geben Sie eine ganze Zahl >= 0 ein.");

        eingabe = in.nextInt();
        in.close();
        System.out.println("Die Fibo-Zahl von " + eingabe + " ist " + fibonacciRek(eingabe));
        System.out.println("Anzahl Aufrufe: " + counter);
    }

    public static long fibonacciRek(long zahl) {
        counter++;
        if (zahl == 0 || zahl == 1)
            return zahl;

        return fibonacciRek(zahl - 1) + fibonacciRek(zahl - 2);
    }


}