
import java.io.*;
import java.util.*;

public class DataGenerator_C {

    private static final int MAX_N = 300;
    private static final int MIN_N = 1;
    private static final int MAX_INPUT_DATA = 1000;
    private static final int MIN_INPUT_DATA = -1000;

    private static final int START = 1;
    private static final int END = 50;

    private static Random random = new Random();

    public static void main(String[] args) throws Exception {
        for (int i = START; i <= END; i++)
            generate(i);
    }

    public static void generate(int nth) throws Exception {
        StringBuilder sb = new StringBuilder();

        int n = random.nextInt(MAX_N) + MIN_N;
        sb.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n - 1)
                    sb.append(random.nextInt(2 * MAX_INPUT_DATA) + MIN_INPUT_DATA + 1);
                else
                    sb.append((random.nextInt(2 * MAX_INPUT_DATA) + MIN_INPUT_DATA + 1) + " ");
            }
            sb.append("\n");
        }

        generateInput(nth, sb.toString());
        generateOutput(nth, sb.toString());
    }

    private static void generateInput(int nth, String input) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nth + ".in"));
        bw.write(input);
        bw.close();
    }

    private static void generateOutput(int nth, String input) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nth + ".out"));
        bw.write(solve(input));
        bw.close();
    }

    private static String solve(String input) {

        Scanner scanner = new Scanner(input);
        int n = scanner.nextInt();

        int max = Integer.MIN_VALUE;
        int[][] dataSet = new int[n][n];

        for (int row = 0; row < n; row++) {
            int data = 0;
            for (int col = 0; col < n; col++) {
                data += scanner.nextInt();
                dataSet[row][col] = data;
            }
        }

        int[][] sumOfData = new int[n + 1][n + 1];
        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < n + 1; col++) {
                sumOfData[row][col] = sumOfData[row - 1][col] + dataSet[row - 1][col - 1];
            }
        }

        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < n + 1; col++) {
                for (int rate = 1; (row - rate >= 0) && (col - rate >= 0); rate++) {
                    int value = sumOfData[row][col] - sumOfData[row - rate][col] - sumOfData[row][col - rate] + sumOfData[row - rate][col - rate];
                    if (value > max) max = value;
                }
            }
        }
        return Integer.toString(max);
    }
}
