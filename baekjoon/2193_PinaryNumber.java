// 이친수
// https://www.acmicpc.net/problem/2156

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // dp[i][j] : j로 끝나며 길이가 i인 이친수
        // 이친수의 개수가 int형을 초과할 수 있으므로 long형으로 생성합니다.
        long[][] dp = new long[n+1][2];
        dp[1][1] = 1;
        for(int i = 2 ; i <= n ; i++) {
            // 길이가 i이며 0으로 끝나는 이친수는 i-1길이를 가진 이친수의 개수입니다.
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            // 길이가 i이며 0으로 끝나는 이친수는 1이 연속으로 나올 수 없기 때문에 i-1길이이며 0으로 끝나는 이친수의 개수입니다.
            dp[i][1] = dp[i-1][0];
        }

        System.out.println(dp[n][0] + dp[n][1]);
    }
}

/*
1
10

101
100

1010
1000
1001
*/
