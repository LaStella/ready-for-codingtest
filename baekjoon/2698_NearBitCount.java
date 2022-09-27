// 인접한 비트의 개수
// https://www.acmicpc.net/problem/2698

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int MOD = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

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
