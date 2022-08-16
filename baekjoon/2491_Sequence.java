// 수열
// https://www.acmicpc.net/problem/2491

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 주어진 수열을 저장합니다.
        int[] s = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < n ; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][0] : i번째 수열에서 커지는 수열의 길이
        // dp[i][1] : i번째 수열에서 작아지는 수열의 길이
        int[][] dp = new int[n][2];

        // 최소 1개의 수를 가지므로 길이를 1로 초기화합니다.
        for (int i = 0 ; i < n ; i++) {
            Arrays.fill(dp[i], 1);
        }

        // 수열의 길이는 최소 1이므로 최대 길이를 1로 초기화합니다.
        int max = 1;

        for (int i = 1 ; i < n ; i++) {
            // 커지는 수열이라면 i-1번까지 커지는 수열의 길이에 1을 더한 길이가 i번 수열에서 커지는 수열의 길이입니다.
            if(s[i] >= s[i-1]) {
                dp[i][0] = dp[i-1][0]+1;
                max = Math.max(max, dp[i][0]);
            }

            // 작아지는 수열 또한 위와 마찬가지로 계산합니다.
            if(s[i] <= s[i-1]) {
                dp[i][1] = dp[i-1][1]+1;
                max = Math.max(max, dp[i][1]);
            }
        }

        System.out.println(max);
    }
}