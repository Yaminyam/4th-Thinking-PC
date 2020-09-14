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
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int col = 0; col < n; col++) {
                dataSet[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int maxIndex = n - 1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (row == maxIndex || col == maxIndex) {
                    if (max < dataSet[row][col]) max = dataSet[row][col];
                } else { //n회
                    int[] maxOfData = new int[n];
                    maxOfData[0] = dataSet[row][col];
                    if (max < maxOfData[0]) max = maxOfData[0];

                    for (int index = 1; index < n; index++) {
                        if (((row + index) > maxIndex) || ((col + index) > maxIndex))
                            break;
                        else {
                            int DataToAdd = 0;
                            DataToAdd += dataSet[row + index][col + index];
                            for (int pos = 0; pos < index; pos++)
                                DataToAdd += dataSet[row+pos][col+index];

                            for (int pos = 0; pos < index; pos++)
                                DataToAdd += dataSet[row+index][col+pos];

                            maxOfData[index] = maxOfData[index - 1] + DataToAdd;
                            if (max < maxOfData[index]) max = maxOfData[index];
                        }
                    }
                }
            }//col
        }//row
        System.out.print(max);
    }

}
