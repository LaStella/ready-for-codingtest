// 1로 만들기
// https://www.acmicpc.net/problem/1463

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        for(int i = 2 ; i <= n ; i++) {
            // i-1의 최소 연산 횟수에서 +1을 해준 횟수로 시작합니다.
            // 이는 곧 -1 연산을 의미합니다.
            dp[i] = dp[i-1]+1;
            // i가 3으로 나누어 떨어진다면 dp[i/3]에 +1(3으로 나누는 연산을 했으므로)한 값과
            // 기존의 값을 비교하여 더 작은 값을 저장합니다.
            if(i % 3 == 0) {
                dp[i] = Math.min(dp[i/3]+1, dp[i]);
            }
            // 위와 마찬가지로 2로 나누는 계산 횟수와 비교하여 더 작은 값을 저장합니다.
            if(i % 2 == 0) {
                dp[i] = Math.min(dp[i/2]+1, dp[i]);
            }
        }

        System.out.println(dp[n]);
    }
}

/*

dp[n] : n의 최소 계산 횟수
dp[1] = 0 ; 1이므로 연산할 필요가없습니다.
 */