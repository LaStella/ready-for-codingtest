// 제곱수의 합
// https://www.acmicpc.net/problem/1699

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            // 제곱근이 정수라면 제곱수 1개로 나타낼 수 있는 자연수입니다.
            if(i % Math.sqrt(i) == 0) {
                dp[i] = 1;
            }
            // 제곱근이 정수가 아니라면 i보다 작은 정수의 제곱수 개수의 합을 비교하여 탐색합니다.
            else {
                // 제곱수의 최소 항 개수를 찾아야 하므로 1제곱의 개수로 초기화합니다. (ex. 4는 1^2을 4번 더한 결과이므로 4로 초기화합니다.)
                dp[i] = i;
                for(int j = 1 ; j * j < i ; j++) {
                    // i는 i보다 작거나 같은 정수의 제곱수로 이루어질 수 있습니다.
                    // i보다 작은 정수의 제곱을 j*j라 하면 i-j*j의 항의 개수에 +1(j*j가 1개의 항이므로)을 하면 i의 항의 개수가 나옵니다.
                    // j의 범위안에서 가장 작은 항의 개수를 찾아 저장합니다.
                    dp[i] = Math.min(dp[i], dp[i-j*j]+1);
                }
            }
        }

        System.out.println(dp[n]);

    }
}