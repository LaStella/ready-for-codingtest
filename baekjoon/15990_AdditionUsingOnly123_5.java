// 1, 2, 3 더하기 5
// https://www.acmicpc.net/problem/15990

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        int[] testcase = new int[t];

        int max = 0;
        for(int i = 0 ; i < t ; i++) {
            testcase[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, testcase[i]);
        }

        // dp[i][j] : 정수i를 1,2,3으로만 이루어진 합이며 마지막이 j의 합으로 나타내는 방법의 수를 1000000009로 나눈 나머지
        // i-1, i-2, i-3의 합을 구할 경우 int형 범위를 초과할 수 있으므로 long형으로 생성합니다.
        // 가장 큰 n을 나타내는 max가 작아 초기값을 저장하는데 범위를 초과할 수 있으므로 max+2를 합니다.
        long[][] dp = new long[max+2][4];
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        // 정수 i를 나타내는 방법의 수는 i에 1,2,3을 뺀 정수를 나타내는 방법의 수들의 합입니다.
        for(int i = 4 ; i <= max ; i++) {
            // 1을 마지막으로 더하여 i를 나타낼 경우 1을 연속으로 사용할 수 없으므로 2와 3을 끝으로 더하는 방법의 수를 더합니다.
            dp[i][1] = (dp[i-1][2] + + dp[i-1][3]) % 1000000009;
            // 위와 마찬가지로 2를 연속으로 사용할 수 없으므로 1과 3을 끝으로 더하는 방법의 수를 더합니다.
            dp[i][2] = (dp[i-2][1] + + dp[i-2][3]) % 1000000009;
            dp[i][3] = (dp[i-3][1] + + dp[i-3][2]) % 1000000009;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < t ; i++) {
            long result  = (dp[testcase[i]][0]+dp[testcase[i]][1]+dp[testcase[i]][2]) % 1000000009;
            sb.append(result+"\n");
        }

        System.out.println(sb);
    }
}