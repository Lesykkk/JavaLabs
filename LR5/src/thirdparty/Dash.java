package thirdparty;

public class Dash {
    private Dash() {}

    public static void print() {
        for (int i = 0; i < 33; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
