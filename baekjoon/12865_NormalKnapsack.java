// 평범한 배낭
// https://www.acmicpc.net/problem/12865

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        int[] w = new int[n+1];
        int[] v = new int[n+1];

        for(int i = 1 ; i <= n ; i++) {
            String[] object = br.readLine().split(" ");
            w[i] = Integer.parseInt(object[0]);
            v[i] = Integer.parseInt(object[1]);
        }

        // dp[i][j] : 최대 j만큼 넣을 수 있는 배낭에 i번째 물건까지 탐색했을때 가치의 최대값
        int[][] dp = new int[n+1][k+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= k ; j++) {
                // 물건을 넣을 만큼 가방의 무게가 충분하지 못한 경우 이전 탐색 가치의 최대값을 저장합니다.
                if(w[i] > j) {
                    dp[i][j] = dp[i-1][j];
                }
                // 물건을 넣을 만큼 가방의 무게가 충분하다면 가방의 무게에서 물건의 무게를 뺀 j-w[i]무게의 배낭에 i-1번째까지 탐색한 최대 가치값에 i번째 물건의 가치를 더한 가치값을 i번째 물건을 넣지 않는 최대 가치값과 비교합니다.
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i]);
                }
            }
        }

        System.out.println(dp[n][k]);

    }
}