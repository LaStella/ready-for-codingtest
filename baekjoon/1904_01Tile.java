// 01타일
// https://www.acmicpc.net/problem/1904

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // dp[i] : 길이가 i인 이진수를 만들 수 있는 가지수
        long[] dp = new long[n+2];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3 ; i <= n ; i++) {
            // i의 길이를 가진 이진수를 만드는 방법은 두 가지입니다.
            // 첫번째로 i-1의 길이를 가진 이진수에 1을 붙여 만드는 이진수
            // 두번째로 i-2의 길이를 가진 이진수에 00을 붙여 만드는 이진수
            dp[i] = (dp[i-1]+dp[i-2]) % 15746;
        }

        System.out.println(dp[n]);
    }
}

/*
1

00
11

100
001
111

0000
1100
1001
0011
1111

10000
00100
11100
00001
11001
10011
00111
11111
*/
