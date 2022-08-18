// 연속합 2
// https://www.acmicpc.net/problem/1912

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 주어진 수열을 저장합니다.
        int[] s = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < n ; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][0] : 0부터 i까지 범위에서 수를 제거하지 않고 가장 큰 연속된 수의 합
        // dp[i][1] : 0부터 i까지 수를 제거하고 가장 큰 연속된 수의 합
        int[][] dp = new int[n][2];
        dp[0][0] = s[0];
        dp[0][1] = s[0];

        int max = s[0];
        for(int i = 1 ; i < n ; i++) {
            // i번 수와 이전까지 가장 큰 연속된 수의 합에 i번 수를 더한 값을 비교하여 저장합니다.
            dp[i][0] = Math.max(s[i], dp[i-1][0]+s[i]);
            // i번 수를 제거한 수열의 합(dp[i-1][0])과 i번 수 이전에 다른 수를 제거한 합(dp[i-1][1])과 i번 수를 합한 값을 비교하여 저장합니다.
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]+s[i]);
            
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(max);
    }
}