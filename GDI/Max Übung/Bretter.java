import java.util.Scanner;

public class Bretter {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int eingabe;
        System.out.println("Bitte geben Sie eine Zahl für die Bretter ein: ");
        eingabe = in.nextInt();
        in.close();
        int zweier = eingabe / 2;

        ausgabeBretter(zweier, zweier, eingabe);
    }

    private static void ausgabeBretter(int zweierRek, int zweier, int eingabe) {
        if (zweierRek > 0) {
            for (int zweierBretter = 0; zweierBretter < zweierRek; zweierBretter++) {
                System.out.print(2 + " ");
            }
            if (eingabe % 2 > 0) {
                System.out.print(1 + " ");
            }
            for (int einerBretter = 0; einerBretter < (zweier - zweierRek); einerBretter++) {
                System.out.print(1 + " " + 1 + " ");
            }
            System.out.println();
            ausgabeBretter(zweierRek - 1, zweier, eingabe);
        }
    }

}