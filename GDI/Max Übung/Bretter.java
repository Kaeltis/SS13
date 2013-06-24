import java.util.*;

public class Bretter {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int eingabe;
        System.out.println("Bitte geben Sie eine Zahl für die Bretter ein: ");
        eingabe = in.nextInt();
        int zweier = eingabe / 2;

        ausgabeBretter(zweier, zweier);
    }
    private static void ausgabeBretter(int eingabeRek, int eingabe) {
        if (eingabeRek > 0) {
            for (int zweierBretter = 0; zweierBretter < eingabeRek; zweierBretter++) {
                System.out.print(2 + " ");
            }
            for (int einerBretter = 0; einerBretter < eingabe - eingabeRek; einerBretter++) {
                System.out.print(1 + " " + 1 + " ");
            }
            System.out.println();
            ausgabeBretter(eingabeRek - 1, eingabe);
        }
    }

}