import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] numerators = new long[N]; // 분자
        long[] denominators = new long[N]; // 분모
        for (int i = 0; i < N; i++) {
            String[] tc = br.readLine().split(" ");
            long a = Long.parseLong(tc[0]);
            long b = Long.parseLong(tc[1]);
            long gcd = getGCD(a, b);
            numerators[i] = a / gcd;
            denominators[i] = b / gcd;
        }
        long resultNumerator = getLCM(denominators);
        long resultDenominator = getGCD(numerators);
        System.out.println(resultDenominator + " " + resultNumerator);
    }

    public static long getGCD(long a, long b) { // 최대공약수
        if (a % b == 0) {
            return b;
        } else {
            return getGCD(b, a % b);
        }
    }

    public static long getGCD(long[] arr) { // 최대공약수
        if (arr.length < 2) {
            return arr[0];
        }
        long result = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            result = getGCD(result, arr[i]);
        }
        return result;
    }

    public static long getLCM(long a, long b) { // 최소공배수
        return a * b / getGCD(a, b);
    }

    public static long getLCM(long[] arr) { // 최소공배수
        if (arr.length < 2) {
            return arr[0];
        }
        long result = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            result = getLCM(result, arr[i]);
        }
        return result;
    }
}