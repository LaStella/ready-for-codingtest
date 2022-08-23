// 연속부분최대곱
// https://www.acmicpc.net/problem/2670

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        double[] d = new double[n+1];

        for (int i = 1 ; i <= n ; i++) {
            d[i] = Double.parseDouble(br.readLine());
        }

        // dp[i] : i번째 수에서 가장 큰 곱
        double[] dp = new double[n+1];

        // 이전(i-1)까지 부분 연속곱과 현재(i)를 비교하여 더 큰 값이 저장됩니다.
        for (int i = 1 ; i <= n ; i++) {
            dp[i] = Math.max(d[i], dp[i-1] * d[i]);
        }

        // 최대 곱을 찾습니다.
        double max = 0;

        for (int i = 1 ; i <= n ; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(String.format("%.3f", max));

    }
}