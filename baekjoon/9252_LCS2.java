// LCS2
// https://www.acmicpc.net/problem/9252

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] dp;
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();

        n = s1.length;
        m = s2.length;

        char[] c1 = new char[n+1];
        char[] c2 = new char[m+1];
        
        // s1과 s2는 0번째 문자가 첫 문자로 저장되어있어 특정 i번째 문자를 구분하는데 혼동이 올 수 있습니다.
        // 이를 해결하기 위해 편의상 1번째 문자를 첫 문자로 만들기 위해 배열을 복사합니다.
        // ex) s1 = {A, C, A, Y, K, P}, c1 = {0, A, C, A, Y, K, P}
        System.arraycopy(s1, 0, c1, 1, n);
        System.arraycopy(s2, 0, c2, 1, m);

        // dp[i][j] : s1문자열의 i번째, s2문자열의 j번째 범위에서 최장 공통 부분 수열의 길이
        dp = new int[n+1][m+1];

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

        System.out.println(dp[n][m]);
        System.out.println(getLCS(c1));
    }

    // dp배열에서 LCS 값이 증가하는 문자를 찾아 더하여 하나의 문자열을 만드는 함수입니다. 
    public static String getLCS(char[] c1) {
        StringBuilder sb = new StringBuilder();
        
        // dp배열의 끝에서부터 시작합니다.
        // dp배열에서 LCS값이 증가하는 구간이 곧 LCS에 해당하는 문자가 있는 위치입니다.
        while(n > 0 && m > 0) {
            // 첫번째 문자열을 하나 줄인 범위에서 LCS의 값이 같다면 첫번째 문자열의 탐색값을 줄입니다.
            if(dp[n][m] == dp[n-1][m]) {
                n--;
            }
            // 두번째 문자열을 하나 줄인 범위에서 LCS의 값이 같다면 두번째 문자열의 탐색값을 줄입니다.
            else if(dp[n][m] == dp[n][m-1]) {
                m--;
            }
            // 위의 두 경우 모두 해당이 안된다면 첫번째 문자열의 n에 해당하는 위치의 문자가 LCS를 이루는 문자입니다.
            else {
                sb.append(c1[n]);
                n--;
                m--;
            }
        }

        // dp배열의 끝에서부터 역순으로 저장되므로 이를 뒤집어서 리턴합니다.
        return sb.reverse().toString();
    }
}