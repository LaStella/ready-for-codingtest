// 1, 2, 3 더하기 3
// https://www.acmicpc.net/problem/15988

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

        // dp[i] : 정수i를 1,2,3의 합으로 나타내는 방법의 수를 1000000009로 나눈 나머지
        // i-1, i-2, i-3의 합을 구할 경우 int형 범위를 초과할 수 있으므로 long형으로 생성합니다.
        // 가장 큰 n을 나타내는 max가 작아 초기값을 저장하는데 범위를 초과할 수 있으므로 max+2를 합니다.
        long[] dp = new long[max+2];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        // 정수 i를 나타내는 방법의 수는 i에 1,2,3을 뺀 정수를 나타내는 방법의 수들의 합입니다.
        for(int i = 4 ; i <= max ; i++) {
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < t ; i++) {
            sb.append(dp[testcase[i]]+"\n");
        }

        System.out.println(sb);
    }
}
