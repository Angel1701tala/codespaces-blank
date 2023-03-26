import java.util.*;

public class PancakeGame {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Ingrese la cantidad de panqueques a comodar: ");
            int n = sc.nextInt();
            int[] pancakes = new int[n];
            System.out.println("Ingrese el diámetro de los panqueques acomodar: ");
            for (int i = 0; i < n; i++) {
                pancakes[i] = sc.nextInt();
            }
            System.out.println("Orden inicial de los panqueques:");
            printPancakes(pancakes);
            sortPancakes(pancakes);
        }
    }

    public static void sortPancakes(int[] pancakes) {
        int n = pancakes.length;
        for (int i = n - 1; i >= 0; i--) {
            int maxIndex = getMaxIndex(pancakes, i);
            if (maxIndex != i) {
                if (maxIndex != 0) {
                    flip(pancakes, maxIndex);
                    System.out.println("Volteando los primeros " + (maxIndex + 1) + " panqueques");
                    printPancakes(pancakes);
                }
                flip(pancakes, i);
                System.out.println("Volteando los primeros " + (i + 1) + " panqueques");
                printPancakes(pancakes);
            }
        }
        System.out.println("Los panqueques están ordenados.");
        printPancakes(pancakes);
    }

    public static void flip(int[] pancakes, int k) {
        int i = 0;
        while (i < k) {
            int temp = pancakes[i];
            pancakes[i] = pancakes[k];
            pancakes[k] = temp;
            i++;
            k--;
        }
    }

    public static int getMaxIndex(int[] pancakes, int n) {
        int maxIndex = 0;
        for (int i = 0; i <= n; i++) {
            if (pancakes[i] > pancakes[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void printPancakes(int[] pancakes) {
        for (int pancake : pancakes) {
            System.out.print(pancake + " ");
        }
        System.out.println();
    }
}