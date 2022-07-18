// 쉬운 계단 수
// https://www.acmicpc.net/problem/10844

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // dp[i][j] : j로 끝나며 길이가 i인 계단수
        int[][] dp = new int[n+1][10];
        for(int i = 1 ; i <= 9 ; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2 ; i <= n ; i++) {
            // 0으로 끝나는 계단수는 이전 계단수의 끝이 1인 경우에만 가능합니다.
            dp[i][0] = dp[i-1][1];
            for(int j = 1 ; j <= 8 ; j++) {
                // 1로 끝나며 길이가 j인 계단수는 0으로 끝나며 길이가 j-1인 계단수와 2로 끝나며 길이가 j-1인 계단수의 합입니다.
                // 위와 같은 과정이 8로 끝나느 계단수까지 동일합니다.
                dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
            }
            // 9로 끝나는 계단수는 이전 계단수의 끝이 8인 경우에만 가능합니다.
            dp[i][9] = dp[i-1][8];
        }

        long sum = 0;
        for(int i = 0 ; i <= 9 ; i++) {
            sum += dp[n][i];
        }
        System.out.println(sum % 1000000000);
    }
}

/*
0   1
1   0 2
2   1 3
3   2 4
8   7 9
9   8

10

21

12
32

23
43

34
54

45
65

56
76

67
87

78
98

89
*/
