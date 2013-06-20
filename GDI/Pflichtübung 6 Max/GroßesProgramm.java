/**
 * Max Jando - 1312825 / Patrick Fruh - 1314827
 * Programm zum Simulieren eines größeren Programms.
 * Moegliche Operationen das Ausgeben von Array.
 */

import java.util.Scanner;

public class GroßesProgramm {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String eingabe;

        System.out.println("Bitte geben Sie ein Wort ein: ");
        eingabe = in.nextLine();
        textAnalyse analyse = new textAnalyse(eingabe);

        int[] amount = analyse.getAnalyseAmount();
        printArray(amount);
        float[] percentage = analyse.getAnaylsePercents();
        printArray(percentage);
        System.out.println(analyse.getCaesarChiffre(26));
        System.out.println(analyse.getUpperLower(eingabe,true));
        System.out.println(textAnalyse.getUpperLower(eingabe,false));
    }


    /**
     * Methode, welche ein int[] Array ausgibt
     */
    public static void printArray(int[] array){
        for (int ganzeZahlen = 0; ganzeZahlen < array.length; ganzeZahlen++) {
            System.out.print(array[ganzeZahlen]+" ");
        }
        System.out.println();
    }

    /**
     * Methode, welche ein float[] Array ausgibt
     */
    public static void printArray(float[] array){
        for (int kommaZahlen = 0; kommaZahlen < array.length; kommaZahlen++) {
            System.out.print(array[kommaZahlen]+" ");
        }
        System.out.println();
    }


}
