/**
 * @author Max Jando
 * Programm um Rekusriv ein Muster auszugeben
 */
public class Muster {

    public static void main(String[] args) {
        musterRek(0);
    }

    private static void musterRek(int eingabe) {
        if (eingabe < 10) {
            if (eingabe % 3 == 0) {
                System.out.print("*");
            } else {
                System.out.print("-");
            }
            musterRek(++eingabe);
        }
    }

}
