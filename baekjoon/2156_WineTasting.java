// 와인 시식
// https://www.acmicpc.net/problem/2156

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 포도주 각 잔의 양을 배열에 저장합니다.
        int[] wine = new int[n+2];
        for(int i = 1 ; i < n+1 ; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        // dp[i][0] : i번째 포도주에서 가장 많이 마신 양
        int[] dp = new int[n+2];
        dp[1] = wine[1];
        dp[2] = wine[1]+wine[2];
        for(int i = 3 ; i < n+1 ; i++) {
            // i번째 와인을 마시지 않고 dp[i-1]의 최대 양과 i번째 와인을 마시는 두 경우(i-3에서 i-1을 마시고 i를 마시는 경우와 i-2에서 i를 마시는 경우)를 비교한 최대값을 저장합니다.
            // 연속에서 3잔을 마실 수 없으므로 이전 포도주를 마시지 않아야 합니다.
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i]));
        }

        System.out.println(dp[n]);
    }
}