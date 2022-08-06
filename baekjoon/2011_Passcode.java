// 암호코드
// https://www.acmicpc.net/problem/2011

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String pw = br.readLine();
        int length = pw.length();

        // dp[i] : 주어진 암호의 i번째까지 탐색하였을때 나올 수 있는 해석의 가짓수
        long[] dp = new long[length+1];
        // 0자리는 해석할 암호가 없으나 i-2 == 0이 되는 경우를 처리하기 위해 1로 초기화 합니다.
        // 1자리는 한문자로만 해석이 가능하므로 1로 초기화합니다.
        dp[0] = 1;
        dp[1] = 1;
        // 주어진 암호의 첫 문자가 0이라면 해석할 수 없는 암호입니다. A는 1부터 사작하기때문에 0은 해석할 수 없습니다.
        if(pw.charAt(0) == '0') {
            System.out.println(0);
        }
        else {
            // 암호의 두번째 자리부터 나올 수 있는 해석의 가짓수를 구합니다.
            for(int i = 2 ; i <= length ; i++) {
                // 암호의 i번째 문자를 정수로 변환합니다.
                int n = pw.charAt(i-1) - '0';

                // 한 문자(A~I)로 변환 가능한 문자라면 i-1번째 까지 해석 가능한 가짓수를 더합니다.
                if(1 <= n && 1<= 9) {
                    dp[i] += dp[i-1];
                }

                // 암호의 i번째 문자와 i-1번째 문자를 정수로 변환합니다.
                n = (pw.charAt(i-2) - '0') * 10 + pw.charAt(i-1) - '0';
                // 한 문자(J~Z)로 변환 가능한 문자라면 i-2번째 까지 해석 가능한 가짓수를 더합니다.
                if(10<= n && n <= 26) {
                    dp[i] += dp[i-2];
                }

                // 문제에서 주어진 출력 조건으로 1000000으로 나눈 나머지를 저장합니다.
                dp[i] %= 1000000;
            }

            System.out.println(dp[length]);
        }
    }
}

/*
----------------실패--------------------------
25114 = dp[25114]
      = dp[2511] + dp[251]
      = (dp[251] + dp[25]) + dp[25]
      = dp[25] + dp[25]+ dp[25]
2511 4 = dp[2511]
    2 5 1 1 4
    25 11 4
    25 1 1 4
    2 5 11 4
251 14 = dp[251]
    2 5 1 14
    25 1 14
---------------------------------------------
dp[n] : n번째 숫자까지 탐색시 나오는 경우의 수
dp[0] = 1

25114
dp[1] = 1
dp[2] = 5, 25 둘 모두 문자로 변환 가능
      = dp[2-1] + dp[2-2]
      = 2

dp[3] = 1 문자 가능, 51문자 불가능
      = dp[3-1]
      = 2

dp[4] = 1, 11 둘 모두 문자로 변환 가능
      = dp[4-1] + dp[4-2]
      = 4

dp[5] = 4, 14 둘 모두 문자로 변환 가능
      = dp[5-1] + dp[5-2]
      = 6
 */