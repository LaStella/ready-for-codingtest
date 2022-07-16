// 연속합
// https://www.acmicpc.net/problem/1912

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 수열을 arr배열에 저장합니다.
        int[] arr = new int[n+1];
        int index = 1;
        for(String s : br.readLine().split(" ")) {
            arr[index++] = Integer.parseInt(s);
        }

        // dp[i] : i번째 수에서 가장 큰 연속된 수의 합
        int[] dp = new int[n+1];
        for(int i = 1 ; i <= n ; i++) {
            // i-1번째 수에서의 연속된 합(dp[i-1])과 비교하여 더 큰 값을 저장합니다.
            dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
        }

        // 가장 큰 연속된 수열의 합을 탐색합니다.
        int max_sum = dp[1];
        for(int i = 2 ; i <= n ; i++) {
            max_sum = Math.max(max_sum, dp[i]);
        }
        System.out.println(max_sum);
    }
}