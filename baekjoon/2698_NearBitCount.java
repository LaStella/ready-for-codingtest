// 인접한 비트의 개수
// https://www.acmicpc.net/problem/2698

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        // dp[i][j][k] : 길이가 i이며 인접한 비트의 수가 j, 수열의 마지막이 k인 경우의 수
        int[][][] dp = new int[101][101][2];

        dp[1][0][0] = 1;
        dp[1][0][1] = 1;

        for (int i = 2 ; i <= 100 ; i++) {
            for (int j = 0 ; j <= 100 ; j++) {
                if (j == 0) {
                    // 인접한 비트가 없는 경우 1로 끝나는 경우의 수는 i-1길이에 0으로 끝나는 경우의 수입니다.
                    // i-1길이에 1로 끝나는 경우 뒤에 1을 붙이게되면 인접한 비트가 늘어나므로 더할 수 없습니다.
                    dp[i][j][1] = dp[i-1][j][0];
                }
                else {
                    // 1. i-1길이에 j-1개의 인접한 비트를 가지며 1로 끝나는 경우 뒤에 1을 붙이면 i길이에 j개의 인접한 비트를 가지며 1로 끝나는 수열이 됩니다.
                    // 2. i-1길이에 j개의 인접한 비트를 가지며 0으로 끝나는 경우 뒤에 1을 붙이면 i길이에 인접한 비트는 늘지 않으며 1로 끝나는 수열이 됩니다.
                    dp[i][j][1] = dp[i-1][j-1][1] + dp[i-1][j][0];
                }
                // 0으로 끝나는 경우의 수는 i-1길이에서 구할 수 있는 모든 경우의 수가 해당됩니다.
                dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1];
            }
        }

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            System.out.println(dp[n][k][0]+dp[n][k][1]);
        }
    }
}

/*

010 + 0 > 0100 (0)
010 + 1 = 0101 (0)

011 + 0 > 0110 (1)
010 + 1 > 0111 (2)

수열 s에서 인접한 비트를 구하는 식을 이용하면 이전 비트의 마지막이 0이면 비트의 수가 늘지 않으며
이전 비트의 마지막이 1인 경우 1을 뒤에 붙일 경우 비트의 수가 늘어납니다.

이를 이용하여 dp를 만듭니다.
dp[i][j][k] : i길이 j개의 비트의 개수일 때 k로 끝나는 경우의 수

dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][1]
dp[i][j][1] = dp[i-1][j-1][1] + dp[i-1][j][0]

 */
