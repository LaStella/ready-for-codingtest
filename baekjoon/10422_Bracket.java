// 괄호
// https://www.acmicpc.net/problem/10422

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] L = new int[T];
        int max_L = 0;
        for (int i = 0 ; i < T ; i++) {
            L[i] = Integer.parseInt(br.readLine());
            max_L = Math.max(max_L, L[i]);
        }

        // dp[i] : 길이가 i인 올바른 괄호의 개수
        // int형 배열은 범위를 초과할 수 있으므로 long형으로 생성합니다.
        long[] dp = new long[max_L+1];
        dp[0] = 1;
        dp[2] = 1;

        for (int i = 4 ; i <= max_L ; i+=2) {
            for (int j = 0 ; j <= i-2 ; j+=2) {
                // 길이가 n인 올바른 괄호(dp[n])는 길의 합이 n-2인 두 올바른 괄호(dp[x], dp[y], x+y = n-2)의 조합 개수입니다.
                // ( dp[x] ) + dp[y] 는 길이가 n인 올바른 괄호입니다.
                // ex. n=6일때 만들 수 있는 방법은 다음과 같습니다.
                // 1. ( dp[0] ) + dp[4]
                // 2. ( dp[2] ) + dp[2]
                // 3. ( dp[4] ) + dp[0]
                dp[i] += dp[j] * dp[i-2-j];
                dp[i] %=  1000000007L;
            }
        }

        for (int i = 0 ; i < T ; i++) {
            System.out.println(dp[L[i]]);
        }

    }
}

/*
괄호의 길이가 홀수인 경우는 올바른 괄호가 될 수 없습니다.
dp[n]을 길이가 n인 올바른 괄호 문자열의 개수라고 합니다.
dp[0] = 1
dp[2] = 1
dp[4] = 2
올바른 괄호과 올바른 괄호를 붙이면 올바른 괄호가 됩니다.
따라서 dp[i] + dp[j] 는 올바른 괄호열이 됩니다.
괄호 안에 괄호가 존재할 수 있습니다.
따라서 ( dp[i] ) + dp[j] 또한 올바른 괄호열입니다.
위 식에 따라 길이는 i+j+2가 됩니다.
길이가 n인 올바른 괄호의 개수는 두 올바른 괄호의 길이 합이 n-2이며

dp[4] = ( dp[0] ) + dp[2]
      = ( dp[2] ) + dp[0]

dp[6] = ( dp[0] ) + dp[4]
      = ( dp[2] ) + dp[2]
      = ( dp[4] ) + dp[0]

()

()()
(())


()()()
(())()
(()())
((()))
()(())

dp[8] = ( dp[0] ) + dp[6]
      = 2 + 4
      = 4 + 2
      = 6 + 0
 */