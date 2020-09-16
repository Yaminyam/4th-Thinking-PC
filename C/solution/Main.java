import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        StringTokenizer st;

        Integer n = Integer.parseInt(br.readLine());

        int max = Integer.MIN_VALUE;
        int[][] dataSet = new int[n][n];

        for (int row = 0; row < n; row++) {
            int data = 0;
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int col = 0; col < n; col++) {
                data += Integer.parseInt(st.nextToken());
                dataSet[row][col] = data;
            }
        }

        int[][] sumOfData = new int[n+1][n+1];
        for (int row = 1; row < n + 1; row++) {
            for (int col = 1; col < n + 1; col++) {
                sumOfData[row][col] = sumOfData[row - 1][col] + dataSet[row-1][col-1];
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
        System.out.print(max);
    }
}
