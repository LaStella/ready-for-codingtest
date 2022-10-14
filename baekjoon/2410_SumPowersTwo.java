// 2의 멱수의 합
// https://www.acmicpc.net/problem/2410

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int[] dp = new int[N+1];
        int MOD = 1000000000;

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2 ; i <= N ; i++) {
            if(i % 2 != 0) {
                dp[i] = dp[i-1];
            }
            else {
                dp[i] = (dp[i-2] + dp[i/2]) % MOD;
            }
        }

        System.out.println(dp[N]);
    }
}

/*
0

1
1

2
1 1
2

3
1 1 1
2 1

4
1 1 1 1
2 1 1
2 2
4

5
1 1 1 1 1
2 1 1 1
2 2 1
4 1

6
1 1 1 1 1 1
2 1 1 1 1
2 2 1 1
4 1 1
2 2 2
4 2

7
1 1 1 1 1 1 1
2 1 1 1 1 1
2 2 1 1 1
4 1 1 1
2 2 2 1
4 2 1


수가 2 늘어날때마다 만드는 경우의 수가 증가합니다.
dp[i]를 i를 2의 멱수의 합으로 나타내는 경우의 수 라고 한다면
dp[i]는 dp[i-2]에서 1을 두 번 더하는 경우와 dp[i/2]에서 2를 곱하는 경우의 합입니다.
dp[i] = dp[i-2] + dp[i/2]
 */