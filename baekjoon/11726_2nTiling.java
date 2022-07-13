// 2xn 타일링
// https://www.acmicpc.net/problem/11726

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // n이 1일 경우 dp[2]는 범위를 벗어나므로 n+2만큼 배열의 크기를 생성합니다.
        // dp[n] : 2*n 크기의 직사각형에 타일을 채우는 방법의 수
        int[] dp = new int[n+2];
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3 ; i <= n ; i++) {
            // i 크기의 직사각형에 타일을 채우는 방법의 수는 i-1크기의 타일을 채우는 방법의 수와 i-2크기의 타일을 채우는 방법의 수의 합입니다.
            // i-2크기의 직사각형에서 2만큼의 공간에 넣을수 있는 타일의 경우의 수는 2가지, 하지만 i-1에서 놓을 수 있는 경우의 수와 중복됩니다.
            // (dp[i-2]*(2-1)) + dp[i-1]*1 = dp[i-2]+dp[i-1]
            dp[i] = (dp[i-1] + dp[i-2]) % 10007;
        }

        System.out.println(dp[n]);

    }
}