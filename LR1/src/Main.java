import java.util.Scanner;

/**
 * The LucasNumber class represents a Lucas number with its index and value.
 * It provides methods to retrieve the index and value and to check if the number
 * can be expressed in the form of w^2 + 1.
 */
class LucasNumber {
    private int index;
    private int value;

    /**
     * @param index the index of the Lucas number in the sequence
     * @param value the value of the Lucas number
     */
    public LucasNumber(int index, int value) {
        this.index = index;
        this.value = value;
    }

    /**
     * @return the index of the Lucas number
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return the value of the Lucas number
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks if the Lucas number can be expressed in the form of w^2 + 1.
     *
     * @return true if the number can be expressed as w^2 + 1, false otherwise
     */
    public boolean checkFormula() {
        int w = (int) Math.sqrt(value - 1);
        return w * w + 1 == value;
    }
}

public class Main {

    /**
     * Prompts the user to input the number of Lucas numbers to generate.
     *
     * @param args command line arguments
     * @return the number of Lucas numbers to generate (N)
     */
    public static int getInput(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = -1;
        while (N <= 0) {
            System.out.print("Введіть кількість чисел Люка (N): ");
            N = scanner.nextInt();
            if (N <= 0) {
                System.out.println("N повинно бути більше за 0!\n");
            }
        }
        scanner.close();
        System.out.println();
        return N;
    }

    /**
     * Generates the first N Lucas numbers and returns them in an array.
     *
     * @param N the number of Lucas numbers to generate
     * @return an array of LucasNumber objects representing the first N Lucas numbers
     */
    public static LucasNumber[] generateLucasNumbers(int N) {
        LucasNumber[] lucasNumbers = new LucasNumber[N];
        int previous = 2;
        int current = 1;

        lucasNumbers[0] = new LucasNumber(0, previous);
        lucasNumbers[1] = new LucasNumber(1, current);

        for (int i = 2; i < N; i++) {
            int next = previous + current;
            previous = current;
            current = next;
            lucasNumbers[i] = new LucasNumber(i, current);
        }

        return lucasNumbers;
    }

    /**
     * Checks if each Lucas number in the array can be expressed in the form of w^2 + 1,
     * and prints the result to the console.
     *
     * @param lucasNumbers an array of LucasNumber objects to be checked
     */
    public static void checkNumbers(LucasNumber[] lucasNumbers) {
        for (int i = 0; i < lucasNumbers.length; i++) {
            System.out.println("Число Люка: " + lucasNumbers[i].getValue());
            if (lucasNumbers[i].checkFormula()) {
                System.out.println("Число " + lucasNumbers[i].getValue() + " можна задати у формі w^2 + 1.\n");
            } else {
                System.out.println("Число " + lucasNumbers[i].getValue() + " НЕ можна задати у формі w^2 + 1.\n");
            }
        }
    }

    public static void main(String[] args) {
        int N = getInput(args);
        LucasNumber[] lucasNumbers = generateLucasNumbers(N);
        checkNumbers(lucasNumbers);
    }
}