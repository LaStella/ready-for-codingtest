// 색상환
// https://www.acmicpc.net/problem/2482

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        // dp[i][j] : i번째 색상에서 j개의 색상을 고르는 경우의 수
        int[][] dp = new int[n+1][n+1];

        // 초기값을 저장합니다.
        for (int i = 1 ; i <= n ; i++) {
            // i개의 색상 중 0개를 고르는 경우는 1가지 입니다.
            dp[i][0] = 1;
            // i개의 색상 중 1개를 고르는 경우는 i가지 입니다.
            dp[i][1] = i;
        }

        for (int i = 3 ; i <= n ; i++) {
            // i번째 색상에서 최대로 뽑을 수 있는 색상의 수는 i/2개입니다.
            for (int j = 2 ; j <= i/2 ; j++) {
                // i번째 색상에서 j개의 색상을 고르는 경우는 두 가지입니다.
                // 1. i-1번째 색상에서 j개를 뽑은 경우 즉, i번째 색상은 뽑지 않습니다.
                // 2. i-2번째 색상에서 j-1개를 뽑고 i번째 색상을 j번째로 뽑는 경우
                dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % 1000000003;
            }
        }

        System.out.println(dp[n][k]);
    }
}

/*
n개의 색상환에서 인접한 두 색상을 고를 수 없으므로 i번째 색상에서 i-1번 색상은 고를 수 없습니다.
i번째 색상에서 j개의 색상을 고른 경우의 수를 dp[i][j]로 표현할때
dp[i][j] = dp[i-2][j-1] + dp[i-1][j]
i번째 색상에서 j개의 색상을 고르는 경우는 두 가지입니다.
1. i-1번째 색상에서 j개를 뽑은 경우 즉, i번째 색상은 뽑지 않습니다.
2. i-2번째 색상에서 j-1개를 뽑고 i번째 색상을 j번째로 뽑는 경우

첫 번째 색상을 i = 1로 시작한다면 다음과 같습니다.

dp[3][0] = 1
dp[3][1] = dp[1][0] + dp[2][1] = 2
dp[3][2] = dp[1][1] + dp[2][2] = 1 + 0 = 1

dp[4][1] = dp[2][0] + dp[3][1] = 3
dp[4][2] = dp[2][1] + dp[3][2] = 2

 */