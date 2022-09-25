// 타일 채우기 3
// https://www.acmicpc.net/problem/14852

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // dp[i][0] : 2x(i-1)크기의 벽을 채우고 i번열 위쪽 행을 채우는 경우의 수
        // dp[i][1] : 2x(i-1)크기의 벽을 채우고 i번열 아래쪽 행을 채우는 경우의 수
        // dp[i][2] : 2xi크기의 벽을 타일로 채우는 경우의 수
        long[][] dp = new long[N+1][3];
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 2;

        if (N >= 2) {
            dp[2][0] = 3;
            dp[2][1] = 3;
            dp[2][2] = 7;
        }

        for (int i = 3 ; i <= N ; i++) {
            dp[i][0] = (dp[i-1][1] + dp[i-1][2]) % MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]*2 + dp[i-2][2]) % MOD;
        }

        System.out.println(dp[N][2]);
    }
}

/*
2xN크기의 벽에 타일을 채우는 경우는 다음과 같습니다.
1. N=1인 경우 채우는 경우의 수는 2입니다.
2. N=2인 경우 채우는 경우의 수는 7입니다.
3. N-1크기의 벽을 채우는 경우의 수에서 2x1크기의 벽을 채우는 경우의 수는 2입니다. dp[N-1]*2
4. N-2크기의 벽을 채우는 경우의 수에서 2x2크기의 벽을 채우는 경우의 수는 3입니다. (7-4(3에서 중복되는 4가지를 제외))
5. N-3크기의 벽을 채우는 경우의 수에서 2x3크기의 벽을 채우는 경우의 수는 2입니다. (3, 4에서 중복되는 경우를 제외)
6. 이후 N-i크기의 벽(i >= 3)을 채우는 경우의 수는 2입니다.
----------------------------------------------------

dp[N][3]
0 : N열 윗행을 채우는 경우의 수
1 : N열 아랫행을 채우는 경우의 수
2 : N열을 채우는 경우의 수


 */