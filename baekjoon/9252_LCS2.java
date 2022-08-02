// LCS2
// https://www.acmicpc.net/problem/9252

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();


        int n = s1.length;
        int m = s2.length;

        char[] c1 = new char[n+1];
        System.arraycopy(s1, 0, c1, 1, n);
        char[] c2 = new char[m+1];
        System.arraycopy(s2, 0, c2, 1, m);

        // dp[i][j] : s1문자열의 i번째, s2문자열의 j번째 범위에서 최장 공통 부분 수열의 길이
        int[][] dp = new int[n+1][m+1];

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= m ; j++) {
                // 두 문자열(c1, c2)에서 i번째 문자와 j번째 문자가 같다면 이전 LCS에 +1할 수 있습니다.
                if(c1[i] == c2[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                // 두 문자열에서 i번째 문자와 j번째 문자가 다르다면 두 문자열에서 각각 범위를 1씩 줄인 LCS 중 큰 값을 가져옵니다.
                // ex) ACA, CAP 에서 dp[3][3]은 A와 P가 다릅니다. 이때 dp[3][2]는 ACA와 CA의 LCS인 2이며 dp[2][3]은 AC와 CAP의 LCS인 1입니다.
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }


    }
}
